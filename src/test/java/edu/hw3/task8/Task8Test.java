package edu.hw3.task8;

import edu.hw3.task6.Stock;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task8Test {
    Task8 task8 = new Task8();

    @Test
    void backwardIteratorExamples() {
        assertThat(task8.backwardIterator(List.of(1, 2, 3))).isEqualTo(List.of(3, 2, 1));
        assertThat(task8.backwardIterator(List.of("one", "two", "three"))).isEqualTo(List.of("three", "two", "one"));
        assertThat(task8.backwardIterator(List.of("a", "b", "c"))).isEqualTo(List.of("c", "b", "a"));
        assertThat(task8.backwardIterator(List.of(new Stock("1", 1), new Stock("2", 2), new Stock("3", 3))))
            .isEqualTo(List.of(new Stock("3", 3), new Stock("2", 2), new Stock("1", 1)));
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> {
            task8.backwardIterator(null);
        });
    }
}
