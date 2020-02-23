package de.rwth_aachen.swc.eaqtool.view;

import de.rwth_aachen.swc.eaqtool.EAQTHelper;
import de.rwth_aachen.swc.eaqtool.meta.EAQTMeta;
import de.rwth_aachen.swc.eaqtool.metric.Normalizer;
import de.rwth_aachen.swc.eaqtool.persist.DataProvider;
import de.rwth_aachen.swc.eaqtool.persist.MetricLog;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class ModelViewCoordinator {

    public static final String VIEW_HEADER_KEY_DATASOURCE_NAME = "data_source_name";
    public static final String VIEW_HEADER_KEY_DATASOURCE_ID = "data_source_id";
    public static final String VIEW_HEADER_KEY_DATASET_NAME = "dataset_name";
    public static final String VIEW_HEADER_KEY_DATASET_VERSION = "dataset_version";
    public static final String VIEW_HEADER_KEY_DATASET_DATE = "dataset_date";

    private ANSIConsoleView view;
    private MetricLog model;

    public ModelViewCoordinator(MetricLog m, ANSIConsoleView v) {
        this.model = m;
        this.view = v;
    }

    public void displayView(MetricLog.VIEW_OUTPUT_GROUPING_CRITERION groupingCriterion) {
        view.display(prepareViewOutput(model, groupingCriterion));
    }

    /**
     * Prepare a ViewOutput object from log data (MetricLog object) to be displayed by a view.
     * @param model the MetricLog object to prepare the view output for
     * @param groupingCriterion Criterion to group the output data by.
     * @return ViewOutput object for a view.
     */
    public ViewOutput prepareViewOutput(MetricLog model, MetricLog.VIEW_OUTPUT_GROUPING_CRITERION groupingCriterion) {
        EAQTMeta metaModels = EAQTMeta.getInstance();
        Map<String, String> head = new LinkedHashMap<>();
        head.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASOURCE_NAME, model.getDataSourceName());
        head.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASOURCE_ID, model.getDataSourceId());
        head.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASET_NAME, model.getHeader().getDatasetName());
        head.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASET_VERSION, model.getHeader().getDatasetVersion());
        head.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASET_DATE, model.getHeader().getDatasetDate());
        Map<String, Map<String, List<String>>> data = new TreeMap<>();
        Map<String, Map<String, String>> bltotals = new TreeMap<>();
        List<String> cat;
        switch (groupingCriterion) {
            case SR:
                cat = EAQTHelper.extractIdsFromPojoList(metaModels.getStakeholderRoles(), null);
                break;
            case MQC:
                cat = EAQTHelper.extractIdsFromPojoList(metaModels.getMeasurableQualityCharacteristics(), null);
                break;
            case QRC:
            default:
                cat = EAQTHelper.extractIdsFromPojoList(metaModels.getQualityRequirementCategories(), null);
                break;
        }
        Collections.sort(cat);
        for (String c : cat) {
            Map<String, List<String>> metric_entry = new TreeMap<>();
            Map<String, String> bl_entry = new TreeMap<>();
            List<String> criteriaList = null;
            switch (groupingCriterion) {
                case SR:
                    criteriaList = EAQTHelper.extractIdsFromPojoList(metaModels.getQualityRequirementCategoriesByStakeholderRole(c), null);
                    break;
                case MQC:
                    criteriaList = EAQTHelper.extractIdsFromPojoList(metaModels.getQualityRequirementCategoriesByMeasurableQualityCharacteristic(c), null);
                    break;
                case QRC:
                default:
                    criteriaList = null;
                    break;
            }
            List<List<String>> data_p = model.getData().get(model.getData().size()-1).getDataP();
            for (DataProvider dp : model.getDataProvider()) {
                if (criteriaList==null) {
                    if (dp.getProviderMetaHook().compareTo(c) == 0) {
                        metric_entry.put(dp.getProviderName(), getPayloadDataForDataProviderId(model, data_p, dp.getProviderId()));
                    }
                } else {
                    for (String q : criteriaList) {
                        if (dp.getProviderMetaHook().compareTo(q) == 0) {
                            metric_entry.put(dp.getProviderName(), getPayloadDataForDataProviderId(model, data_p, dp.getProviderId()));
                        }
                    }
                }
            }
            data.put(c, metric_entry);
            Double total = 0.0;
            Double bad = 0.0;
            Double norm = 0.0;
            Double good = 0.0;
            String marker;
            for (Map.Entry<String, List<String>> me : metric_entry.entrySet()) {
                total++;
                marker = me.getValue().get(2);
                if (me.getValue().get(1).compareTo("-")!=0 && me.getValue().get(0).compareTo("")!=0) {
                    if (marker.compareTo(MetricLog.BENCHMARK_LEVEL.BL_GREEN.toString())==0) {
                        good++;
                    } else if (marker.compareTo(MetricLog.BENCHMARK_LEVEL.BL_RED.toString())==0) {
                        bad++;
                    } else {
                        norm++;
                    }
                }
            }
            Double dg = total>0 ? Normalizer.round( (good/total)*100 , Normalizer.STANDARD_PRECISION) : 0.0;
            Double dy = total>0 ? Normalizer.round( (norm/total)*100 , Normalizer.STANDARD_PRECISION) : 0.0;
            Double dr = total>0 ? Normalizer.round( (bad/total)*100 , Normalizer.STANDARD_PRECISION) : 0.0;
            bl_entry.put( MetricLog.BENCHMARK_LEVEL.BL_GREEN.toString(), dg.toString() );
            bl_entry.put( MetricLog.BENCHMARK_LEVEL.BL_YELLOW.toString(), dy.toString() );
            bl_entry.put( MetricLog.BENCHMARK_LEVEL.BL_RED.toString(), dr.toString() );
            bltotals.put(c, bl_entry);
        }
        Double total = 0.0;
        Double bad = 0.0;
        Double norm = 0.0;
        Double good = 0.0;
        Map<String, String> qindex = new TreeMap<>();
        List<List<String>> data_p = model.getData().get(model.getData().size()-1).getDataP();
        String marker;
        for (DataProvider dp : model.getDataProvider()) {
            List<String> pl = getPayloadDataForDataProviderId(model, data_p, dp.getProviderId());
            total++;
            marker = pl.get(2);
            if (pl.get(1).compareTo("-")!=0  && pl.get(0).compareTo("")!=0) {
                if (marker.compareTo(MetricLog.BENCHMARK_LEVEL.BL_GREEN.toString()) == 0) {
                    good++;
                } else if (marker.compareTo(MetricLog.BENCHMARK_LEVEL.BL_RED.toString()) == 0) {
                    bad++;
                } else {
                    norm++;
                }
            }
        }
        Double dg = total>0 ? Normalizer.round( (good/total)*100 , Normalizer.STANDARD_PRECISION) : 0.0;
        Double dy = total>0 ? Normalizer.round( (norm/total)*100 , Normalizer.STANDARD_PRECISION) : 0.0;
        Double dr = total>0 ? Normalizer.round( (bad/total)*100 , Normalizer.STANDARD_PRECISION) : 0.0;
        qindex.put( MetricLog.BENCHMARK_LEVEL.BL_GREEN.toString(), dg.toString() );
        qindex.put( MetricLog.BENCHMARK_LEVEL.BL_YELLOW.toString(), dy.toString() );
        qindex.put( MetricLog.BENCHMARK_LEVEL.BL_RED.toString(), dr.toString() );
        return new ViewOutput(head, data, bltotals, qindex);
    }

    private List<String> getPayloadDataForDataProviderId(MetricLog model, List<List<String>> payload, String dataProviderId) {
        List<String> ret = new ArrayList<>();

        DecimalFormatSymbols sep = new DecimalFormatSymbols(Locale.getDefault());
        sep.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", sep);
        List<String> p = getPayloadEntryForDataProviderId(payload, dataProviderId);
        String BLmarker = "";
        if (p.get(2).compareTo("")!=0) {
            BLmarker = model.getBenchmarkLevel(dataProviderId, Double.parseDouble(p.get(2))).toString();
        }
        String normValue = (p.get(2).compareTo("")!=0 ? p.get(2) : StringUtils.repeat(" ", Normalizer.STANDARD_PRECISION + 2));    // payload index 2 = norm. value
        String rawValue = (p.get(1).compareTo("")!=0 ? p.get(1) : "-");     // payload index 1 = raw value
        String sweetspot = model.getDataProviderById(dataProviderId).getProviderSweetspot();
        ret.add(p.get(2).compareTo("")!=0 ? df.format(Double.parseDouble(normValue)) : "");
        ret.add(rawValue);
        ret.add(BLmarker);
        ret.add(sweetspot);

        return ret;
    }

    private List<String> getPayloadEntryForDataProviderId(List<List<String>> payload, String dataProviderId) {
        List<String> ret = new ArrayList<>();

        for (List<String> p : payload) {
            if (p.get(0).compareTo(dataProviderId) == 0) {  // payload index 0 = Metric ID
                ret = p;
                break;
            }
        }

        return ret;
    }

}
