package com.belajar.thread.belajarthread;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;

public class TimerTest {

    @Test
    void testTimer() throws InterruptedException {
        var task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("Delayed Task");
            }

        };

        var timer = new Timer();
        timer.schedule(task, 2000L);

        Thread.sleep(3000L);
    }

    @Test
    void testTimerPeriodic() throws InterruptedException {
        var task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("Delayed Task");
            }

        };

        var timer = new Timer();
        timer.schedule(task, 2000L, 2000L);

        Thread.sleep(6000L);
    }
}
