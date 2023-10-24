package edu.hw3.task2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {
    private static Task2 task2;

    @BeforeAll
    static void init() {
        task2 = new Task2();
    }

    @Test
    void clusterizeExamples() {
        assertThat(task2.clusterize("()()()")).isEqualTo(new String[] {"()", "()", "()"});
        assertThat(task2.clusterize("((()))")).isEqualTo(new String[] {"((()))"});
        assertThat(task2.clusterize("((()))(())()()(()())")).isEqualTo(new String[] {"((()))", "(())", "()", "()", "(()())"});
        assertThat(task2.clusterize("((())())(()(()()))")).isEqualTo(new String[] {"((())())", "(()(()()))"});
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> {task2.clusterize(null);});
        assertThrows(IllegalArgumentException.class, () -> {task2.clusterize(")(()())");});
        assertThrows(IllegalArgumentException.class, () -> {task2.clusterize( "");});
        assertThrows(IllegalArgumentException.class, () -> {task2.clusterize( " ");});
        assertThrows(IllegalArgumentException.class, () -> {task2.clusterize( "abc");});
    }
}
