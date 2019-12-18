package de.rwth_aachen.swc.eaqtool.metric;

public class NormalizerRaw extends Normalizer {

    private NormalizerRaw(Builder b) { }

    public Double normalize (Double val) { return val; }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_RAW;
    }

    public static class Builder {

        public Builder() { }

        public NormalizerRaw build() {
            return new NormalizerRaw(this);
        }
    }

}
