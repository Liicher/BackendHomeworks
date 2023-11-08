package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Напишите программу, которая ищет все пятницы, выпадающие на 13-е число в заданном году.
 * Для 1925 года вывод может выглядеть следующим образом: [1925-02-13, 1925-03-13, 1925-11-13].
 * Для 2024 года: [2024-09-13, 2024-12-13].
 * После этого используя TemporalAdjuster, напишите функцию,
 * которая для заданной даты ищет следующую ближайшую пятницу 13.
 */

public class FridayThe13th {
    private static final int FRIDAY_13 = 13;

    public List<LocalDate> getAllFridaysThe13thOfTheYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException();
        }

        List<LocalDate> result = new ArrayList<>();
        LocalDate findInYear = LocalDate.of(year, 1, FRIDAY_13);
        while (findInYear.getYear() == year) {
            if (findInYear.getDayOfWeek() == DayOfWeek.FRIDAY) {
                result.add(findInYear);
            }
            findInYear = findInYear.plusMonths(1);
        }
        return result;
    }

    public LocalDate getNextDateOfFridayThe13th(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException();
        }

        LocalDate result = date.getDayOfMonth() < FRIDAY_13
            ? LocalDate.of(date.getYear(), date.getMonth(), FRIDAY_13)
            : LocalDate.of(date.getYear(), date.plusMonths(1).getMonth(), FRIDAY_13);

        while (result.getDayOfWeek() != DayOfWeek.FRIDAY) {
            // TemporalAdjuster
            result = result.with(t -> t.plus(Period.ofMonths(1)));
        }
        return result;
    }
}
