package de.rwth_aachen.swc.eaqtool.metric;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.rwth_aachen.swc.eaqtool.EAQTConf;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for metric suite model and helper methods.
 */
public class MSMeta {

    private static MSMeta instance;
    private static MetricSuite suite;

    /**
     * Bootstrap loader for metric suite.
     */
    private MSMeta(String metricsuiteFile) {
        if(metricsuiteFile==null) metricsuiteFile = EAQTHelper.getResourceDirectory() + EAQTConf.REL_DIR_METRIC_META;
        File metric_meta_file = new File(metricsuiteFile);
        ObjectMapper mapper = new ObjectMapper();
        try {
            MetricSuite ms = mapper.readValue(metric_meta_file, MetricSuite.class);
            suite = ms;
        } catch (IOException ioex) {
            System.err.println(ioex);
        }
    }

    /**
     * Lazy singleton, see constructor.
     * @return Initialized MSMeta instance.
     */
    public static MSMeta getInstance(){
        if (instance == null) {
            instance = new MSMeta(null);
        }
        return instance;
    }

    /**
     * Lazy singleton, see constructor.
     * @return Initialized MSMeta instance.
     */
    public static MSMeta getInstance(String metricsuiteFile){
        if (instance == null) {
            instance = new MSMeta(metricsuiteFile);
        }
        return instance;
    }

    /**
     * Direct access to MetricSuite POJO.
     * @return MetricSuite instance.
     */
    public MetricSuite getMetricSuite() {
        return suite;
    }

    /**
     * Return POJO by its ID.
     * @param returnType Class of POJO to return.
     * @param metaAggregator Aggregator object knowing list of returnType POJOs.
     * @param id POJO id.
     * @return Entity by id.
     */
    public <T> T getEntityById(Class<T> returnType, Object metaAggregator, String id) {
        T ret = null;

        try {
            for (T c : (List<T>) metaAggregator.getClass().getMethod("get" + returnType.getSimpleName()).invoke(metaAggregator)) {
                try {
                    if (c.getClass().getMethod("getId").invoke(c).equals(id)) ret = c;
                } catch (Exception ex) {
                    System.err.println(ex);
                }

            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

        return returnType.cast(ret);
    }

    /**
     * Get list of A-type entities by id of B-type entity from a list of n-to-m relations between A and B
     * Returns list of POJOs of entity type A.
     * @param returnType Class of POJOs to return (entity type A).
     * @param linkList List of n-to-m relations between A and B entities.
     * @param metaAggregator Aggregator object knowing list of returnType POJOs (type A).
     * @param returnTypeIdName Name of foreign key for return types (A) in the n-to-m relations linkList.
     * @param searchTypeIdName Name of foreign key for search type (B) in the n-to-m relations linkList.
     * @return List of entities of type A by given entity id of type B.
     */
    public <T, S> List<T> getEntitiesByEntityId(Class<T> returnType, List<S> linkList, Object metaAggregator, String returnTypeIdName, String searchTypeIdName, String id) {
        List<T> ret = new ArrayList<>();

        T test;
        for (S link : linkList) {
            try {
                if (link.getClass().getMethod("get" + searchTypeIdName.toUpperCase() + "Id").invoke(link).equals(id)) {
                    String returnTypeId = link.getClass().getMethod("get" + returnTypeIdName.toUpperCase() + "Id").invoke(link).toString();
                    test = getEntityById(returnType, metaAggregator, returnTypeId);
                    ret.add(returnType.cast(test));
                }
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }

        return ret;
    }

    /* wrapper methods: Get entity list */

    /**
     * @return List of Metrics.
     */
    public List<Metric> getMetrics() {
        return suite.getMetric();
    }

    /**
     * @return List of Ammps (as bootstrapped).
     */
    public List<Ammp> getAmmpList() {
        return suite.getAmmp();
    }

    /* wrapper methods: Get entity by ID */

    /**
     * @return Metric by id.
     */
    public Metric getMetricById(String id) {
        return getEntityById(Metric.class, suite, id);
    }

    /**
     * @return Ammp by id.
     */
    public Ammp getAmmpById(String id) {
        return getEntityById(Ammp.class, suite, id);
    }

}
