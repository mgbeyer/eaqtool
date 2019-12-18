package de.rwth_aachen.swc.eaqtool.metric;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.*;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;
import de.rwth_aachen.swc.eaqtool.repo.EAModelRepository;
import de.rwth_aachen.swc.eaqtool.repo.Specification;
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
        "QRC_Id",
        "Normalizer",
        "Scale",
        "Script",
        "AmmpList"
})
@JsonIdentityReference(alwaysAsId = true)
@JsonIdentityInfo(scope=Metric.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="AmmpList", resolver = DedupingObjectIdResolver.class)
public class Metric implements Serializable
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
    @JsonProperty("QRC_Id")
    private String qRCId;
    @JsonProperty("Normalizer")
    private Normalizer normalizer;
    @JsonProperty("Scale")
    private Scale scale = null;
    @JsonProperty("Script")
    private List<String> script = null;
    @JsonProperty("AmmpList")
    private List<Ammp> ammpList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8646154622726094910L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Metric() {
    }

    /**
     *
     * @param id
     * @param scale
     * @param ammpList
     * @param qRCId
     * @param description
     * @param name
     * @param script
     * @param normalizer
     * @param date
     * @param version
     */
    public Metric(String id, String name, String description, String version, String date, String qRCId, Normalizer normalizer, Scale scale, List<String> script, List<Ammp> ammpList) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = version;
        this.date = date;
        this.qRCId = qRCId;
        this.normalizer = normalizer;
        this.scale = scale;
        this.script = script;
        this.ammpList = ammpList;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    public Metric withId(String id) {
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

    public Metric withName(String name) {
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

    public Metric withDescription(String description) {
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

    public Metric withVersion(String version) {
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

    public Metric withDate(String date) {
        this.date = date;
        return this;
    }

    @JsonProperty("QRC_Id")
    public String getQRCId() {
        return qRCId;
    }

    @JsonProperty("QRC_Id")
    public void setQRCId(String qRCId) {
        this.qRCId = qRCId;
    }

    public Metric withQRCId(String qRCId) {
        this.qRCId = qRCId;
        return this;
    }

    @JsonProperty("Normalizer")
    public Normalizer getNormalizer() {
        return normalizer;
    }

    @JsonProperty("Normalizer")
    public void setNormalizer(String normalizer) {
        this.normalizer = NormalizerFactory.getNormalizer(normalizer);
    }

    public Metric withNormalizer(String normalizer) {
        this.normalizer = NormalizerFactory.getNormalizer(normalizer);
        return this;
    }

    @JsonProperty("Scale")
    public Scale getScale() {
        return scale;
    }

    @JsonProperty("Scale")
    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public Metric withScale(Scale scale) {
        this.scale = scale;
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

    public Metric withScript(List<String> script) {
        this.script = script;
        return this;
    }

    @JsonProperty("AmmpList")
    public List<Ammp> getAmmpList() {
        return ammpList;
    }

    @JsonProperty("AmmpList")
    public void setAmmpList(List<Ammp> ammpList) {
        this.ammpList = ammpList;
    }

    public Metric withAmmpList(List<Ammp> ammpList) {
        this.ammpList = ammpList;
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

    public Metric withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("description", description).append("version", version).append("date", date).append("qRCId", qRCId).append("normalizer", normalizer).append("scale", scale).append("script", script).append("ammpList", ammpList).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(scale).append(ammpList).append(additionalProperties).append(qRCId).append(description).append(name).append(script).append(normalizer).append(date).append(version).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metric) == false) {
            return false;
        }
        Metric rhs = ((Metric) other);
        return new EqualsBuilder().append(id, rhs.id).append(scale, rhs.scale).append(ammpList, rhs.ammpList).append(additionalProperties, rhs.additionalProperties).append(qRCId, rhs.qRCId).append(description, rhs.description).append(name, rhs.name).append(script, rhs.script).append(normalizer, rhs.normalizer).append(date, rhs.date).append(version, rhs.version).isEquals();
    }

    public String getScriptStr() {
        return script!=null ? EAQTHelper.assembleScriptFromList(script) : "";
    }

    public ArrayList<String> run(EAModelRepository repo, Specification spec) {
        System.out.println("running... " + this.getId() + " (" + this.getName() + ")");
        String rawValue = "";
        String normValue = "";
        if (repo != null && spec != null) {
            if (repo.query(spec) != null) {
                rawValue = repo.query(spec).get(0).toString();
                if (this.getNormalizer() != null) {
                    if (repo.query(spec).size()>1) {
                        if (this.getNormalizer().getType()==NormalizerFactory.NORMALIZER_TYPE_RATIOX) {
                            ((NormalizerRatioX)this.getNormalizer()).setDivisor(Double.parseDouble(repo.query(spec).get(1).toString()));
                        } else if (this.getNormalizer().getType()==NormalizerFactory.NORMALIZER_TYPE_RATIOAB) {
                            ((NormalizerRatioAb)this.getNormalizer()).setDivisor(Double.parseDouble(repo.query(spec).get(1).toString()));
                        }
                    }
                    normValue = !rawValue.isEmpty() ? this.getNormalizer().normalize(Double.parseDouble(rawValue)).toString() : "";
                }
            }
        }
        return new ArrayList<String>(Arrays.asList(this.getId(), rawValue, normValue));
    }

}