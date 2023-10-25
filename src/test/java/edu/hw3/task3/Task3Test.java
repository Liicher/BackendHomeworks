package edu.hw3.task3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {
    private static Task3 task3;

    @BeforeAll
    static void init() {
        task3 = new Task3();
    }

    @Test
    void freqDictExamples() {
        assertThat(task3.freqDict(List.of("a", "bb", "a", "bb"))).isEqualTo(Map.of("a", 2, "bb", 2));
        assertThat(task3.freqDict(List.of("this", "and", "that", "and"))).isEqualTo(Map.of("that", 1, "and", 2, "this", 1));
        assertThat(task3.freqDict(List.of("код", "код", "код", "bug"))).isEqualTo(Map.of("код", 3, "bug", 1));
        assertThat(task3.freqDict(List.of(1, 1, 2, 2))).isEqualTo(Map.of(1, 2, 2, 2));

        // Способом выше не получается вставить null
        String[] str = {"код", "код", null, "bug"};
        List<String> input = Arrays.asList(str);
        Map<String, Integer> response = task3.freqDict(input);
        Map<String, Integer> result = new HashMap<>();
        result.put("код", 2);
        result.put(null, 1);
        result.put("bug", 1);
        assertThat(response).isEqualTo(result);
    }

    @Test
    void invalidInputs() {
        List<String> inputOne = null;
        List<String> inputTwo = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {task3.freqDict(inputOne);});
        assertThrows(IllegalArgumentException.class, () -> {task3.freqDict(inputTwo);});
    }
}
