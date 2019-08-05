package com.github.wuchao.webproject.java;

import org.junit.Test;

public class TernaryOperatorTests {

    @Test
    public void testNullPointerException1() {
        Double d = null;
        Integer i = d != null ? d.intValue() : 0;
        System.out.println(i);
    }

    @Test
    public void testNullPointerException2() {
        Double d = null;
        Integer i = null;
        i = d != null ? d.intValue() : i;
        System.out.println(i);
    }

    @Test
    public void testNullPointerException3() {
        Double d = null;
        Integer i = d != null ? d.intValue() : null;
        System.out.println(i);
    }

}
