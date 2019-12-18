package de.rwth_aachen.swc.eaqtool;

import de.rwth_aachen.swc.eaqtool.meta.EAQTMeta;
import de.rwth_aachen.swc.eaqtool.metric.MSMeta;
import de.rwth_aachen.swc.eaqtool.metric.Metric;
import de.rwth_aachen.swc.eaqtool.metric.MetricSuite;
import de.rwth_aachen.swc.eaqtool.persist.*;
import de.rwth_aachen.swc.eaqtool.repo.EAModelRepository;
import de.rwth_aachen.swc.eaqtool.repo.XMLFileReader;
import de.rwth_aachen.swc.eaqtool.repo.XMLFileReaderFactory;
import de.rwth_aachen.swc.eaqtool.repo.XQEAModelRepository;
import de.rwth_aachen.swc.eaqtool.view.ANSIConsoleView;
import de.rwth_aachen.swc.eaqtool.view.ModelViewCoordinator;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.*;

public class EAQTool {

    private static EAModelRepository repo;

    public static void main(String[] args) throws ParseException {

        Options options = generateCliOptions();
        CommandLine cli = generateCommandLine(options, args);

        String fileName = null;
        String metricsuiteName = null;
        String eametaName = null;
        String qmetaName = null;
        String persistpathName = null;
        Boolean dryrun = false;
        MetricLog.VIEW_OUTPUT_GROUPING_CRITERION grpCat = EAQTConf.CLI_OPT_DESCR_GROUPING_CAT_DEFAULT;
        if (cli != null) {
            if (cli.hasOption(EAQTConf.CLI_OPT_SHORT_FILE)) {
                fileName = cli.getOptionValue(EAQTConf.CLI_OPT_SHORT_FILE);
            }
            if (cli.hasOption(EAQTConf.CLI_OPT_SHORT_METRICSUITE)) {
                metricsuiteName = cli.getOptionValue(EAQTConf.CLI_OPT_SHORT_METRICSUITE);
            }
            if (cli.hasOption(EAQTConf.CLI_OPT_SHORT_EAMETA)) {
                eametaName = cli.getOptionValue(EAQTConf.CLI_OPT_SHORT_EAMETA);
            }
            if (cli.hasOption(EAQTConf.CLI_OPT_SHORT_QMETA)) {
                qmetaName = cli.getOptionValue(EAQTConf.CLI_OPT_SHORT_QMETA);
            }
            if (cli.hasOption(EAQTConf.CLI_OPT_SHORT_PERSISTPATH)) {
                persistpathName = cli.getOptionValue(EAQTConf.CLI_OPT_SHORT_PERSISTPATH);
            }
            if (cli.hasOption(EAQTConf.CLI_OPT_SHORT_DRYRUN)) {
                dryrun = true;
            }
            if (cli.hasOption(EAQTConf.CLI_OPT_SHORT_GROUPING_CAT)) {
                String catStr = cli.getOptionValue(EAQTConf.CLI_OPT_SHORT_GROUPING_CAT);
                Boolean useCat = false;
                for (MetricLog.VIEW_OUTPUT_GROUPING_CRITERION c : MetricLog.VIEW_OUTPUT_GROUPING_CRITERION.values()) {
                    if (c.name().compareTo(catStr)==0) {
                        grpCat = c;
                    }
                }

            }

            EAQTMeta metaModels;
            if (eametaName!=null || qmetaName!=null) {
                metaModels = EAQTMeta.getInstance(eametaName, qmetaName);
            } else {
                metaModels = EAQTMeta.getInstance();
            }
            MSMeta ms;
            if (metricsuiteName!=null) {
                ms = MSMeta.getInstance(metricsuiteName);
            } else {
                ms = MSMeta.getInstance();
            }
            MetricSuite metricSuite = ms.getMetricSuite();
            XMLFileReaderFactory fileReaderFactory = new XMLFileReaderFactory(fileName);
            XMLFileReader reader = fileReaderFactory.getReader();
            if (metricSuite.getScriptingLanguage().compareTo(MetricSuite.SCRIPTING_DSL_TYPE_XQUERY)==0) {
                repo = XQEAModelRepository.getInstance(reader);
            }
            MetricLog metricLog = prepareMetricLogFromMetricSuite(metricSuite);

            // run metric suite
            List<List<String>> payload = metricSuite.run(repo);
            List<Datum> data = new ArrayList<>();
            Datum d = new Datum();
            d.setDataTs(EAQTHelper.date2ISO8601(new Date(System.currentTimeMillis()), EAQTHelper.ISO8601_TIME_FORMAT));
            d.setDataP(payload);
            data.add(d);
            metricLog.setData(data);

            Map<String, Object> params = new HashMap<>();
            String persistPath = persistpathName==null ? EAQTConf.ABS_DIR_PERSIST_METRIC_RESULTS : persistpathName;
            String targetFile = metricLog.buildPersistenceFilename(persistPath);
            params.put(JacksonPersistenceFactory.PARAM_FORMAT_KEY, JacksonPersistenceFactory.PARAM_FORMAT_VALUE_PRETTY);
            params.put(JacksonPersistenceFactory.PARAM_BASENAME_KEY, targetFile);
            JacksonPersistenceFactory fac = new JacksonPersistenceFactory(JacksonPersistenceFactory.FWTYPE_FILE, params);

            // deserialize legacy metric data
            JacksonDeserializer deserializer = (JacksonDeserializer) fac.getPojoDeserializer();
            MetricLog legacyMetricLog = deserializer.deserialize();

            String continuity_violation = null;
            String lastEntry = metricLog.getData().get(0).getDataTs();
            String firstEntry = lastEntry;
            if (legacyMetricLog!=null) {
                System.out.println("Checking continuity for legacy data from source: " + deserializer.getBaseName());
                continuity_violation = metricLog.checkContinuity(legacyMetricLog);
            }

            if (continuity_violation == null) {
                // append the current dataset to legacy data (if present)
                if (legacyMetricLog!=null) {
                    System.out.println("Appending legacy data...");
                    if (legacyMetricLog.getHeader()!=null) {
                        firstEntry = legacyMetricLog.getHeader().getStartTime();
                    }
                    List<Datum> legacyData = legacyMetricLog.getData();
                    Datum currentData = metricLog.getData().get(0);
                    legacyData.add(currentData);
                    metricLog.setData(legacyData);
                }
                metricLog.getHeader().setStartTime(firstEntry);
                metricLog.getHeader().setEndTime(lastEntry);
                if (!dryrun) {
                    // serialize (merged) metric data
                    JacksonSerializer serializer = (JacksonSerializer) fac.getPojoSerializer();
                    System.out.println("Persisting metric suite run results to: " + serializer.getBaseName());
                    serializer.serialize(metricLog);
                }
            } else {
                System.out.println("Please note that target: " + deserializer.getBaseName() +
                    "\nbased on data source: " + ((XMLFileReader)repo.getReader()).getBasePath() +
                    "\nwith data source ID: " + ((XMLFileReader)repo.getReader()).getSourceId() +
                    "\nhas not been overwritten because of a continuity violation (discrepancy between current and legacy data).\n" +
                    continuity_violation + "\n");
            }

            // display results (console)
            ANSIConsoleView view = new ANSIConsoleView();
            ModelViewCoordinator control = new ModelViewCoordinator(metricLog, view);
            control.displayView(grpCat);
        }

    }

