package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Task1Test {
    Task1 task1 = new Task1();

    @Test
    void minutesToSeconds() {
        String input = "13:56";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(836);
    }

    @Test
    void invalidSecondsInput() {
        String input = "10:60";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    void invalidCharactersInput() {
        String input = "aa:bb";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    void invalidBlankInput() {
        String input = " ";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    void invalidNullInput() {
        String input = null;
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    void invalidTimeInput() {
        String input = "12:32:23";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    void invalidEmptyTimeInput() {
        String input = ":";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    void invalidBlankTimeInput() {
        String input = " : ";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(-1);
    }

    @Test
    void trimValidInput() {
        String input = " 03:12 ";
        int response = task1.minutesToSeconds(input);
        Assertions.assertThat(response).isEqualTo(192);
    }
}
