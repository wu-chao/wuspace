package com.github.wuchao.webproject.java.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTests {

    @Test
    public void testFuture1() throws ExecutionException, InterruptedException {
        CallableTask task = new CallableTask();
        Future future = new FutureTask(task);
        System.out.println("结果：" + future.get());
    }

    @Test
    public void testFuture2() throws ExecutionException, InterruptedException {
        CallableTask task = new CallableTask();
        Future<Integer> future = new ExecutorCompletionService<Integer>(Executors.newCachedThreadPool()).submit(task);
        System.out.println("结果：" + future.get());
    }


    public class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return waitAndReturn();
        }

        public Integer waitAndReturn() {
            Random random = new Random();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return random.nextInt(100);
        }
    }

}
