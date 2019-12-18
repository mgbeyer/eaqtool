package de.rwth_aachen.swc.eaqtool.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Eam_RelationshipCategory",
        "Eam_RelationshipType",
        "Eam_RelationshipWeight",
        "Eam_RelationshipElementDependencyDirection",
        "Eam_Link_Rt_Rc",
        "Eam_Link_Rr_Redd",
        "Eam_LayerCategory",
        "Eam_LayerWeight",
        "Eam_AspectCategory",
        "Eam_AspectWeight",
        "Eam_Link_Lc_Et",
        "Eam_Link_Ac_Et",
        "Eam_ElementType",
        "Eam_ViewCategory",
        "Eam_ViewPurpose",
        "Eam_ViewScope",
        "Eam_BasicView",
        "Eam_Link_Vi_Vic",
        "Eam_Link_Vi_Vip",
        "Eam_Link_Vi_Vis",
        "Eam_Link_Vi_Sr",
        "Eam_Link_Vi_Et"
})
public class EaMeta implements Serializable
{

    @JsonProperty("Eam_RelationshipCategory")
    private List<EamRelationshipCategory> eamRelationshipCategory = new ArrayList<EamRelationshipCategory>();
    @JsonProperty("Eam_RelationshipType")
    private List<EamRelationshipType> eamRelationshipType = new ArrayList<EamRelationshipType>();
    @JsonProperty("Eam_RelationshipWeight")
    private List<EamRelationshipWeight> eamRelationshipWeight = null;
    @JsonProperty("Eam_RelationshipElementDependencyDirection")
    private List<EamRelationshipElementDependencyDirection> eamRelationshipElementDependencyDirection = new ArrayList<EamRelationshipElementDependencyDirection>();
    @JsonProperty("Eam_Link_Rt_Rc")
    private List<EamLinkRtRc> eamLinkRtRc = new ArrayList<EamLinkRtRc>();
    @JsonProperty("Eam_Link_Rr_Redd")
    private List<EamLinkRtRedd> eamLinkRtRedd = new ArrayList<EamLinkRtRedd>();
    @JsonProperty("Eam_LayerCategory")
    private List<EamLayerCategory> eamLayerCategory = new ArrayList<EamLayerCategory>();
    @JsonProperty("Eam_LayerWeight")
    private List<EamLayerWeight> eamLayerWeight = null;
    @JsonProperty("Eam_AspectCategory")
    private List<EamAspectCategory> eamAspectCategory = new ArrayList<EamAspectCategory>();
    @JsonProperty("Eam_AspectWeight")
    private List<EamAspectWeight> eamAspectWeight = null;
    @JsonProperty("Eam_Link_Lc_Et")
    private List<EamLinkLcEt> eamLinkLcEt = new ArrayList<EamLinkLcEt>();
    @JsonProperty("Eam_Link_Ac_Et")
    private List<EamLinkAcEt> eamLinkAcEt = new ArrayList<EamLinkAcEt>();
    @JsonProperty("Eam_ElementType")
    private List<EamElementType> eamElementType = new ArrayList<EamElementType>();
    @JsonProperty("Eam_ViewCategory")
    private List<EamViewCategory> eamViewCategory = new ArrayList<EamViewCategory>();
    @JsonProperty("Eam_ViewPurpose")
    private List<EamViewPurpose> eamViewPurpose = new ArrayList<EamViewPurpose>();
    @JsonProperty("Eam_ViewScope")
    private List<EamViewScope> eamViewScope = new ArrayList<EamViewScope>();
    @JsonProperty("Eam_BasicView")
    private List<EamBasicView> eamBasicView = new ArrayList<EamBasicView>();
    @JsonProperty("Eam_Link_Vi_Vic")
    private List<EamLinkViVic> eamLinkViVic = new ArrayList<EamLinkViVic>();
    @JsonProperty("Eam_Link_Vi_Vip")
    private List<EamLinkViVip> eamLinkViVip = new ArrayList<EamLinkViVip>();
    @JsonProperty("Eam_Link_Vi_Vis")
    private List<EamLinkViVis> eamLinkViVis = new ArrayList<EamLinkViVis>();
    @JsonProperty("Eam_Link_Vi_Sr")
    private List<EamLinkViSr> eamLinkViSr = new ArrayList<EamLinkViSr>();
    @JsonProperty("Eam_Link_Vi_Et")
    private List<EamLinkViEt> eamLinkViEt = new ArrayList<EamLinkViEt>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5691371214086376911L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EaMeta() {
    }

