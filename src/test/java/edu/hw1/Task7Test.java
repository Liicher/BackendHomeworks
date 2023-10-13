package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {
    Task7 task7 = new Task7();

    @Test
    void rotateRight() {
        Assertions.assertThat(task7.rotateRight(8, 1)).isEqualTo(4);
        Assertions.assertThat(task7.rotateRight(0, 7)).isEqualTo(0);
    }

    @Test
    void rotateLeft() {
        Assertions.assertThat(task7.rotateLeft(17, 2)).isEqualTo(6);
        Assertions.assertThat(task7.rotateLeft(1, 5)).isEqualTo(1);
    }

    @Test
    void invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            task7.rotateLeft(-3, 11);});
        assertThrows(IllegalArgumentException.class, () -> {
            task7.rotateLeft(4, -3);});
    }
}
