package de.rwth_aachen.swc.eaqtool.metric;

import static java.lang.Math.exp;

public class Normalizer3pl extends Normalizer {

    private Double m = 1.0;     // optional
    private Double k = 1.0;     // optional
    private Double xzero;       // mandatory

    public Double getM() {
        return m;
    }

    public Double getK() {
        return k;
    }

    public Double getXzero() {
        return xzero;
    }

    private Normalizer3pl(Builder b) {
        if (b.m!=null) this.m = b.m;
        if (b.k!=null) this.k = b.k;
        this.xzero = b.xzero;
    }

    public Double normalize (Double val) {
        return Normalizer.round(getM() / ( 1.0 + exp( -getK() * ( val - getXzero() ) ) ), Normalizer.STANDARD_PRECISION);
    }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_3PL;
    }

    public static class Builder {
        private Double m;         // optional
        private Double k;         // optional
        private Double xzero;     // mandatory

        public Builder(Double xzero) {
            this.xzero = xzero;
        }

        public Normalizer3pl.Builder setM(Double m) {
            this.m = m;
            return this;
        }

        public Normalizer3pl.Builder setK(Double k) {
            this.k = k;
            return this;
        }

        public Normalizer3pl build() {
            return new Normalizer3pl(this);
        }
    }

}