    /**
     *
     * @param eamLinkViVis
     * @param eamLayerCategory
     * @param eamLayerWeight
     * @param eamRelationshipType
     * @param eamRelationshipWeight
     * @param eamLinkLcEt
     * @param eamLinkAcEt
     * @param eamElementType
     * @param eamLinkViEt
     * @param eamLinkViVip
     * @param eamRelationshipElementDependencyDirection
     * @param eamLinkRtRc
     * @param eamViewPurpose
     * @param eamRelationshipCategory
     * @param eamLinkRtRedd
     * @param eamAspectCategory
     * @param eamAspectWeight
     * @param eamLinkViVic
     * @param eamViewScope
     * @param eamLinkViSr
     * @param eamViewCategory
     * @param eamBasicView
     */
    public EaMeta(List<EamRelationshipCategory> eamRelationshipCategory, List<EamRelationshipType> eamRelationshipType, List<EamRelationshipWeight> eamRelationshipWeight, List<EamRelationshipElementDependencyDirection> eamRelationshipElementDependencyDirection, List<EamLinkRtRc> eamLinkRtRc, List<EamLinkRtRedd> eamLinkRtRedd, List<EamLayerCategory> eamLayerCategory, List<EamLayerWeight> eamLayerWeight, List<EamAspectCategory> eamAspectCategory, List<EamAspectWeight> eamAspectWeight, List<EamLinkLcEt> eamLinkLcEt, List<EamLinkAcEt> eamLinkAcEt, List<EamElementType> eamElementType, List<EamViewCategory> eamViewCategory, List<EamViewPurpose> eamViewPurpose, List<EamViewScope> eamViewScope, List<EamBasicView> eamBasicView, List<EamLinkViVic> eamLinkViVic, List<EamLinkViVip> eamLinkViVip, List<EamLinkViVis> eamLinkViVis, List<EamLinkViSr> eamLinkViSr, List<EamLinkViEt> eamLinkViEt) {
        super();
        this.eamRelationshipCategory = eamRelationshipCategory;
        this.eamRelationshipType = eamRelationshipType;
        this.eamRelationshipElementDependencyDirection = eamRelationshipElementDependencyDirection;
        this.eamLinkRtRc = eamLinkRtRc;
        this.eamLinkRtRedd = eamLinkRtRedd;
        this.eamLayerCategory = eamLayerCategory;
        this.eamAspectCategory = eamAspectCategory;
        this.eamLinkLcEt = eamLinkLcEt;
        this.eamLinkAcEt = eamLinkAcEt;
        this.eamElementType = eamElementType;
        this.eamViewCategory = eamViewCategory;
        this.eamViewPurpose = eamViewPurpose;
        this.eamViewScope = eamViewScope;
        this.eamBasicView = eamBasicView;
        this.eamLinkViVic = eamLinkViVic;
        this.eamLinkViVip = eamLinkViVip;
        this.eamLinkViVis = eamLinkViVis;
        this.eamLinkViSr = eamLinkViSr;
        this.eamLinkViEt = eamLinkViEt;
        this.eamRelationshipWeight = eamRelationshipWeight;
        this.eamLayerWeight = eamLayerWeight;
        this.eamAspectWeight = eamAspectWeight;
    }

