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
        "RC_id",
        "RT_id"
})
public class EamLinkRtRc implements Serializable
{

    @JsonProperty("RC_id")
    private String rCId;
    @JsonProperty("RT_id")
    private String rTId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1120105566842802589L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkRtRc() {
    }

    /**
     *
     * @param rTId
     * @param rCId
     */
    public EamLinkRtRc(String rCId, String rTId) {
        super();
        this.rCId = rCId;
        this.rTId = rTId;
    }

    @JsonProperty("RC_id")
    public String getRCId() {
        return rCId;
    }

    @JsonProperty("RC_id")
    public void setRCId(String rCId) {
        this.rCId = rCId;
    }

    public EamLinkRtRc withRCId(String rCId) {
        this.rCId = rCId;
        return this;
    }

    @JsonProperty("RT_id")
    public String getRTId() {
        return rTId;
    }

    @JsonProperty("RT_id")
    public void setRTId(String rTId) {
        this.rTId = rTId;
    }

    public EamLinkRtRc withRTId(String rTId) {
        this.rTId = rTId;
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

    public EamLinkRtRc withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("rCId", rCId).append("rTId", rTId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(rTId).append(rCId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkRtRc) == false) {
            return false;
        }
        EamLinkRtRc rhs = ((EamLinkRtRc) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(rTId, rhs.rTId).append(rCId, rhs.rCId).isEquals();
    }

}
