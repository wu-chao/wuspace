package com.github.wuchao.webproject.java.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTests {

    @Test
    public void testScale() {
        System.out.println(BigDecimal.valueOf(80.55).setScale(1, RoundingMode.HALF_UP).doubleValue());
        System.out.println(BigDecimal.valueOf(80.55).setScale(1, RoundingMode.HALF_DOWN).doubleValue());
        System.out.println(BigDecimal.valueOf(80.55).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(BigDecimal.valueOf(80.5555).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(BigDecimal.valueOf(new Double(80.5555).doubleValue()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());

        System.out.println(new BigDecimal(new Double(80.5555)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(new BigDecimal(new Double(80.5555).doubleValue()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());

        System.out.println(new BigDecimal("80.5").setScale(1, RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal("80.5").setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(new BigDecimal("80.5555").setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());

        /**
         * 打印结果：
         * 80.6
         * 80.5
         * 80.6
         * 80.556
         * 80.556
         *
         * 80.555
         * 80.555
         *
         * 80.5
         * 80.5
         * 80.556
         *
         * 不要使用 new BigDecimal(double/Double) 这种形式，BigDecimal 的构造函数 public BigDecimal(double val) 会损失了 double 参数的精度
         * 建议使用类似下面形式的方法来将 double/Double 转换成 BigDecimal：
         * public static BigDecimal valueOf(double val)
         * public BigDecimal(String val)
         */
    }

    @Test
    public void testE() {
        BigDecimal bigDecimal1 = new BigDecimal(0E-12);
        BigDecimal bigDecimal2 = new BigDecimal((Object)"0E-12");
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
    }

}
