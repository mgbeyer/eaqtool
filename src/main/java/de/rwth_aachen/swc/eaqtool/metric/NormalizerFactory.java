package de.rwth_aachen.swc.eaqtool.metric;

import de.rwth_aachen.swc.eaqtool.EAQTHelper;

import java.util.Map;

public class NormalizerFactory {

    public static final String NORMALIZER_TYPE_SBOWLES = "NT_SBOWLES";
    public static final String NORMALIZER_TYPE_01SIGMOID = "NT_01SIGMOID";
    public static final String NORMALIZER_TYPE_3PL = "NT_3PL";
    public static final String NORMALIZER_TYPE_STEP = "NT_STEP";
    public static final String NORMALIZER_TYPE_RAW = "NT_RAW";
    public static final String NORMALIZER_TYPE_RATIOX = "NT_RATIOX";
    public static final String NORMALIZER_TYPE_RATIOAB = "NT_RATIOAB";
    public static final String NORMALIZER_TYPE_MINMAX = "NT_MINMAX";

    public static Normalizer getNormalizer(String typestr) {
        Normalizer ret = null;

        String type = EAQTHelper.getTypeNameFromJsonTypestring(typestr);
        Map<String, Object> params = EAQTHelper.getParamsFromJsonTypestring(typestr);

        if (type.equals(NORMALIZER_TYPE_SBOWLES)) {
            Double par_s = null;
            if (params.get("s")!=null) {
                try {
                    par_s = Double.parseDouble(params.get("s").toString());
                } catch (NumberFormatException ex) {
                    System.err.println(ex);
                }
            }
            ret = new NormalizerSbowles.Builder()
                    .setS(par_s)
                    .build();
        } else if (type.equals(NORMALIZER_TYPE_01SIGMOID)) {
            Double par_k = null;
            if (params.get("k")!=null) {
                try {
                    par_k = Double.parseDouble(params.get("k").toString());
                } catch (NumberFormatException ex) {
                    System.err.println(ex);
                }
            }
            ret = new Normalizer01sigmoid.Builder()
                    .setK(par_k)
                    .build();
        } else if (type.equals(NORMALIZER_TYPE_3PL)) {
            Double par_m = null;
            Double par_k = null;
            Double par_xzero = null;
            if (params.get("m")!=null) {
                try {
                    par_m = Double.parseDouble(params.get("m").toString());
                } catch (NumberFormatException ex) {
                    System.err.println(ex);
                }
            }
            if (params.get("k")!=null) {
                try {
                    par_k = Double.parseDouble(params.get("k").toString());
                } catch (NumberFormatException ex) {
                    System.err.println(ex);
                }
            }
            if (params.get("xzero")!=null) {
                try {
                    par_xzero = Double.parseDouble(params.get("xzero").toString());
                } catch (NumberFormatException ex) {
                    System.err.println(ex);
                }
            }
            if (par_xzero!=null) {
                ret = new Normalizer3pl.Builder(par_xzero)
                        .setM(par_m)
                        .setK(par_k)
                        .build();
            } else {
                System.err.println("NormalizerFactory: Failed to create Normalizer3pl, missing parameter (xzero).");
            }
        } else if (type.equals(NORMALIZER_TYPE_STEP)) {
            ret = new NormalizerStep.Builder().build();
        } else if (type.equals(NORMALIZER_TYPE_RAW)) {
            ret = new NormalizerRaw.Builder().build();
        } else if (type.equals(NORMALIZER_TYPE_RATIOX)) {
            Boolean par_reverseRatio = false;
            if (params.get("reverseRatio")!=null) {
                par_reverseRatio = Boolean.parseBoolean(params.get("reverseRatio").toString());
            }
            ret = new NormalizerRatioX.Builder()
                    .setReverseRatio(par_reverseRatio)
                    .build();
        } else if (type.equals(NORMALIZER_TYPE_RATIOAB)) {
            Boolean par_reverseRatio = false;
            if (params.get("reverseRatio")!=null) {
                par_reverseRatio = Boolean.parseBoolean(params.get("reverseRatio").toString());
            }
            ret = new NormalizerRatioAb.Builder()
                    .setReverseRatio(par_reverseRatio)
                    .build();
        } else if (type.equals(NORMALIZER_TYPE_MINMAX)) {
            Double par_min = null;
            Double par_max = null;
            if (params.get("min")!=null) {
                try {
                    par_min = Double.parseDouble(params.get("min").toString());
                } catch (NumberFormatException ex) {
                    System.err.println(ex);
                }
            }
            if (params.get("max")!=null) {
                try {
                    par_max = Double.parseDouble(params.get("max").toString());
                } catch (NumberFormatException ex) {
                    System.err.println(ex);
                }
            }
            if (par_min!=null && par_max!=null) {
                ret = new NormalizerMinMax.Builder(par_min, par_max)
                        .setMin(par_min)
                        .setMax(par_max)
                        .build();
            } else {
                System.err.println("NormalizerFactory: Failed to create NormalizerMinMax, missing parameter(s) (min/max).");
            }
        }

        if (ret!=null) ret.setInvokedTypestr(typestr);

        return ret;
    }

}