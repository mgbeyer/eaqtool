package de.rwth_aachen.swc.eaqtool.metric;

import java.util.Map;

import static java.lang.Math.exp;

public class Normalizer01sigmoid extends Normalizer {

    private Double k = 1.0;     // optional

    public Double getK() {
        return k;
    }

    public Normalizer01sigmoid(Builder b) {
        if (b.k!=null) this.k = b.k;
    }

    public Double normalize (Double val) {
        return Normalizer.round(( 2.0 / ( 1.0 + exp( -(val / getK()) ) ) ) - 1.0, Normalizer.STANDARD_PRECISION);
    }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_01SIGMOID;
    }

    public static class Builder {
        private Double k;

        public Builder() {
        }

        public Normalizer01sigmoid.Builder setK(Double k) {
            this.k = k;
            return this;
        }

        public Normalizer01sigmoid build() {
            return new Normalizer01sigmoid(this);

        }
    }

}
