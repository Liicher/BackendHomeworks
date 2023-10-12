package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {
    Task6 task6 = new Task6();

    @Test
    void countK() {
        int input = 6621;
        Task6.count = 0;
        int response = task6.countK(input);
        Assertions.assertThat(response).isEqualTo(5);
    }

    @Test
    void countK2() {
        int input = 6554;
        Task6.count = 0;
        int response = task6.countK(input);
        Assertions.assertThat(response).isEqualTo(4);
    }

    @Test
    void illegalInput() {
        int input = 3333;
        Task6.count = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(input);
        });
    }

    @Test
    void illegalLowerInput() {
        int input = 997;
        Task6.count = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(input);
        });
    }

    @Test
    void illegalNegativeInput() {
        int input = -5346;
        Task6.count = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(input);
        });
    }

    @Test
    void illegalIncreasedInput() {
        int input = 16432;
        Task6.count = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(input);
        });
    }
}
