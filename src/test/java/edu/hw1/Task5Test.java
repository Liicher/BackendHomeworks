package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Task5Test {
    Task5 task5 = new Task5();

    @Test
    void isPalindromeDescendantTrue() {
        Assertions.assertThat(task5.isPalindromeDescendant(11211230)).isEqualTo(true);
        Assertions.assertThat(task5.isPalindromeDescendant(111)).isEqualTo(true);
    }

    @Test
    void isPalindromeDescendantFalse() {
        Assertions.assertThat(task5.isPalindromeDescendant(123234)).isEqualTo(false);
        Assertions.assertThat(task5.isPalindromeDescendant(-111)).isEqualTo(false);
        Assertions.assertThat(task5.isPalindromeDescendant(3)).isEqualTo(false);
    }
}
