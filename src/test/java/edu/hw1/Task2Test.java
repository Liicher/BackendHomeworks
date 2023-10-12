package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Task2Test {
    Task2 task2 = new Task2();

    @Test
    void countDigits() {
        int input = 4666;
        int response = task2.countDigits(input);
        Assertions.assertThat(response).isEqualTo(4);
    }

    @Test
    void countNumberZero() {
        int input = 0;
        int response = task2.countDigits(input);
        Assertions.assertThat(response).isEqualTo(1);
    }

    @Test
    void countNegativeNumber() {
        int input = -733;
        int response = task2.countDigits(input);
        Assertions.assertThat(response).isEqualTo(3);
    }
}
