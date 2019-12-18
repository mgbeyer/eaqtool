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
        "VIP_id"
})
public class EamLinkViVip implements Serializable
{

    @JsonProperty("VI_id")
    private String vIId;
    @JsonProperty("VIP_id")
    private String vIPId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5820632449163130946L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkViVip() {
    }

    /**
     *
     * @param vIPId
     * @param vIId
     */
    public EamLinkViVip(String vIId, String vIPId) {
        super();
        this.vIId = vIId;
        this.vIPId = vIPId;
    }

    @JsonProperty("VI_id")
    public String getVIId() {
        return vIId;
    }

    @JsonProperty("VI_id")
    public void setVIId(String vIId) {
        this.vIId = vIId;
    }

    public EamLinkViVip withVIId(String vIId) {
        this.vIId = vIId;
        return this;
    }

    @JsonProperty("VIP_id")
    public String getVIPId() {
        return vIPId;
    }

    @JsonProperty("VIP_id")
    public void setVIPId(String vIPId) {
        this.vIPId = vIPId;
    }

    public EamLinkViVip withVIPId(String vIPId) {
        this.vIPId = vIPId;
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

    public EamLinkViVip withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vIId", vIId).append("vIPId", vIPId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vIPId).append(additionalProperties).append(vIId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkViVip) == false) {
            return false;
        }
        EamLinkViVip rhs = ((EamLinkViVip) other);
        return new EqualsBuilder().append(vIPId, rhs.vIPId).append(additionalProperties, rhs.additionalProperties).append(vIId, rhs.vIId).isEquals();
    }

}
