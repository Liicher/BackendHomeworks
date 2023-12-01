package edu.hw8.task2;

/**
 * Реализуйте имплементацию FixedThreadPool,
 * фабричный метод create принимает на вход количество потоков.
 * Внутри класса должны использоваться "голые" потоки -- Thread[].
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TIMEOUT = 1000;
    private final int amountOfThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> threadsQueue;

    public FixedThreadPool(int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        this.threads = new Thread[amountOfThreads];
        this.threadsQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < amountOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            Runnable task = threadsQueue.take();
                            task.run();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            threadsQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (int i = 0; i < amountOfThreads; i++) {
            try {
                threads[i].join(TIMEOUT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (threads[i] != null && threads[i].isAlive()) {
                LOGGER.info("{} interrupted!", threads[i].getName());
                threads[i].interrupt();
            }
        }
    }
}
