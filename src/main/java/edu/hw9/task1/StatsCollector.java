package edu.hw9.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Создайте многопоточную систему сбора статистики.
 * Много потоков передают численные данные,
 * а другие потоки агрегируют эти данные:
 * - считают сумму
 * - среднее
 * - максимум
 * - минимум
 */

@SuppressWarnings("MagicNumber")
public class StatsCollector {
    private final ExecutorService executorService;
    private final Map<String, List<Float>> metrics;

    public StatsCollector(int amountOfThreads) {
        this.executorService = Executors.newFixedThreadPool(amountOfThreads);
        this.metrics = new HashMap<>();
    }

    public void push(String metricName, float[] data) {
        executorService.execute(() -> {
            synchronized (metrics) {
                if (!metrics.containsKey(metricName)) {
                    metrics.put(metricName, new ArrayList<>());
                }

                for (float d : data) {
                    metrics.get(metricName).add(d);
                }
            }
        });
    }

    public Map<String, Float> stats() {
        Map<String, Float> stats = new TreeMap<>();
        for (String metricName : metrics.keySet()) {
            List<Float> data = metrics.get(metricName);
            float sum = 0;
            float min = Float.MAX_VALUE;
            float max = Float.MIN_VALUE;
            for (float d : data) {
                sum += d;
                if (d < min) {
                    min = d;
                }
                if (d > max) {
                    max = d;
                }
            }
            float average = sum / data.size();
            stats.put(metricName + "_sum", sum);
            stats.put(metricName + "_average", average);
            stats.put(metricName + "_min", min);
            stats.put(metricName + "_max", max);
        }
        return stats;
    }
}
