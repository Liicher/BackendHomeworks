package edu.hw8.task2;

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
    private static long[] results;

    /*public static void main(String[] args) {
        Task2 task2 = new Task2();
        FixedThreadPool fixedThreadPool = new FixedThreadPool(4);
        fixedThreadPool.start();

        int n = 92;
        var latch = new CountDownLatch(n + 1);
        for (int i = 0; i <= n; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                results[index] = task2.fibonacci(index);
                latch.countDown();
            });
        }

        System.out.println(Arrays.toString(results));
    }*/

    private long fibonacci(int n) {
        if (n < 0 || n > 92) {
            throw new IllegalArgumentException();
        } else if (n <= 1) {
            return n;
        }

        if (results[n] == 0) {
            results[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
        return results[n];
    }
}
