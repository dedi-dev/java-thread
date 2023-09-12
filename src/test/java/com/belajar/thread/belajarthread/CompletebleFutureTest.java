package com.belajar.thread.belajarthread;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

public class CompletebleFutureTest {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private Random random = new Random();

    public CompletableFuture<String> getValue() {
        CompletableFuture<String> future = new CompletableFuture<>();

        executor.execute(() -> {
            try {
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
        Future<String> future = getValue();
        CompletableFuture<String> future2 = getValue().thenApply(val -> val + " new.");
        System.out.println(future.get());
        System.out.println(future2.get());
    }
}
