package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Task5Test {
    Task5 task5 = new Task5();

    @Test
    void isPalindromeDescendant() {
        int input = 11211230;
        boolean response = task5.isPalindromeDescendant(input);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void isPalindromeDescendant1() {
        int input = 123234; // 357 // 87
        boolean response = task5.isPalindromeDescendant(input);
        Assertions.assertThat(response).isEqualTo(false);
    }

    @Test
    void isPalindromeDescendant2() {
        int input = 111;
        boolean response = task5.isPalindromeDescendant(input);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void isPalindromeDescendant4() {
        int input = -111;
        boolean response = task5.isPalindromeDescendant(input);
        Assertions.assertThat(response).isEqualTo(false);
    }
}
