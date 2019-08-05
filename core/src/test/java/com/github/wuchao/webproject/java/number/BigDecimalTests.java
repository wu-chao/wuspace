package com.github.wuchao.webproject.java.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTests {

    @Test
    public void testScale() {
        System.out.println(BigDecimal.valueOf(80.5).setScale(1, RoundingMode.HALF_UP).doubleValue());
        System.out.println(BigDecimal.valueOf(80.5).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(BigDecimal.valueOf(80.5555).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(new BigDecimal(new Double(80.5555)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(new BigDecimal("80.5").setScale(1, RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal("80.5").setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(new BigDecimal("80.5555").setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());

        /**
         * 打印结果：
         * 80.5
         * 80.5
         * 80.556
         * 80.555
         * 80.5
         * 80.5
         * 80.556
         */
    }

}
