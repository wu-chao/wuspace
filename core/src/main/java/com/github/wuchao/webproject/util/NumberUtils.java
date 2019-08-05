package com.github.wuchao.webproject.util;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.HashMap;

public abstract class NumberUtils {

    public static Double format(Object source, int precision) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(precision);
        return Double.valueOf(numberFormat.format(source).replaceAll(",", ""));
    }

    /**
     * 阿拉伯数字转中文数字
     *
     * @param num
     * @return
     */
    public static String trans2ChineseNumber(int num) {

        String[] numeric = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        StringBuilder builder = new StringBuilder();
        builder.append(numeric[num / 1000] + "千").
                append(numeric[num / 100 % 10] + "百").
                append(numeric[num / 10 % 10] + "十").
                append(numeric[num % 10]);

        //去掉了零千....
        int index = -1;
        while ((index = builder.indexOf(numeric[0], index + 1)) != -1) {
            if (index < builder.length() - 1) {
                builder.deleteCharAt(index + 1);
            }
        }

        //去掉双零
        index = 0;
        while ((index = builder.indexOf("零零", index)) != -1) {
            builder.deleteCharAt(index);
        }

        if (builder.length() > 1) {
            //去掉开头的零
            if (builder.indexOf(numeric[0]) == 0) {
                builder.deleteCharAt(0);
            }
            //去掉末尾的零
            if (builder.indexOf(numeric[0]) == builder.length() - 1) {
                builder.deleteCharAt(builder.length() - 1);
            }

        }

        // 把开头一十换成十
        if (builder.indexOf("一十") == 0) {
            builder.deleteCharAt(0);
        }
        return builder.toString();

    }

    /**
     * 当浮点型数据位数超过10位之后，数据变成科学计数法显示。用此方法可以使其正常显示。
     *
     * @param value
     * @return Sting
     */
    public static String formatDouble(double value) {
        if (value != 0.00) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
            return df.format(value);
        } else {
            return "0.00";
        }

    }

    public static HashMap<String, Long> chineseNumbers = new HashMap<String, Long>() {
        private static final long serialVersionUID = 1L;

        {
            put("零", 0L);
            put("一", 1L);
            put("壹", 1L);
            put("二", 2L);
            put("两", 2L);
            put("貳", 2L);
            put("贰", 2L);
            put("叁", 3L);
            put("參", 3L);
            put("三", 3L);
            put("肆", 4L);
            put("四", 4L);
            put("五", 5L);
            put("伍", 5L);
            put("陸", 6L);
            put("陆", 6L);
            put("六", 6L);
            put("柒", 7L);
            put("七", 7L);
            put("捌", 8L);
            put("八", 8L);
            put("九", 9L);
            put("玖", 9L);
            put("十", 10L);
            put("拾", 10L);
            put("佰", 100L);
            put("百", 100L);
            put("仟", 1000L);
            put("千", 1000L);
            put("万", 10000L);
            put("萬", 10000L);
            put("億", 100000000L);
            put("亿", 100000000L);
        }
    };

    /**
     * 返回中文数字字符串所对应的 long 类型的阿拉伯数字
     * <p>
     * 汉字表示的数字转换成阿拉伯数字（https://www.oschina.net/code/snippet_2621936_53837）
     * <p>
     * 先把汉字数字模型作为二叉树(binary tree)的数据结构：每一个节点是一个汉字,每一层上的节点数都是最大节点数。第一步是把汉字里最大的数值作为父节点。每一个父节点的左节点是左边汉字的最大数值，右节点是右边汉字的最大数值。
     * 二叉树形成完了之后,算法很简单:每个父节点乘以它的左节点，加上它的右节点。最后可以用递归函数算出来。
     *
     * @param chineseNumStr
     * @return
     */
    public static Long trans2Number(String chineseNumStr) {
        int sLen = chineseNumStr.length();
        if (sLen == 0) {
            return 0L;
        }
        if (sLen > 1) {
            // index of the highest singular character value in the string
            int pivot = 0;

            // loop through the characters in the string to get the character with the highest value. That is your pivot
            for (int i = 0; i < sLen; i++) {
                if (trans2Number(String.valueOf(chineseNumStr.charAt(i))) > trans2Number(String.valueOf(chineseNumStr.charAt(pivot)))) {
                    pivot = i;
                }
            }

            long value = trans2Number(String.valueOf(chineseNumStr.charAt(pivot)));
            long LHS, RHS;

            // multiply value with LHS
            LHS = trans2Number(chineseNumStr.substring(0, pivot));

            // add value with RHS
            RHS = trans2Number(chineseNumStr.substring(pivot + 1));

            if (LHS > 0) {
                value *= LHS;
            }

            value += RHS;
            return value;

        } else {

            return chineseNumbers.get(chineseNumStr).longValue();
        }
    }

    /**
     * 若double是整数则去掉零
     *
     * @param d
     * @return
     */
    public static String doubleOrInt(Double d) {
        if (d == null) {
            return null;
        }
        return d - d.intValue() == 0 ? String.valueOf(d.intValue()) : String.valueOf(d);
    }

    /**
     * 判断字符串是否是数字类型（int、long、float、double 之类的）
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.matches("[-+]?[0-9]*\\.?[0-9]+");
        }
        return false;
    }

}
