package edu.hw8.task2;

/**
 * Реализуйте имплементацию FixedThreadPool,
 * фабричный метод create принимает на вход количество потоков.
 * Внутри класса должны использоваться "голые" потоки -- Thread[].
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> threadsQueue;
    private final int amountOfThreads;
    private final Thread[] threads;

    public FixedThreadPool(int amountOfThreads) {
        this.threadsQueue = new LinkedBlockingDeque<>();
        this.amountOfThreads = amountOfThreads;
        threads = new Thread[amountOfThreads];
    }

    @Override
    public void start() {
        for (int i = 0; i < amountOfThreads; i++) {
            threads[i] = new Thread();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            threadsQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        for (int i = 0; i < amountOfThreads; i++) {
            if (threads[i] != null && threads[i].isAlive()) {
                threads[i].interrupt();
            }
        }
    }
}
