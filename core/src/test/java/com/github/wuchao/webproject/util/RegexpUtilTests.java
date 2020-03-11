package com.github.wuchao.webproject.util;

import org.junit.Test;

public class RegexpUtilTests {

    @Test
    public void testRegexpUtil() {
        System.out.println("\nisMatched:" + (RegexpUtil.isMatched("16623233333", "^(1)\\d{10}$") ? "true" : "false"));
    }
}
