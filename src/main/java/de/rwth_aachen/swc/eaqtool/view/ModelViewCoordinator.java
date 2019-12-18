package de.rwth_aachen.swc.eaqtool.view;

import de.rwth_aachen.swc.eaqtool.persist.MetricLog;

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
        view.display(model.prepareViewOutput(groupingCriterion));
    }

}
