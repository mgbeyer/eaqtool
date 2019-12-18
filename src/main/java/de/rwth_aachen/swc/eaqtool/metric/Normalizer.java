package de.rwth_aachen.swc.eaqtool.metric;

import org.apache.commons.math3.util.Precision;

abstract public class Normalizer {

    private String invokedTypestr;

    public static final Integer STANDARD_PRECISION = 2;

    public static Double round(Double val, Integer precision) {
        return Precision.round(val, precision);
    }

    abstract public Double normalize (Double val);
    abstract public String getType();

    public String getInvokedTypestr() {
        return this.invokedTypestr;
    }

    public void setInvokedTypestr(String invokedTypestr) {
        this.invokedTypestr = invokedTypestr;
    }
}
