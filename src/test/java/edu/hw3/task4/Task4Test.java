package edu.hw3.task4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    private final Task4 task4 = new Task4();

    @Test
    void convertToRomanExamples() {
        assertThat(task4.convertToRoman(2)).isEqualTo("II");
        assertThat(task4.convertToRoman(12)).isEqualTo("XII");
        assertThat(task4.convertToRoman(16)).isEqualTo("XVI");
        assertThat(task4.convertToRoman(143)).isEqualTo("CXLIII");
        assertThat(task4.convertToRoman(2432)).isEqualTo("MMCDXXXII");
        assertThat(task4.convertToRoman(3999)).isEqualTo("MMMCMXCIX");
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> {task4.convertToRoman(0);});
        assertThrows(IllegalArgumentException.class, () -> {task4.convertToRoman(-123);});
        assertThrows(IllegalArgumentException.class, () -> {task4.convertToRoman(4000);});
    }
}