    @JsonProperty("Eam_RelationshipCategory")
    public List<EamRelationshipCategory> getEamRelationshipCategory() {
        return eamRelationshipCategory;
    }

    @JsonProperty("Eam_RelationshipCategory")
    public void setEamRelationshipCategory(List<EamRelationshipCategory> eamRelationshipCategory) {
        this.eamRelationshipCategory = eamRelationshipCategory;
    }

    public EaMeta withEamRelationshipCategory(List<EamRelationshipCategory> eamRelationshipCategory) {
        this.eamRelationshipCategory = eamRelationshipCategory;
        return this;
    }

    @JsonProperty("Eam_RelationshipType")
    public List<EamRelationshipType> getEamRelationshipType() {
        return eamRelationshipType;
    }

    @JsonProperty("Eam_RelationshipType")
    public void setEamRelationshipType(List<EamRelationshipType> eamRelationshipType) {
        this.eamRelationshipType = eamRelationshipType;
    }

    public EaMeta withEamRelationshipType(List<EamRelationshipType> eamRelationshipType) {
        this.eamRelationshipType = eamRelationshipType;
        return this;
    }

    @JsonProperty("Eam_RelationshipWeight")
    public List<EamRelationshipWeight> getEamRelationshipWeight() {
        return eamRelationshipWeight;
    }

    @JsonProperty("Eam_RelationshipWeight")
    public void setEamRelationshipWeight(List<EamRelationshipWeight> eamRelationshipWeight) {
        this.eamRelationshipWeight = eamRelationshipWeight;
    }

    public EaMeta withEamRelationshipWeight(List<EamRelationshipWeight> eamRelationshipWeight) {
        this.eamRelationshipWeight = eamRelationshipWeight;
        return this;
    }

    @JsonProperty("Eam_RelationshipElementDependencyDirection")
    public List<EamRelationshipElementDependencyDirection> getEamRelationshipElementDependencyDirection() {
        return eamRelationshipElementDependencyDirection;
    }

    @JsonProperty("Eam_RelationshipElementDependencyDirection")
    public void setEamRelationshipElementDependencyDirection(List<EamRelationshipElementDependencyDirection> eamRelationshipElementDependencyDirection) {
        this.eamRelationshipElementDependencyDirection = eamRelationshipElementDependencyDirection;
    }

    public EaMeta withEamRelationshipElementDependencyDirection(List<EamRelationshipElementDependencyDirection> eamRelationshipElementDependencyDirection) {
        this.eamRelationshipElementDependencyDirection = eamRelationshipElementDependencyDirection;
        return this;
    }

    @JsonProperty("Eam_Link_Rt_Rc")
    public List<EamLinkRtRc> getEamLinkRtRc() {
        return eamLinkRtRc;
    }

    @JsonProperty("Eam_Link_Rt_Rc")
    public void setEamLinkRtRc(List<EamLinkRtRc> eamLinkRtRc) {
        this.eamLinkRtRc = eamLinkRtRc;
    }

    public EaMeta withEamLinkRtRc(List<EamLinkRtRc> eamLinkRtRc) {
        this.eamLinkRtRc = eamLinkRtRc;
        return this;
    }

    @JsonProperty("Eam_Link_Rr_Redd")
    public List<EamLinkRtRedd> getEamLinkRtRedd() {
        return eamLinkRtRedd;
    }

    @JsonProperty("Eam_Link_Rr_Redd")
    public void setEamLinkRtRedd(List<EamLinkRtRedd> eamLinkRtRedd) {
        this.eamLinkRtRedd = eamLinkRtRedd;
    }

    public EaMeta withEamLinkRrRedd(List<EamLinkRtRedd> eamLinkRtRedd) {
        this.eamLinkRtRedd = eamLinkRtRedd;
        return this;
    }

    @JsonProperty("Eam_LayerCategory")
    public List<EamLayerCategory> getEamLayerCategory() {
        return eamLayerCategory;
    }

