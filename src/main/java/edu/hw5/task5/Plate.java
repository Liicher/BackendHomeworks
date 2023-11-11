package edu.hw5.task5;

/**
 * Напишите регулярное выражение для валидации российских номерных знаков.
 */

public class Plate {
    private static final String PLATE_REGEX = "^[ABCEHKMOPTXYАВСЕНКМОРТХУ]\\d{3}[ABCEHKMOPTXYАВСЕНКМОРТХУ]{2}\\d{2,3}";

    public boolean isAllowedRussianCarPlate(String plate) {
        if (plate == null) {
            throw new IllegalArgumentException();
        }
        return plate.matches(PLATE_REGEX);
    }
}
