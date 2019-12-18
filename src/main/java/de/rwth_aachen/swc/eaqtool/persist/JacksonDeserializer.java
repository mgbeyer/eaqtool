package de.rwth_aachen.swc.eaqtool.persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;

import java.io.*;
import java.util.Map;

public class JacksonDeserializer extends PojoDeserializer<ObjectMapper, Reader> {

    private ObjectMapper mapper;
    private FileReader reader;
    private Map<String, Object> params;

    protected JacksonDeserializer(ObjectMapper mapper, FileReader reader) {
        super(mapper, reader);
        this.mapper = mapper;
        this.reader = reader;
    }

    protected JacksonDeserializer(ObjectMapper mapper, FileReader reader, Map<String, Object> params) {
        super(mapper, reader);
        this.mapper = mapper;
        this.reader = reader;
        this.params = params;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public String getBaseName() {
        return EAQTHelper.paramsGetValueByKey(this.params, JacksonPersistenceFactory.PARAM_BASENAME_KEY)!=null ? EAQTHelper.paramsGetValueByKey(this.params, JacksonPersistenceFactory.PARAM_BASENAME_KEY).toString() : "";
    }

    public FileReader getReader() { return reader; }

    /**
     * Deserialize JSON-TS using Jackson ObjectMapper and Reader provided by the constructor.
     * @return MetricLog.
     */
    public MetricLog deserialize() {
        MetricLog ret = null;

        try {
            if (getReader()!=null) ret = getMapper().readValue(getReader(), MetricLog.class);
        } catch (IOException e) {
            System.out.println(e);
        }

        return ret;
    }

}
