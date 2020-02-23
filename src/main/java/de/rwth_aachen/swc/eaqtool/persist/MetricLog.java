package de.rwth_aachen.swc.eaqtool.persist;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;
import de.rwth_aachen.swc.eaqtool.metric.Normalizer;
import de.rwth_aachen.swc.eaqtool.metric.Scale;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "docType",
        "version",
        "dataSourceName",
        "dataSourceId",
        "header",
        "globals",
        "dataProvider",
        "data"
})
public class MetricLog implements Serializable
{

    public static enum VIEW_OUTPUT_GROUPING_CRITERION { QRC, MQC, SR };
    public static enum BENCHMARK_LEVEL { BL_GREEN, BL_YELLOW, BL_RED, BL_YELLOW_PLUS, BL_YELLOW_MINUS };

    @JsonProperty("docType")
    private String docType;
    @JsonProperty("version")
    private String version;
    @JsonProperty("dataSourceName")
    private String dataSourceName;
    @JsonProperty("dataSourceId")
    private String dataSourceId;
    @JsonProperty("header")
    private Header header;
    @JsonProperty("globals")
    private Globals globals;
    @JsonProperty("dataProvider")
    private List<DataProvider> dataProvider = null;
    @JsonProperty("data")
    private List<Datum> data = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1125917431409262413L;

    /**
     * No args constructor for use in serialization
     *
     */
    public MetricLog() {
    }

    /**
     *
     * @param docType
     * @param dataProvider
     * @param data
     * @param header
     * @param globals
     * @param version
     * @param dataSourceName
     * @param dataSourceId
     */
    public MetricLog(String docType, String version, String dataSourceName, String dataSourceId, Header header, Globals globals, List<DataProvider> dataProvider, List<Datum> data) {
        super();
        this.docType = docType;
        this.version = version;
        this.dataSourceName = dataSourceName;
        this.dataSourceId = dataSourceId;
        this.header = header;
        this.globals = globals;
        this.dataProvider = dataProvider;
        this.data = data;
    }

    @JsonProperty("docType")
    public String getDocType() {
        return docType;
    }

    @JsonProperty("docType")
    public void setDocType(String docType) {
        this.docType = docType;
    }

    public MetricLog withDocType(String docType) {
        this.docType = docType;
        return this;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public MetricLog withVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("dataSourceName")
    public String getDataSourceName() {
        return dataSourceName;
    }

    @JsonProperty("dataSourceName")
    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public MetricLog withDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
        return this;
    }

    @JsonProperty("dataSourceId")
    public String getDataSourceId() {
        return dataSourceId;
    }

