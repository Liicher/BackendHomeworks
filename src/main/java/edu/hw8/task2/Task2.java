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
    /*public static void main(String[] args) throws InterruptedException {
        Task2 task2 = new Task2();
        FixedThreadPool fixedThreadPool = new FixedThreadPool(4);
        fixedThreadPool.start();
        List<Integer> res = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(10);

        int n = 92;
        for (int i = 0; i <= 10; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                res.add(task2.fibonacci(index));
                latch.countDown();
            });
        }

        latch.await();

        fixedThreadPool.close();
        System.out.println(res);
    }*/

    private int fibonacci(int n) {
        if (n < 0 || n > 92) {
            throw new IllegalArgumentException();
        } else if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
