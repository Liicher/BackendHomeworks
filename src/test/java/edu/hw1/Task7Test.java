package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.LinkPermission;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test {
    Task7 task7 = new Task7();

    @Test
    void rotateRight() {
        int n = 8;
        int shift = 1;
        int response = task7.rotateRight(n, shift);
        Assertions.assertThat(response).isEqualTo(4);
    }

    @Test
    void rotateLeft() {
        int n = 17;
        int shift = 2;
        int response = task7.rotateLeft(n, shift);
        Assertions.assertThat(response).isEqualTo(6);
    }

    @Test
    void validInputOne() {
        int n = 1;
        int shift = 6;
        int response = task7.rotateLeft(n, shift);
        Assertions.assertThat(response).isEqualTo(1);
    }

    @Test
    void validInputZero() {
        int n = 0;
        int shift = 11;
        int response = task7.rotateRight(n, shift);
        Assertions.assertThat(response).isEqualTo(0);
    }

    @Test
    void invalidNegativeNumberInput() {
        int n = -3;
        int shift = 11;
        assertThrows(IllegalArgumentException.class, () -> {
            task7.rotateLeft(n, shift);
        });
    }

    @Test
    void invalidNegativeShiftInput() {
        int n = 4;
        int shift = -3;
        assertThrows(IllegalArgumentException.class, () -> {
            task7.rotateLeft(n, shift);
        });
    }
}
