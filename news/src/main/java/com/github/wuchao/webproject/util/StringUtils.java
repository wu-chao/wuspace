package com.github.wuchao.webproject.util;

public abstract class StringUtils {

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String convertArrayToString(Object[] ig) {
        return org.apache.commons.lang3.StringUtils.join(ig, ',');

//        if (ig != null && ig.length > 0) {
//            StringBuilder str = new StringBuilder("");
//            for (int i = 0; i < ig.length; i++) {
//                str.append(String.valueOf(ig[i])).append(",");
//            }
//            return str.substring(0, str.length() - 1);
//        }
//        return "";
    }

    /**
     * 去除 HTML 标签
     *
     * @param content
     * @return
     */
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

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    // 首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}