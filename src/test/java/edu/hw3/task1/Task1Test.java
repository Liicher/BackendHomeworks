package edu.hw3.task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    private static Task1 task1;

    @BeforeAll
    static void init() {
        task1 = new Task1();
    }

    @Test
    @DisplayName("Given examples")
    void atbashExamples() {
        assertThat(task1.atbash("Hello world!")).isEqualTo("Svool dliow!");
        assertThat(task1.atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"))
            .isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
        assertThat(task1.atbash(" ")).isEqualTo(" ");
        assertThat(task1.atbash("Привет")).isEqualTo("Привет");
    }

    @Test
    @DisplayName("Invalid input")
    void invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {task1.atbash(null);});
    }
}
