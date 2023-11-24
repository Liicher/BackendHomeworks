package edu.hw7.task4;

import java.security.SecureRandom;

public class SingleThreadCalc {
    // Сторона квадрата = 2
    // Радиус = 1
    // Координаты центра окружности/квадрата и радиус
    private static final int X0 = 1;
    private static final int Y0 = 1;
    private static final int RADIUS = 1;
    private static final int FOUR_CONST = 4;
    private static final SecureRandom RANDOM = new SecureRandom();

    public double piCalculation(long totalCount) {
        int circleCount = 0;
        for (long i = 0; i < totalCount; i++) {
            float x = RANDOM.nextFloat(0, 2);
            float y = RANDOM.nextFloat(0, 2);
            if (Math.pow(x - X0, 2) + Math.pow(y - Y0, 2) <= Math.pow(RADIUS, 2)) {
                circleCount++;
            }
        }
        return FOUR_CONST * ((float) circleCount / totalCount);
    }
}
