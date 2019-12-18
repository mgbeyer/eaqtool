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
        "QRC_id",
        "MQC_id"
})
public class QmLinkQrcMqc implements Serializable
{

    @JsonProperty("QRC_id")
    private String qRCId;
    @JsonProperty("MQC_id")
    private String mQCId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2341683913269551299L;

    /**
     * No args constructor for use in serialization
     *
     */
    public QmLinkQrcMqc() {
    }

    /**
     *
     * @param qRCId
     * @param mQCId
     */
    public QmLinkQrcMqc(String qRCId, String mQCId) {
        super();
        this.qRCId = qRCId;
        this.mQCId = mQCId;
    }

    @JsonProperty("QRC_id")
    public String getQRCId() {
        return qRCId;
    }

    @JsonProperty("QRC_id")
    public void setQRCId(String qRCId) {
        this.qRCId = qRCId;
    }

    public QmLinkQrcMqc withQRCId(String qRCId) {
        this.qRCId = qRCId;
        return this;
    }

    @JsonProperty("MQC_id")
    public String getMQCId() {
        return mQCId;
    }

    @JsonProperty("MQC_id")
    public void setMQCId(String mQCId) {
        this.mQCId = mQCId;
    }

    public QmLinkQrcMqc withMQCId(String mQCId) {
        this.mQCId = mQCId;
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

    public QmLinkQrcMqc withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("qRCId", qRCId).append("mQCId", mQCId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(qRCId).append(mQCId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QmLinkQrcMqc) == false) {
            return false;
        }
        QmLinkQrcMqc rhs = ((QmLinkQrcMqc) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(qRCId, rhs.qRCId).append(mQCId, rhs.mQCId).isEquals();
    }

}