    /**
     * CLI parsing, def. stage.
     * @return Definition of cli options.
     */
    private static Options generateCliOptions() {
        final Options options = new Options();
        final Option fileOption = Option.builder(EAQTConf.CLI_OPT_SHORT_FILE)
                .required(EAQTConf.CLI_OPT_REQUIRED_FILE)
                .longOpt(EAQTConf.CLI_OPT_LONG_FILE)
                .hasArg(EAQTConf.CLI_OPT_HASARG_FILE)
                .desc(EAQTConf.CLI_OPT_DESCR_FILE)
                .build();
        options.addOption(fileOption);
        final Option metricOption = Option.builder(EAQTConf.CLI_OPT_SHORT_METRICSUITE)
                .required(EAQTConf.CLI_OPT_REQUIRED_METRICSUITE)
                .longOpt(EAQTConf.CLI_OPT_LONG_METRICSUITE)
                .hasArg(EAQTConf.CLI_OPT_HASARG_METRICSUITE)
                .desc(EAQTConf.CLI_OPT_DESCR_METRICSUITE)
                .build();
        options.addOption(metricOption);
        final Option eametaOption = Option.builder(EAQTConf.CLI_OPT_SHORT_EAMETA)
                .required(EAQTConf.CLI_OPT_REQUIRED_EAMETA)
                .longOpt(EAQTConf.CLI_OPT_LONG_EAMETA)
                .hasArg(EAQTConf.CLI_OPT_HASARG_EAMETA)
                .desc(EAQTConf.CLI_OPT_DESCR_EAMETA)
                .build();
        options.addOption(eametaOption);
        final Option qmetaOption = Option.builder(EAQTConf.CLI_OPT_SHORT_QMETA)
                .required(EAQTConf.CLI_OPT_REQUIRED_QMETA)
                .longOpt(EAQTConf.CLI_OPT_LONG_QMETA)
                .hasArg(EAQTConf.CLI_OPT_HASARG_QMETA)
                .desc(EAQTConf.CLI_OPT_DESCR_QMETA)
                .build();
        options.addOption(qmetaOption);
        final Option persistpathOption = Option.builder(EAQTConf.CLI_OPT_SHORT_PERSISTPATH)
                .required(EAQTConf.CLI_OPT_REQUIRED_PERSISTPATH)
                .longOpt(EAQTConf.CLI_OPT_LONG_PERSISTPATH)
                .hasArg(EAQTConf.CLI_OPT_HASARG_PERSISTPATH)
                .desc(EAQTConf.CLI_OPT_DESCR_PERSISTPATH)
                .build();
        options.addOption(persistpathOption);
        final Option dryrunOption = Option.builder(EAQTConf.CLI_OPT_SHORT_DRYRUN)
                .required(EAQTConf.CLI_OPT_REQUIRED_DRYRUN)
                .longOpt(EAQTConf.CLI_OPT_LONG_DRYRUN)
                .hasArg(EAQTConf.CLI_OPT_HASARG_DRYRUN)
                .desc(EAQTConf.CLI_OPT_DESCR_DRYRUN)
                .build();
        options.addOption(dryrunOption);
        final Option groupcatOption = Option.builder(EAQTConf.CLI_OPT_SHORT_GROUPING_CAT)
                .required(EAQTConf.CLI_OPT_REQUIRED_GROUPING_CAT)
                .longOpt(EAQTConf.CLI_OPT_LONG_GROUPING_CAT)
                .hasArg(EAQTConf.CLI_OPT_HASARG_GROUPING_CAT)
                .desc(EAQTConf.CLI_OPT_DESCR_GROUPING_CAT)
                .build();
        options.addOption(groupcatOption);
        return options;
    }

