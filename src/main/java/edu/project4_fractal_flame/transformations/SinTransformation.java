package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;

public class SinTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double x = point.getX();
        double y = point.getY();
        return new Point(Math.sin(x), Math.sin(y));
    }
}
