package edu.hw3.task7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * В результате у вас должен работать следующий код:
 * TreeMap<String, String> tree = ...;
 * tree.add(null, "testFile");
 * assertThat(tree.contains(null)).isTrue();
 */
class Task7Test {
    private static Task7 task7;

    @BeforeAll
    static void init() {
        task7 = new Task7();
    }

    @Test
    void treeNullKeyExample() {
        String key = null;
        String value = "testFile";
        Map<String, String> tree = task7.nullTree(key, value);
        assertThat(tree.containsKey(null)).isTrue();
    }
}
