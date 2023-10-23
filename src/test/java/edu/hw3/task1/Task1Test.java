package edu.hw3.task1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    Task1 task1 = new Task1();

    @Test
    void atbashExamplesAndNullInput() {
        assertThat(task1.atbash("Hello world!")).isEqualTo("Svool dliow!");
        assertThat(task1.atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"))
            .isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
        assertThat(task1.atbash(" ")).isEqualTo(" ");
        assertThat(task1.atbash("Привет")).isEqualTo("Привет");

        assertThrows(IllegalArgumentException.class, () -> {task1.atbash(null);});
    }
}
