package de.rwth_aachen.swc.eaqtool.metric;

public class NormalizerStep extends Normalizer {

    private NormalizerStep(Builder b) { }

    public Double normalize (Double val) {
        return val > 0.0 ? 1.0 : 0.0;
    }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_STEP;
    }

    public static class Builder {

        public Builder() { }

        public NormalizerStep build() {
            return new NormalizerStep(this);
        }
    }

}