    @JsonProperty("Eam_LayerCategory")
    public void setEamLayerCategory(List<EamLayerCategory> eamLayerCategory) {
        this.eamLayerCategory = eamLayerCategory;
    }

    public EaMeta withEamLayerCategory(List<EamLayerCategory> eamLayerCategory) {
        this.eamLayerCategory = eamLayerCategory;
        return this;
    }

    @JsonProperty("Eam_LayerWeight")
    public List<EamLayerWeight> getEamLayerWeight() {
        return eamLayerWeight;
    }

    @JsonProperty("Eam_LayerWeight")
    public void setEamLayerWeight(List<EamLayerWeight> eamLayerWeight) {
        this.eamLayerWeight = eamLayerWeight;
    }

    public EaMeta withEamLayerWeight(List<EamLayerWeight> eamLayerWeight) {
        this.eamLayerWeight = eamLayerWeight;
        return this;
    }

    @JsonProperty("Eam_AspectCategory")
    public List<EamAspectCategory> getEamAspectCategory() {
        return eamAspectCategory;
    }

    @JsonProperty("Eam_AspectCategory")
    public void setEamAspectCategory(List<EamAspectCategory> eamAspectCategory) {
        this.eamAspectCategory = eamAspectCategory;
    }

    public EaMeta withEamAspectCategory(List<EamAspectCategory> eamAspectCategory) {
        this.eamAspectCategory = eamAspectCategory;
        return this;
    }

    @JsonProperty("Eam_AspectWeight")
    public List<EamAspectWeight> getEamAspectWeight() {
        return eamAspectWeight;
    }

    @JsonProperty("Eam_AspectWeight")
    public void setEamAspectWeight(List<EamAspectWeight> eamAspectWeight) {
        this.eamAspectWeight = eamAspectWeight;
    }

    public EaMeta withEamAspectWeight(List<EamAspectWeight> eamAspectWeight) {
        this.eamAspectWeight = eamAspectWeight;
        return this;
    }

    @JsonProperty("Eam_Link_Lc_Et")
    public List<EamLinkLcEt> getEamLinkLcEt() {
        return eamLinkLcEt;
    }

    @JsonProperty("Eam_Link_Lc_Et")
    public void setEamLinkLcEt(List<EamLinkLcEt> eamLinkLcEt) {
        this.eamLinkLcEt = eamLinkLcEt;
    }

    public EaMeta withEamLinkLcEt(List<EamLinkLcEt> eamLinkLcEt) {
        this.eamLinkLcEt = eamLinkLcEt;
        return this;
    }

    @JsonProperty("Eam_Link_Ac_Et")
    public List<EamLinkAcEt> getEamLinkAcEt() {
        return eamLinkAcEt;
    }

    @JsonProperty("Eam_Link_Ac_Et")
    public void setEamLinkAcEt(List<EamLinkAcEt> eamLinkAcEt) {
        this.eamLinkAcEt = eamLinkAcEt;
    }

    public EaMeta withEamLinkAcEt(List<EamLinkAcEt> eamLinkAcEt) {
        this.eamLinkAcEt = eamLinkAcEt;
        return this;
    }

    @JsonProperty("Eam_ElementType")
    public List<EamElementType> getEamElementType() {
        return eamElementType;
    }

    @JsonProperty("Eam_ElementType")
    public void setEamElementType(List<EamElementType> eamElementType) {
        this.eamElementType = eamElementType;
    }

    public EaMeta withEamElementType(List<EamElementType> eamElementType) {
        this.eamElementType = eamElementType;
        return this;
    }

    @JsonProperty("Eam_ViewCategory")
    public List<EamViewCategory> getEamViewCategory() {
        return eamViewCategory;
    }

    @JsonProperty("Eam_ViewCategory")
    public void setEamViewCategory(List<EamViewCategory> eamViewCategory) {
        this.eamViewCategory = eamViewCategory;
    }

