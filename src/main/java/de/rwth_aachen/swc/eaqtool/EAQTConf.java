package de.rwth_aachen.swc.eaqtool;

import de.rwth_aachen.swc.eaqtool.persist.MetricLog;

import java.io.File;

public class EAQTConf {

    public static final String APPNAME = "EAQTool";
    public static final String APPVERSION = "1.0";

    public static final String REL_DIR_EA_META = "meta" + File.separator + "ea_meta.json";
    public static final String REL_DIR_QUALITY_META = "meta" + File.separator + "quality_meta.json";
    public static final String REL_DIR_METRIC_META = "metric" + File.separator + "metrics.json";
    public static final String ABS_DIR_PERSIST_METRIC_RESULTS = System.getProperty("user.home") + File.separator +
                                                                APPNAME + File.separator + "persist" + File.separator;

    public static final String CLI_OPT_DESCR_FILE = "Path and filename of XML EA model to evaluate.";
    public static final String CLI_OPT_SHORT_FILE = "f";
    public static final String CLI_OPT_LONG_FILE = "file";
    public static final Boolean CLI_OPT_REQUIRED_FILE = true;
    public static final Boolean CLI_OPT_HASARG_FILE = true;

    public static final String CLI_OPT_DESCR_METRICSUITE = "Path and filename of metric suite.";
    public static final String CLI_OPT_SHORT_METRICSUITE = "m";
    public static final String CLI_OPT_LONG_METRICSUITE = "metrics";
    public static final Boolean CLI_OPT_REQUIRED_METRICSUITE = false;
    public static final Boolean CLI_OPT_HASARG_METRICSUITE = true;

    public static final String CLI_OPT_DESCR_EAMETA = "Path and filename of EA meta model.";
    public static final String CLI_OPT_SHORT_EAMETA = "ea";
    public static final String CLI_OPT_LONG_EAMETA = "eameta";
    public static final Boolean CLI_OPT_REQUIRED_EAMETA = false;
    public static final Boolean CLI_OPT_HASARG_EAMETA = true;

    public static final String CLI_OPT_DESCR_QMETA = "Path and filename of quality meta model.";
    public static final String CLI_OPT_SHORT_QMETA = "q";
    public static final String CLI_OPT_LONG_QMETA = "qmeta";
    public static final Boolean CLI_OPT_REQUIRED_QMETA = false;
    public static final Boolean CLI_OPT_HASARG_QMETA = true;

    public static final String CLI_OPT_DESCR_PERSISTPATH = "Path for metric suite run result persistence.";
    public static final String CLI_OPT_SHORT_PERSISTPATH = "p";
    public static final String CLI_OPT_LONG_PERSISTPATH = "persist";
    public static final Boolean CLI_OPT_REQUIRED_PERSISTPATH = false;
    public static final Boolean CLI_OPT_HASARG_PERSISTPATH = true;

    public static final String CLI_OPT_DESCR_DRYRUN = "Do not persist results.";
    public static final String CLI_OPT_SHORT_DRYRUN = "d";
    public static final String CLI_OPT_LONG_DRYRUN = "dryrun";
    public static final Boolean CLI_OPT_REQUIRED_DRYRUN = false;
    public static final Boolean CLI_OPT_HASARG_DRYRUN = false;
    public static final String CLI_OPT_DESCR_GROUPING_CAT = "Grouping category for result display [" +
            MetricLog.VIEW_OUTPUT_GROUPING_CRITERION.QRC + " (default) | " +
            MetricLog.VIEW_OUTPUT_GROUPING_CRITERION.MQC + " | " +
            MetricLog.VIEW_OUTPUT_GROUPING_CRITERION.SR +
            "].";
    public static final MetricLog.VIEW_OUTPUT_GROUPING_CRITERION CLI_OPT_DESCR_GROUPING_CAT_DEFAULT = MetricLog.VIEW_OUTPUT_GROUPING_CRITERION.QRC;
    public static final String CLI_OPT_SHORT_GROUPING_CAT = "gc";
    public static final String CLI_OPT_LONG_GROUPING_CAT = "groupingcat";
    public static final Boolean CLI_OPT_REQUIRED_GROUPING_CAT = false;
    public static final Boolean CLI_OPT_HASARG_GROUPING_CAT = true;

    public static final String NAMESPACE_DEFAULT = "http://www.opengroup.org/xsd/archimate/3.0/";
    public static final String NS_PREFIX_DEFAULT = "ea";
    public static final String NAMESPACE_MATH = "http://www.w3.org/2005/xpath-functions/math";
    public static final String NS_PREFIX_MATH = "math";

    public static final String XQ_EXTENSION_CONTEXT_LOCAL_NAME = "_ROOT";
    public static final String XQ_EXTENSION_NAMESPACE = "http://eaqtool.swc.rwth-aachen.de/";
    public static final String XQ_EXTENSION_PREFIX = "eaqt";

    public static final String XQ_EXTENSION_LEXICAL_EXECXQ = "execXq";
    public static final String XQ_EXTENSION_LEXICAL_EXECEAMETA = "execEaMeta";
    public static final String XQ_EXTENSION_LEXICAL_AGEOFMODEL = "ageOfModel";
    public static final String XQ_EXTENSION_LEXICAL_EXECNORMALIZER = "execNormalizer";

    public static final String JSONTS_DOCTYPE = "jsonts";
    public static final String JSONTS_VERSION = "1.0";

}
