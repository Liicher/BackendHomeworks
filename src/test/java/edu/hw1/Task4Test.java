package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    Task4 task4 = new Task4();
    @Test
    void fixString() {
        String input = "hTsii  s aimex dpus rtni.g";
        String response = task4.fixString(input);
        Assertions.assertThat(response).isEqualTo("This is a mixed up string.");
    }

    @Test
    void fixString2() {
        String input = "badce";
        String response = task4.fixString(input);
        Assertions.assertThat(response).isEqualTo("abcde");
    }

    @Test
    void blankStringInput() {
        String input = " ";
        String response = task4.fixString(input);
        Assertions.assertThat(response).isEqualTo(" ");
    }

    @Test
    void emptyStringInput() {
        String input = "";
        String response = task4.fixString(input);
        Assertions.assertThat(response).isEqualTo("");
    }
}
