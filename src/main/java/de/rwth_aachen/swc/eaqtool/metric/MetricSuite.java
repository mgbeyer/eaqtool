package de.rwth_aachen.swc.eaqtool.metric;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;
import de.rwth_aachen.swc.eaqtool.repo.EAModelRepository;
import de.rwth_aachen.swc.eaqtool.repo.SpecificationFactory;
import de.rwth_aachen.swc.eaqtool.repo.XQSpecificationFactory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Name",
        "Description",
        "Version",
        "Date",
        "ScriptingLanguage",
        "GlobalBLGreen",
        "GlobalBLRed",
        "GlobalBLMarginGreen",
        "GlobalBLMarginRed",
        "StandardScaleMin",
        "StandardScaleMax",
        "Metric",
        "Ammp"
})
public class MetricSuite implements Serializable
{
    public static final String SCRIPTING_DSL_TYPE_XQUERY = "XQuery";

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("ScriptingLanguage")
    private String scriptingLanguage;
    @JsonProperty("GlobalBLGreen")
    private Double globalBLGreen;
    @JsonProperty("GlobalBLRed")
    private Double globalBLRed;
    @JsonProperty("GlobalBLMarginGreen")
    private Double globalBLMarginGreen;
    @JsonProperty("GlobalBLMarginRed")
    private Double globalBLMarginRed;
    @JsonProperty("StandardScaleMin")
    private Double standardScaleMin;
    @JsonProperty("StandardScaleMax")
    private Double standardScaleMax;
    @JsonProperty("Metric")
    private List<Metric> metric = null;
    @JsonProperty("Ammp")
    private List<Ammp> ammp = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8667898999289810110L;

    /**
     * No args constructor for use in serialization
     *
     */
    public MetricSuite() {
    }

