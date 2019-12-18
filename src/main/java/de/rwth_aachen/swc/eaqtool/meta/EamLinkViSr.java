package de.rwth_aachen.swc.eaqtool.meta;

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
        "VI_id",
        "SR_id"
})
public class EamLinkViSr implements Serializable
{

    @JsonProperty("VI_id")
    private String vIId;
    @JsonProperty("SR_id")
    private String sRId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2227361233278263861L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkViSr() {
    }

    /**
     *
     * @param vIId
     * @param sRId
     */
    public EamLinkViSr(String vIId, String sRId) {
        super();
        this.vIId = vIId;
        this.sRId = sRId;
    }

    @JsonProperty("VI_id")
    public String getVIId() {
        return vIId;
    }

    @JsonProperty("VI_id")
    public void setVIId(String vIId) {
        this.vIId = vIId;
    }

    public EamLinkViSr withVIId(String vIId) {
        this.vIId = vIId;
        return this;
    }

    @JsonProperty("SR_id")
    public String getSRId() {
        return sRId;
    }

    @JsonProperty("SR_id")
    public void setSRId(String sRId) {
        this.sRId = sRId;
    }

    public EamLinkViSr withSRId(String sRId) {
        this.sRId = sRId;
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

    public EamLinkViSr withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vIId", vIId).append("sRId", sRId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(vIId).append(sRId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkViSr) == false) {
            return false;
        }
        EamLinkViSr rhs = ((EamLinkViSr) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(vIId, rhs.vIId).append(sRId, rhs.sRId).isEquals();
    }

}
