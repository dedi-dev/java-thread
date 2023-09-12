package com.belajar.thread.belajarthread;

import org.junit.jupiter.api.Test;

public class ThreadTest {
    
    @Test
    void mainThread() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread() {
        Runnable runnable = () -> {
            System.out.println("new thread = " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
