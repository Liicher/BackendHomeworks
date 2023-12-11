package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double x = point.getX();
        double y = point.getY();
        double sqrt = Math.sqrt((x * x) + (y * y));
        double statement = sqrt * Math.atan(y / x);
        return new Point(sqrt * Math.sin(statement), -sqrt * Math.cos(statement));
    }
}
