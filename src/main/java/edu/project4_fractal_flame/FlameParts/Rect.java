package edu.project4_fractal_flame.FlameParts;

public class Rect {
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contain(Point p) {
        return true;
    }
}
