package edu.hw8.task3;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SingleThreadCrackerTest {
    // lock - straw - pass
    private static final String PASSWORD_FILE = "src/test/java/edu/hw8/task3/passwords.txt";

    @Test
    void singleThreadTest() {
        Map<String, String> expected = Map.of(
            "a.v.petrov", "lock",
            "v.v.belov","straw",
            "a.s.ivanov", "pass");

        SingleThreadCracker singleThreadCracker = new SingleThreadCracker(PASSWORD_FILE);
        singleThreadCracker.crack();
        Map<String, String> response = singleThreadCracker.getCrackedPasswords();

        assertThat(response).isEqualTo(expected);
    }
}
