package edu.hw9.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        collector.push("metric_name", new float[] {0.1f, 0.05f, 1.4f, 5.1f, 0.3f});
        Thread.sleep(100);

        Map<String, Float> expected = Map.of(
            "metric_name_sum", 6.95f,
            "metric_name_average", 1.39f,
            "metric_name_min", 0.05f,
            "metric_name_max", 5.1f
        );

        assertThat(collector.stats()).isEqualTo(expected);
    }

}
