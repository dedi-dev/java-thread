package com.belajar.thread.belajarthread;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class CompletebleFutureTest {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private Random random = new Random();
    private String result = "";

    public CompletableFuture<String> getValue() {
        CompletableFuture<String> future = new CompletableFuture<>();

        executor.execute(() -> {
            try {
                for (int i = 10; i >= 0; i--) {
                    System.out.println("hello ke " + i + " " + Thread.currentThread().getName());
                }
                Thread.sleep(2000L);
                future.complete("Complete");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    @Test
    void getFuture() throws ExecutionException, InterruptedException {
        // Future<String> future = getValue();
        CompletableFuture<String> future2 = getValue().thenApply(val -> val + " new.");
        // System.out.println(future.get());
        // System.out.println(future2.get());

        // CompletableFuture<Void> future3 = future2.thenAccept(val -> System.out.println("future 3 + " + val));
        // future3.get();

        future2.thenAccept(val -> {
            result = val;
            System.out.println("future 3 + " + result);
        });

        // CompletableFuture.runAsync(() -> {
        //     try {
        //         for (int i = 10; i >= 0; i--) {
        //             System.out.println("hello runAsync ke " + i);
        //         }
        //         Thread.sleep(2000L);
        //         System.out.println("Finishing ===");
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }, executor);

        System.out.println("the first");
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
