package edu.hw7.task4;

public class Circle {
    private static final int X0 = 1;
    private static final int Y0 = 1;
    private static final int RADIUS = 1;

    public boolean isInCircle(float x, float y) {
        return Math.pow(x - X0, 2) + Math.pow(y - Y0, 2) <= Math.pow(RADIUS, 2);
    }
}
