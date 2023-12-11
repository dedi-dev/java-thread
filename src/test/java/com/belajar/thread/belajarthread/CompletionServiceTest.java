package com.belajar.thread.belajarthread;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class CompletionServiceTest {

    private Random random = new Random();
    
    @Test
    void test() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(10);
        var executorCompletion = new ExecutorCompletionService<>(executor);

        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 101; i++) {
                final Integer index = i;
                executorCompletion.submit(() -> {
                    Thread.sleep(random.nextInt(2000));
                    return "Task " + index;
                });
            }
        });

        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                try {
                    Future<Object> future = executorCompletion.poll(5, TimeUnit.SECONDS);
                    if (future == null) {
                        break;
                    } else {
                        System.out.println(future.get());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }
}
