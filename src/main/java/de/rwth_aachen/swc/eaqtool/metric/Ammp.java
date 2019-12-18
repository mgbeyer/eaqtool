package de.rwth_aachen.swc.eaqtool.metric;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "Name",
        "Description",
        "Version",
        "Date",
        "Script"
})
@JsonIdentityInfo(scope=Ammp.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="Id", resolver = DedupingObjectIdResolver.class)
public class Ammp implements Serializable
{

    @JsonProperty("Id")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Script")
    private List<String> script = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7519566355561117902L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Ammp() {
    }

    /**
     *
     * @param id
     * @param description
     * @param name
     * @param script
     * @param date
     * @param version
     */
    public Ammp(String id, String name, String description, String version, String date, List<String> script) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = version;
        this.date = date;
        this.script = script;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    public Ammp withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public Ammp withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Ammp withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("Version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("Version")
    public void setVersion(String version) {
        this.version = version;
    }

    public Ammp withVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(String date) {
        this.date = date;
    }

    public Ammp withDate(String date) {
        this.date = date;
        return this;
    }

    @JsonProperty("Script")
    public List<String> getScript() {
        return script;
    }

    @JsonProperty("Script")
    public void setScript(List<String> script) {
        this.script = script;
    }

    public Ammp withScript(List<String> script) {
        this.script = script;
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

    public Ammp withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("description", description).append("version", version).append("date", date).append("script", script).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(additionalProperties).append(description).append(name).append(script).append(date).append(version).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Ammp) == false) {
            return false;
        }
        Ammp rhs = ((Ammp) other);
        return new EqualsBuilder().append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(name, rhs.name).append(script, rhs.script).append(date, rhs.date).append(version, rhs.version).isEquals();
    }

    public String getScriptStr() {
        return EAQTHelper.assembleScriptFromList(script);
    }

}