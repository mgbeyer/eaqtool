package de.rwth_aachen.swc.eaqtool.view;

import de.rwth_aachen.swc.eaqtool.metric.Scale;
import de.rwth_aachen.swc.eaqtool.persist.MetricLog;
import org.fusesource.jansi.Ansi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.fusesource.jansi.Ansi.ansi;

public class ANSIConsoleView {

    public static final String STRING_PADDING_CHAR = ".";
    public static final String BL_MARKER_GREEN = "*";
    public static final String BL_MARKER_RED = "!";
    public static final String BL_MARKER_YELLOW_PLUS = "+";
    public static final String BL_MARKER_YELLOW_MINUS = "-";
    public static final String BL_MARKER_SWEETSPOT_MIN = "↓";
    public static final String BL_MARKER_SWEETSPOT_MAX = "↑";

    private static Map<String, String> headerText = new HashMap<>();

    public ANSIConsoleView() {
        headerText.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASOURCE_NAME, "Data source name");
        headerText.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASOURCE_ID, "Data source ID");
        headerText.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASET_NAME, "Dataset name");
        headerText.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASET_VERSION, "Dataset version");
        headerText.put(ModelViewCoordinator.VIEW_HEADER_KEY_DATASET_DATE, "Dataset date");
    }

    public void display(ViewOutput output) {
        int pad = getLongestDataProviderName(output);
        for (Map.Entry<String, String> h : output.getHeader().entrySet()) {
            System.out.println( ansi().
                    fgMagenta().
                    a(Ansi.Attribute.INTENSITY_BOLD).
                    a(headerText.get(h.getKey()) + ": ").
                    fgDefault().
                    a(Ansi.Attribute.INTENSITY_BOLD_OFF).
                    a(h.getValue())
            );
        }
        System.out.println();
        for (Map.Entry<String, Map<String, List<String>>> entry : output.getData().entrySet()) {
            System.out.println( ansi().
                    fgCyan().
                    a(Ansi.Attribute.INTENSITY_BOLD).
                    a(Ansi.Attribute.UNDERLINE).
                    a(entry.getKey()).
                    a(Ansi.Attribute.UNDERLINE_OFF).
                    a(Ansi.Attribute.INTENSITY_BOLD_OFF)
            );
            Map<String, List<String>> line = entry.getValue();
            for (Map.Entry<String, List<String>> datum : line.entrySet()) {
                String name = rightPad(datum.getKey(), pad, STRING_PADDING_CHAR);
                String normValue = datum.getValue().get(0);
                String rawValue = datum.getValue().get(1);
                String BLmarker = datum.getValue().get(2);
                String sweetspot = datum.getValue().get(3);
                String spmarker = " ";
                if (sweetspot.compareTo(Scale.SWEETSPOT_SCALE_MAX)==0) {
                    spmarker = BL_MARKER_SWEETSPOT_MAX;
                } else if (sweetspot.compareTo(Scale.SWEETSPOT_SCALE_MIN)==0) {
                    spmarker = BL_MARKER_SWEETSPOT_MIN;
                }
                Ansi.Color cfgName = Ansi.Color.DEFAULT;
                Ansi.Color cbgName = Ansi.Color.DEFAULT;
                Ansi.Color cfgMarker = Ansi.Color.DEFAULT;
                Ansi.Color cbgMarker = Ansi.Color.DEFAULT;
                String BLGreenRedTextMarker = "";
                String BLYellowTextMarker = "";
                if (BLmarker==BL_MARKER_GREEN || BLmarker==BL_MARKER_RED) {
                    BLYellowTextMarker = " ";
                    if (BLmarker==BL_MARKER_GREEN) {
                        cfgName = Ansi.Color.BLACK;
                        cbgName = Ansi.Color.GREEN;
                        BLGreenRedTextMarker = BL_MARKER_GREEN + " ";
                    }
                    if (BLmarker==BL_MARKER_RED) {
                        cfgName = Ansi.Color.BLACK;
                        cbgName = Ansi.Color.RED;
                        BLGreenRedTextMarker = BL_MARKER_RED + " ";
                    }
                } else {
                    BLGreenRedTextMarker = "  ";
                    cfgName = Ansi.Color.DEFAULT;
                    cbgName = Ansi.Color.DEFAULT;
                    if (BLmarker==BL_MARKER_YELLOW_PLUS) {
                        cfgMarker = Ansi.Color.BLACK;
                        cbgMarker = Ansi.Color.GREEN;
                        BLYellowTextMarker = BL_MARKER_YELLOW_PLUS;
                    } else if (BLmarker==BL_MARKER_YELLOW_MINUS) {
                        cfgMarker = Ansi.Color.BLACK;
                        cbgMarker = Ansi.Color.RED;
                        BLYellowTextMarker = BL_MARKER_YELLOW_MINUS;
                    } else {
                        BLYellowTextMarker = " ";
                        cfgMarker = Ansi.Color.DEFAULT;
                        cbgMarker = Ansi.Color.DEFAULT;
                    }
                }
                String rawVal = rawValue.compareTo("-")!=0 ? "(" + rawValue + ")" : "";
                System.out.println( ansi().
                        fg(cfgName).bg(cbgName).
                        a(BLGreenRedTextMarker).a(name).a(": ").a(spmarker).a(" ").a(normValue).
                        fgDefault().bgDefault().
                        a(" ").fg(cfgMarker).bg(cbgMarker).a(BLYellowTextMarker).fgDefault().bgDefault().a(" ").
                        a(rawVal)
                );
            }
            String good = output.getBltotals().get(entry.getKey()).get(MetricLog.BENCHMARK_LEVEL.BL_GREEN.toString());
            String norm = output.getBltotals().get(entry.getKey()).get(MetricLog.BENCHMARK_LEVEL.BL_YELLOW.toString());
            String bad = output.getBltotals().get(entry.getKey()).get(MetricLog.BENCHMARK_LEVEL.BL_RED.toString());
            System.out.println( ansi().
                    fgBlue().
                    a(Ansi.Attribute.INTENSITY_BOLD).
                    a("% (+) " + good + " (o) " + norm + " (-) " + bad).
                    a(Ansi.Attribute.INTENSITY_BOLD_OFF)
            );
            System.out.println();
        }
        String good = output.getQindex().get(MetricLog.BENCHMARK_LEVEL.BL_GREEN.toString());
        String norm = output.getQindex().get(MetricLog.BENCHMARK_LEVEL.BL_YELLOW.toString());
        String bad = output.getQindex().get(MetricLog.BENCHMARK_LEVEL.BL_RED.toString());
        System.out.println( ansi().
                fgMagenta().
                a(Ansi.Attribute.INTENSITY_BOLD).
                a("Overall % (+) " + good + " (o) " + norm + " (-) " + bad).
                a(Ansi.Attribute.INTENSITY_BOLD_OFF)
        );
    }

    private int getLongestDataProviderName(ViewOutput output) {
        int ret = 0;

        for (Map.Entry<String, Map<String, List<String>>> entry : output.getData().entrySet()) {
            Map<String, List<String>> line = entry.getValue();
            for (Map.Entry<String, List<String>> datum : line.entrySet()) {
                if (ret<datum.getKey().length()) ret = datum.getKey().length();
            }
        }

        return ret;
    }

}
