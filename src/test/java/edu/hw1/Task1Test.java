package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Task1Test {
    Task1 task1 = new Task1();

    @Test
    void validMinutesToSeconds() {
        Assertions.assertThat(task1.minutesToSeconds("13:56")).isEqualTo(836);
        Assertions.assertThat(task1.minutesToSeconds("   13:56  ")).isEqualTo(836);
    }

    @Test
    void invalidInput() {
        Assertions.assertThat(task1.minutesToSeconds("10:60")).isEqualTo(-1);
        Assertions.assertThat(task1.minutesToSeconds(" ")).isEqualTo(-1);
        Assertions.assertThat(task1.minutesToSeconds("")).isEqualTo(-1);
        Assertions.assertThat(task1.minutesToSeconds(null)).isEqualTo(-1);
        Assertions.assertThat(task1.minutesToSeconds("aa:bb")).isEqualTo(-1);
        Assertions.assertThat(task1.minutesToSeconds("12:32:23")).isEqualTo(-1);
        Assertions.assertThat(task1.minutesToSeconds(":")).isEqualTo(-1);
        Assertions.assertThat(task1.minutesToSeconds(" : ")).isEqualTo(-1);
    }
}
