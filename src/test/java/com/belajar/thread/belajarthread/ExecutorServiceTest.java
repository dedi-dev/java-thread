package com.belajar.thread.belajarthread;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ExecutorServiceTest {

    @Test
    void testSingleThresdExecutor() throws InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i <= 10; i++) {
            Integer no = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println("No. " + no + ". Current thread = " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };

            executor.execute(runnable);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testFixThreadExecutor() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i <= 100; i++) {
            Integer no = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println("No. " + no + ". Current thread = " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };

            executor.execute(runnable);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
