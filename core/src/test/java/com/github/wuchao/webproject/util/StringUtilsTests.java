package com.github.wuchao.webproject.util;

import org.junit.Test;

public class StringUtilsTests {

    @Test
    public void testUnderline2hump() {
        String str1 = "abc_d";
        String str2 = "abc_D";
        String str3 = "ABC_d";
        String str4 = "ABC_D";

        System.out.println(StringUtils.underline2hump(str1));
        System.out.println(StringUtils.underline2hump(str2));
        System.out.println(StringUtils.underline2hump(str3));
        System.out.println(StringUtils.underline2hump(str4));

        /**
         * 执行结果：
         * abcD
         * abcD
         * abcD
         * abcD
         */
    }

    @Test
    public void testHump2upperCaseUnderline1() {
        String str1 = "abc_d";
        String str2 = "abcD";
        String str3 = "ABCD";
        String str4 = "abcd";
        String str5 = "abc_D";
        String str6 = "ABC_d";
        String str7 = "abc_";
        String str8 = "_d";

        System.out.println(StringUtils.hump2upperCaseUnderline(str1));
        System.out.println(StringUtils.hump2upperCaseUnderline(str2));
        System.out.println(StringUtils.hump2upperCaseUnderline(str3));
        System.out.println(StringUtils.hump2upperCaseUnderline(str4));
        System.out.println(StringUtils.hump2upperCaseUnderline(str5));
        System.out.println(StringUtils.hump2upperCaseUnderline(str6));
        System.out.println(StringUtils.hump2upperCaseUnderline(str7));
        System.out.println(StringUtils.hump2upperCaseUnderline(str8));

        /**
         * 执行结果：
         * ABC__D
         * ABC_D
         * A_B_C_D
         * ABCD
         * ABC___D
         * A_B_C__D
         * ABC__
         * _D
         */
    }

    @Test
    public void testSubString() {
        String dateTimeStr = "2019-11-14 20:14:58.000";
        System.out.println(dateTimeStr.substring(0, 10));
        System.out.println(dateTimeStr.substring(0, 19));

        String dateStr = "2019-11-14";
        System.out.println(dateStr.substring(0, 10));
        System.out.println(dateStr.substring(0, 19));
    }

    @Test
    public void testRemoveTrailingZero() {
        String str1 = "1234";
        String str2 = "1234.0";
        String str3 = "1234.10";
        String str4 = "1234.110000";

        System.out.println(StringUtils.removeTrailingZero(str1));
        System.out.println(StringUtils.removeTrailingZero(str2));
        System.out.println(StringUtils.removeTrailingZero(str3));
        System.out.println(StringUtils.removeTrailingZero(str4));

        /**
         * 执行结果：
         * 1234
         * 1234
         * 1234.1
         * 1234.11
         */
    }

    @Test
    public void testRemoveTrailingZero2() {
        String str1 = "1234";
        String str2 = "1234.0";
        String str3 = "1234.10";
        String str4 = "1234.110000";

        System.out.println(StringUtils.removeTrailingZero2(str1));
        System.out.println(StringUtils.removeTrailingZero2(str2));
        System.out.println(StringUtils.removeTrailingZero2(str3));
        System.out.println(StringUtils.removeTrailingZero2(str4));

        /**
         * 执行结果：
         * 1234
         * 1234
         * 1234.1
         * 1234.11
         */
    }

}
