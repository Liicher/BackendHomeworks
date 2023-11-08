package edu.hw5.task5;

import java.util.regex.Pattern;

/**
 * Напишите регулярное выражение для валидации российских номерных знаков.
 */

public class Plate {
    private static final Pattern PLATE_REGEX =
        Pattern.compile("^[ABCEHKMOPTXYАВСЕНКМОРТХУ]\\d{3}[ABCEHKMOPTXYАВСЕНКМОРТХУ]{2}\\d{2,3}");

    public boolean isAllowedRussianCarPlate(String plate) {
        if (plate == null) {
            throw new IllegalArgumentException();
        }
        return plate.matches(String.valueOf(PLATE_REGEX));
    }
}
