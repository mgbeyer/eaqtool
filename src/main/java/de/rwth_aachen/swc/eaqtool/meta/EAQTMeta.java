package de.rwth_aachen.swc.eaqtool.meta;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.rwth_aachen.swc.eaqtool.EAQTConf;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for meta model POJOs and helper methods.
 */
public class EAQTMeta {

    private static EAQTMeta instance;
    private static QualityMeta qmeta;
    private static EaMeta eameta;

    /**
     * Bootstrap loader for EA meta and quality meta models.
     */
    private EAQTMeta(String eametaFile, String qmetaFile) {
        if(eametaFile==null) eametaFile = EAQTHelper.getResourceDirectory() + EAQTConf.REL_DIR_EA_META;
        if(qmetaFile==null) qmetaFile = EAQTHelper.getResourceDirectory() + EAQTConf.REL_DIR_QUALITY_META;
        File quality_meta_file = new File(qmetaFile);
        File ea_meta_file = new File(eametaFile);
        ObjectMapper mapper = new ObjectMapper();
        try {
            QualityMeta quality_meta = mapper.readValue(quality_meta_file, QualityMeta.class);
            qmeta= quality_meta;
            EaMeta ea_meta = mapper.readValue(ea_meta_file, EaMeta.class);
            eameta= ea_meta;
        } catch (IOException ioex) {
            System.err.println(ioex);
        }
    }

    /**
     * Lazy singleton, see constructor.
     * @return Initialized EAQTMeta instance.
     */
    public static EAQTMeta getInstance(){
        if (instance == null) {
            instance = new EAQTMeta(null, null);
        }
        return instance;
    }

    /**
     * Lazy singleton, see constructor.
     * @return Initialized EAQTMeta instance.
     */
    public static EAQTMeta getInstance(String eametaFile, String qmetaFile){
        if (instance == null) {
            instance = new EAQTMeta(eametaFile, qmetaFile);
        }
        return instance;
    }

    /**
     * Direct access to POJO for quality meta model.
     * @return QualityMeta instance.
     */
    public QualityMeta getQualityMeta() {
        return qmeta;
    }

