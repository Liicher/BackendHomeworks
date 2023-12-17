package edu.hw9.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.TreeMap;
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
            "metric_name_average", 1.39f,
            "metric_name_max", 5.1f,
            "metric_name_min", 0.05f,
            "metric_name_sum", 6.95f
        );

        assertThat(collector.stats()).isEqualTo(expected);
    }

    @Test
    void collectorMultiTest() throws InterruptedException {
        collector.push("metric_name_1", new float[] {0.1f, 0.05f, 1.4f, 5.1f, 0.3f});
        collector.push("metric_name_2", new float[] {0.1f, 0.2f, 1.3f, 5.4f, 0.5f});
        collector.push("metric_name_3", new float[] {0.6f, 0.7f, 1.8f, 5.9f, 0.0f});
        Thread.sleep(100);

        Map<String, Float> expected = new TreeMap<>();
        expected.put("metric_name_1_average", 1.39f);
        expected.put("metric_name_1_max", 5.1f);
        expected.put("metric_name_1_min", 0.05f);
        expected.put("metric_name_1_sum", 6.95f);
        expected.put("metric_name_2_average", 1.5f);
        expected.put("metric_name_2_max", 5.4f);
        expected.put("metric_name_2_min", 0.1f);
        expected.put("metric_name_2_sum", 7.5f);
        expected.put("metric_name_3_average", 1.8f);
        expected.put("metric_name_3_max", 5.9f);
        expected.put("metric_name_3_min", 0.0f);
        expected.put("metric_name_3_sum", 9.0f);

        assertThat(collector.stats()).isEqualTo(expected);
    }
}
