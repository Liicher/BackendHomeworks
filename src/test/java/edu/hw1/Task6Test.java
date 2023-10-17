package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {
    Task6 task6 = new Task6();

    @Test
    void countK() {
        Task6.count = 0;
        Assertions.assertThat(task6.countK(6621)).isEqualTo(5);
        Task6.count = 0;
        Assertions.assertThat(task6.countK(6554)).isEqualTo(4);
        Task6.count = 0;
        Assertions.assertThat(task6.countK(6174)).isEqualTo(0);
    }

    @Test
    void invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(997);});
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(-5346);});
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(16432);});
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(3333);});
    }
}
