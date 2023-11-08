package edu.hw5.task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlateTest {
    private Plate plate;

    @BeforeEach
    void init() {
        plate = new Plate();
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> plate.isAllowedRussianCarPlate(null));
    }

    @Test
    void isAllowedRussianCarPlate() {
        assertThat(plate.isAllowedRussianCarPlate("1234")).isFalse();
        assertThat(plate.isAllowedRussianCarPlate("P021OM152")).isTrue();
    }
}
