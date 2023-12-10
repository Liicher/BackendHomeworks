package edu.hw9.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StatsCollectorTest {
    private StatsCollector collector;

    @BeforeEach
    void init() {
        collector = new StatsCollector(2);
    }

    @Test
    void collectorTest() throws InterruptedException {
        collector.push("metric_name", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        Thread.sleep(10);

        Map<String, Double> expected = Map.of(
            "metric_name_sum", 6.95,
            "metric_name_average", 1.39,
            "metric_name_min", 0.05,
            "metric_name_max", 5.1
        );

        assertThat(collector.stats()).isEqualTo(expected);
    }

}
