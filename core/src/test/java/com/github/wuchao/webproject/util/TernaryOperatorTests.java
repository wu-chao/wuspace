package com.github.wuchao.webproject.util;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

public class TernaryOperatorTests {

    @Test
    public void testNullPointerException() {
        List<Integer> list = null;
        Integer size = CollectionUtils.isNotEmpty(list) ? list.size() : 0;
        System.out.println(size);

        Double d = null;
        Integer i = d != null ? d.intValue() : 0;
    }

}
