package edu.hw3.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void validInput() {
        assertDoesNotThrow(() -> {
            new Stock("Good", 2000);
        });
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock(null, 20);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock("ok", -20);
        });
    }
}
