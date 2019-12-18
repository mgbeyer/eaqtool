package de.rwth_aachen.swc.eaqtool.metric;

import static java.lang.Math.pow;

public class NormalizerSbowles extends Normalizer {

    private Double s = 2.0;     // optional

    public Double getS() {
        return s;
    }

    private NormalizerSbowles(Builder b) {
        if (b.s!=null) this.s = b.s;
    }

    public Double normalize (Double val) {
        return Normalizer.round(pow(1.0 - (1.0 / (1.0 + val)), getS()), Normalizer.STANDARD_PRECISION);
    }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_SBOWLES;
    }

    public static class Builder {
        private Double s;

        public Builder() {
        }

        public Builder setS(Double s) {
            this.s = s;
            return this;
        }

        public NormalizerSbowles build() {
            return new NormalizerSbowles(this);
        }
    }

}
