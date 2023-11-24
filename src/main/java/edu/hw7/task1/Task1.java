package edu.hw7.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Напишите программу, в которой несколько потоков увеличивают общий счетчик на 1.
 * Напишите тесты: убедитесь, что счетчик потокобезопасен и использует классы Atomic для исключения состояния гонки.
 */

public class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static AtomicInteger COUNT = new AtomicInteger(0);
    private final List<Thread> threads =  new ArrayList<>();

    public void countBattle(int sprintCount, int amountOfThreads) {
        for (int i = 0; i < amountOfThreads; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < sprintCount / amountOfThreads; j++) {
                        COUNT.incrementAndGet();
                        LOGGER.info("Thread - " + COUNT);
                    }
                }
            });
            t.start();
            threads.add(t);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getCount() {
        return COUNT.get();
    }
}
