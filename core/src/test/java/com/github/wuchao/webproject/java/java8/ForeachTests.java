package com.github.wuchao.webproject.java.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ForeachTests {

    @Test
    public void testThrowException() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3);
        /*intList.forEach(i -> {
            System.out.println(111);
            throw new RuntimeException("在 foreach 循环中抛出异常");
        });*/

       /* intList.stream().forEach(i -> {
            System.out.println(111);
            throw new RuntimeException("在 foreach 循环中抛出异常");
        });*/

        intList.stream()
                .map(i -> {
                    System.out.println(111);
                    if (i == 3) {
                        throw new RuntimeException("在 foreach 循环中抛出异常");
                    }
                    return i;
                })
                .collect(Collectors.toList());

    }

    /**
     * java.lang.NullPointerException
     */
    @Test
    public void testNullFor() {
        List<Integer> intList = null;
        for (Integer i : intList) {
            System.out.println(i);
        }
    }

    @Test
    public void testEmptyFor() {
        List<Integer> intList = Collections.emptyList();

        for (Integer i : intList) {
            System.out.println(i);
        }

        intList.forEach(i -> {
            System.out.println(i);
        });
    }

}
