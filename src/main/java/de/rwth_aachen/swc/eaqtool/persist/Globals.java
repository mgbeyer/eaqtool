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
        "globalBLGreen",
        "globalBLRed",
        "globalBLMarginGreen",
        "globalBLMarginRed",
        "standardScaleMin",
        "standardScaleMax"
})
public class Globals implements Serializable
{

    @JsonProperty("globalBLGreen")
    private String globalBLGreen;
    @JsonProperty("globalBLRed")
    private String globalBLRed;
    @JsonProperty("globalBLMarginGreen")
    private String globalBLMarginGreen;
    @JsonProperty("globalBLMarginRed")
    private String globalBLMarginRed;
    @JsonProperty("standardScaleMin")
    private String standardScaleMin;
    @JsonProperty("standardScaleMax")
    private String standardScaleMax;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7121712817984659352L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Globals() {
    }

    /**
     *
     * @param standardScaleMax
     * @param globalBLMarginRed
     * @param standardScaleMin
     * @param globalBLMarginGreen
     * @param globalBLGreen
     * @param globalBLRed
     */
    public Globals(String globalBLGreen, String globalBLRed, String globalBLMarginGreen, String globalBLMarginRed, String standardScaleMin, String standardScaleMax) {
        super();
        this.globalBLGreen = globalBLGreen;
        this.globalBLRed = globalBLRed;
        this.globalBLMarginGreen = globalBLMarginGreen;
        this.globalBLMarginRed = globalBLMarginRed;
        this.standardScaleMin = standardScaleMin;
        this.standardScaleMax = standardScaleMax;
    }

    @JsonProperty("globalBLGreen")
    public String getGlobalBLGreen() {
        return globalBLGreen;
    }

    @JsonProperty("globalBLGreen")
    public void setGlobalBLGreen(String globalBLGreen) {
        this.globalBLGreen = globalBLGreen;
    }

    public Globals withGlobalBLGreen(String globalBLGreen) {
        this.globalBLGreen = globalBLGreen;
        return this;
    }

    @JsonProperty("globalBLRed")
    public String getGlobalBLRed() {
        return globalBLRed;
    }

    @JsonProperty("globalBLRed")
    public void setGlobalBLRed(String globalBLRed) {
        this.globalBLRed = globalBLRed;
    }

    public Globals withGlobalBLRed(String globalBLRed) {
        this.globalBLRed = globalBLRed;
        return this;
    }

    @JsonProperty("globalBLMarginGreen")
    public String getGlobalBLMarginGreen() {
        return globalBLMarginGreen;
    }

    @JsonProperty("globalBLMarginGreen")
    public void setGlobalBLMarginGreen(String globalBLMarginGreen) {
        this.globalBLMarginGreen = globalBLMarginGreen;
    }

    public Globals withGlobalBLMarginGreen(String globalBLMarginGreen) {
        this.globalBLMarginGreen = globalBLMarginGreen;
        return this;
    }

    @JsonProperty("globalBLMarginRed")
    public String getGlobalBLMarginRed() {
        return globalBLMarginRed;
    }

    @JsonProperty("globalBLMarginRed")
    public void setGlobalBLMarginRed(String globalBLMarginRed) {
        this.globalBLMarginRed = globalBLMarginRed;
    }

    public Globals withGlobalBLMarginRed(String globalBLMarginRed) {
        this.globalBLMarginRed = globalBLMarginRed;
        return this;
    }

    @JsonProperty("standardScaleMin")
    public String getStandardScaleMin() {
        return standardScaleMin;
    }

    @JsonProperty("standardScaleMin")
    public void setStandardScaleMin(String standardScaleMin) {
        this.standardScaleMin = standardScaleMin;
    }

    public Globals withStandardScaleMin(String standardScaleMin) {
        this.standardScaleMin = standardScaleMin;
        return this;
    }

    @JsonProperty("standardScaleMax")
    public String getStandardScaleMax() {
        return standardScaleMax;
    }

    @JsonProperty("standardScaleMax")
    public void setStandardScaleMax(String standardScaleMax) {
        this.standardScaleMax = standardScaleMax;
    }

    public Globals withStandardScaleMax(String standardScaleMax) {
        this.standardScaleMax = standardScaleMax;
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

    public Globals withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("globalBLGreen", globalBLGreen).append("globalBLRed", globalBLRed).append("globalBLMarginGreen", globalBLMarginGreen).append("globalBLMarginRed", globalBLMarginRed).append("standardScaleMin", standardScaleMin).append("standardScaleMax", standardScaleMax).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(standardScaleMax).append(globalBLMarginRed).append(standardScaleMin).append(globalBLMarginGreen).append(globalBLGreen).append(globalBLRed).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Globals) == false) {
            return false;
        }
        Globals rhs = ((Globals) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(standardScaleMax, rhs.standardScaleMax).append(globalBLMarginRed, rhs.globalBLMarginRed).append(standardScaleMin, rhs.standardScaleMin).append(globalBLMarginGreen, rhs.globalBLMarginGreen).append(globalBLGreen, rhs.globalBLGreen).append(globalBLRed, rhs.globalBLRed).isEquals();
    }

}