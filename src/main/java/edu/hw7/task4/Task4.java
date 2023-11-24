package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Реализуйте расчет числа Пи, используя метод Монте-Карло и несколько потоков.
 *
 * В ответе текстом приведите среднее время ускорения решения в зависимости от количества потоков,
 * а так же уровень погрешности для симуляции в 10млн, 100млн и 1млрд симуляций.
 */

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int LAPS = 3;
    private static int amountOfThreads = 2;
    private static long amountOfPoints = 10_000_000L;

    public void ratePiCalculationConsoleOutput() {
        // Среднее время в зависимости от потоков (с выводом результата расчета пи)
        double timeSingle = timeRateSingleThread();
        LOGGER.info("Amount of threads: 1");
        LOGGER.info("Amount of points: " + amountOfPoints);
        LOGGER.info("Time requires: " + timeSingle);
        LOGGER.info("");
        for (int i = 0; i < LAPS; i++) {
            double timeMulti = timeRateMultiThread();
            LOGGER.info("Amount of threads: " + amountOfThreads);
            LOGGER.info("Amount of points: " + amountOfPoints);
            LOGGER.info("Time requires: " + timeMulti);

            amountOfThreads *= 2;
            LOGGER.info("");
        }

        // Разница результатов в зависимости от количества симуляций
        // 10_000_000 - 100_000_000 - 1_000_000_000
        amountOfThreads = 12;
        for (int i = 0; i < LAPS; i++) {
            double timeMulti = timeRateMultiThread();
            LOGGER.info("Amount of threads: " + amountOfThreads);
            LOGGER.info("Amount of points: " + amountOfPoints);
            LOGGER.info("Time requires: " + timeMulti);

            amountOfPoints *= 10;
            LOGGER.info("");
        }
    }

    private double timeRateSingleThread() {
        long timeStart = System.nanoTime();
        LOGGER.info("Single thread calculations result: " + new SingleThreadCalc().piCalculation(amountOfPoints));
        long timeEnd = System.nanoTime();
        return (double) (timeEnd - timeStart) / 1_000_000_000L;
    }

    private double timeRateMultiThread() {
        long timeStart = System.nanoTime();
        LOGGER.info("Multi thread calculations result: "
            + new MultiThreadCalc().piCalculation(amountOfPoints, amountOfThreads));
        long timeEnd = System.nanoTime();
        return (double) (timeEnd - timeStart) / 1_000_000_000L;
    }
}
