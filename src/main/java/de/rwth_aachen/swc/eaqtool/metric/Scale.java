package de.rwth_aachen.swc.eaqtool.metric;

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
        "ScaleMin",
        "ScaleMax",
        "Sweetspot",
        "BLGreen",
        "BLRed"
})
public class Scale implements Serializable
{

    public static final String SWEETSPOT_SCALE_MIN = "SCALE_MIN";
    public static final String SWEETSPOT_SCALE_MAX = "SCALE_MAX";

    @JsonProperty("ScaleMin")
    private Double scaleMin;
    @JsonProperty("ScaleMax")
    private Double scaleMax;
    @JsonProperty("Sweetspot")
    private String sweetspot;
    @JsonProperty("BLGreen")
    private Double blGreen;
    @JsonProperty("BLRed")
    private Double blRed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4667702076989150174L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Scale() {
    }

    /**
     *
     * @param scaleMin
     * @param blRed
     * @param sweetspot
     * @param blGreen
     * @param scaleMax
     */
    public Scale(Double scaleMin, Double scaleMax, String sweetspot, Double blGreen, Double blRed) {
        super();
        this.scaleMin = scaleMin;
        this.scaleMax = scaleMax;
        this.sweetspot = sweetspot;
        this.blGreen = blGreen;
        this.blRed = blRed;
    }

    @JsonProperty("ScaleMin")
    public Double getScaleMin() {
        return scaleMin;
    }

    @JsonProperty("ScaleMin")
    public void setScaleMin(Double scaleMin) {
        this.scaleMin = scaleMin;
    }

    public Scale withScaleMin(Double scaleMin) {
        this.scaleMin = scaleMin;
        return this;
    }

    @JsonProperty("ScaleMax")
    public Double getScaleMax() {
        return scaleMax;
    }

    @JsonProperty("ScaleMax")
    public void setScaleMax(Double scaleMax) {
        this.scaleMax = scaleMax;
    }

    public Scale withScaleMax(Double scaleMax) {
        this.scaleMax = scaleMax;
        return this;
    }

    @JsonProperty("Sweetspot")
    public String getSweetspot() {
        return sweetspot;
    }

    @JsonProperty("Sweetspot")
    public void setSweetspot(String sweetspot) {
        this.sweetspot = sweetspot;
    }

    public Scale withSweetspot(String sweetspot) {
        this.sweetspot = sweetspot;
        return this;
    }

    @JsonProperty("BLGreen")
    public Double getBLGreen() {
        return blGreen;
    }

    @JsonProperty("BLGreen")
    public void setBLGreen(Double blGreen) {
        this.blGreen = blGreen;
    }

    public Scale withBLGreen(Double blGreen) {
        this.blGreen = blGreen;
        return this;
    }

    @JsonProperty("BLRed")
    public Double getBLRed() {
        return blRed;
    }

    @JsonProperty("BLRed")
    public void setBLRed(Double blRed) {
        this.blRed = blRed;
    }

    public Scale withBLRed(Double blRed) {
        this.blRed = blRed;
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

    public Scale withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("scaleMin", scaleMin).append("scaleMax", scaleMax).append("sweetspot", sweetspot).append("blGreen", blGreen).append("blRed", blRed).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(scaleMin).append(additionalProperties).append(blRed).append(sweetspot).append(blGreen).append(scaleMax).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Scale) == false) {
            return false;
        }
        Scale rhs = ((Scale) other);
        return new EqualsBuilder().append(scaleMin, rhs.scaleMin).append(additionalProperties, rhs.additionalProperties).append(blRed, rhs.blRed).append(sweetspot, rhs.sweetspot).append(blGreen, rhs.blGreen).append(scaleMax, rhs.scaleMax).isEquals();
    }

}