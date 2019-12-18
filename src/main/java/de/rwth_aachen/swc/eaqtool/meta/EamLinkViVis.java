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
        "VIS_id"
})
public class EamLinkViVis implements Serializable
{

    @JsonProperty("VI_id")
    private String vIId;
    @JsonProperty("VIS_id")
    private String vISId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6584111558517128345L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamLinkViVis() {
    }

    /**
     *
     * @param vISId
     * @param vIId
     */
    public EamLinkViVis(String vIId, String vISId) {
        super();
        this.vIId = vIId;
        this.vISId = vISId;
    }

    @JsonProperty("VI_id")
    public String getVIId() {
        return vIId;
    }

    @JsonProperty("VI_id")
    public void setVIId(String vIId) {
        this.vIId = vIId;
    }

    public EamLinkViVis withVIId(String vIId) {
        this.vIId = vIId;
        return this;
    }

    @JsonProperty("VIS_id")
    public String getVISId() {
        return vISId;
    }

    @JsonProperty("VIS_id")
    public void setVISId(String vISId) {
        this.vISId = vISId;
    }

    public EamLinkViVis withVISId(String vISId) {
        this.vISId = vISId;
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

    public EamLinkViVis withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vIId", vIId).append("vISId", vISId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(vISId).append(vIId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamLinkViVis) == false) {
            return false;
        }
        EamLinkViVis rhs = ((EamLinkViVis) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(vISId, rhs.vISId).append(vIId, rhs.vIId).isEquals();
    }

}
