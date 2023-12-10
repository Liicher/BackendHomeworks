package edu.hw9.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class StatsCollector {
    private final ExecutorService executorService;
    private final Map<String, List<Double>> metrics;

    public StatsCollector(int amountOfThreads) {
        this.executorService = Executors.newFixedThreadPool(amountOfThreads);
        this.metrics = new HashMap<>();
    }

    public void push(String metricName, double[] data) {
        executorService.execute(() -> {
            synchronized (metrics) {
                if (!metrics.containsKey(metricName)) {
                    metrics.put(metricName, new ArrayList<>());
                }

                for (double d : data) {
                    metrics.get(metricName).add(d);
                }
            }
        });
    }

    public Map<String, Double> stats() {
        Map<String, Double> stats = new HashMap<>();
        for (String metricName : metrics.keySet()) {
            List<Double> data = metrics.get(metricName);
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            for (double d : data) {
                sum += d;
                if (d < min) {
                    min = d;
                }
                if (d > max) {
                    max = d;
                }
            }
            double average = sum / data.size();

            stats.put(metricName + "_sum", sum);
            stats.put(metricName + "_average", average);
            stats.put(metricName + "_min", min);
            stats.put(metricName + "_max", max);
        }
        return stats;
    }
}
