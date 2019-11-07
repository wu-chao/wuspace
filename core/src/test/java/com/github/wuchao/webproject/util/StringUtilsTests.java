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

}
