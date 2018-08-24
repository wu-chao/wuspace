package com.github.wuchao.webproject.java;

import org.junit.Test;

import java.util.Random;

public class RandomTests {

    @Test
    public void testRandom() {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            System.out.println(random.nextInt(3));
        }
    }

}
