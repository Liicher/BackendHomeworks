package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Task2Test {
    Task2 task2 = new Task2();

    @Test
    void countDigits() {
        Assertions.assertThat(task2.countDigits(4666)).isEqualTo(4);
        Assertions.assertThat(task2.countDigits(0)).isEqualTo(1);
        Assertions.assertThat(task2.countDigits(-765)).isEqualTo(3);
    }
}
