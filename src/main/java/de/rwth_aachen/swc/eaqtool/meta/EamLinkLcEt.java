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
        "ET_id",
        "LC_id"
})
public class EamLinkLcEt implements Serializable
{

    @JsonProperty("ET_id")
    private String eTId;
    @JsonProperty("LC_id")
    private String lCId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8299003133636520997L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkLcEt() {
    }

    /**
     *
     * @param lCId
     * @param eTId
     */
    public EamLinkLcEt(String eTId, String lCId) {
        super();
        this.eTId = eTId;
        this.lCId = lCId;
    }

    @JsonProperty("ET_id")
    public String getETId() {
        return eTId;
    }

    @JsonProperty("ET_id")
    public void setETId(String eTId) {
        this.eTId = eTId;
    }

    public EamLinkLcEt withETId(String eTId) {
        this.eTId = eTId;
        return this;
    }

    @JsonProperty("LC_id")
    public String getLCId() {
        return lCId;
    }

    @JsonProperty("LC_id")
    public void setLCId(String lCId) {
        this.lCId = lCId;
    }

    public EamLinkLcEt withLCId(String lCId) {
        this.lCId = lCId;
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

    public EamLinkLcEt withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("eTId", eTId).append("lCId", lCId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lCId).append(additionalProperties).append(eTId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkLcEt) == false) {
            return false;
        }
        EamLinkLcEt rhs = ((EamLinkLcEt) other);
        return new EqualsBuilder().append(lCId, rhs.lCId).append(additionalProperties, rhs.additionalProperties).append(eTId, rhs.eTId).isEquals();
    }

}
