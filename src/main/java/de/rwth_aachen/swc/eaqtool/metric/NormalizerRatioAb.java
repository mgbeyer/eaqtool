package de.rwth_aachen.swc.eaqtool.metric;

public class NormalizerRatioAb extends Normalizer {

    private Boolean reverseRatio = false;      // optional
    private Double divisor = 1.0;              // non-builder

    public Boolean getReverseRatio() {
        return reverseRatio;
    }

    public Double getDivisor() {
        return divisor;
    }

    public void setDivisor(Double divisor) {
        this.divisor = divisor;
    }

    private NormalizerRatioAb(Builder b) {
        if (b.reverseRatio!=null) this.reverseRatio = b.reverseRatio;
    }

    public Double normalize (Double val) {
        if(reverseRatio) {
            return 1 - (val / (val + divisor));
        } else {
            return val / (val + divisor);
        }
    }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_RATIOAB;
    }

    public static class Builder {

        private Boolean reverseRatio = false;      // optional

        public Builder() { }

        public NormalizerRatioAb.Builder setReverseRatio(Boolean reverseRatio) {
            this.reverseRatio = reverseRatio;
            return this;
        }

        public NormalizerRatioAb build() {
            return new NormalizerRatioAb(this);
        }
    }

}
