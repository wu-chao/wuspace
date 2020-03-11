package com.github.wuchao.webproject.java.bitwiseOperation;

import org.junit.Test;

public class BitwiseOperationTests {

    @Test
    public void test3() {
        int a = 2;
        System.out.println("a 非的结果是：" + (~a));

        System.out.println(-2 >>> 1);
        System.out.println(-1 >>> 1);
    }

}
