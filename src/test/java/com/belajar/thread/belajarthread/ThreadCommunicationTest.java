package com.belajar.thread.belajarthread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;
    
    @Test
    void waitAndNotify() throws InterruptedException {
        final var lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized(lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized(lock) {
                message = "Hello";
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
