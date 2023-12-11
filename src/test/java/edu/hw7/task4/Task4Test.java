package edu.hw7.task4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    @Test
    @Disabled
    void timeTest() {
        SingleThreadCalc singleThreadCalc = new SingleThreadCalc();
        MultiThreadCalc multiThreadCalc = new MultiThreadCalc();

        long timeSingleStart = System.nanoTime();
        singleThreadCalc.piCalculation(100_000);
        long singleTimeEnd = System.nanoTime();
        long singleTimeResult = singleTimeEnd - timeSingleStart;

        long timeMultiStart = System.nanoTime();
        multiThreadCalc.piCalculation(100_000, 8);
        long multiTimeEnd = System.nanoTime();
        long multiTimeResult = multiTimeEnd - timeMultiStart;
        assertThat(multiTimeResult).isLessThan(singleTimeResult);

        // Для показа и сравнения времени вычислений
        new Task4().ratePiCalculationConsoleOutput();
    }
}