    @JsonProperty("dataSourceId")
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public MetricLog withDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
        return this;
    }

    @JsonProperty("header")
    public Header getHeader() {
        return header;
    }

    @JsonProperty("header")
    public void setHeader(Header header) {
        this.header = header;
    }

    public MetricLog withHeader(Header header) {
        this.header = header;
        return this;
    }

    @JsonProperty("globals")
    public Globals getGlobals() {
        return globals;
    }

    @JsonProperty("globals")
    public void setGlobals(Globals globals) {
        this.globals = globals;
    }

    public MetricLog withGlobals(Globals globals) {
        this.globals = globals;
        return this;
    }

    @JsonProperty("dataProvider")
    public List<DataProvider> getDataProvider() {
        return dataProvider;
    }

    @JsonProperty("dataProvider")
    public void setDataProvider(List<DataProvider> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public MetricLog withDataProvider(List<DataProvider> dataProvider) {
        this.dataProvider = dataProvider;
        return this;
    }

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    public MetricLog withData(List<Datum> data) {
        this.data = data;
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

    public MetricLog withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("docType", docType).append("version", version).append("dataSourceName", dataSourceName).append("dataSourceId", dataSourceId).append("header", header).append("globals", globals).append("dataProvider", dataProvider).append("data", data).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(globals).append(docType).append(dataProvider).append(additionalProperties).append(data).append(header).append(version).append(dataSourceName).append(dataSourceId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MetricLog) == false) {
            return false;
        }
        MetricLog rhs = ((MetricLog) other);
        return new EqualsBuilder().append(globals, rhs.globals).append(docType, rhs.docType).append(dataProvider, rhs.dataProvider).append(additionalProperties, rhs.additionalProperties).append(data, rhs.data).append(header, rhs.header).append(version, rhs.version).append(dataSourceName, rhs.dataSourceName).append(dataSourceId, rhs.dataSourceId).isEquals();
    }


    /**
     * Return data provider object with given ID.
     * @param id Data provider id.
     * @return data provider object.
     */
    public DataProvider getDataProviderById(String id) {
        for (DataProvider dp : this.getDataProvider()) {
            if (dp.getProviderId().compareTo(id) == 0) return dp;
        }
        return null;
    }

    /**
     * Replace data provider object with given ID.
     * @param id Data provider id.
     * @param dataProvider Data provider replacement object.
     */
    public void replaceDataProviderById(String id, DataProvider dataProvider) {
        for (DataProvider dp : this.getDataProvider()) {
            if (dp.getProviderId().compareTo(id) == 0) {
                this.dataProvider.set(this.dataProvider.indexOf(dp), dataProvider);
            }
        }
    }

    /**
     * Check if this MetricLog object's data conforms to a given MetricLog object containing legacy data.
     * "Conform" means, its data can logically be appended to the legacy data without a continuity violation.
     * Continuity is violated if:
     * + dataSourceId (in header) unique Id has changed
     * + metric suite name or version have changed
     * + # metrics has changed
     * + global scale min/max values have changed
     * + any metric ID, version, normalizer or scale min/max values have changed
     * @param legacyMetricLog Legacy data.
     * @return null if continuity (data integrity) is kept by this MetricLog object, String containing violation(s) otherwise.
     */
    public String checkContinuity(MetricLog legacyMetricLog) {
        String errmsg = "";
        if (this.getDataSourceId().compareTo(legacyMetricLog.getDataSourceId()) != 0) {
            errmsg = errmsg + " dataSourceId: [" + this.getDataSourceId() + ", " + legacyMetricLog.getDataSourceId() + "] ";
        }
        Header logHeader = this.getHeader();
        Header legacyHeader = legacyMetricLog.getHeader();
        if (logHeader==null && legacyHeader!=null) {
            if (EAQTHelper.valueOf(legacyHeader.getDatasetName())!="" || EAQTHelper.valueOf(legacyHeader.getDatasetVersion())!="") {
                errmsg = errmsg + " Header: [legacy data contained header information about metric suite which is missing in current data] ";
            }
        } else if (logHeader!=null && legacyHeader==null) {
            if (EAQTHelper.valueOf(logHeader.getDatasetName())!="" || EAQTHelper.valueOf(logHeader.getDatasetVersion())!="") {
                errmsg = errmsg + " Header: [current data contains header information about metric suite which is missing in legacy data] ";
            }
        } else {
            if (logHeader!=null && legacyHeader!=null) {
                if (logHeader.getDatasetName().compareTo(legacyHeader.getDatasetName()) != 0) {
                    errmsg = errmsg + " datasetName: [" + logHeader.getDatasetName() + ", " + legacyHeader.getDatasetName() + "] ";
                }
                if (logHeader.getDatasetVersion().compareTo(legacyHeader.getDatasetVersion()) != 0) {
                    errmsg = errmsg + " datasetVersion: [" + logHeader.getDatasetVersion() + ", " + legacyHeader.getDatasetVersion() + "] ";
                }
            }
        }
        Globals logGlobals = this.getGlobals();
        Globals legacyGlobals = legacyMetricLog.getGlobals();
        if (logGlobals.getStandardScaleMin().toString().compareTo(legacyGlobals.getStandardScaleMin().toString()) != 0) {
            errmsg = errmsg + " standardScaleMin: [" + logGlobals.getStandardScaleMin() + ", " + legacyGlobals.getStandardScaleMin() + "] ";
        }
        if (logGlobals.getStandardScaleMax().toString().compareTo(legacyGlobals.getStandardScaleMax().toString()) != 0) {
            errmsg = errmsg + " standardScaleMax: [" + logGlobals.getStandardScaleMax() + ", " + legacyGlobals.getStandardScaleMax() + "] ";
        }
        List<DataProvider> logDataProvider = this.getDataProvider();
        List<DataProvider> legacyDataProvider = legacyMetricLog.getDataProvider();
        if (logDataProvider.size() != legacyDataProvider.size()) {
            errmsg = errmsg + " # dataProvider: [" + logDataProvider.size() + ", " + legacyDataProvider.size() + "] ";
        } else {
            String badIdList = "";
            for (DataProvider log_dp : logDataProvider) {
                Boolean id_found = false;
                for (DataProvider legacy_dp : legacyDataProvider) {
                    if (log_dp.equalsMetricLogContinuity(legacy_dp)) id_found = true;
                }
                // If just ONE Metric Id/Version/Normalizer or Scale Min/Max has changed it's a dealbreaker for continuity!
                if (!id_found) {
                    badIdList = badIdList + " " + log_dp.getProviderId() + " ";
                }
            }
            if (badIdList!="") {
                errmsg = errmsg + " ID, version, normalizer or scale min/max values differ for current dataProvider: Id [" + badIdList + "] ";
            }
        }
        return EAQTHelper.valueOf(errmsg) == "" ? null : "\n list of data mismatches between Attribute: [current, legacy] \n" + errmsg;
    }

    /**
     * Return Benchmark Level for a given data provider ID and a value.
     * @param dataProviderId ID of data provider.
     * @param val Value to compute BL for.
     * @return Benchmark Level.
     */
    public BENCHMARK_LEVEL getBenchmarkLevel(String dataProviderId, Double val) {
        BENCHMARK_LEVEL ret = null;

        DataProvider dp = getDataProviderById(dataProviderId);

        // Take over data for BL thresholds.
        // All local BL thresholds are expected to adhere to the given sweetspot setting, no matter if scaling is local or global.
        // In case there are only global BL thresholds, take over global BL thresholds according to sweetspot:
        // If sweetspot = SCALE_MIN take over global BL values unmodified, if sweetspot = SCALE_MAX take over global BL as 1-BL to local
        // In all other cases take over local data or global data as local data.
        String localScaleMin = (EAQTHelper.valueOf(dp.getProviderScaleMin()) != "" ? EAQTHelper.valueOf(dp.getProviderScaleMin()) : EAQTHelper.valueOf(this.getGlobals().getStandardScaleMin()));
        String localScaleMax = (EAQTHelper.valueOf(dp.getProviderScaleMax()) != "" ? EAQTHelper.valueOf(dp.getProviderScaleMax()) : EAQTHelper.valueOf(this.getGlobals().getStandardScaleMax()));
        String localBLGreen = "";
        String localBLRed = "";
        // no local BL thresholds
        if ( EAQTHelper.valueOf(dp.getProviderBLGreen()) == "" || EAQTHelper.valueOf(dp.getProviderBLRed()) == "") {
            if (EAQTHelper.valueOf(this.getGlobals().getGlobalBLGreen()) != "" &&
                EAQTHelper.valueOf(this.getGlobals().getGlobalBLRed()) != "") {
                if (dp.getProviderSweetspot() == Scale.SWEETSPOT_SCALE_MAX) {
                    localBLGreen = Double.toString( Normalizer.round(1-Double.parseDouble(EAQTHelper.valueOf(this.getGlobals().getGlobalBLGreen())), Normalizer.STANDARD_PRECISION) );
                    localBLRed = Double.toString( Normalizer.round(1-Double.parseDouble(EAQTHelper.valueOf(this.getGlobals().getGlobalBLRed())), Normalizer.STANDARD_PRECISION) );
                } else {
                    localBLGreen = EAQTHelper.valueOf(this.getGlobals().getGlobalBLGreen());
                    localBLRed = EAQTHelper.valueOf(this.getGlobals().getGlobalBLRed());
                }
            }
        } else {
            localBLGreen = EAQTHelper.valueOf(dp.getProviderBLGreen());
            localBLRed = EAQTHelper.valueOf(dp.getProviderBLRed());
        }

        String sweetspot = EAQTHelper.valueOf(dp.getProviderSweetspot());
        if (localBLGreen.compareTo("")!=0 && localBLRed.compareTo("")!=0 && localScaleMin.compareTo("")!=0 && localScaleMax.compareTo("")!=0 && sweetspot.compareTo("")!=0) {
            Double scaleMin  = Normalizer.round(Double.parseDouble(localScaleMin), Normalizer.STANDARD_PRECISION);
            Double scaleMax = Normalizer.round(Double.parseDouble(localScaleMax), Normalizer.STANDARD_PRECISION);
            Double intervalSize = Normalizer.round(Math.abs(scaleMax - scaleMin), Normalizer.STANDARD_PRECISION);
            Double benchmarkGreen = Normalizer.round(Double.parseDouble(localBLGreen) * intervalSize, Normalizer.STANDARD_PRECISION);
            Double benchmarkRed = Normalizer.round(Double.parseDouble(localBLRed) * intervalSize, Normalizer.STANDARD_PRECISION);
            Double BLMarginGreen = (EAQTHelper.valueOf(this.getGlobals().getGlobalBLMarginGreen()).compareTo("")!=0) ? Double.parseDouble(this.getGlobals().getGlobalBLMarginGreen()) * intervalSize : null;
            Double BLMarginRed = (EAQTHelper.valueOf(this.getGlobals().getGlobalBLMarginRed()).compareTo("")!=0) ? Double.parseDouble(this.getGlobals().getGlobalBLMarginRed()) * intervalSize : null;
            if (sweetspot.compareTo(Scale.SWEETSPOT_SCALE_MIN) == 0) {
                if (val <= benchmarkGreen) {
                    ret = BENCHMARK_LEVEL.BL_GREEN;
                } else if (val >= benchmarkRed) {
                    ret = BENCHMARK_LEVEL.BL_RED;
                } else {
                    ret = BENCHMARK_LEVEL.BL_YELLOW;
                    if (ret==BENCHMARK_LEVEL.BL_YELLOW && BLMarginGreen!=null && BLMarginRed!=null) {
                        if (val > benchmarkGreen && val <= benchmarkGreen+BLMarginGreen) {
                            ret = BENCHMARK_LEVEL.BL_YELLOW_PLUS;
                        } else if (val < benchmarkRed && val >= benchmarkRed-BLMarginRed) {
                            ret = BENCHMARK_LEVEL.BL_YELLOW_MINUS;
                        }
                    }
                }
            } else if (sweetspot.compareTo(Scale.SWEETSPOT_SCALE_MAX) == 0) {
                if (val >= benchmarkGreen) {
                    ret = BENCHMARK_LEVEL.BL_GREEN;
                } else if (val <= benchmarkRed) {
                    ret = BENCHMARK_LEVEL.BL_RED;
                } else {
                    ret = BENCHMARK_LEVEL.BL_YELLOW;
                    if (ret==BENCHMARK_LEVEL.BL_YELLOW && BLMarginGreen!=null && BLMarginRed!=null) {
                        if (val < benchmarkGreen && val >= benchmarkGreen-BLMarginGreen) {
                            ret = BENCHMARK_LEVEL.BL_YELLOW_PLUS;
                        } else if (val > benchmarkRed && val <= benchmarkRed+BLMarginRed) {
                            ret = BENCHMARK_LEVEL.BL_YELLOW_MINUS;
                        }
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Build a consistent filename for metric result persistence, based on given path.
     * @param path
     * @return String filename.
     */
    public String buildPersistenceFilename(String path) {
        return path +
               EAQTHelper.sanitizeFilename(this.getHeader().getDatasetName()) + "_" +
               EAQTHelper.sanitizeFilename(this.getHeader().getDatasetVersion()) + "_" +
               FilenameUtils.getBaseName(this.getDataSourceName()) + "." +
               JacksonPersistenceFactory.FILE_EXT;
    }

}