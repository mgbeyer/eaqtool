package de.rwth_aachen.swc.eaqtool.repo;

import java.util.Map;

public class XQSpecificationFactory extends SpecificationFactory {

    private String script;
    private Map<String, Object> extVars;

    public XQSpecificationFactory() {
    }

    /**
     * Return Specification object for xq query, prefix script with default namespace and provide map of ext. variables.
     * @return Specification for xq script.
     */
    public XQSpecification getSpecification() {
        return new XQSpecification(script, extVars);
    }

    /**
     * Load generic parameter object and initialize it to specific parameter to instantiate Specification with
     * (in this case: Either String for script or Map<String, Object> for parameters)
     * @param o Parameter object to load.
     */
    public void loadParam(Object o) {
        if (o instanceof String) {
            this.script = (String)o;
        }
        if (o instanceof Map) {
            this.extVars = (Map)o;
        }
    }

}
