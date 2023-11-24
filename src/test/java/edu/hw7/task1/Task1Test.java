package edu.hw7.task1;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    private final static int THREADS = 8;
    private final static int RESULT = 1000;

    @Test
    void taskTest() throws InterruptedException {
        Task1 task1 = new Task1();
        task1.countBattle(RESULT, THREADS);
        int response = task1.getCount();

        assertThat(response).isEqualTo(RESULT);
    }
}
