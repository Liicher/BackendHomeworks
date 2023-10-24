package edu.hw3.task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    private static Task6 task6;

    @BeforeAll
    static void init() {
        task6 = new Task6();
    }

    @Test
    void stockMarketExamples() {
        assertThat(task6.mostValuableStock()).isEqualTo(null);

        Stock stock = new Stock("4", 4);
        task6.add(new Stock("1", 1));
        task6.add(stock);
        task6.add(new Stock("3", 3));
        task6.add(new Stock("2", 2));
        assertThat(task6.mostValuableStock()).isEqualTo(stock);

        task6.remove(stock);
        assertThat(task6.mostValuableStock()).isEqualTo(new Stock("3", 3));
    }
}
