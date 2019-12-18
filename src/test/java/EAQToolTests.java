import de.rwth_aachen.swc.eaqtool.EAQTConf;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;
import de.rwth_aachen.swc.eaqtool.meta.QmMeasurableQualityCharacteristic;
import de.rwth_aachen.swc.eaqtool.meta.QmQualityRequirementCategory;
import de.rwth_aachen.swc.eaqtool.meta.QmStakeholderRole;
import de.rwth_aachen.swc.eaqtool.metric.*;
import de.rwth_aachen.swc.eaqtool.persist.*;
import de.rwth_aachen.swc.eaqtool.repo.*;
import net.sf.saxon.s9api.XdmItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.rwth_aachen.swc.eaqtool.meta.EAQTMeta;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class
 * Please note: Some of the tests in here require the REL_DIR_METRIC_META config. var. to point to the metric/metrics-test.json metric suite definition file!
 * Otherwise those tests will fail (or even crash), because they test against an exemplary immutable metric suite, or require specific stuff (for example specific test AMMP scripts).
 */
public class EAQToolTests {

    @Test
    public void EAQTMetaModelsShouldNotReturnNull() {
        EAQTMeta meta = EAQTMeta.getInstance();

        Assertions.assertNotNull(meta.getQualityMeta());
        Assertions.assertNotNull(meta.getEaMeta());
    }

    @Test
    public void MetricSuiteMetaModelShouldNotReturnNull() {
        MetricSuite ms = MSMeta.getInstance().getMetricSuite();

        Assertions.assertNotNull(ms);
    }

    @Test
    public void DedupingObjectIdResolver() {
        MSMeta meta = MSMeta.getInstance();
        MetricSuite ms = meta.getMetricSuite();
        Ammp ammp = meta.getAmmpById("AMMP_number-of-elem");
        Boolean ammp1 = ms.getMetric().get(0).getAmmpList().contains(ammp);
        Boolean ammp2 = ms.getMetric().get(1).getAmmpList().contains(ammp);

        assertEquals(true, ammp1 && ammp2, "1st and 2nd demo metric reference the same Ammp object");
    }

    @Test
    public void EAQTMetaQualityModelShouldYieldSixStakeholderRoles() {
        EAQTMeta meta = EAQTMeta.getInstance();
        List<QmStakeholderRole> sr = meta.getQualityMeta().getQmStakeholderRole();

        assertEquals(6, sr.size(), "# basic stakeholder roles: 6");
    }

    @Test
    public void EAQTMeta_getStakeholderRolesByQualityRequirementCategory_QRC_Completeness() {
        EAQTMeta meta = EAQTMeta.getInstance();
        List<QmStakeholderRole> test = meta.getStakeholderRolesByQualityRequirementCategory("QRC_Completeness");

        assertEquals(3, test.size(), "# basic stakeholder roles: 3");
        assertEquals("SR_Architect", test.get(0).getId(), "1st should yield SR_Architect");
        assertEquals("SR_ManagerUpperLevel", test.get(1).getId(), "2nd should yield SR_ManagerUpperLevel");
        assertEquals("SR_ManagerOperational", test.get(2).getId(), "3rd should yield SR_ManagerOperational");
    }

    @Test
    public void EAQTMeta_getQualityRequirementCategoriesByStakeholderRoles_SR_Architect() {
        EAQTMeta meta = EAQTMeta.getInstance();
        List<QmQualityRequirementCategory> test = meta.getQualityRequirementCategoriesByStakeholderRole("SR_Architect");

        assertEquals(3, test.size(), "# quality requirement categories: 3");
        assertEquals("QRC_Completeness", test.get(0).getId(), "1st should yield QRC_Completeness");
        assertEquals("QRC_Correctness", test.get(1).getId(), "2nd should yield QRC_Correctness");
        assertEquals("QRC_Maintainability", test.get(2).getId(), "3rd should yield QRC_Maintainability");
    }

    @Test
    public void EAQTMeta_getQualityRequirementCategoriesByMeasurableQualityCharacteristic_MQC_Timeliness() {
        EAQTMeta meta = EAQTMeta.getInstance();
        List<QmQualityRequirementCategory> test = meta.getQualityRequirementCategoriesByMeasurableQualityCharacteristic("MQC_Timeliness");

        assertEquals(2, test.size(), "# quality requirement categories: 2");
        assertEquals("QRC_Completeness", test.get(0).getId(), "1st should yield QRC_Completeness");
        assertEquals("QRC_Accuracy", test.get(1).getId(), "2nd should yield QRC_Accuracy");
    }

    @Test
    public void EAQTMeta_getMeasurableQualityCharacteristicsByQualityRequirementCategory_QRC_Correctness() {
        EAQTMeta meta = EAQTMeta.getInstance();
        List<QmMeasurableQualityCharacteristic> test = meta.getMeasurableQualityCharacteristicsByQualityRequirementCategory("QRC_Correctness");

        assertEquals(2, test.size(), "# measurable quality characteristics: 2");
        assertEquals("MQC_Consistency", test.get(0).getId(), "1st should yield MQC_Consistency");
        assertEquals("MQC_Validity", test.get(1).getId(), "2nd should yield MQC_Validity");
    }

    @Test
    public void NormalizerFactoryBasicTest() {
        NormalizerSbowles sb = (NormalizerSbowles) NormalizerFactory.getNormalizer(NormalizerFactory.NORMALIZER_TYPE_SBOWLES);

        Assertions.assertNotNull(sb);
    }

    @Test
    public void NormalizerSbowles() {
        NormalizerSbowles n = (NormalizerSbowles) NormalizerFactory.getNormalizer(NormalizerFactory.NORMALIZER_TYPE_SBOWLES);

        assertEquals(0.56, n.normalize(3.0), "s-bowles normalization of value 3.0 with default for s parameter of 2.0 and standard precision of 2: 0.56");
    }

