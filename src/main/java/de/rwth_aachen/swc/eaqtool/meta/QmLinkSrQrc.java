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
        "SR_id",
        "QRC_id"
})
public class QmLinkSrQrc implements Serializable
{

    @JsonProperty("SR_id")
    private String sRId;
    @JsonProperty("QRC_id")
    private String qRCId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5021189532288638948L;

    /**
     * No args constructor for use in serialization
     *
     */
    public QmLinkSrQrc() {
    }

    /**
     *
     * @param qRCId
     * @param sRId
     */
    public QmLinkSrQrc(String sRId, String qRCId) {
        super();
        this.sRId = sRId;
        this.qRCId = qRCId;
    }

    @JsonProperty("SR_id")
    public String getSRId() {
        return sRId;
    }

    @JsonProperty("SR_id")
    public void setSRId(String sRId) {
        this.sRId = sRId;
    }

    public QmLinkSrQrc withSRId(String sRId) {
        this.sRId = sRId;
        return this;
    }

    @JsonProperty("QRC_id")
    public String getQRCId() {
        return qRCId;
    }

    @JsonProperty("QRC_id")
    public void setQRCId(String qRCId) {
        this.qRCId = qRCId;
    }

    public QmLinkSrQrc withQRCId(String qRCId) {
        this.qRCId = qRCId;
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

    public QmLinkSrQrc withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sRId", sRId).append("qRCId", qRCId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(qRCId).append(sRId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QmLinkSrQrc) == false) {
            return false;
        }
        QmLinkSrQrc rhs = ((QmLinkSrQrc) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(qRCId, rhs.qRCId).append(sRId, rhs.sRId).isEquals();
    }

}
