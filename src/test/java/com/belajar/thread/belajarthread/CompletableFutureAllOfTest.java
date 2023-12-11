package com.belajar.thread.belajarthread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureAllOfTest {

    public CompletableFuture<String> future() {
        CompletableFuture<String> result = new CompletableFuture<>();

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("MyCustomThread-");
        executor.initialize();

        int total = 100;
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i <= total; i++) {
            int index = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("Job " + index + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (index == 20) {
                    // Memeriksa jumlah tugas yang sedang berjalan
                    int activeCount = executor.getActiveCount();
                    System.out.println("Jumlah tugas yang sedang berjalan: " + activeCount);

                    // Memeriksa jumlah tugas yang sedang menunggu dalam antrian
                    int queueSize = executor.getThreadPoolExecutor().getQueue().size();
                    System.out.println("Jumlah tugas dalam antrian: " + queueSize);

                    // Memeriksa apakah ada tugas yang sedang berjalan atau menunggu
                    boolean hasTasks = (activeCount > 0 || queueSize > 0);
                    System.out.println("Ada tugas yang sedang berjalan atau menunggu: " + hasTasks);

                }
            }, executor);
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        allOf.thenRun(() -> {
            System.out.println("All jobs are completed");
            result.complete("All jobs are completed");
        });

        return result;
    }

    public static void main(String[] args) {
        CompletableFutureAllOfTest asyncron = new CompletableFutureAllOfTest();
        CompletableFuture<String> result = asyncron.future();

        result.thenAccept((val) -> {
            System.out.println("Result = " + val);
        });

        System.out.println("the first ===");
    }
}