    public EaMeta withEamViewpointCategory(List<EamViewCategory> eamViewCategory) {
        this.eamViewCategory = eamViewCategory;
        return this;
    }

    @JsonProperty("Eam_ViewPurpose")
    public List<EamViewPurpose> getEamViewPurpose() {
        return eamViewPurpose;
    }

    @JsonProperty("Eam_ViewPurpose")
    public void setEamViewPurpose(List<EamViewPurpose> eamViewPurpose) {
        this.eamViewPurpose = eamViewPurpose;
    }

    public EaMeta withEamViewpointPurpose(List<EamViewPurpose> eamViewPurpose) {
        this.eamViewPurpose = eamViewPurpose;
        return this;
    }

    @JsonProperty("Eam_ViewScope")
    public List<EamViewScope> getEamViewScope() {
        return eamViewScope;
    }

    @JsonProperty("Eam_ViewScope")
    public void setEamViewScope(List<EamViewScope> eamViewScope) {
        this.eamViewScope = eamViewScope;
    }

    public EaMeta withEamViewpointScope(List<EamViewScope> eamViewScope) {
        this.eamViewScope = eamViewScope;
        return this;
    }

    @JsonProperty("Eam_BasicView")
    public List<EamBasicView> getEamBasicView() {
        return eamBasicView;
    }

    @JsonProperty("Eam_BasicView")
    public void setEamBasicView(List<EamBasicView> eamBasicView) {
        this.eamBasicView = eamBasicView;
    }

    public EaMeta withEamBasicViewpoint(List<EamBasicView> eamBasicView) {
        this.eamBasicView = eamBasicView;
        return this;
    }

    @JsonProperty("Eam_Link_Vi_Vic")
    public List<EamLinkViVic> getEamLinkViVic() {
        return eamLinkViVic;
    }

    @JsonProperty("Eam_Link_Vi_Vic")
    public void setEamLinkViVic(List<EamLinkViVic> eamLinkViVic) {
        this.eamLinkViVic = eamLinkViVic;
    }

    public EaMeta withEamLinkViVic(List<EamLinkViVic> eamLinkViVic) {
        this.eamLinkViVic = eamLinkViVic;
        return this;
    }

    @JsonProperty("Eam_Link_Vi_Vip")
    public List<EamLinkViVip> getEamLinkViVip() {
        return eamLinkViVip;
    }

    @JsonProperty("Eam_Link_Vi_Vip")
    public void setEamLinkViVip(List<EamLinkViVip> eamLinkViVip) {
        this.eamLinkViVip = eamLinkViVip;
    }

    public EaMeta withEamLinkViVip(List<EamLinkViVip> eamLinkViVip) {
        this.eamLinkViVip = eamLinkViVip;
        return this;
    }

    @JsonProperty("Eam_Link_Vi_Vis")
    public List<EamLinkViVis> getEamLinkViVis() {
        return eamLinkViVis;
    }

    @JsonProperty("Eam_Link_Vi_Vis")
    public void setEamLinkViVis(List<EamLinkViVis> eamLinkViVis) {
        this.eamLinkViVis = eamLinkViVis;
    }

    public EaMeta withEamLinkViVis(List<EamLinkViVis> eamLinkViVis) {
        this.eamLinkViVis = eamLinkViVis;
        return this;
    }

    @JsonProperty("Eam_Link_Vi_Sr")
    public List<EamLinkViSr> getEamLinkViSr() {
        return eamLinkViSr;
    }

    @JsonProperty("Eam_Link_Vi_Sr")
    public void setEamLinkViSr(List<EamLinkViSr> eamLinkViSr) {
        this.eamLinkViSr = eamLinkViSr;
    }

    public EaMeta withEamLinkVpSr(List<EamLinkViSr> eamLinkViSr) {
        this.eamLinkViSr = eamLinkViSr;
        return this;
    }

