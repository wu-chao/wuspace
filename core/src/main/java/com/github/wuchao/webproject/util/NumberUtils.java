package com.github.wuchao.webproject.util;

import java.text.NumberFormat;

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

        //把开头一十换成十
        if (builder.indexOf("一十") == 0) {
            builder.deleteCharAt(0);
        }
        return builder.toString();

    }

}
