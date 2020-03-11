package com.github.wuchao.webproject.util;

import org.junit.Test;

public class PatternMatchUtilsTests {

    @Test
    public void testSimpleMatch() {
        String pattern = "*str*";
        String str = "string";

        int firstIndex = pattern.indexOf("*");
        int nextIndex = pattern.indexOf("*", firstIndex + 1);
        String part = pattern.substring(firstIndex + 1, nextIndex);

        int partIndex = str.indexOf(part);
        while (partIndex != -1) {
            String str1 = pattern.substring(nextIndex);
            String str2 = str.substring(partIndex + part.length());

            partIndex = str.indexOf(part, partIndex + 1);
        }
    }

}
