package edu.hw10.task2;

@SuppressWarnings("all")
/**
 * Реализуйте кэширующих прокси для возвращаемых значений:
 *
 * public interface FibCalculator {
 *        @Cache(persist = true)
 *     public long fib(int number);
 * }
 * FibCalculator c = ...;
 * FibCalculator proxy = CacheProxy.create(c, c.getClass());
 *
 * У аннотации @Cache есть опциональный параметр persist,
 * который сохраняет результаты на диск, например, во временный каталог.
 *
 * Интерфейс FibCalculator приведён для примера, CacheProxy должен уметь работать и с другими интерфейсами.
 *
 * В задании может пригодиться интерфейс InvocationHandler.
 */

public class Task2 {
}
