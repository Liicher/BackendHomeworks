package edu.hw5.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class DatesTest {
    private Dates dates;

    @BeforeEach
    void init() {
        dates = new Dates();
    }

    @Test
    void invalidInputs() {
        assertThat(dates.parseDate("2020-10-101")).isEqualTo(Optional.empty());
        assertThat(dates.parseDate("2020-101-10")).isEqualTo(Optional.empty());
        assertThat(dates.parseDate("20210-10-10")).isEqualTo(Optional.empty());
        assertThat(dates.parseDate("smth")).isEqualTo(Optional.empty());
        assertThat(dates.parseDate("")).isEqualTo(Optional.empty());
        assertThat(dates.parseDate(null)).isEqualTo(Optional.empty());
    }

    @Test
    void parseDate() {
        assertThat(dates.parseDate("2020-10-10")).isEqualTo(Optional.of(LocalDate.of(2020, 10, 10)));
        assertThat(dates.parseDate("2020-12-2")).isEqualTo(Optional.of(LocalDate.of(2020, 12, 2)));
        assertThat(dates.parseDate("1/3/1976")).isEqualTo(Optional.of(LocalDate.of(1976, 1, 3)));
        assertThat(dates.parseDate("1/3/20")).isEqualTo(Optional.of(LocalDate.of(2020, 1, 3)));
        assertThat(dates.parseDate("tomorrow")).isEqualTo(Optional.of(LocalDate.now().plusDays(1)));
        assertThat(dates.parseDate("today")).isEqualTo(Optional.of(LocalDate.now()));
        assertThat(dates.parseDate("yesterday")).isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
        assertThat(dates.parseDate("1 day ago")).isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
        assertThat(dates.parseDate("2234 day ago")).isEqualTo(Optional.of(LocalDate.now().minusDays(2234)));
    }
}
