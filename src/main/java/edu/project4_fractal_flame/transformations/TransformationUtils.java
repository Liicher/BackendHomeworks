package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;

public class TransformationUtils {
    public static final double PI = 3.141592;

    private TransformationUtils() {
    }

    public static double radius(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.sqrt(x * x + y * y);
    }

    public static double theta(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.atan(x / y);
    }

    public static double phi(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.atan(y / x);
    }
}
