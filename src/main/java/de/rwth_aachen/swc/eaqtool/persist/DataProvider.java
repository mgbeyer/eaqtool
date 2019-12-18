package de.rwth_aachen.swc.eaqtool.persist;

import java.io.Serializable;
import java.util.HashMap;
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
        "providerId",
        "providerName",
        "providerDescription",
        "providerVersion",
        "providerDate",
        "providerMetaHook",
        "providerNormalizer",
        "providerScaleMin",
        "providerScaleMax",
        "providerSweetspot",
        "providerBLGreen",
        "providerBLRed"
})
public class DataProvider implements Serializable
{

    @JsonProperty("providerId")
    private String providerId;
    @JsonProperty("providerName")
    private String providerName;
    @JsonProperty("providerDescription")
    private String providerDescription;
    @JsonProperty("providerVersion")
    private String providerVersion;
    @JsonProperty("providerDate")
    private String providerDate;
    @JsonProperty("providerMetaHook")
    private String providerMetaHook;
    @JsonProperty("providerNormalizer")
    private String providerNormalizer;
    @JsonProperty("providerScaleMin")
    private String providerScaleMin;
    @JsonProperty("providerScaleMax")
    private String providerScaleMax;
    @JsonProperty("providerSweetspot")
    private String providerSweetspot;
    @JsonProperty("providerBLGreen")
    private String providerBLGreen;
    @JsonProperty("providerBLRed")
    private String providerBLRed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 43724633083919119L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataProvider() {
    }

    /**
     *
     * @param providerNormalizer
     * @param providerName
     * @param providerBLRed
     * @param providerScaleMax
     * @param providerSweetspot
     * @param providerId
     * @param providerDate
     * @param providerBLGreen
     * @param providerMetaHook
     * @param providerDescription
     * @param providerVersion
     * @param providerScaleMin
     */
    public DataProvider(String providerId, String providerName, String providerDescription, String providerVersion, String providerDate, String providerMetaHook, String providerNormalizer, String providerScaleMin, String providerScaleMax, String providerSweetspot, String providerBLGreen, String providerBLRed) {
        super();
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerDescription = providerDescription;
        this.providerVersion = providerVersion;
        this.providerDate = providerDate;
        this.providerMetaHook = providerMetaHook;
        this.providerNormalizer = providerNormalizer;
        this.providerScaleMin = providerScaleMin;
        this.providerScaleMax = providerScaleMax;
        this.providerSweetspot = providerSweetspot;
        this.providerBLGreen = providerBLGreen;
        this.providerBLRed = providerBLRed;
    }

    @JsonProperty("providerId")
    public String getProviderId() {
        return providerId;
    }

    @JsonProperty("providerId")
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public DataProvider withProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    @JsonProperty("providerName")
    public String getProviderName() {
        return providerName;
    }

    @JsonProperty("providerName")
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public DataProvider withProviderName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    @JsonProperty("providerDescription")
    public String getProviderDescription() {
        return providerDescription;
    }

