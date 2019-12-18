package de.rwth_aachen.swc.eaqtool.persist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
        "data_ts",
        "data_p"
})
public class Datum implements Serializable
{

    @JsonProperty("data_ts")
    private String dataTs;
    @JsonProperty("data_p")
    private List<List<String>> dataP = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 9176705053996211336L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param dataTs
     * @param dataP
     */
    public Datum(String dataTs, List<List<String>> dataP) {
        super();
        this.dataTs = dataTs;
        this.dataP = dataP;
    }

    @JsonProperty("data_ts")
    public String getDataTs() {
        return dataTs;
    }

    @JsonProperty("data_ts")
    public void setDataTs(String dataTs) {
        this.dataTs = dataTs;
    }

    public Datum withDataTs(String dataTs) {
        this.dataTs = dataTs;
        return this;
    }

    @JsonProperty("data_p")
    public List<List<String>> getDataP() {
        return dataP;
    }

    @JsonProperty("data_p")
    public void setDataP(List<List<String>> dataP) {
        this.dataP = dataP;
    }

    public Datum withDataP(List<List<String>> dataP) {
        this.dataP = dataP;
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

    public Datum withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dataTs", dataTs).append("dataP", dataP).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dataTs).append(additionalProperties).append(dataP).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum) == false) {
            return false;
        }
        Datum rhs = ((Datum) other);
        return new EqualsBuilder().append(dataTs, rhs.dataTs).append(additionalProperties, rhs.additionalProperties).append(dataP, rhs.dataP).isEquals();
    }

}