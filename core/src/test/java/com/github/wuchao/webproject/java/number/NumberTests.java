package com.github.wuchao.webproject.java.number;

import com.github.wuchao.webproject.util.NumberUtils;
import org.junit.Test;

public class NumberTests {

    @Test
    public void testTrans2ChineseNumber() {
        System.out.println(NumberUtils.trans2ChineseNumber(2018)); // 二千零一十八
    }

    @Test
    public void testTrans2Number() {
        System.out.println(NumberUtils.trans2Number("十六"));
        System.out.println(NumberUtils.trans2Number("一十六"));
        System.out.println(NumberUtils.trans2Number("一百一十六"));
        System.out.println(NumberUtils.trans2Number("三千五百六十一"));
        System.out.println(NumberUtils.trans2Number("三万六千七百九十八亿三千两百七十五万八千六百九十九"));
    }

}