    @JsonProperty("providerDescription")
    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public DataProvider withProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
        return this;
    }

    @JsonProperty("providerVersion")
    public String getProviderVersion() {
        return providerVersion;
    }

    @JsonProperty("providerVersion")
    public void setProviderVersion(String providerVersion) {
        this.providerVersion = providerVersion;
    }

    public DataProvider withProviderVersion(String providerVersion) {
        this.providerVersion = providerVersion;
        return this;
    }

    @JsonProperty("providerDate")
    public String getProviderDate() {
        return providerDate;
    }

    @JsonProperty("providerDate")
    public void setProviderDate(String providerDate) {
        this.providerDate = providerDate;
    }

    public DataProvider withProviderDate(String providerDate) {
        this.providerDate = providerDate;
        return this;
    }

    @JsonProperty("providerMetaHook")
    public String getProviderMetaHook() {
        return providerMetaHook;
    }

    @JsonProperty("providerMetaHook")
    public void setProviderMetaHook(String providerMetaHook) {
        this.providerMetaHook = providerMetaHook;
    }

    public DataProvider withProviderMetaHook(String providerMetaHook) {
        this.providerMetaHook = providerMetaHook;
        return this;
    }

    @JsonProperty("providerNormalizer")
    public String getProviderNormalizer() {
        return providerNormalizer;
    }

    @JsonProperty("providerNormalizer")
    public void setProviderNormalizer(String providerNormalizer) {
        this.providerNormalizer = providerNormalizer;
    }

    public DataProvider withProviderNormalizer(String providerNormalizer) {
        this.providerNormalizer = providerNormalizer;
        return this;
    }

    @JsonProperty("providerScaleMin")
    public String getProviderScaleMin() {
        return providerScaleMin;
    }

    @JsonProperty("providerScaleMin")
    public void setProviderScaleMin(String providerScaleMin) {
        this.providerScaleMin = providerScaleMin;
    }

    public DataProvider withProviderScaleMin(String providerScaleMin) {
        this.providerScaleMin = providerScaleMin;
        return this;
    }

    @JsonProperty("providerScaleMax")
    public String getProviderScaleMax() {
        return providerScaleMax;
    }

    @JsonProperty("providerScaleMax")
    public void setProviderScaleMax(String providerScaleMax) {
        this.providerScaleMax = providerScaleMax;
    }

    public DataProvider withProviderScaleMax(String providerScaleMax) {
        this.providerScaleMax = providerScaleMax;
        return this;
    }

    @JsonProperty("providerSweetspot")
    public String getProviderSweetspot() {
        return providerSweetspot;
    }

    @JsonProperty("providerSweetspot")
    public void setProviderSweetspot(String providerSweetspot) {
        this.providerSweetspot = providerSweetspot;
    }

    public DataProvider withProviderSweetspot(String providerSweetspot) {
        this.providerSweetspot = providerSweetspot;
        return this;
    }

    @JsonProperty("providerBLGreen")
    public String getProviderBLGreen() {
        return providerBLGreen;
    }

    @JsonProperty("providerBLGreen")
    public void setProviderBLGreen(String providerBLGreen) {
        this.providerBLGreen = providerBLGreen;
    }

    public DataProvider withProviderBLGreen(String providerBLGreen) {
        this.providerBLGreen = providerBLGreen;
        return this;
    }

    @JsonProperty("providerBLRed")
    public String getProviderBLRed() {
        return providerBLRed;
    }

    @JsonProperty("providerBLRed")
    public void setProviderBLRed(String providerBLRed) {
        this.providerBLRed = providerBLRed;
    }

    public DataProvider withProviderBLRed(String providerBLRed) {
        this.providerBLRed = providerBLRed;
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

    public DataProvider withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("providerId", providerId).append("providerName", providerName).append("providerDescription", providerDescription).append("providerVersion", providerVersion).append("providerDate", providerDate).append("providerMetaHook", providerMetaHook).append("providerNormalizer", providerNormalizer).append("providerScaleMin", providerScaleMin).append("providerScaleMax", providerScaleMax).append("providerSweetspot", providerSweetspot).append("providerBLGreen", providerBLGreen).append("providerBLRed", providerBLRed).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(providerScaleMax).append(providerDate).append(providerMetaHook).append(providerVersion).append(providerScaleMin).append(providerNormalizer).append(providerName).append(additionalProperties).append(providerBLRed).append(providerSweetspot).append(providerId).append(providerBLGreen).append(providerDescription).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DataProvider) == false) {
            return false;
        }
        DataProvider rhs = ((DataProvider) other);
        return new EqualsBuilder().append(providerScaleMax, rhs.providerScaleMax).append(providerDate, rhs.providerDate).append(providerMetaHook, rhs.providerMetaHook).append(providerVersion, rhs.providerVersion).append(providerScaleMin, rhs.providerScaleMin).append(providerNormalizer, rhs.providerNormalizer).append(providerName, rhs.providerName).append(additionalProperties, rhs.additionalProperties).append(providerBLRed, rhs.providerBLRed).append(providerSweetspot, rhs.providerSweetspot).append(providerId, rhs.providerId).append(providerBLGreen, rhs.providerBLGreen).append(providerDescription, rhs.providerDescription).isEquals();
    }

    /**
     * Specialized equals method for a continuity check to ensure the persistence of merged current and legacy log data stays consistent.
     * Here two DataProvider objects are considered equal if their ID, version, normalizer or scale min/max values do not differ.
     */
    public boolean equalsMetricLogContinuity(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DataProvider) == false) {
            return false;
        }
        DataProvider rhs = ((DataProvider) other);
        return new EqualsBuilder().append(providerScaleMax, rhs.providerScaleMax).append(providerVersion, rhs.providerVersion).append(providerScaleMin, rhs.providerScaleMin).append(providerNormalizer, rhs.providerNormalizer).append(providerId, rhs.providerId).isEquals();
    }
}