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
        "RT_id",
        "REDD_id"
})
public class EamLinkRtRedd implements Serializable
{

    @JsonProperty("RT_id")
    private String rTId;
    @JsonProperty("REDD_id")
    private String rEDDId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6912783515439031498L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkRtRedd() {
    }

    /**
     *
     * @param rTId
     * @param rEDDId
     */
    public EamLinkRtRedd(String rTId, String rEDDId) {
        super();
        this.rTId = rTId;
        this.rEDDId = rEDDId;
    }

    @JsonProperty("RT_id")
    public String getRTId() {
        return rTId;
    }

    @JsonProperty("RT_id")
    public void setRTId(String rTId) {
        this.rTId = rTId;
    }

    public EamLinkRtRedd withRTId(String rTId) {
        this.rTId = rTId;
        return this;
    }

    @JsonProperty("REDD_id")
    public String getREDDId() {
        return rEDDId;
    }

    @JsonProperty("REDD_id")
    public void setREDDId(String rEDDId) {
        this.rEDDId = rEDDId;
    }

    public EamLinkRtRedd withREDDId(String rEDDId) {
        this.rEDDId = rEDDId;
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

    public EamLinkRtRedd withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("rTId", rTId).append("rEDDId", rEDDId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(rTId).append(rEDDId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkRtRedd) == false) {
            return false;
        }
        EamLinkRtRedd rhs = ((EamLinkRtRedd) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(rTId, rhs.rTId).append(rEDDId, rhs.rEDDId).isEquals();
    }

}
