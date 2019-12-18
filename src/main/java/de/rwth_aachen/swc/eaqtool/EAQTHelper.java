package de.rwth_aachen.swc.eaqtool;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class EAQTHelper {

    public static final String ISO8601_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Get base directory for resources.
     * @return Base dir with appended trailing seperator.
     */
    public static String getResourceDirectory() {
        Path resourceDirectory = Paths.get("src", "main", "resources");
        return resourceDirectory.toFile().getAbsolutePath() + File.separator;
    }

    /**
     * Get map of key-value-pairs from a given type string used in JSON descriptor files.
     * @param typestr Type string to parse.
     * @return Map containing keys (Strings) asd values (Objects) of parsed type string.
     */
    public static Map<String, Object> getParamsFromJsonTypestring (String typestr) {
        Map<String, Object> ret = new HashMap<String, Object>();

        if (!typestr.isEmpty() && typestr.split(":").length==2) {
            for (String pair : typestr.split(":")[1].split(",")) {
                String[] kv = pair.split("=");
                ret.put(kv[0], kv.length==2 ? kv[1] : null);
            }
        }

        return ret;
    }

    /**
     * Get type name from a given type string used in JSON descriptor files.
     * @param typestr Type string to parse.
     * @return Type name.
     */
    public static String getTypeNameFromJsonTypestring (String typestr) {
        return typestr.split(":")[0];
    }

    /**
     * Concat Strings from a List to a single String (each line terminated by linebreak).
     * @param strlist List of Strings (script).
     * @return Script in a single String.
     */
    public static String assembleScriptFromList(List<String> strlist) {
        return String.join("\n", strlist);
    }

    /**
     * Return true if a parameter map contains a given key with a given value.
     * @param params Parameter Map to search, String for key, Object for value.
     * @param key String of key to search for.
     * @param value Object representing the value to search for (usually a String).
     * @return Boolean.
     */
    public static Boolean paramsContainsKeyWithValue(Map<String, Object> params, String key, Object value) {
        Boolean ret = false;

        if (params!=null) {
            if (params.containsKey(key)) {
                if (params.get(key).toString().equals(value)) {
                    ret = true;
                }
            }
        }

        return ret;
    }

    /**
     * Return value object from a parameter map by a given key.
     * @param params Parameter Map to search, String for key, Object for value.
     * @param key String of key to search for.
     * @return Value Object.
     */
    public static Object paramsGetValueByKey(Map<String, Object> params, String key) {
        Object ret = null;

        if (params!=null) {
            if (params.containsKey(key)) {
                ret = params.get(key);
            }
        }

        return ret;
    }

    /**
     * Convert a java.util.Date to ISO 8601 date string.
     * @param date Date to convert.
     * @param format Format String.
     * @return Convertes ISO 8601 conform String representation of given Date.
     */
    public static String date2ISO8601 (Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Clear String of characters unfit for a filename.
     * @param name String to clean.
     * @return Cleaned-up String.
     */
    public static String sanitizeFilename(String name) {
        return name.replaceAll("\\W+", "");
    }

    /**
     * Return a List of Strings containing IDs from a given List of POJO objects.
     * @param obj List of POJOs.
     * @param getIdMethod Name of Method to get ID, if empty the default (getId) will be taken.
     * @return POJO ID List.
     */
    public static <T> List<String> extractIdsFromPojoList(List<T> obj, String getIdMethod) {
        List<String> ret = new ArrayList<>();

        try {
            if (getIdMethod==null || getIdMethod=="") getIdMethod = "getId";
            for (T it : obj) {
                ret.add(it.getClass().getMethod("getId").invoke(it).toString());
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

        return ret;
    }

    /**
     * Replacement for Object toString to avoid NullPointerExceptions.
     * @param obj Object to get String representation from.
     * @return Null-safe toString representation of obj.
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * Transforms a String into lower case with first character capitalized.
     * @param s String to transform.
     * @return Resulting String.
     */
    public static String capStr(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

}
