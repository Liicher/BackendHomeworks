package edu.hw3.task7;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * В результате у вас должен работать следующий код:
 * TreeMap<String, String> tree = ...;
 * tree.add(null, "test");
 * assertThat(tree.contains(null)).isTrue();
 */
class Task7Test {
    Task7 task7 = new Task7();

    @Test
    void treeNullKeyExample() {
        String key = null;
        String value = "test";
        TreeMap<String, String> tree = task7.nullTree(key, value);
        assertThat(tree.containsKey(null)).isTrue();
    }
}
