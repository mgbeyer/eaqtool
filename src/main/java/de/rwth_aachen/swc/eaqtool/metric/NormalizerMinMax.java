package de.rwth_aachen.swc.eaqtool.metric;

public class NormalizerMinMax extends Normalizer {

    private Double min;     // mandatory
    private Double max;     // mandatory

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    private NormalizerMinMax(Builder b) {
        this.min = b.min;
        this.max = b.max;
    }

    public Double normalize (Double val) {
        return Normalizer.round( (val-getMin()) / (getMax()-getMin()), Normalizer.STANDARD_PRECISION );
    }

    public String getType() {
        return NormalizerFactory.NORMALIZER_TYPE_MINMAX;
    }

    public static class Builder {
        private Double min;         // mandatory
        private Double max;         // mandatory

        public Builder(Double mix, Double max) {
            this.min = min;
            this.max = max;
        }

        public NormalizerMinMax.Builder setMin(Double min) {
            this.min = min;
            return this;
        }

        public NormalizerMinMax.Builder setMax(Double max) {
            this.max = max;
            return this;
        }

        public NormalizerMinMax build() {
            return new NormalizerMinMax(this);
        }
    }

}
