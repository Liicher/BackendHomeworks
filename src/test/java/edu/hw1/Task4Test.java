package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    Task4 task4 = new Task4();
    @Test
    void fixString() {
        Assertions.assertThat(task4.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
        Assertions.assertThat(task4.fixString("badce")).isEqualTo("abcde");
        Assertions.assertThat(task4.fixString(" ")).isEqualTo(" ");
        Assertions.assertThat(task4.fixString("")).isEqualTo("");
    }

    @Test
    void invalidNullInput() {
        String input = null;
        assertThrows(IllegalArgumentException.class, () -> {
            task4.fixString(input);
        });
    }
}
