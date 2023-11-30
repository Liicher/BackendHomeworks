package edu.hw8.task2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    private static final int AMOUNT_OF_THREADS = 4;
    private static final int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

    @Test
    void fibonacciTest() throws InterruptedException {
        Task2 task2 = new Task2();
        FixedThreadPool fixedThreadPool = new FixedThreadPool(AMOUNT_OF_THREADS);
        fixedThreadPool.start();
        int[] res = new int[10];
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            int finalI = i;
            fixedThreadPool.execute(() -> {
                res[finalI] = task2.fibonacci(index);
                latch.countDown();
            });
        }

        latch.await();
        fixedThreadPool.close();
        assertThat(res).isEqualTo(expected);
    }

}