    /**
     * CLI parsing
     *
     * @param options Options from def. stage.
     * @param args Command-line arguments provided to application.
     * @return Instance of CommandLine as parsed from the provided options and command line arguments.
     */
    private static CommandLine generateCommandLine(final Options options, final String[] args) {
        final CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdLineParser.parse(options, args);
        }
        catch (ParseException parseException) {
            printHelp(options);
        }
        return commandLine;
    }

    /**
     * Generate help CLI information.
     *
     * @param options Instance of Options to be used.
     * @return HelpFormatter instance that can be used to print help information.
     */
    private static void printHelp(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(EAQTConf.APPNAME, options);
    }

    /**
     * Prepare a log object for result serialization/persistence and display (view).
     */
    private static MetricLog prepareMetricLogFromMetricSuite(MetricSuite metricSuite) {
        MetricLog metricLog = new MetricLog();

        metricLog.setDocType(EAQTConf.JSONTS_DOCTYPE);
        metricLog.setVersion(EAQTConf.JSONTS_VERSION);
        metricLog.setDataSourceName( ((XMLFileReader)repo.getReader()).getBasePath() );
        metricLog.setDataSourceId(((XMLFileReader)repo.getReader()).getSourceId());
        Header header = new Header(
                metricSuite.getName(),
                metricSuite.getDescription(),
                metricSuite.getVersion(),
                metricSuite.getDate(),
                "", "",
                EAQTConf.APPNAME,
                EAQTConf.APPVERSION);
        metricLog.setHeader(header);
        Globals globals = new Globals(
                EAQTHelper.valueOf(metricSuite.getGlobalBLGreen()),
                EAQTHelper.valueOf(metricSuite.getGlobalBLRed()),
                EAQTHelper.valueOf(metricSuite.getGlobalBLMarginGreen()),
                EAQTHelper.valueOf(metricSuite.getGlobalBLMarginRed()),
                EAQTHelper.valueOf(metricSuite.getStandardScaleMin()),
                EAQTHelper.valueOf(metricSuite.getStandardScaleMax())
        );
        metricLog.setGlobals(globals);
        List<DataProvider> metrics = new ArrayList<>();
        for (Metric m : metricSuite.getMetric()) {
            metrics.add(new DataProvider(
                    m.getId(),
                    m.getName(),
                    m.getDescription(),
                    m.getVersion(),
                    m.getDate(),
                    m.getQRCId(),
                    EAQTHelper.valueOf(m.getNormalizer()) != "" ? EAQTHelper.valueOf(m.getNormalizer().getInvokedTypestr()) : "",
                    EAQTHelper.valueOf(m.getScale()) != "" ? EAQTHelper.valueOf(m.getScale().getScaleMin()) : "",
                    EAQTHelper.valueOf(m.getScale()) != "" ? EAQTHelper.valueOf(m.getScale().getScaleMax()) : "",
                    EAQTHelper.valueOf(m.getScale()) != "" ? EAQTHelper.valueOf(m.getScale().getSweetspot()) : "",
                    EAQTHelper.valueOf(m.getScale()) != "" ? EAQTHelper.valueOf(m.getScale().getBLGreen()) : "",
                    EAQTHelper.valueOf(m.getScale()) != "" ? EAQTHelper.valueOf(m.getScale().getBLRed()) : ""
            ));
        }
        metricLog.setDataProvider(metrics);

        return metricLog;
    }

}
