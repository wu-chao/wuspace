package com.wuspace.util;

import java.text.NumberFormat;

public abstract class NumberUtils {

    public static Double format(Object source, int precision) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(precision);
        return Double.valueOf(numberFormat.format(source).replaceAll(",", ""));
    }
}
