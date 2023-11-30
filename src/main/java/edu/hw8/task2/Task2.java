package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Реализуйте свой собственный интерфейс ThreadPool:
 * public interface ThreadPool extends AutoCloseable {
 *     void start();
 *     void execute(Runnable runnable);
 * }
 * Реализуйте имплементацию FixedThreadPool, фабричный метод create принимает на вход количество потоков.
 * Внутри класса должны использоваться "голые" потоки -- Thread[].
 * Продемонстрируйте работоспособность на примере параллельного вычисления чисел Фибоначчи.
 */

@SuppressWarnings("MagicNumber")
public class Task2 {
    public int fibonacci(int n) {
        if (n < 0 || n > 92) {
            throw new IllegalArgumentException();
        } else if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
