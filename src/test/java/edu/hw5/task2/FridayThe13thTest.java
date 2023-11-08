package edu.hw5.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class FridayThe13thTest {
    private FridayThe13th fridayThe13th;

    @BeforeEach
    void init() {
        fridayThe13th = new FridayThe13th();
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> fridayThe13th.getAllFridaysThe13thOfTheYear(-5));
        assertThrows(IllegalArgumentException.class, () -> fridayThe13th.getNextDateOfFridayThe13th(null));
    }

    @Test
    void exampleTest() {
        List<LocalDate> response = fridayThe13th.getAllFridaysThe13thOfTheYear(1925);
        assertThat(response).isEqualTo(List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)));

        response.clear();
        response = fridayThe13th.getAllFridaysThe13thOfTheYear(2024);
        assertThat(response).isEqualTo(List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)));
    }

    @Test
    void exampleTest2() {
        LocalDate response = fridayThe13th.getNextDateOfFridayThe13th(LocalDate.of(1925, 2, 14));
        assertThat(response).isEqualTo(LocalDate.of(1925, 3, 13));

        response = fridayThe13th.getNextDateOfFridayThe13th(LocalDate.of(2024, 10, 15));
        assertThat(response).isEqualTo(LocalDate.of(2024, 12, 13));
    }
}