    @Test
    public void NormalizerSbowlesWithParam() {
        NormalizerSbowles n = (NormalizerSbowles) NormalizerFactory.getNormalizer(NormalizerFactory.NORMALIZER_TYPE_SBOWLES + ":s=3");

        assertEquals(0.42, n.normalize(3.0), "s-bowles normalization of value 3.0 with s parameter of 3.0 and standard precision of 2: 0.42");
    }

    @Test
    public void Normalizer01sigmoid() {
        Normalizer01sigmoid n = (Normalizer01sigmoid) NormalizerFactory.getNormalizer(NormalizerFactory.NORMALIZER_TYPE_01SIGMOID);

        assertEquals(0.91, n.normalize(3.0), "01-sigmoid normalization of value 3.0 with default for k parameter of 1.0 and standard precision of 2: 0.91");
    }

    @Test
    public void Normalizer01sigmoidWithParam() {
        Normalizer01sigmoid n = (Normalizer01sigmoid) NormalizerFactory.getNormalizer(NormalizerFactory.NORMALIZER_TYPE_01SIGMOID + ":k=2");

        assertEquals(0.64, n.normalize(3.0), "01-sigmoid normalization of value 3.0 with k parameter of 2.0 and standard precision of 2: 0.64");
    }

    @Test
    public void Normalizer3plFailForMissingXzeroParam() {
        Normalizer3pl n = (Normalizer3pl) NormalizerFactory.getNormalizer(NormalizerFactory.NORMALIZER_TYPE_3PL);

        assertThrows(NullPointerException.class, () -> {
            n.normalize(2.0);
        });    }

    @Test
    public void Normalizer3pl() {
        Normalizer3pl n = (Normalizer3pl) NormalizerFactory.getNormalizer(NormalizerFactory.NORMALIZER_TYPE_3PL + ":xzero=2.0");

        assertEquals(0.5, n.normalize(2.0), "3pl normalization of value 2.0 with default for m parameter of 1.0, default for k parameter of 1.0, xzero parameter of 2.0 and standard precision of 2: 0.5");
    }

    @Test
    public void XQExtensionFunctionExecNormalizer() {
        String xq = "let $raw := 2.0 let $norm := eaqt:execNormalizer( concat('NT_3PL:xzero=2.0,raw=', $raw) ) return $norm";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(0.5, Double.parseDouble(results.get(0).getStringValue()), "3pl normalization (embedded in xquery script) of value 2.0 with default for m parameter of 1.0, default for k parameter of 1.0, xzero parameter of 2.0 and standard precision of 2: 0.5");
    }

    @Test
    public void XMLFileReaderFactoryShouldNotReturnNull() {
        XMLFileReaderFactory factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiMetal.xml");
        XMLFileReader reader = factory.getReader();

        Assertions.assertNotNull(reader);
    }

    @Test
    public void XQSpecificationFactoryShouldNotReturnNull() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("testA", "hallo");
        map.put("testB", "welt");
        XQSpecificationFactory factory = new XQSpecificationFactory();
        factory.loadParam("just a test"); factory.loadParam(map);
        XQSpecification spec = factory.getSpecification();

