package com.github.wuchao.webproject.util;

import org.junit.Test;

public class StringuUtilsTests {

    @Test
    public void testGetLCString() {
        String str1 = "abceeeeeeeeeeeeee";
        String str2 = "ddddddddabcdfffff";
        StringUtils.getLCString(str1.toCharArray(), str2.toCharArray());

        /**
         * 执行结果：
         *
         * 第1个公共子串:
         * abc
         */
    }

}
