package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCalc {
    private static final int X0 = 1;
    private static final int Y0 = 1;
    private static final int RADIUS = 1;
    private static final int FOUR_CONST = 4;

    public double piCalculation(long totalCount, int amountOfThreads) {
        AtomicInteger circleCount = new AtomicInteger();
        Thread[] threads = new Thread[amountOfThreads];

        for (int i = 0; i < amountOfThreads; i++) {
            threads[i] = calculate(amountOfThreads, circleCount, totalCount);
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return FOUR_CONST * ((float) circleCount.get() / totalCount);
    }

    private Thread calculate(int amountOfThreads, AtomicInteger circleCount, long totalCount) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < totalCount / amountOfThreads; i++) {
                    float x = ThreadLocalRandom.current().nextFloat(0, 2);
                    float y = ThreadLocalRandom.current().nextFloat(0, 2);
                    if (Math.pow(x - X0, 2) + Math.pow(y - Y0, 2) <= Math.pow(RADIUS, 2)) {
                        circleCount.incrementAndGet();
                    }
                }
            }
        });
        thread.start();
        return thread;
    }
}
