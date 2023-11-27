package edu.hw7.task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализуйте функцию, которая вычисляет факториал числа в многопоточном режиме при помощи parallelStream.
 */

public class Task2 {
    private final List<Long> factList = new ArrayList<>();

    public long multithreadingFactorial(int factValue) {
        factList.clear();
        for (long i = 0; i < factValue; i++) {
            factList.add(i + 1);
        }
        return factList.parallelStream().reduce(1L, (x, y) -> x * y);
    }
}
