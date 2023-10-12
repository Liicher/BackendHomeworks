package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {
    Task3 task3 = new Task3();

    @Test
    void isNestableTrue() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};
        boolean response = task3.isNestable(a1, a2);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void isNestableFalse() {
        int[] a1 = {9, 9, 8};
        int[] a2 = {8, 9};
        boolean response = task3.isNestable(a1, a2);
        Assertions.assertThat(response).isEqualTo(false);
    }

    @Test
    void emptyInputArray() {
        int[] a1 = {};
        int[] a2 = {0, 6};
        assertThrows(IllegalArgumentException.class, () -> {
            task3.isNestable(a1, a2);
        });
    }

    @Test
    void invalidNullInput() {
        int[] a1 = null;
        int[] a2 = {0, 6};
        assertThrows(IllegalArgumentException.class, () -> {
          task3.isNestable(a1, a2);
        });
    }
}
