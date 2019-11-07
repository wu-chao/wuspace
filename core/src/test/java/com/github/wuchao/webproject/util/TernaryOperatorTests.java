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
        Double dd = (d == null ? d : 200.0);
    }

    @Test
    public void test2() {
        Integer i = null;
        Integer r = i == null ? i : 1;
        System.out.println(r);
    }

}
