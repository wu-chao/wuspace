package com.github.wuchao.webproject.util;

import org.junit.Test;

public class RegexpUtilTest {

    @Test
    public void testRegexpUtil() {
        System.out.println("\nisMatched:" + (RegexpUtil.isMatched("16623233333", "^(1)\\d{10}$") ? "true" : "false"));
    }
}
