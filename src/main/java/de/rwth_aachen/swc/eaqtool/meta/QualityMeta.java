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
        "Qm_StakeholderRole",
        "Qm_QualityRequirementCategory",
        "Qm_MeasurableQualityCharacteristic",
        "Qm_Link_Sr_Qrc",
        "Qm_Link_Qrc_Mqc"
})
public class QualityMeta implements Serializable
{

    @JsonProperty("Qm_StakeholderRole")
    private List<QmStakeholderRole> qmStakeholderRole = new ArrayList<QmStakeholderRole>();
    @JsonProperty("Qm_QualityRequirementCategory")
    private List<QmQualityRequirementCategory> qmQualityRequirementCategory = new ArrayList<QmQualityRequirementCategory>();
    @JsonProperty("Qm_MeasurableQualityCharacteristic")
    private List<QmMeasurableQualityCharacteristic> qmMeasurableQualityCharacteristic = new ArrayList<QmMeasurableQualityCharacteristic>();
    @JsonProperty("Qm_Link_Sr_Qrc")
    private List<QmLinkSrQrc> qmLinkSrQrc = new ArrayList<QmLinkSrQrc>();
    @JsonProperty("Qm_Link_Qrc_Mqc")
    private List<QmLinkQrcMqc> qmLinkQrcMqc = new ArrayList<QmLinkQrcMqc>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5869105340125675753L;

    /**
     * No args constructor for use in serialization
     *
     */
    public QualityMeta() {
    }

    /**
     *
     * @param qmLinkQrcMqc
     * @param qmMeasurableQualityCharacteristic
     * @param qmLinkSrQrc
     * @param qmQualityRequirementCategory
     * @param qmStakeholderRole
     */
    public QualityMeta(List<QmStakeholderRole> qmStakeholderRole, List<QmQualityRequirementCategory> qmQualityRequirementCategory, List<QmMeasurableQualityCharacteristic> qmMeasurableQualityCharacteristic, List<QmLinkSrQrc> qmLinkSrQrc, List<QmLinkQrcMqc> qmLinkQrcMqc) {
        super();
        this.qmStakeholderRole = qmStakeholderRole;
        this.qmQualityRequirementCategory = qmQualityRequirementCategory;
        this.qmMeasurableQualityCharacteristic = qmMeasurableQualityCharacteristic;
        this.qmLinkSrQrc = qmLinkSrQrc;
        this.qmLinkQrcMqc = qmLinkQrcMqc;
    }

    @JsonProperty("Qm_StakeholderRole")
    public List<QmStakeholderRole> getQmStakeholderRole() {
        return qmStakeholderRole;
    }

    @JsonProperty("Qm_StakeholderRole")
    public void setQmStakeholderRole(List<QmStakeholderRole> qmStakeholderRole) {
        this.qmStakeholderRole = qmStakeholderRole;
    }

    public QualityMeta withQmStakeholderRole(List<QmStakeholderRole> qmStakeholderRole) {
        this.qmStakeholderRole = qmStakeholderRole;
        return this;
    }

    @JsonProperty("Qm_QualityRequirementCategory")
    public List<QmQualityRequirementCategory> getQmQualityRequirementCategory() {
        return qmQualityRequirementCategory;
    }

    @JsonProperty("Qm_QualityRequirementCategory")
    public void setQmQualityRequirementCategory(List<QmQualityRequirementCategory> qmQualityRequirementCategory) {
        this.qmQualityRequirementCategory = qmQualityRequirementCategory;
    }

    public QualityMeta withQmQualityRequirementCategory(List<QmQualityRequirementCategory> qmQualityRequirementCategory) {
        this.qmQualityRequirementCategory = qmQualityRequirementCategory;
        return this;
    }

    @JsonProperty("Qm_MeasurableQualityCharacteristic")
    public List<QmMeasurableQualityCharacteristic> getQmMeasurableQualityCharacteristic() {
        return qmMeasurableQualityCharacteristic;
    }

    @JsonProperty("Qm_MeasurableQualityCharacteristic")
    public void setQmMeasurableQualityCharacteristic(List<QmMeasurableQualityCharacteristic> qmMeasurableQualityCharacteristic) {
        this.qmMeasurableQualityCharacteristic = qmMeasurableQualityCharacteristic;
    }

    public QualityMeta withQmMeasurableQualityCharacteristic(List<QmMeasurableQualityCharacteristic> qmMeasurableQualityCharacteristic) {
        this.qmMeasurableQualityCharacteristic = qmMeasurableQualityCharacteristic;
        return this;
    }

    @JsonProperty("Qm_Link_Sr_Qrc")
    public List<QmLinkSrQrc> getQmLinkSrQrc() {
        return qmLinkSrQrc;
    }

    @JsonProperty("Qm_Link_Sr_Qrc")
    public void setQmLinkSrQrc(List<QmLinkSrQrc> qmLinkSrQrc) {
        this.qmLinkSrQrc = qmLinkSrQrc;
    }

    public QualityMeta withQmLinkSrQrc(List<QmLinkSrQrc> qmLinkSrQrc) {
        this.qmLinkSrQrc = qmLinkSrQrc;
        return this;
    }

    @JsonProperty("Qm_Link_Qrc_Mqc")
    public List<QmLinkQrcMqc> getQmLinkQrcMqc() {
        return qmLinkQrcMqc;
    }

    @JsonProperty("Qm_Link_Qrc_Mqc")
    public void setQmLinkQrcMqc(List<QmLinkQrcMqc> qmLinkQrcMqc) {
        this.qmLinkQrcMqc = qmLinkQrcMqc;
    }

    public QualityMeta withQmLinkQrcMqc(List<QmLinkQrcMqc> qmLinkQrcMqc) {
        this.qmLinkQrcMqc = qmLinkQrcMqc;
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

    public QualityMeta withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("qmStakeholderRole", qmStakeholderRole).append("qmQualityRequirementCategory", qmQualityRequirementCategory).append("qmMeasurableQualityCharacteristic", qmMeasurableQualityCharacteristic).append("qmLinkSrQrc", qmLinkSrQrc).append("qmLinkQrcMqc", qmLinkQrcMqc).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(qmLinkQrcMqc).append(qmMeasurableQualityCharacteristic).append(qmLinkSrQrc).append(qmQualityRequirementCategory).append(qmStakeholderRole).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QualityMeta) == false) {
            return false;
        }
        QualityMeta rhs = ((QualityMeta) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(qmLinkQrcMqc, rhs.qmLinkQrcMqc).append(qmMeasurableQualityCharacteristic, rhs.qmMeasurableQualityCharacteristic).append(qmLinkSrQrc, rhs.qmLinkSrQrc).append(qmQualityRequirementCategory, rhs.qmQualityRequirementCategory).append(qmStakeholderRole, rhs.qmStakeholderRole).isEquals();
    }

}
