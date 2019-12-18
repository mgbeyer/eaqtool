package de.rwth_aachen.swc.eaqtool.persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.rwth_aachen.swc.eaqtool.EAQTHelper;

import java.io.*;
import java.util.Map;

public class JacksonPersistenceFactory extends PojoPersistenceFactory {

    public static final String FWTYPE_FILE = "file";
    public static final String FILE_EXT = "json";
    public static final String PARAM_FORMAT_KEY = "format";
    public static final String PARAM_FORMAT_VALUE_PRETTY = "pretty";
    public static final String PARAM_BASENAME_KEY = "basename";

    private String rwtype;
    private Map<String, Object> params;

    public JacksonPersistenceFactory(String rwType) {
        this.rwtype = rwType;
    }

    public JacksonPersistenceFactory(String rwType, Map<String, Object> params) {
        this.rwtype = rwType;
        this.params = params;
    }

    public PojoSerializer getPojoSerializer() {
        ObjectMapper m = new ObjectMapper();
        FileWriter w = null;
        String baseName = EAQTHelper.paramsGetValueByKey(this.params, PARAM_BASENAME_KEY)!=null ? EAQTHelper.paramsGetValueByKey(this.params, PARAM_BASENAME_KEY).toString() : null;
        try {
            switch (rwtype) {
                case FWTYPE_FILE:
                    if (baseName!=null) {
                        File f = new File(baseName);
                        f.getParentFile().mkdirs();
                        w = new FileWriter(f);
                    }
                break;
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        return new JacksonSerializer(m, w, this.params);
    }

    public PojoDeserializer getPojoDeserializer() {
        ObjectMapper m = new ObjectMapper();
        FileReader r = null;
        String baseName = EAQTHelper.paramsGetValueByKey(this.params, PARAM_BASENAME_KEY)!=null ? EAQTHelper.paramsGetValueByKey(this.params, PARAM_BASENAME_KEY).toString() : null;
        try {
            switch (rwtype) {
                case FWTYPE_FILE:
                    if (baseName!=null) {
                        if (new File(baseName).exists()) {
                            r = new FileReader(baseName);
                        }
                    }
                    break;
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        return new JacksonDeserializer(m, r, this.params);
    }

}
