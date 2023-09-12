package com.belajar.thread.belajarthread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class FutureTest {
    
    @Test
    void callable() throws InterruptedException, ExecutionException{
        var executor = Executors.newSingleThreadExecutor();
        Callable callable = () -> {
            Thread.sleep(5000L);
            return "Hi....";
        };

        Future<String> future = executor.submit(callable);

        while (!future.isDone()) {
            System.out.println("Waiting...");
            Thread.sleep(1000L);
        }

        System.out.println(future.get());
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            // Thread.sleep(value * 500);
            System.out.println("ini value ke = " + value + ", Thread = " + Thread.currentThread().getName());
            return String.valueOf(value);
        }).collect(Collectors.toList());

        var futures = executor.invokeAll(callables);

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            // Thread.sleep(value * 500);
            System.out.println("ini value ke = " + value + ", Thread = " + Thread.currentThread().getName());
            return String.valueOf(value);
        }).collect(Collectors.toList());

        var future = executor.invokeAny(callables);

        System.out.println(future);
    }
}
