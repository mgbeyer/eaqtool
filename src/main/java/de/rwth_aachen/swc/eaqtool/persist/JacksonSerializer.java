package de.rwth_aachen.swc.eaqtool.persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;

import java.io.*;
import java.util.Map;

public class JacksonSerializer extends PojoSerializer<ObjectMapper, Writer> {

    private ObjectMapper mapper;
    private FileWriter writer;
    private Map<String, Object> params;

    protected JacksonSerializer(ObjectMapper mapper, FileWriter writer) {
        super(mapper, writer);
        this.mapper = mapper;
        this.writer = writer;
    }

    protected JacksonSerializer(ObjectMapper mapper, FileWriter writer, Map<String, Object> params) {
        super(mapper, writer);
        this.mapper = mapper;
        this.writer = writer;
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

    public FileWriter getWriter() {
        return writer;
    }

    /**
     * Serialize JSON-TS using Jackson ObjectMapper and Writer provided by the constructor.
     * Makes exemplary use of an optionally provided parameter map (serialize using pretty formatting or condensed).
     * @param o POJO Object to serialize.
     */
    public void serialize(Object o) {
        MetricLog log = (MetricLog)o;
        try {
            if (EAQTHelper.paramsContainsKeyWithValue(
                    getParams(), JacksonPersistenceFactory.PARAM_FORMAT_KEY, JacksonPersistenceFactory.PARAM_FORMAT_VALUE_PRETTY
            )) {
                getMapper().writerWithDefaultPrettyPrinter().writeValue(getWriter(), log);
            } else {
                getMapper().writeValue(getWriter(), log);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
