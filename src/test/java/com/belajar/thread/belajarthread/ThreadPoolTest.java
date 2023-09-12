package com.belajar.thread.belajarthread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ThreadPoolTest {
    
    @Test
    void create(){
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);

        var threadPool = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);
    }

    @Test
    void executor() throws InterruptedException{
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(1000);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

        Runnable runnable = () -> {
            try {
                Thread.sleep(2000L);
                System.out.println("Current thread = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        executor.execute(runnable);

        Thread.sleep(3000L);
    }

    @Test
    void shutdown() throws InterruptedException{
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(1000);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

        for(int i = 0; i <= 100; i++) {
            Integer no = i;
            Runnable runnable = () -> {
            try {
                Thread.sleep(2000L);
                System.out.println("No. " + no + ". Current thread = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        executor.execute(runnable);
        }

        // Thread.sleep(3000L);
        // executor.shutdownNow(); // shutdown threadpool and return runnable not yet run
        executor.shutdown(); // shutdown when all runnable finish run
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void rejected() throws InterruptedException{
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(10);
        var rejectedHandler = new LogRejectedHandler();

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue, rejectedHandler);

        for(int i = 0; i <= 1000; i++) {
            Integer no = i;
            Runnable runnable = () -> {
            try {
                Thread.sleep(2000L);
                System.out.println("No. " + no + ". Current thread = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        executor.execute(runnable);
        }

        // Thread.sleep(3000L);
        // executor.shutdownNow(); // shutdown threadpool and return runnable not yet run
        executor.shutdown(); // shutdown when all runnable finish run
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    public static class LogRejectedHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("task = " + r + " was rejected");
        }
        
    }
}