        Assertions.assertNotNull(spec);
    }

    @Test
    public void XQSpecificationFactoryNoParameterMap() {
        XQSpecificationFactory factory = new XQSpecificationFactory();
        factory.loadParam("just a test");
        XQSpecification spec = factory.getSpecification();

        Assertions.assertNotNull(spec);
    }

    @Test
    public void XQSpecification() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("testA", "hallo");
        map.put("testB", "welt");
        XQSpecificationFactory factory = new XQSpecificationFactory();
        factory.loadParam("just a test"); factory.loadParam(map);
        XQSpecification spec = factory.getSpecification();

        assertEquals(map.get("testB"), spec.getExtVar("testB"), "Test extrnal variables map");
        assertEquals(true, spec.getScript().endsWith("just a test"), "Test script");
    }

    @Test
    public void XQEAModelRepository() {
        String xq = "for $elem in /ea:model/ea:elements " +
                    "return count($elem/ea:element)";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(4, Integer.parseInt(results.get(0).getStringValue()), "Return number of elements in test model ArchiTest.xml: 4");
    }

    @Test
    public void XQEAModelRepositoryWithParameters() {
        String xq =
            "declare variable $view as xs:string external;" +
            "for $elem in /ea:model/ea:views/ea:diagrams/ea:view/descendant-or-self::ea:node " +
            "let $elemref := $elem/@elementRef " +
            "let $elemnames := /ea:model/ea:elements/ea:element[@identifier=$elemref]/ea:name " +
            "where $elem[@xsi:type='Element'] and $elem/ancestor::ea:view/ea:name/string()=$view " +
            "order by $elemnames/string() " +
            "return $elemnames/string()";
        HashMap<String, Object> map = new HashMap<>();
        map.put("view", "Physical");
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq); spec_factory.loadParam(map);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(2, results.size(), "Return the names of two elements in the Physical view");
        assertEquals(true, XQEAModelRepository.resultsetContains(results, "LAN"), "Elements LAN, DBMS");
        assertEquals(true, XQEAModelRepository.resultsetContains(results, "DBMS"), "Elements LAN, DBMS");
    }

    @Test
    public void XQExtensionFunctionExecXq() {
        String xq =
            "for $v in /ea:model/ea:views/ea:diagrams/ea:view/ea:name/text() " +
            "let $num := eaqt:execXq(concat('AMMP_number-of-elem-by-view:v=', $v)) " +
            "return concat($v, ': ', $num)";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(true, results.get(0).getStringValue().endsWith("2"), "Return number of elements in view 'Physical' from test model ArchiTest.xml: 2");
        assertEquals(true, results.get(1).getStringValue().endsWith("1"), "Return number of elements in view 'Data Access Service' from test model ArchiTest.xml: 1");
    }

    @Test
    public void XQExtensionFunctionExecEaMetaGetEntitiesByEntityId() {
        String xq =
            "let $ret := eaqt:execEaMeta(concat('getRelationshipTypesByRelationshipCategory:id=', 'RC_Structural')) " +
            "return $ret";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(4, results.size(), "Return relationship types for category 'RC_Structural': 4");
        assertEquals(true, results.toString().contains("RT_Composition") && results.toString().contains("RT_Aggregation") && results.toString().contains("RT_Assignment") && results.toString().contains("RT_Realization"), "Return relationship types for category 'RC_Structural': RT_Composition, RT_Aggregation, RT_Assignment, RT_Realization");
    }

    @Test
    public void XQExtensionFunctionExecEaMetaGetStakeholderRolesByBasicView() {
        String xq =
            "let $ret := eaqt:execEaMeta(concat('getStakeholderRolesByBasicView:id=', 'Organization')) " +
            "return $ret";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(5, results.size(), "Return stakeholder roles for basic vp category 'Organization': 5");
    }

    @Test
    public void XQExtensionFunctionExecEaMetaGetListOfEntities() {
        String xq =
            "let $ret := eaqt:execEaMeta('getViewPurposes') " +
            "return $ret";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(3, results.size(), "Return list of view purposes: 3");
        assertEquals(true, results.toString().contains("VPP_Designing") && results.toString().contains("VPP_Deciding") && results.toString().contains("VPP_Informing"), "Return list of view purposes: VPP_Designing, VPP_Deciding, VPP_Informing");
    }

    @Test
    public void MetricLogSerializer() {
        MetricLog log = makeALog();
        String targetFile = log.buildPersistenceFilename(EAQTConf.ABS_DIR_PERSIST_METRIC_RESULTS);
        Map<String, Object> params = new HashMap<>();
        params.put(JacksonPersistenceFactory.PARAM_FORMAT_KEY, JacksonPersistenceFactory.PARAM_FORMAT_VALUE_PRETTY);
        params.put(JacksonPersistenceFactory.PARAM_BASENAME_KEY, targetFile);
        JacksonPersistenceFactory fac = new JacksonPersistenceFactory(JacksonPersistenceFactory.FWTYPE_FILE, params);
        JacksonSerializer serializer = (JacksonSerializer) fac.getPojoSerializer();
        serializer.serialize(log);
        JacksonDeserializer deserializer = (JacksonDeserializer) fac.getPojoDeserializer();
        MetricLog delog = deserializer.deserialize();

        Assertions.assertNotNull(delog);
        assertEquals("M_ratio-test-2", delog.getDataProvider().get(1).getProviderId(), "Return 2nd provider ID from serialized and then immediately deserialized metric log: M_ratio-test-2");
    }

    @Test
    public void MetricLogContinuityViolation() {
        MetricLog log = makeALog();
        MetricLog newlog = makeALog();
        // change some stuff...
        log.getDataProvider().get(0).setProviderVersion("1.1");

        assertEquals(false, newlog.checkContinuity(log)==null, "Continuity checker should be grumpy about the change and respond with error String.");
    }

    @Test
    public void MetricLogContinuityIsFine() {
        MetricLog log = makeALog();
        MetricLog newlog = makeALog();

        assertEquals(null, newlog.checkContinuity(log), "Continuity checker should be happy and respond with null.");
    }

    @Test
    public void MetricRunBasicTest() {
        MetricSuite ms = MSMeta.getInstance().getMetricSuite();
        Metric m = ms.getMetric().get(0);
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(m.getScriptStr());
        XQSpecification spec = spec_factory.getSpecification();
        ArrayList<String> result = m.run(repo, spec);

        assertEquals("M_ratio-test", result.get(0), "Return 1st Metric run ID in Test Suite: M_ratio-test");
        assertEquals("0.5", result.get(1), "Return 1st Metric run raw value in Test Suite: 0.5");
        assertEquals("0.04", result.get(2), "Return 1st Metric run normalized and rounded (standard) value in Test Suite: 0.04");
    }

    @Test
    public void MetricSuiteRunBasicTest() {
        MetricSuite ms = MSMeta.getInstance().getMetricSuite();
        Metric m = ms.getMetric().get(0);
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        List<List<String>> result = ms.run(repo);

        assertEquals("M_ratio-test-2", result.get(1).get(0), "Return 2nd Metric Suite run Metric ID in Test Suite: M_ratio-test-2");
        assertEquals(true, result.get(1).get(1).toString().startsWith("0.66666"), "Return 2nd Metric Suite run Metric raw value in Test Suite starts with: 0.66666");
        assertEquals("", result.get(1).get(2), "Return 2nd Metric Suite run no Normalizer (no normalized value");
    }

    @Test
    public void XQExtensionFunctionAgeOfModel() {
        String xq = "let $age := eaqt:ageOfModel() return $age";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals(true, !results.get(0).getStringValue().isEmpty() && Integer.parseInt(results.get(0).getStringValue())>0, "Return age of model (file) in days is set and number");
    }

    @Test
    public void BLLevels() {
        MetricLog log = new MetricLog();
        log.setDocType("jsonts");
        log.setVersion("1.0");
        log.setDataSourceName("/just/a/path/dummy.xml");
        log.setDataSourceId("1234567890");
        log.setHeader(null);
        Globals globals = new Globals(null, null, "0.1", "0.1", "0.0", "1.0");
        log.setGlobals(globals);
        List<DataProvider> metrics = new ArrayList<>();
        metrics.add(new DataProvider("dummy", "Test Provider", "", "", "", "", "", null, null, null, null, null));
        log.setDataProvider(metrics);
        log.setData(null);
        DataProvider dummy = log.getDataProviderById("dummy");

        // local thresholds, local scaling
        log.getGlobals().setGlobalBLGreen(null);
        log.getGlobals().setGlobalBLRed(null);
        dummy.setProviderScaleMin("0.0");
        dummy.setProviderScaleMax("1.0");
        dummy.setProviderSweetspot("SCALE_MIN");
        dummy.setProviderBLGreen("0.2");
        dummy.setProviderBLRed("0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.2), "BL GREEN, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.9), "BL RED, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW PLUS, 0.2 < n <= 0.3");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW MINUS, 0.8 > n >= 0.7");
        dummy.setProviderSweetspot("SCALE_MAX");
        dummy.setProviderBLRed("0.2");
        dummy.setProviderBLGreen("0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.9), "BL GREEN, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.2), "BL RED, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW PLUS, 0.8 > n >= 0.7");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW MINUS, 0.2 < n <= 0.3");

        // global thresholds, local scaling
        log.getGlobals().setGlobalBLGreen("0.2");
        log.getGlobals().setGlobalBLRed("0.8");
        dummy.setProviderScaleMin("0.0");
        dummy.setProviderScaleMax("1.0");
        dummy.setProviderSweetspot("SCALE_MIN");
        dummy.setProviderBLGreen(null);
        dummy.setProviderBLRed(null);
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.2), "BL GREEN, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.9), "BL RED, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW PLUS, 0.2 < n <= 0.3");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW MINUS, 0.8 > n >= 0.7");
        dummy.setProviderSweetspot("SCALE_MAX");
        dummy.setProviderBLRed(null);
        dummy.setProviderBLGreen(null);
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.9), "BL GREEN, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.2), "BL RED, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW PLUS, 0.8 > n >= 0.7");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW MINUS, 0.2 < n <= 0.3");

        // local thresholds, global scaling
        log.getGlobals().setGlobalBLGreen(null);
        log.getGlobals().setGlobalBLRed(null);
        dummy.setProviderScaleMin(null);
        dummy.setProviderScaleMax(null);
        dummy.setProviderSweetspot("SCALE_MIN");
        dummy.setProviderBLGreen("0.2");
        dummy.setProviderBLRed("0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.2), "BL GREEN, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.9), "BL RED, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW PLUS, 0.2 < n <= 0.3");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW MINUS, 0.8 > n >= 0.7");
        dummy.setProviderSweetspot("SCALE_MAX");
        dummy.setProviderBLRed("0.2");
        dummy.setProviderBLGreen("0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.9), "BL GREEN, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.2), "BL RED, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW PLUS, 0.8 > n >= 0.7");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW MINUS, 0.2 < n <= 0.3");

        // global thresholds, global scaling
        log.getGlobals().setGlobalBLGreen("0.2");
        log.getGlobals().setGlobalBLRed("0.8");
        dummy.setProviderScaleMin(null);
        dummy.setProviderScaleMax(null);
        dummy.setProviderSweetspot("SCALE_MIN");
        dummy.setProviderBLGreen(null);
        dummy.setProviderBLRed(null);
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.2), "BL GREEN, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.9), "BL RED, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW PLUS, 0.2 < n <= 0.3");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW MINUS, 0.8 > n >= 0.7");
        dummy.setProviderSweetspot("SCALE_MAX");
        dummy.setProviderBLRed(null);
        dummy.setProviderBLGreen(null);
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_GREEN, log.getBenchmarkLevel("dummy", 0.9), "BL GREEN, n >= 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_RED, log.getBenchmarkLevel("dummy", 0.2), "BL RED, n <= 0.2");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW, log.getBenchmarkLevel("dummy", 0.5), "BL YELLOW, 0.2 < n < 0.8");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_PLUS, log.getBenchmarkLevel("dummy", 0.75), "BL YELLOW PLUS, 0.8 > n >= 0.7");
        assertEquals(MetricLog.BENCHMARK_LEVEL.BL_YELLOW_MINUS, log.getBenchmarkLevel("dummy", 0.25), "BL YELLOW MINUS, 0.2 < n <= 0.3");
    }

    @Test
    public void VCC_JCP() {
        String xq =
            "let $num := eaqt:execXq('AMMP_JCP:vid=id-17883') " +   // 02. Order-to-contract
            "return $num";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiMetal.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals("0.6", results.get(0).getStringValue(), "Return JCP of view '02. Order-to-contract' : 0.6");
    }

    @Test
    public void VCC_DRCD() {
        String xq =
            "let $num := eaqt:execXq('AMMP_DRCD:vid=id-87715c98-130c-493e-a03d-8e8c695143da') " +   // DRCD_Test
            "return $num";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiTest.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals("6", results.get(0).getStringValue(), "Return DRCD of view 'DRCD_Test' : 6");
    }

    @Test
    public void VCCbas() {
        String xq =
            "let $num := eaqt:execXq('AMMP_VCCbas:vid=id-17883') " +   // 02. Order-to-contract
            "return $num";
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiMetal.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        assertEquals("3.6", results.get(0).getStringValue(), "Return VCCbas of view '02. Order-to-contract' : 3.6");
    }


    // not a real test, I just use it to debug xq stuff... if you can see this, I just forgot to delete it before release, my bad ;-)
    @Test
    public void _DEBUG() {

        String xq =
        "let $views := /ea:model/ea:views/ea:diagrams/ea:view "+
        "let $ret := ( "+
        "  for $vi in $views "+
        "  let $VCCbas := eaqt:execXq(concat('AMMP_VCCbas:vid=', $vi/@identifier/string())) "+
        "  let $VI := eaqt:execXq(concat('AMMP_VI:vid=', $vi/@identifier/string())) "+
        "  let $VA := 1 - eaqt:execNormalizer( concat('NT_3PL:xzero=4.0,k=1.5,raw=', $VCCbas) )"+
        "  let $d := $VI + $VA - 1 "+
        "  let $zop := if($d < 0) then 1 else 0 "+
        "  order by $d "+
        "  return <tmp view='{$vi/ea:name/string()}' id='{$vi/@identifier/string()}' ZOP='{$zop}' D='{$d}' VI='{$VI}' VA='{$VA}' /> "+
        ") "+
        "return $ret";

/*
        "(:@=INC_maxElemImportance:)"+
        "let $elems := /ea:model/ea:elements/ea:element "+
        "let $rels := /ea:model/ea:relationships/ea:relationship "+
        "return (avg("+
        "  for $el in $elems "+
        "  let $elemtype := $el/@xsi:type/string() "+
        "  (: let $elem_weight := eaqt:execXq(concat('HLP_getElementWeightByType:elemtype=', $elemtype)) :) "+
        "  (:@=INC_getElementWeightByType:)"+
        "  let $elem_weight := $INC_getElementWeightByType "+
        "  let $outbound := $rels[@source=$el/@identifier]/@xsi:type/string() "+
        "  let $degree := count($outbound) "+
        "  let $omega := math:exp($degree div 3.5) - 1 "+
        "  return if(empty($outbound)) then 0 else $omega * ($elem_weight * (sum( "+
        "    for $reltype in $outbound "+
        "    (: return eaqt:execXq(concat('HLP_getRelationshipWeightByType:reltype=', $reltype)) :) "+
        "    (:@=INC_getRelationshipWeightByType:)"+
        "    return $INC_getRelationshipWeightByType"+
        "  ) div count($outbound)))"+
        "), $INC_maxElemImportance)";
*/

/*
        "(:@=INC_maxElemImportance:)"+
        "let $omega_k := 3.5 "+
        "let $elems := /ea:model/ea:elements/ea:element "+
        "let $rels := /ea:model/ea:relationships/ea:relationship "+
        "let $ret := ( "+
        "  for $el in $elems "+
//        "  order by $el/@xsi:type/string(), $el/ea:name/string() "+
        "  let $elemtype := $el/@xsi:type/string() "+
        "  (: let $elem_weight := eaqt:execXq(concat('HLP_getElementWeightByType:elemtype=', $elemtype)) :) "+
        "  (:@=INC_getElementWeightByType:)"+
        "  let $elem_weight := $INC_getElementWeightByType "+
        "  let $outbound := $rels[@source=$el/@identifier]/@xsi:type/string() "+
        "  let $degree := count($outbound) "+
//        "  let $omega := ( 20.0 div ( 1.0 + math:exp( -($degree div 10) ) ) ) - 10.0 "+
        "  let $omega := math:exp($degree div $omega_k) - 1 "+
        "  let $i_of_e := if(empty($outbound)) then 0 else $elem_weight * (sum( "+
        "    for $reltype in $outbound "+
        "    (: return eaqt:execXq(concat('HLP_getRelationshipWeightByType:reltype=', $reltype)) :) "+
        "    (:@=INC_getRelationshipWeightByType:) "+
        "    return $INC_getRelationshipWeightByType "+
        "  ) div count($outbound)) "+
        "  let $rating := $omega * $i_of_e "+
        "  let $norm := eaqt:execNormalizer( concat('NT_3PL:xzero=45.0,k=0.025,raw=', $rating) ) "+
        "  order by $rating descending "+
        "  return <tmp elem='{$el/ea:name/string()}' type='{$el/@xsi:type/string()}' rating='{$rating}' norm='{$norm}' outdeg='{$degree}' IE='{$i_of_e}' omega='{$omega}' maxIE='{$INC_maxElemImportance}' /> "+
        ") "+
        "return $ret";
*/

/*
        "let $isolation_ratio_threshold := 0.66 "+
        "let $views := /ea:model/ea:views/ea:diagrams/ea:view "+
        "let $ret := ( "+
        "  for $vi in $views "+
        "  order by $vi/ea:name/string() "+
        "  let $vielem := $vi/descendant-or-self::ea:node[@xsi:type='Element']/@elementRef "+
        "  let $num_isolated_nodes := eaqt:execXq(concat('AMMP_countIsolatedElementsInView:vid=', $vi/@identifier/string())) "+
        "  let $isolation_ratio := if(count($vielem)=0) then 0 else $num_isolated_nodes div count($vielem) "+
        "  return if(count($vielem)<3 or $isolation_ratio>=$isolation_ratio_threshold) then $vi/ea:name/string() else () "+
        ") "+
        "return $ret";
 */
/*
        "let $views := /ea:model/ea:views/ea:diagrams/ea:view "+
        "for $vi in $views "+
        "order by $vi/ea:name/string() "+
        "let $VCCbas := eaqt:execXq(concat('AMMP_VCCbas:vid=', $vi/@identifier/string())) "+
        "let $VCCnorm := eaqt:execNormalizer( concat('NT_3PL:xzero=4.0,k=1.5,raw=', $VCCbas) ) "+
        "return if($VCCbas > 5) then <tmp view='{$vi/ea:name/string()}' val='{$VCCnorm}' /> else ()";
 */
/*
        "let $views := eaqt:execXq('BAS_getViews') "+
        "let $Mvcc := eaqt:execXq('AMMP_VCCoverall') "+
        "let $Vvcc := avg( "+
        "  for $vi in $views "+
        "  let $VCCbas := eaqt:execXq(concat('AMMP_VCCbas:vid=', $vi)) "+
        "  return $VCCbas "+
        ") "+
        "return ($Mvcc, $Vvcc)";
 */
/*
        "let $threshold := 0.625 "+
        "let $views := /ea:model/ea:views/ea:diagrams/ea:view "+
        "let $basicview := eaqt:execEaMeta('getBasicViews') "+
        "let $ret := ("+
        "  for $vid in $views "+
        "  order by $vid "+
        "  let $unique := eaqt:execXq(concat('AMMP_getUniqueElementTypesInView:vid=', $vid/@identifier/string())) "+
        "  return <tmp view='{$vid/ea:name/string()}'>{ "+
        "    for $bas in $basicview "+
        "    let $allowed := eaqt:execEaMeta(concat('getElementTypesByBasicView:id=', $bas)) "+
        "    (:@=INC_VICLbas:) "+
        "    order by $INC_VICLbas descending "+
        //"    return if($INC_VICLbas >= $threshold) then <vals view='{$vid/ea:name/string()}' val='{$INC_VICLbas}' /> else () "+
        "    return <vals basicview='{$bas}' val='{$INC_VICLbas}' /> "+
        "  }</tmp>\n"+
        ")"+
        "return $ret";
*/
/*
        "let $threshold := 0.625 "+
        "let $views := /ea:model/ea:views/ea:diagrams/ea:view "+
        "let $basicview := eaqt:execEaMeta('getBasicViews') "+
        "let $ret := ("+
        "  for $bas in $basicview "+
        "  order by $bas "+
        "  let $allowed := eaqt:execEaMeta(concat('getElementTypesByBasicView:id=', $bas)) "+
        "  return <tmp basicview='{$bas}'>{ "+
        "    for $vid in $views "+
        "    let $unique := eaqt:execXq(concat('AMMP_getUniqueElementTypesInView:vid=', $vid/@identifier/string())) "+
        "    (:@=INC_VICLbas:) "+
        "    order by $INC_VICLbas descending "+
        //"    return if($INC_VICLbas >= $threshold) then <vals view='{$vid/ea:name/string()}' val='{$INC_VICLbas}' /> else () "+
        "    return <vals view='{$vid/ea:name/string()}' val='{$INC_VICLbas}' /> "+
        "  }</tmp>\n"+
        ")"+
        "return $ret";
*/
/*
        "let $stakeholders := eaqt:execEaMeta('getStakeholderRoles') "+
        "let $basicview := eaqt:execEaMeta('getBasicViews') "+
        "let $views := eaqt:execXq('BAS_getViews') "+
        "let $count := (distinct-values("+
        "  for $bvi in distinct-values("+
        "    for $vi in $views "+
        "    let $covered := eaqt:execXq(concat('AMMP_basicViewsCoveredByView:vid=', $vi)) "+
        "    return $covered[. = $basicview]"+
        "  )"+
        //"  return eaqt:execEaMeta(concat('getStakeholderRolesByBasicView:id=', $bvi))"+
                "return $bvi"+
        ")) "+
        "return $count";
 */
/*
        "  let $serving_BA := eaqt:execXq('AMMP_CountIsZero_Rel-By-Type_Between_Layers-By-Type:reltype=Serving,layertype1=Business,layertype2=Application') "+
        "  let $serving_AT := eaqt:execXq('AMMP_CountIsZero_Rel-By-Type_Between_Layers-By-Type:reltype=Serving,layertype1=Application,layertype2=Technology') "+
        "  let $serving_BT := eaqt:execXq('AMMP_CountIsZero_Rel-By-Type_Between_Layers-By-Type:reltype=Serving,layertype1=Business,layertype2=Technology') "+
        "  let $realization_AB := eaqt:execXq('AMMP_CountIsZero_Rel-By-Type-And-Dir_Between_Layers-By-Type:reltype=Realization,layertype_source=Application,layertype_target=Business') "+
        "  let $realization_TA := eaqt:execXq('AMMP_CountIsZero_Rel-By-Type-And-Dir_Between_Layers-By-Type:reltype=Realization,layertype_source=Technology,layertype_target=Application') "+
        "  let $realization_TB := eaqt:execXq('AMMP_CountIsZero_Rel-By-Type-And-Dir_Between_Layers-By-Type:reltype=Realization,layertype_source=Technology,layertype_target=Business') "+
        "  return (concat('serving_BA: ', $serving_BA), concat('serving_AT: ', $serving_AT), concat('serving_BT: ', $serving_BT), concat('realization_AB: ', $realization_AB), concat('realization_TA: ', $realization_TA), concat('realization_TB: ', $realization_TB))";
*/
/*
        "let $views := /ea:model/ea:views/ea:diagrams/ea:view "+
        "let $res := ("+
        "  for $vi in $views "+
        "  let $max := eaqt:execXq(concat('AMMP_getMaxClosenessOfViewToBasicType:vid=', $vi/@identifier/string())) "+
        "  order by $vi/ea:name/string() "+
        "  return <tmp view='{$vi/ea:name/string()}' closeness='{$max}' /> "+
        //"   return if($max>=0.625) then $vi/ea:name/string() else () "+
        ") "+
        "return $res";

 */
/*
        "let $views_ne := /ea:model/ea:views/ea:diagrams/ea:view[count(descendant::ea:node[@xsi:type='Element'])>0] "+
        "  for $vi in $views_ne "+
        "  order by $vi/ea:name/string() "+
        "  let $VCCbas := eaqt:execXq(concat('AMMP_VCCbas:vid=', $vi/@identifier/string())) "+
        "  let $VI := eaqt:execXq(concat('AMMP_VI:vid=', $vi/@identifier/string())) "+
        "  let $VA := 1 - eaqt:execNormalizer( concat('NT_3PL:xzero=4.0,k=1.5,raw=', $VCCbas) )"+
        //"  return if($VI<1 and $VI>=0.9 and $VA>=0.95) then $vi else () "+
        "  return <tmp view='{$vi/ea:name/string()}' VI='{$VI}' VA='{$VA}' VCCbas='{$VCCbas}' /> ";
        //"return ( avg($ret/@VI), avg($ret/@VA) )";
*/
/*
        "let $views := eaqt:execXq('BAS_getViews') "+
        "let $Mvcc := eaqt:execXq('AMMP_VCCoverall') "+
        "let $Vvcc := avg( "+
        "  for $vi in $views "+
        "  let $VCCbas := eaqt:execXq(concat('AMMP_VCCbas:vid=', $vi)) "+
        "  return $VCCbas "+
        ") "+
        "return ($Mvcc, $Vvcc)";
*/
/*
        "let $views := eaqt:execXq('BAS_getViews') "+
        "for $vi in $views "+
        "let $VCCbas := eaqt:execXq(concat('AMMP_VCCbas:vid=', $vi)) "+
        "let $VI := eaqt:execXq(concat('AMMP_VI:vid=', $vi)) "+
        "let $VA := 1 - eaqt:execNormalizer( concat('NT_3PL:xzero=4.0,k=1.5,raw=', $VCCbas) )"+
        "return if($VI + $VA - 1 < 0) then ($vi, $VI + $VA - 1, $VI, $VA, $VCCbas) else ()";
*/
/*
        String xq =
        "let $elemtype := 'TechnologyFunction' "+
        "let $layer_id := eaqt:execEaMeta(concat('getLayerCategoryByElementType:id=', $elemtype)) "+
        "return max("+
        "  for $id in $layer_id "+
        "  let $layer_weight := eaqt:execEaMeta(concat('getLayerWeightById:"+XQEAModelRepository.PARAM_ATTRIB+"=value,id=', $id)) "+
        "  return $layer_weight "+
        ")";
*/
/*
        String xq =
        "let $lcats := eaqt:execEaMeta('getLayerCategories') "+
        "let $acats := eaqt:execEaMeta('getAspectCategories') "+
        "let $rtypes := eaqt:execEaMeta('getRelationshipTypes') "+
        "let $lmax := max("+
        "  for $lcat in $lcats "+
        "  return xs:integer( eaqt:execEaMeta(concat('getLayerWeightById:@attrib=value,id=', $lcat)) ) "+
        ")"+
        "let $amax := max("+
        "  for $acat in $acats "+
        "  return xs:integer( eaqt:execEaMeta(concat('getAspectWeightById:@attrib=value,id=', $acat)) ) "+
        ")"+
        "let $rmax := max("+
        "  for $rtype in $rtypes "+
        "  return xs:integer( eaqt:execEaMeta(concat('getRelationshipWeightById:@attrib=value,id=', $rtype)) ) "+
        ")"+
        "let $maxweight := ( xs:integer($lmax) + xs:integer($amax) ) * xs:integer($rmax)"+
        "return $maxweight";
*/
/*
        String xq =
        "let $lcats := eaqt:execEaMeta('getLayerCategories') "+
        "let $acats := eaqt:execEaMeta('getAspectCategories') "+
        "let $rtypes := eaqt:execEaMeta('getRelationshipTypes') "+
        "let $lmax := max("+
        "  for $lcat in $lcats "+
        "  return xs:integer( eaqt:execEaMeta(concat('getLayerWeightById:@attrib=value,id=', $lcat)) ) "+
        ")"+
        "let $amax := max("+
        "  for $acat in $acats "+
        "  return xs:integer( eaqt:execEaMeta(concat('getAspectWeightById:@attrib=value,id=', $acat)) ) "+
        ")"+
        "let $rmax := max("+
        "  for $rtype in $rtypes "+
        "  return xs:integer( eaqt:execEaMeta(concat('getRelationshipWeightById:@attrib=value,id=', $rtype)) ) "+
        ")"+
        "let $maxweight := ( xs:integer($lmax) + xs:integer($amax) ) * xs:integer($rmax)"+
        "let $elems := /ea:model/ea:elements/ea:element "+
        "let $rels := /ea:model/ea:relationships/ea:relationship "+
        "return avg("+
        "  for $el in $elems "+
        "  let $elemtype := $el/@xsi:type/string() "+
        "  let $layer_id := eaqt:execEaMeta(concat('getLayerCategoryByElementType:id=', $elemtype)) "+
        "  let $aspect_id := eaqt:execEaMeta(concat('getAspectCategoryByElementType:id=', $elemtype)) "+
        "  let $layer_weight := max( "+
        "    for $id in $layer_id "+
        "    let $layer_weight := eaqt:execEaMeta(concat('getLayerWeightById:@attrib=value,id=', $id)) "+
        "    return $layer_weight "+
        "  ) "+
        "  let $aspect_weight := eaqt:execEaMeta(concat('getAspectWeightById:@attrib=value,id=', $aspect_id)) "+
        "  let $elem_weight := xs:integer($layer_weight) + xs:integer($aspect_weight) "+
        "  let $outbound := $rels[@source=$el/@identifier]/@xsi:type/string() "+
        "  return if(empty($outbound)) then 0 else $elem_weight * (sum( "+
        "    for $r in $outbound "+
        "    let $rel_id := eaqt:execEaMeta(concat('getRelationshipTypeById:id=', $r)) "+
        "    let $rel_weight := eaqt:execEaMeta(concat('getRelationshipWeightById:@attrib=value,id=', $rel_id)) "+
        "    return xs:integer($rel_weight) "+
        "  ) div count($outbound))"+
        ") div $maxweight";
*/
/*
        String xq =
        "let $stakeholders := eaqt:execEaMeta('getStakeholderRoles') "+
        "let $basicvp := eaqt:execEaMeta('getBasicViews') "+
        "let $views := eaqt:execXq('BAS_getViews') "+
        "return count(distinct-values("+
        "  for $bvp in distinct-values("+
        "    for $vp in $views "+
        "    let $covered := eaqt:execXq(concat('AMMP_basicVpsCoveredByVp:vpid=', $vp)) "+
        "    return $covered[. = $basicvp]"+
        "  )"+
        "  return eaqt:execEaMeta(concat('getStakeholderRolesByBasicView:id=', $bvp))"+
        ")) div count($stakeholders)";
*/

/*
        String xq =
        "let $threshold := 0.75 "+
        "let $views := eaqt:execXq('BAS_getViews') "+
        "return sum("+
        "  for $vp in $views "+
        "  let $max := eaqt:execXq(concat('AMMP_getMaxClosenessOfVpToBasicType:vpid=', $vp)) "+
        "  return if($max>=$threshold) then 1 else 0 "+
        ") div count($views)";

*/
/*
        String xq =
        "let $threshold := 0.75 "+
        "let $views := eaqt:execXq('BAS_getViews') "+
        "return sum("+
        "  for $vp in $views "+
        "  let $max := eaqt:execXq(concat('AMMP_getMaxClosenessOfVpToBasicType:vpid=', $vp)) "+
        "  return if($max>=$threshold) then 1 else 0 "+
        ") div count($views)";
*/
/*
        String xq =
        "let $views := eaqt:execXq('BAS_getViews') "+
        "let $threshold := 0.75 "+
        "let $basicvp := eaqt:execEaMeta('getBasicViews') "+
        "return distinct-values("+
        "for $vp in $views "+
        "let $unique := eaqt:execXq(concat('AMMP_getUniqueElementTypesInVp:vpid=', $vp)) "+
        "return ("+
        "  for $bas in $basicvp "+
        "  let $allowed := eaqt:execEaMeta(concat('getElementTypesByBasicView:id=', $bas)) "+
        "  let $na := $unique[not(. = $allowed)] "+
        "  let $an := $allowed[not(. = ($allowed[. = $unique]) )] "+
        "  let $conformity := if (empty($unique)) then 0 else 1 - (count($na) div count($unique)) "+
        "  let $rateofuse := if (empty($unique)) then 0 else 1 - (count($an) div count($allowed)) "+
        "  let $close := ($conformity + $rateofuse) div 2 "+
        "  return if($close >= $threshold) then $bas else ''"+
        ")[. = $basicvp]"+
        ")";

*/
/*
        String xq =
        "let $basicvp := eaqt:execEaMeta('getBasicViews') "+
        "let $views := eaqt:execXq('BAS_getViews') "+
        "return distinct-values("+
        "  for $vp in $views "+
        "  let $covered := eaqt:execXq(concat('AMMP_isCoveredByVp:vpid=', $vp)) "+
        "  return $covered[. = $basicvp]"+
        ")";

*/
/*
        String xq =
        "let $unique := ('Driver', 'Goal', 'Assessment', 'Stakeholder') (: ArchiMetal \"id-17789\" :)" +
        "let $basicvp := eaqt:execEaMeta('getBasicViews') " +
        "  for $bas in $basicvp "+
        "  let $allowed := eaqt:execEaMeta(concat('getElementTypesByBasicView:id=', $bas)) "+
        "  let $na := $unique[not(. = $allowed)] "+
        "  let $an := $allowed[not(. = ($allowed[. = $unique]) )] "+
        "  let $conformity := if (empty($unique)) then 0 else 1 - (count($na) div count($unique)) "+
        "  let $rateofuse := if (empty($unique)) then 0 else 1 - (count($an) div count($allowed)) "+
        "  return concat($bas, ': ', $conformity, ', ', $rateofuse)";

*/
/*
        String xq =
        "let $views := eaqt:execXq('BAS_getViews') "+
        "for $vp in $views "+
        "let $retval := eaqt:execXq(concat('AMMP_getClosenessOfVpToBasicType:vpid=', $vp)) "+
        "return concat ($vp, ': ', $retval)";

 */
/*
        String xq =
        "let $basicvp := eaqt:execEaMeta('getBasicViews') " +
        "let $views := eaqt:execXq('BAS_getViews') "+
        "return ("+
        "  for $vp in $views "+
        "    let $unique := eaqt:execXq(concat('AMMP_getUniqueElementTypesInVp:vpid=', $vp)) "+
        "    return ("+
        "      for $bas in $basicvp "+
        "      let $allowed := eaqt:execEaMeta(concat('getElementTypesByBasicView:id=', $bas)) "+
        "      return concat($vp, ' - ', $bas, ' - ', string-join($allowed, ', '))" +
        "  )"+
        ")";

 */
/*
    String xq =
        "let $views := eaqt:execXq('BAS_getViews') " +
        "  for $vp in $views " +
        "  let $unique := eaqt:execXq(concat('AMMP_getUniqueElementTypesInVp:vpid=', $vp)) " +
        "return concat($vp, ': ', string-join($unique, ', '))";
*/
        XMLFileReaderFactory filereader_factory = new XMLFileReaderFactory(EAQTHelper.getResourceDirectory() + "ArchiMetal.xml");
        XMLFileReader reader = filereader_factory.getReader();
        XQEAModelRepository repo = XQEAModelRepository.getInstance(reader);
        XQSpecificationFactory spec_factory = new XQSpecificationFactory();
        spec_factory.loadParam(xq);
        XQSpecification spec = spec_factory.getSpecification();
        List<XdmItem> results = repo.query(spec);

        System.out.println();
    }


    private MetricLog makeALog() {
        MetricLog log = new MetricLog();
        log.setDocType("jsonts");
        log.setVersion("1.0");
        log.setDataSourceName("/just/a/path/dummy.xml");
        log.setDataSourceId("1234567890");
        Header header = new Header("datasetName", "datasetDescription", "1.0", "1955-11-12T18:38:00", "1955-11-12T18:38:00", "2015-10-21T19:28:00", "dataSource", "dataSourceVersion");
        log.setHeader(header);
        Globals globals = new Globals("0.25", "0.75", "0.1", "0.1", "0.0", "1.0");
        log.setGlobals(globals);
        List<DataProvider> metrics = new ArrayList<>();
        metrics.add(new DataProvider("M_ratio-test", "Ratio Test", "providerDescription", "providerVersion", "1955-11-12", "providerMetaHook", "providerNormalizer", "providerScaleMin", "providerScaleMax", "SCALE_MIN", "0.5", "0.75"));
        metrics.add(new DataProvider("M_ratio-test-2", "Ratio Test 2", "providerDescription", "providerVersion", "1955-11-12", "providerMetaHook", "providerNormalizer", "providerScaleMin", "providerScaleMax", "SCALE_MAX", "0.65", "0.2"));
        log.setDataProvider(metrics);
        List<List<String>> payload = null;
        List<Datum> data = new ArrayList<>();
        Datum d1 = new Datum();
        payload = new ArrayList<>();
        payload.add(new ArrayList<String>(Arrays.asList("M_ratio-test", "11", "0.84")));
        payload.add(new ArrayList<String>(Arrays.asList("M_ratio-test-2", "4", "0.22")));
        d1.setDataTs("1955-11-12T18:38:00");
        d1.setDataP(payload);
        data.add(d1);
        Datum d2 = new Datum();
        payload = new ArrayList<>();
        payload.add(new ArrayList<String>(Arrays.asList("M_ratio-test", "13.5", "0.04")));
        payload.add(new ArrayList<String>(Arrays.asList("M_ratio-test-2", "0", "0.0")));
        d2.setDataTs("1985-10-26T21:00:00");
        d2.setDataP(payload);
        data.add(d2);
        Datum d3 = new Datum();
        payload = new ArrayList<>();
        payload.add(new ArrayList<String>(Arrays.asList("M_ratio-test", "3", "0.77")));
        payload.add(new ArrayList<String>(Arrays.asList("M_ratio-test-2", "4", "0.32")));
        d3.setDataTs("2015-10-21T19:28:00");
        d3.setDataP(payload);
        data.add(d3);
        log.setData(data);
        return log;
    }

}




