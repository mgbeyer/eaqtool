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
        "VIC_id"
})
public class EamLinkViVic implements Serializable
{

    @JsonProperty("VI_id")
    private String vIId;
    @JsonProperty("VIC_id")
    private String vICId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5692692815560597673L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkViVic() {
    }

    /**
     *
     * @param vICId
     * @param vIId
     */
    public EamLinkViVic(String vIId, String vICId) {
        super();
        this.vIId = vIId;
        this.vICId = vICId;
    }

    @JsonProperty("VI_id")
    public String getVIId() {
        return vIId;
    }

    @JsonProperty("VI_id")
    public void setVIId(String vIId) {
        this.vIId = vIId;
    }

    public EamLinkViVic withVIId(String vIId) {
        this.vIId = vIId;
        return this;
    }

    @JsonProperty("VIC_id")
    public String getVICId() {
        return vICId;
    }

    @JsonProperty("VIC_id")
    public void setVICId(String vICId) {
        this.vICId = vICId;
    }

    public EamLinkViVic withVICId(String vICId) {
        this.vICId = vICId;
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

    public EamLinkViVic withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vIId", vIId).append("vICId", vICId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(vICId).append(vIId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkViVic) == false) {
            return false;
        }
        EamLinkViVic rhs = ((EamLinkViVic) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(vICId, rhs.vICId).append(vIId, rhs.vIId).isEquals();
    }

}