    @JsonProperty("Eam_Link_Vi_Et")
    public List<EamLinkViEt> getEamLinkViEt() {
        return eamLinkViEt;
    }

    @JsonProperty("Eam_Link_Vi_Et")
    public void setEamLinkViEt(List<EamLinkViEt> eamLinkViEt) {
        this.eamLinkViEt = eamLinkViEt;
    }

    public EaMeta withEamLinkVpEt(List<EamLinkViEt> eamLinkViEt) {
        this.eamLinkViEt = eamLinkViEt;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public EaMeta withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("eamRelationshipCategory", eamRelationshipCategory).append("eamRelationshipType", eamRelationshipType).append("eamRelationshipWeight", eamRelationshipWeight).append("eamRelationshipElementDependencyDirection", eamRelationshipElementDependencyDirection).append("eamLinkRtRc", eamLinkRtRc).append("eamLinkRrRedd", eamLinkRtRedd).append("eamLayerCategory", eamLayerCategory).append("eamLayerWeight", eamLayerWeight).append("eamAspectCategory", eamAspectCategory).append("eamAspectWeight", eamAspectWeight).append("eamLinkLcEt", eamLinkLcEt).append("eamLinkAcEt", eamLinkAcEt).append("eamElementType", eamElementType).append("eamViewpointCategory", eamViewCategory).append("eamViewpointPurpose", eamViewPurpose).append("eamViewpointScope", eamViewScope).append("eamBasicViewpoint", eamBasicView).append("eamLinkViVic", eamLinkViVic).append("eamLinkViVip", eamLinkViVip).append("eamLinkViVis", eamLinkViVis).append("eamLinkVpSr", eamLinkViSr).append("eamLinkVpEt", eamLinkViEt).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(eamLinkViVis).append(eamLayerCategory).append(eamLinkLcEt).append(eamRelationshipType).append(eamLinkAcEt).append(eamRelationshipWeight).append(eamElementType).append(eamLinkViEt).append(eamLinkViVip).append(eamRelationshipElementDependencyDirection).append(eamLinkRtRc).append(eamViewPurpose).append(eamAspectWeight).append(eamRelationshipCategory).append(eamLinkRtRedd).append(eamAspectCategory).append(eamLinkViVic).append(eamViewScope).append(eamLinkViSr).append(additionalProperties).append(eamViewCategory).append(eamLayerWeight).append(eamBasicView).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EaMeta) == false) {
            return false;
        }
        EaMeta rhs = ((EaMeta) other);
        return new EqualsBuilder().append(eamLinkViVis, rhs.eamLinkViVis).append(eamLayerCategory, rhs.eamLayerCategory).append(eamLinkLcEt, rhs.eamLinkLcEt).append(eamRelationshipType, rhs.eamRelationshipType).append(eamLinkAcEt, rhs.eamLinkAcEt).append(eamRelationshipWeight, rhs.eamRelationshipWeight).append(eamElementType, rhs.eamElementType).append(eamLinkViEt, rhs.eamLinkViEt).append(eamLinkViVip, rhs.eamLinkViVip).append(eamRelationshipElementDependencyDirection, rhs.eamRelationshipElementDependencyDirection).append(eamLinkRtRc, rhs.eamLinkRtRc).append(eamViewPurpose, rhs.eamViewPurpose).append(eamAspectWeight, rhs.eamAspectWeight).append(eamRelationshipCategory, rhs.eamRelationshipCategory).append(eamLinkRtRedd, rhs.eamLinkRtRedd).append(eamAspectCategory, rhs.eamAspectCategory).append(eamLinkViVic, rhs.eamLinkViVic).append(eamViewScope, rhs.eamViewScope).append(eamLinkViSr, rhs.eamLinkViSr).append(additionalProperties, rhs.additionalProperties).append(eamViewCategory, rhs.eamViewCategory).append(eamLayerWeight, rhs.eamLayerWeight).append(eamBasicView, rhs.eamBasicView).isEquals();
    }

}
