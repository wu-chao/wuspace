package com.wuspace.commons.util;

/**
 * Created by wu-chao on 17-8-20.
 */
public abstract class StringUtils {

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String convertArrayToString(Object[] ig) {
        if (ig != null && ig.length > 0) {
            StringBuilder str = new StringBuilder("");
            for (int i = 0; i < ig.length; i++) {
                str.append(String.valueOf(ig[i])).append(",");
            }
            return str.substring(0, str.length() - 1);
        }
        return "";
    }

    public static String cleanHTML(String content) {
        return content.replaceAll("</?[^>]+>", "").replaceAll("<a>\\s*|\t|\r|\n</a>", "");
    }

    public static Double getPercentValue(String percent) {
        if (!isEmpty(percent)) {
            return Double.valueOf(percent.substring(0, percent.length() - 1));
        } else {
            return null;
        }
    }
}
