package edu.hw3.task7;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * При этом, можно передать Comparator который будет обрабатывать null.
 * Напишите и продемонстрируйте такой Comparator.
 */
public class Task7 {
    public TreeMap<String, String> nullTree(String key, String value) {
        // Не совсем понял суть, потому что всё это проще выполнить сразу в тесте
        Comparator comparator = Comparator.nullsFirst(Comparator.naturalOrder());
        TreeMap<String, String> tree = new TreeMap<>(comparator);
        tree.put(key, value);
        return tree;
    }
}
