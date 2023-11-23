package edu.hw7.task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализуйте функцию, которая вычисляет факториал числа в многопоточном режиме при помощи parallelStream.
 */

public class Task2 {
    private final List<Integer> factList = new ArrayList<>();

    public int multithreadingFactorial(int factValue) {
        for (int i = 0; i < factValue; i++) {
            factList.add(i + 1);
        }
        return factList.parallelStream().reduce(1, (x, y) -> x * y);
    }
}
