package edu.hw8.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MultiThreadCrackerTest {
    // lock - straw - pass
    private static final String PASSWORD_FILE = "src/test/java/edu/hw8/task3/passwords.txt";
    private static final Map<String, String> expectedMap = Map.of(
        "a.v.petrov", "lock",
        "v.v.belov","straw",
        "a.s.ivanov", "pass");

    private SingleThreadCracker singleThreadCracker;
    private MultiThreadCracker multiThreadCracker;

    @BeforeEach
    void init() {
        singleThreadCracker = new SingleThreadCracker(PASSWORD_FILE);
        multiThreadCracker = new MultiThreadCracker(PASSWORD_FILE, 12);
    }

    @Test
    void multiThreadWorkTestAndTimeComparison() {
        double timeSingle;
        double timeMulti;

        double start = System.nanoTime();
        multiThreadCracker.crack();
        timeMulti = (System.nanoTime() - start) / 1_000_000_000;
        Map<String, String> response = multiThreadCracker.getCrackedPasswords();

        // Работоспособность многопоточного "хакера"
        assertThat(response).isEqualTo(expectedMap);

        start = System.nanoTime();
        singleThreadCracker.crack();
        timeSingle = (System.nanoTime() - start) / 1_000_000_000;

        // Многопоточная версия быстрее одно поточной версии
        assertThat(timeMulti).isLessThan(timeSingle);
    }
}
