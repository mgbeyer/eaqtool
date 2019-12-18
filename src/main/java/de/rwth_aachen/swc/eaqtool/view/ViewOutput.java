package de.rwth_aachen.swc.eaqtool.view;

import java.util.List;
import java.util.Map;

/**
 * Dumb and simple output object to be sent to a view for display (a more elaborated view might need something more elaborated ;-))
 * header: Map of Strings to be displayed as a header section, K=name/description, V=content
 * data: Map of K=category Id (eg. QRC), V=Map of K=Data provider name, V=list of data Strings (norm. value, raw value, BL-marker)
 * bltotals: Map of K=category Id, V=Map of V=BL, K=% of occurences in this category
 * qindex: Map of K=BL, K=overall % of occurences
 */
public class ViewOutput {

    private Map<String, String> header;
    private Map<String, Map<String, List<String>>> data;
    private Map<String, Map<String, String>> bltotals;
    private Map<String, String> qindex;

    public ViewOutput(Map<String, String> header, Map<String, Map<String, List<String>>> data, Map<String, Map<String, String>> bltotals, Map<String, String> qindex) {
        this.header = header;
        this.data = data;
        this.bltotals = bltotals;
        this.qindex = qindex;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, Map<String, List<String>>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, List<String>>> data) {
        this.data = data;
    }

    public Map<String, Map<String, String>> getBltotals() { return bltotals; }

    public void setBltotals(Map<String, Map<String, String>> bltotals) { this.bltotals = bltotals; }

    public Map<String, String> getQindex() { return qindex; }

    public void setQindex(Map<String, String> qindex) { this.qindex = qindex; }
}
