package de.rwth_aachen.swc.eaqtool.persist;

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
        "datasetName",
        "datasetDescription",
        "datasetVersion",
        "datasetDate",
        "startTime",
        "endTime",
        "dataGenerator",
        "dataGeneratorVersion"
})
public class Header implements Serializable
{

    @JsonProperty("datasetName")
    private String datasetName;
    @JsonProperty("datasetDescription")
    private String datasetDescription;
    @JsonProperty("datasetVersion")
    private String datasetVersion;
    @JsonProperty("datasetDate")
    private String datasetDate;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("dataGenerator")
    private String dataGenerator;
    @JsonProperty("dataGeneratorVersion")
    private String dataGeneratorVersion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3194837191083779476L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Header() {
    }

    /**
     *
     * @param startTime
     * @param dataGenerator
     * @param datasetDate
     * @param datasetName
     * @param datasetDescription
     * @param dataGeneratorVersion
     * @param endTime
     * @param datasetVersion
     */
    public Header(String datasetName, String datasetDescription, String datasetVersion, String datasetDate, String startTime, String endTime, String dataGenerator, String dataGeneratorVersion) {
        super();
        this.datasetName = datasetName;
        this.datasetDescription = datasetDescription;
        this.datasetVersion = datasetVersion;
        this.datasetDate = datasetDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dataGenerator = dataGenerator;
        this.dataGeneratorVersion = dataGeneratorVersion;
    }

    @JsonProperty("datasetName")
    public String getDatasetName() {
        return datasetName;
    }

    @JsonProperty("datasetName")
    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public Header withDatasetName(String datasetName) {
        this.datasetName = datasetName;
        return this;
    }

    @JsonProperty("datasetDescription")
    public String getDatasetDescription() {
        return datasetDescription;
    }

    @JsonProperty("datasetDescription")
    public void setDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
    }

    public Header withDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
        return this;
    }

    @JsonProperty("datasetVersion")
    public String getDatasetVersion() {
        return datasetVersion;
    }

    @JsonProperty("datasetVersion")
    public void setDatasetVersion(String datasetVersion) {
        this.datasetVersion = datasetVersion;
    }

    public Header withDatasetVersion(String datasetVersion) {
        this.datasetVersion = datasetVersion;
        return this;
    }

    @JsonProperty("datasetDate")
    public String getDatasetDate() {
        return datasetDate;
    }

    @JsonProperty("datasetDate")
    public void setDatasetDate(String datasetDate) {
        this.datasetDate = datasetDate;
    }

    public Header withDatasetDate(String datasetDate) {
        this.datasetDate = datasetDate;
        return this;
    }

    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Header withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    @JsonProperty("endTime")
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Header withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @JsonProperty("dataGenerator")
    public String getDataGenerator() {
        return dataGenerator;
    }

    @JsonProperty("dataGenerator")
    public void setDataGenerator(String dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    public Header withDataGenerator(String dataGenerator) {
        this.dataGenerator = dataGenerator;
        return this;
    }

    @JsonProperty("dataGeneratorVersion")
    public String getDataGeneratorVersion() {
        return dataGeneratorVersion;
    }

    @JsonProperty("dataGeneratorVersion")
    public void setDataGeneratorVersion(String dataGeneratorVersion) {
        this.dataGeneratorVersion = dataGeneratorVersion;
    }

    public Header withDataGeneratorVersion(String dataGeneratorVersion) {
        this.dataGeneratorVersion = dataGeneratorVersion;
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

    public Header withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("datasetName", datasetName).append("datasetDescription", datasetDescription).append("datasetVersion", datasetVersion).append("datasetDate", datasetDate).append("startTime", startTime).append("endTime", endTime).append("dataGenerator", dataGenerator).append("dataGeneratorVersion", dataGeneratorVersion).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(startTime).append(dataGenerator).append(datasetDate).append(additionalProperties).append(datasetName).append(datasetDescription).append(dataGeneratorVersion).append(endTime).append(datasetVersion).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Header) == false) {
            return false;
        }
        Header rhs = ((Header) other);
        return new EqualsBuilder().append(startTime, rhs.startTime).append(dataGenerator, rhs.dataGenerator).append(datasetDate, rhs.datasetDate).append(additionalProperties, rhs.additionalProperties).append(datasetName, rhs.datasetName).append(datasetDescription, rhs.datasetDescription).append(dataGeneratorVersion, rhs.dataGeneratorVersion).append(endTime, rhs.endTime).append(datasetVersion, rhs.datasetVersion).isEquals();
    }

}