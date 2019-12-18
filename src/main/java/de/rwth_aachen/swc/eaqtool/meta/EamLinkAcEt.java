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
        "AC_id"
})
public class EamLinkAcEt implements Serializable
{

    @JsonProperty("ET_id")
    private String eTId;
    @JsonProperty("AC_id")
    private String aCId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1882518249674649509L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkAcEt() {
    }

    /**
     *
     * @param aCId
     * @param eTId
     */
    public EamLinkAcEt(String eTId, String aCId) {
        super();
        this.eTId = eTId;
        this.aCId = aCId;
    }

    @JsonProperty("ET_id")
    public String getETId() {
        return eTId;
    }

    @JsonProperty("ET_id")
    public void setETId(String eTId) {
        this.eTId = eTId;
    }

    public EamLinkAcEt withETId(String eTId) {
        this.eTId = eTId;
        return this;
    }

    @JsonProperty("AC_id")
    public String getACId() {
        return aCId;
    }

    @JsonProperty("AC_id")
    public void setACId(String aCId) {
        this.aCId = aCId;
    }

    public EamLinkAcEt withACId(String aCId) {
        this.aCId = aCId;
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

    public EamLinkAcEt withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("eTId", eTId).append("aCId", aCId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(aCId).append(additionalProperties).append(eTId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkAcEt) == false) {
            return false;
        }
        EamLinkAcEt rhs = ((EamLinkAcEt) other);
        return new EqualsBuilder().append(aCId, rhs.aCId).append(additionalProperties, rhs.additionalProperties).append(eTId, rhs.eTId).isEquals();
    }

}
