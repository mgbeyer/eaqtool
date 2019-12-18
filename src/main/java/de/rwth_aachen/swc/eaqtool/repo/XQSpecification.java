package de.rwth_aachen.swc.eaqtool.repo;

import de.rwth_aachen.swc.eaqtool.metric.Ammp;
import de.rwth_aachen.swc.eaqtool.metric.MSMeta;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XQSpecification implements Specification {

    public static final String REGEX_INCLUDE = "(\\(:\\@=([a-zA-Z0-9_-]+):\\))";

    private String script;
    private Map<String, Object> extVars;

    public XQSpecification(String script, Map<String, Object> extVars) {
        this.script = script;
        this.extVars = extVars;
    }

    public String getScript() {
        return script;
    }

    public Map<String, Object> getExtVars() {
        return extVars;
    }

    public Object getExtVar(String key) {
        return extVars.get(key);
    }

    public void setExtVar(String key, Object value) {
        extVars.put(key, value);
    }

    @Override
    public String toQuery() {
        return handleIncludes();
    }


    /**
     * Parse a script and fill in all includes.
     * @return Script with all include directives replaced by their respective AMMP script snippets.
     */
    private String handleIncludes() {
        Pattern r = Pattern.compile(REGEX_INCLUDE);
        Matcher m = r.matcher(this.script);
        StringBuffer sb = new StringBuffer(this.script.length());
        String snippet = "";
        while (m.find()) {
            Ammp ammp = MSMeta.getInstance().getAmmpById(m.group(2));
            snippet = ammp!=null ? ammp.getScriptStr() : "";
            m.appendReplacement(sb, Matcher.quoteReplacement(snippet));
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