    /**
     *
     * @param metric
     * @param description
     * @param name
     * @param date
     * @param scriptingLanguage
     * @param globalBLGreen
     * @param globalBLRed
     * @param globalBLMarginGreen
     * @param globalBLMarginRed
     * @param standardScaleMin
     * @param standardScaleMax
     * @param ammp
     * @param version
     */
    public MetricSuite(String name, String description, String version, String date, String scriptingLanguage, Double globalBLGreen, Double globalBLRed, Double globalBLMarginGreen, Double globalBLMarginRed, Double standardScaleMin, Double standardScaleMax, List<Metric> metric, List<Ammp> ammp) {
        super();
        this.name = name;
        this.description = description;
        this.version = version;
        this.date = date;
        this.scriptingLanguage = scriptingLanguage;
        this.globalBLGreen = globalBLGreen;
        this.globalBLRed = globalBLRed;
        this.globalBLMarginGreen = globalBLMarginGreen;
        this.globalBLMarginRed = globalBLMarginRed;
        this.standardScaleMin = standardScaleMin;
        this.standardScaleMax = standardScaleMax;
        this.metric = metric;
        this.ammp = ammp;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public MetricSuite withName(String name) {
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

    public MetricSuite withDescription(String description) {
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

    public MetricSuite withVersion(String version) {
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

    public MetricSuite withDate(String date) {
        this.date = date;
        return this;
    }

    @JsonProperty("ScriptingLanguage")
    public String getScriptingLanguage() {
        return scriptingLanguage;
    }

    @JsonProperty("ScriptingLanguage")
    public void setScriptingLanguage(String scriptingLanguage) {
        this.scriptingLanguage = scriptingLanguage;
    }

    public MetricSuite withScriptingLanguage(String scriptingLanguage) {
        this.scriptingLanguage = scriptingLanguage;
        return this;
    }

    @JsonProperty("GlobalBLGreen")
    public Double getGlobalBLGreen() {
        return globalBLGreen;
    }

    @JsonProperty("GlobalBLGreen")
    public void setGlobalBLGreen(Double globalBLGreen) {
        this.globalBLGreen = globalBLGreen;
    }

    public MetricSuite withGlobalBLGreen(Double globalBLGreen) {
        this.globalBLGreen = globalBLGreen;
        return this;
    }

    @JsonProperty("GlobalBLRed")
    public Double getGlobalBLRed() {
        return globalBLRed;
    }

    @JsonProperty("GlobalBLRed")
    public void setGlobalBLRed(Double globalBLRed) {
        this.globalBLRed = globalBLRed;
    }

    public MetricSuite withGlobalBLRed(Double globalBLRed) {
        this.globalBLRed = globalBLRed;
        return this;
    }

    @JsonProperty("GlobalBLMarginGreen")
    public Double getGlobalBLMarginGreen() {
        return globalBLMarginGreen;
    }

    @JsonProperty("GlobalBLMarginGreen")
    public void setGlobalBLMarginGreen(Double globalBLMarginGreen) {
        this.globalBLMarginGreen = globalBLMarginGreen;
    }

    public MetricSuite withGlobalBLMarginGreen(Double globalBLMarginGreen) {
        this.globalBLMarginGreen = globalBLMarginGreen;
        return this;
    }

    @JsonProperty("GlobalBLMarginRed")
    public Double getGlobalBLMarginRed() {
        return globalBLMarginRed;
    }

    @JsonProperty("GlobalBLMarginRed")
    public void setGlobalBLMarginRed(Double globalBLMarginRed) {
        this.globalBLMarginRed = globalBLMarginRed;
    }

    public MetricSuite withGlobalBLMarginRed(Double globalBLMarginRed) {
        this.globalBLMarginRed = globalBLMarginRed;
        return this;
    }

    @JsonProperty("StandardScaleMin")
    public Double getStandardScaleMin() {
        return standardScaleMin;
    }

    @JsonProperty("StandardScaleMin")
    public void setStandardScaleMin(Double standardScaleMin) {
        this.standardScaleMin = standardScaleMin;
    }

    public MetricSuite withStandardScaleMin(Double standardScaleMin) {
        this.standardScaleMin = standardScaleMin;
        return this;
    }

    @JsonProperty("StandardScaleMax")
    public Double getStandardScaleMax() {
        return standardScaleMax;
    }

    @JsonProperty("StandardScaleMax")
    public void setStandardScaleMax(Double standardScaleMax) {
        this.standardScaleMax = standardScaleMax;
    }

    public MetricSuite withStandardScaleMax(Double standardScaleMax) {
        this.standardScaleMax = standardScaleMax;
        return this;
    }
    @JsonProperty("Metric")
    public List<Metric> getMetric() {
        return metric;
    }

    @JsonProperty("Metric")
    public void setMetric(List<Metric> metric) {
        this.metric = metric;
    }

    public MetricSuite withMetric(List<Metric> metric) {
        this.metric = metric;
        return this;
    }

    @JsonProperty("Ammp")
    public List<Ammp> getAmmp() {
        return ammp;
    }

    @JsonProperty("Ammp")
    public void setAmmp(List<Ammp> ammp) {
        this.ammp = ammp;
    }

    public MetricSuite withAmmp(List<Ammp> ammp) {
        this.ammp = ammp;
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

    public MetricSuite withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("description", description).append("version", version).append("date", date).append("scriptingLanguage", scriptingLanguage).append("globalBLGreen", globalBLGreen).append("globalBLRed", globalBLRed).append("globalBLMarginGreen", globalBLMarginGreen).append("globalBLMarginRed", globalBLMarginRed).append("standardScaleMin", standardScaleMin).append("standardScaleMax", standardScaleMax).append("metric", metric).append("ammp", ammp).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(date).append(scriptingLanguage).append(globalBLGreen).append(ammp).append(version).append(additionalProperties).append(metric).append(description).append(name).append(standardScaleMax).append(globalBLMarginRed).append(standardScaleMin).append(globalBLMarginGreen).append(globalBLRed).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MetricSuite) == false) {
            return false;
        }
        MetricSuite rhs = ((MetricSuite) other);
        return new EqualsBuilder().append(date, rhs.date).append(scriptingLanguage, rhs.scriptingLanguage).append(globalBLGreen, rhs.globalBLGreen).append(ammp, rhs.ammp).append(version, rhs.version).append(additionalProperties, rhs.additionalProperties).append(metric, rhs.metric).append(description, rhs.description).append(name, rhs.name).append(standardScaleMax, rhs.standardScaleMax).append(globalBLMarginRed, rhs.globalBLMarginRed).append(standardScaleMin, rhs.standardScaleMin).append(globalBLMarginGreen, rhs.globalBLMarginGreen).append(globalBLRed, rhs.globalBLRed).isEquals();
    }


    public List<List<String>> run(EAModelRepository repo) {
        List<List<String>> ret = new ArrayList<>();
        SpecificationFactory spec_factory = new XQSpecificationFactory();
        for (Metric m : this.getMetric()) {
            if (this.scriptingLanguage.compareTo(SCRIPTING_DSL_TYPE_XQUERY)==0 && EAQTHelper.valueOf(m.getScriptStr()) !="") {
                spec_factory.loadParam(m.getScriptStr());
                ret.add(m.run(repo, spec_factory.getSpecification()));
            }
        }
        return ret;
    }

}