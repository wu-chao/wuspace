package com.github.wuchao.webproject.java;

import org.junit.Test;

public class ClassCastTests {

    @Test
    public void testClassCast() {
        Object[] objects = {1, 2, null};
        Integer i = (Integer) objects[2];
        System.out.println("-----" + i);

        /**
         * -----null
         */
    }

}
