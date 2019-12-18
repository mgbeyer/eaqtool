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
        "ET_id"
})
public class EamLinkViEt implements Serializable
{

    @JsonProperty("VI_id")
    private String vIId;
    @JsonProperty("ET_id")
    private String eTId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7956343226979019603L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkViEt() {
    }

    /**
     *
     * @param eTId
     * @param vIId
     */
    public EamLinkViEt(String vIId, String eTId) {
        super();
        this.vIId = vIId;
        this.eTId = eTId;
    }

    @JsonProperty("VI_id")
    public String getVIId() {
        return vIId;
    }

    @JsonProperty("VI_id")
    public void setVIId(String vIId) {
        this.vIId = vIId;
    }

    public EamLinkViEt withVIId(String vIId) {
        this.vIId = vIId;
        return this;
    }

    @JsonProperty("ET_id")
    public String getETId() {
        return eTId;
    }

    @JsonProperty("ET_id")
    public void setETId(String eTId) {
        this.eTId = eTId;
    }

    public EamLinkViEt withETId(String eTId) {
        this.eTId = eTId;
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

    public EamLinkViEt withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vIId", vIId).append("eTId", eTId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(eTId).append(vIId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkViEt) == false) {
            return false;
        }
        EamLinkViEt rhs = ((EamLinkViEt) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(eTId, rhs.eTId).append(vIId, rhs.vIId).isEquals();
    }

}
