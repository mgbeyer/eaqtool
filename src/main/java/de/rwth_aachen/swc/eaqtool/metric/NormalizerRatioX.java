package de.rwth_aachen.swc.eaqtool.metric;

public class NormalizerRatioX extends Normalizer {

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

    private NormalizerRatioX(Builder b) {
        if (b.reverseRatio!=null) this.reverseRatio = b.reverseRatio;
    }

    public Double normalize (Double val) {
        if(reverseRatio) {
            return 1 - (val / divisor);
        } else {
            return val / divisor;
        }
    }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_RATIOX;
    }

    public static class Builder {

        private Boolean reverseRatio = false;      // optional

        public Builder() { }

        public NormalizerRatioX.Builder setReverseRatio(Boolean reverseRatio) {
            this.reverseRatio = reverseRatio;
            return this;
        }

        public NormalizerRatioX build() {
            return new NormalizerRatioX(this);
        }
    }

}
