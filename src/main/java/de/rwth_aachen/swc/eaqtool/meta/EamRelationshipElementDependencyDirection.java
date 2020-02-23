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
        "id"
})
public class EamRelationshipElementDependencyDirection implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3844459643807967408L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EamRelationshipElementDependencyDirection() {
    }

    /**
     *
     * @param id
     */
    public EamRelationshipElementDependencyDirection(String id) {
        super();
        this.id = id;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public EamRelationshipElementDependencyDirection withId(String id) {
        this.id = id;
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

    public EamRelationshipElementDependencyDirection withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EamRelationshipElementDependencyDirection) == false) {
            return false;
        }
        EamRelationshipElementDependencyDirection rhs = ((EamRelationshipElementDependencyDirection) other);
        return new EqualsBuilder().append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}