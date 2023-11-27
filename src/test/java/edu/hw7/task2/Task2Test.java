package edu.hw7.task2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    @Test
    void task2Test() {
        Task2 task2 = new Task2();
        assertThat(task2.multithreadingFactorial(1)).isEqualTo(1);
        assertThat(task2.multithreadingFactorial(2)).isEqualTo(2);
        assertThat(task2.multithreadingFactorial(3)).isEqualTo(6);
        assertThat(task2.multithreadingFactorial(4)).isEqualTo(24);
        assertThat(task2.multithreadingFactorial(5)).isEqualTo(120);
        assertThat(task2.multithreadingFactorial(15)).isEqualTo(1307674368000L);
    }
}
