package com.github.wuchao.webproject.java.thread;

import org.junit.Test;

public class ThreadTests {


    /**
     * [Thread中interrupted()方法和isInterrupted()方法区别总结](https://blog.csdn.net/zhuyong7/article/details/80852884)
     */
    @Test
    public void testThread() {

        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {

            i++;

            if (i == 50) {
                System.out.println(Thread.currentThread().getName() + " ：" + i);
                boolean isInterrupted = Thread.interrupted();
                System.out.println("------------------：" + isInterrupted);
                System.out.println("------------------：" + Thread.currentThread().isInterrupted());
            }

            if (i == 100) {
                Thread.currentThread().interrupt();
                System.out.println("------------------：" + i);
                System.out.println("------------------1：" + Thread.interrupted());
                System.out.println("------------------2：" + Thread.interrupted());
            }

        }

    }

}