    /**
     * Direct access to POJO for ea meta model.
     * @return EAMeta instance.
     */
    public EaMeta getEaMeta() {
        return eameta;
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


    /**
     * Return weight of element by element type (layer weight + aspect weight)
     * @param type Type of element.
     * @return weight.
     */
    public Integer getElementWeightByType(String type) {
        Integer ret = 0;

        String layer = getLayerCategoryByElementType(type)!=null ? getLayerCategoryByElementType(type).get(0).getId() : "";
        String aspect = getAspectCategoryByElementType(type)!=null ? getAspectCategoryByElementType(type).get(0).getId() : "";
        if (layer.compareTo("")!=0 && aspect.compareTo("")!=0) {
            ret = getLayerWeightById(layer).get(0).getValue() + getAspectWeightById(aspect).get(0).getValue();
        }

        return ret;
    }

    /**
     * Return weight of relationship by element type
     * @param type Type of relationship.
     * @return weight.
     */
    public Integer getRelationshipWeightByType(String type) {
        Integer ret = 0;

        String rel = getRelationshipTypeById(type)!=null ? getRelationshipTypeById(type).get(0).getId() : "";
        if (rel.compareTo("")!=0) {
            ret = getRelationshipWeightById(rel).get(0).getValue();
        }

        return ret;
    }


    /* wrapper methods: Get entity list */

    /**
     * @return List of Stakeholder Roles.
     */
    public List<QmStakeholderRole> getStakeholderRoles() {
        return qmeta.getQmStakeholderRole();
    }

    /**
     * @return List of Quality Requirement Categories.
     */
    public List<QmQualityRequirementCategory> getQualityRequirementCategories() {
        return qmeta.getQmQualityRequirementCategory();
    }

    /**
     * @return List of Measurable Quality Characteristics.
     */
    public List<QmMeasurableQualityCharacteristic> getMeasurableQualityCharacteristics() {
        return qmeta.getQmMeasurableQualityCharacteristic();
    }

    /**
     * @return List of Layer Categories.
     */
    public List<EamLayerCategory> getLayerCategories() {
        return eameta.getEamLayerCategory();
    }

    /**
     * @return List of Aspect Categories.
     */
    public List<EamAspectCategory> getAspectCategories() {
        return eameta.getEamAspectCategory();
    }

    /**
     * @return List of Relationship Categories.
     */
    public List<EamRelationshipCategory> getRelationshipCategories() {
        return eameta.getEamRelationshipCategory();
    }

    /**
     * @return List of Element Types.
     */
    public List<EamElementType> getElementTypes() {
        return eameta.getEamElementType();
    }

    /**
     * @return List of Relationship Types.
     */
    public List<EamRelationshipType> getRelationshipTypes() {
        return eameta.getEamRelationshipType();
    }

    /**
     * @return List of Basic Views.
     */
    public List<EamBasicView> getBasicViews() {
        return eameta.getEamBasicView();
    }

    /**
     * @return List of View Categories.
     */
    public List<EamViewCategory> getViewCategories() {
        return eameta.getEamViewCategory();
    }

    /**
     * @return List of View Scopes.
     */
    public List<EamViewScope> getViewScopes() {
        return eameta.getEamViewScope();
    }

    /**
     * @return List of View Purposes.
     */
    public List<EamViewPurpose> getViewPurposes() {
        return eameta.getEamViewPurpose();
    }



    /* wrapper methods: Get entity by ID */

    /**
     * @return Quality Requirement Category by id.
     */
    public List<QmQualityRequirementCategory> getQualityRequirementCategoryById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(QmQualityRequirementCategory.class, qmeta, id));
        return ret;
    }

    /**
     * @return Measurable Quality Characteristic by id.
     */
    public List<QmMeasurableQualityCharacteristic> getMeasurableQualityCharacteristicById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(QmMeasurableQualityCharacteristic.class, qmeta, id));
        return ret;
    }

    /**
     * @return Stakeholder Role by id.
     */
    public List<QmStakeholderRole> getStakeholderRoleById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(QmStakeholderRole.class, qmeta, id));
        return ret;
    }

    /**
     * @return Layer Category by id.
     */
    public List<EamLayerCategory> getLayerCategoryById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamLayerCategory.class, eameta, id));
        return ret;
    }

    /**
     * @return Aspect Category by id.
     */
    public List<EamAspectCategory> getAspectCategoryById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamAspectCategory.class, eameta, id));
        return ret;
    }

    /**
     * @return Relationship Category by id.
     */
    public List<EamRelationshipCategory> getRelationshipCategoryById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamRelationshipCategory.class, eameta, id));
        return ret;
    }

    /**
     * @return Element Type by id.
     */
    public List<EamElementType> getElementTypeById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamElementType.class, eameta, id));
        return ret;
    }

    /**
     * @return Relationship Type by id.
     */
    public List<EamRelationshipType> getRelationshipTypeById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamRelationshipType.class, eameta, id));
        return ret;
    }

    /**
     * @return Basic View by id.
     */
    public List<EamBasicView> getBasicViewById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamBasicView.class, eameta, id));
        return ret;
    }

    /**
     * @return View Category by id.
     */
    public List<EamViewCategory> getViewCategoryById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamViewCategory.class, eameta, id));
        return ret;
    }

    /**
     * @return View Scope by id.
     */
    public List<EamViewScope> getViewScopeById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamViewScope.class, eameta, id));
        return ret;
    }

    /**
     * @return View Purpose by id.
     */
    public List<EamViewPurpose> getViewPurposeById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamViewPurpose.class, eameta, id));
        return ret;
    }

    /**
     * @return Relationship weight by id.
     */
    public List<EamRelationshipWeight> getRelationshipWeightById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamRelationshipWeight.class, eameta, id));
        return ret;
    }

    /**
     * @return Layer weight by id.
     */
    public List<EamLayerWeight> getLayerWeightById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamLayerWeight.class, eameta, id));
        return ret;
    }

    /**
     * @return Aspect weight by id.
     */
    public List<EamAspectWeight> getAspectWeightById(String id) {
        List ret = new ArrayList();
        ret.add(getEntityById(EamAspectWeight.class, eameta, id));
        return ret;
    }


    /* wrapper methods: Get entities by entity ID */

    /**
     * @return List of Stakeholder Roles By Quality Requirement Category.
     */
    public List<QmStakeholderRole> getStakeholderRolesByQualityRequirementCategory(String id) {
        return getEntitiesByEntityId(QmStakeholderRole.class, qmeta.getQmLinkSrQrc(), qmeta,"SR", "QRC", id);
    }

    /**
     * @return List of Quality Requirement Categories By Stakeholder Role.
     */
    public List<QmQualityRequirementCategory> getQualityRequirementCategoriesByStakeholderRole(String id) {
        return getEntitiesByEntityId(QmQualityRequirementCategory.class, qmeta.getQmLinkSrQrc(), qmeta,"QRC", "SR", id);
    }

    /**
     * @return List of Quality Requirement Categories By Measurable Quality Characteristic.
     */
    public List<QmQualityRequirementCategory> getQualityRequirementCategoriesByMeasurableQualityCharacteristic(String id) {
        return getEntitiesByEntityId(QmQualityRequirementCategory.class, qmeta.getQmLinkQrcMqc(), qmeta,"QRC", "MQC", id);
    }

    /**
     * @return List of Measurable Quality Characteristic By Quality Requirement Category.
     */
    public List<QmMeasurableQualityCharacteristic> getMeasurableQualityCharacteristicsByQualityRequirementCategory(String id) {
        return getEntitiesByEntityId(QmMeasurableQualityCharacteristic.class, qmeta.getQmLinkQrcMqc(), qmeta,"MQC", "QRC", id);
    }

    /**
     * @return Layer Category By Element Type.
     */
    public List<EamLayerCategory> getLayerCategoryByElementType(String id) {
        return getEntitiesByEntityId(EamLayerCategory.class, eameta.getEamLinkLcEt(), eameta,"LC", "ET", id);
    }

    /**
     * @return Aspect Category By Element Type.
     */
    public List<EamAspectCategory> getAspectCategoryByElementType(String id) {
        return getEntitiesByEntityId(EamAspectCategory.class, eameta.getEamLinkAcEt(), eameta,"AC", "ET", id);
    }

    /**
     * @return Relationship Category By Relationship Type.
     */
    public List<EamRelationshipCategory> getRelationshipCategoryByRelationshipType(String id) {
        return getEntitiesByEntityId(EamRelationshipCategory.class, eameta.getEamLinkRtRc(), eameta,"RC", "RT", id);
    }

    /**
     * @return List of Element Types By Layer Category.
     */
    public List<EamElementType> getElementTypesByLayerCategory(String id) {
        return getEntitiesByEntityId(EamElementType.class, eameta.getEamLinkLcEt(), eameta,"ET", "LC", id);
    }

    /**
     * @return List of Element Types By Aspect Category.
     */
    public List<EamElementType> getElementTypesByAspectCategory(String id) {
        return getEntitiesByEntityId(EamElementType.class, eameta.getEamLinkAcEt(), eameta,"ET", "AC", id);
    }

    /**
     * @return List of Element Types By Basic View.
     */
    public List<EamElementType> getElementTypesByBasicView(String id) {
        return getEntitiesByEntityId(EamElementType.class, eameta.getEamLinkViEt(), eameta,"ET", "VI", id);
    }

    /**
     * @return Basic View by Element Type.
     */
    public EamBasicView getBasicViewByElementType(String id) {
        List<EamBasicView> res = getEntitiesByEntityId(EamBasicView.class, eameta.getEamLinkViEt(), eameta,"VI", "ET", id);
        return (res==null ? null : res.get(0));
    }

    /**
     * @return List of Basic Views by Stakeholder Role.
     */
    public List<EamBasicView> getBasicViewsByStakeholderRole(String id) {
        return getEntitiesByEntityId(EamBasicView.class, eameta.getEamLinkViSr(), eameta,"VI", "SR", id);
    }

    /**
     * @return List of Stakeholder Roles by Basic View.
     */
    public List<QmStakeholderRole> getStakeholderRolesByBasicView(String id) {
         return getEntitiesByEntityId(QmStakeholderRole.class, eameta.getEamLinkViSr(), qmeta,"SR", "VI", id);
    }

    /**
     * @return List of Relationship Types By Relationship Category.
     */
    public List<EamRelationshipType> getRelationshipTypesByRelationshipCategory(String id) {
        return getEntitiesByEntityId(EamRelationshipType.class, eameta.getEamLinkRtRc(), eameta,"RT", "RC", id);
    }

}
