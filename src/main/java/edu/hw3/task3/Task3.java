package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * На вход подаётся список объектов одного типа.
 * Верните частотный словарь этого списка.
 */
public class Task3 {
    public <T> Map<T, Integer> freqDict(List<T> input) {
        // Все проверки на не пустоту списка
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Map<T, Integer> output = new HashMap<>();
        for (T t : input) {
            if (!output.containsKey(t)) {
                output.put(t, 1);
            } else {
                int count = output.get(t);
                output.put(t, ++count);
            }
        }
        return output;
    }
}
