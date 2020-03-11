package com.github.wuchao.webproject.java.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class CollectionTests {

    @Test
    public void testSetAdd() {
        Set<Integer> sets = new HashSet<>(5);
        System.out.println(sets.add(1));
        System.out.println(sets.add(2));
        System.out.println(sets.add(1));
    }

}
