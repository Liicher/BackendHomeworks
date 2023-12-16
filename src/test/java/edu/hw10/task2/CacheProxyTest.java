package edu.hw10.task2;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CacheProxyTest {
    private File file;

    @BeforeEach
    void init() {
        file = new File("src/test/java/edu/hw10/task2/cache.txt");
    }

    @AfterEach
    void clear() {
        file.delete();
    }

    @Test
    void fibonacciTest() {
        FibCalculator proxy = CacheProxy.create(new FibCalculatorImpl(), FibCalculator.class, "src/test/java/edu/hw10/task2/cache.txt");

        proxy.fib(5);
        proxy.fib(6);
        proxy.fib(7);

        assertThat(file).isNotEmpty();
        assertThat(file).hasContent("{fib_5_=5, fib_6_=8, fib_7_=13}");
    }
}
