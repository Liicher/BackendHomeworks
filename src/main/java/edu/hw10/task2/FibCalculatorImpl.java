package edu.hw10.task2;

@SuppressWarnings("MagicNumber")
public class FibCalculatorImpl implements FibCalculator {
    @Override
    @Cache(persist = true)
    public long fib(int number) {
        if (number < 0 || number > 92) {
            throw new IllegalArgumentException();
        }

        if (number <= 1) {
            return number;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
