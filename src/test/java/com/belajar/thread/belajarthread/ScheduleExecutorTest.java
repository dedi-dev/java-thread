package com.belajar.thread.belajarthread;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ScheduleExecutorTest {
    
    @Test
    void delayed() throws InterruptedException {
        var executor = Executors.newSingleThreadScheduledExecutor();

        var future = executor.schedule(() -> System.out.println("Delayed ---"), 5, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.MILLISECONDS));

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void schedule() throws InterruptedException {
        var executor = Executors.newSingleThreadScheduledExecutor();

        var future = executor.scheduleAtFixedRate(() -> System.out.println("Scheduled ---"), 2, 2, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.MILLISECONDS));

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
