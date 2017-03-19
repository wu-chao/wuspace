package com.wuspace.domain;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by WUCHAO on 2017/3/19.
 */
@Component
public class Receiver {

    /**
     * For convenience, this POJO also has a CountDownLatch. This allows it to signal that the message is received.
     * This is something you are not likely to implement in a production application.
     */
    private CountDownLatch countDownLatch;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
