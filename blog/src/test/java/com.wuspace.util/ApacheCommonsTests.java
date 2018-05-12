package com.wuspace.util;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ApacheCommonsTests {

    @Test
    public void testObjectUtils() {
        List variables1 = null;
        List variables2 = new ArrayList(0);
        List variables3 = new ArrayList();
        List<String> variables4 = new ArrayList(){{add("fff"); add("bbb");}};
        System.out.println("1: anyNotNull=" + ObjectUtils.anyNotNull(variables1)); // false
        System.out.println("2: anyNotNull=" + ObjectUtils.anyNotNull(variables2)); // true
        System.out.println("3: anyNotNull=" + ObjectUtils.anyNotNull(variables3)); // true
        System.out.println("4: anyNotNull=" + ObjectUtils.anyNotNull(variables4)); // true

        /**
         * ObjectUtils.anyNotNull() 参数
         */

    }

}
