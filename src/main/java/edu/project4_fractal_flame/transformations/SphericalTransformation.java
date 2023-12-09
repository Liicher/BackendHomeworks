package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;

public class SphericalTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = radius(point) * radius(point);
        double den = Math.pow(x, 2) + Math.pow(y, 2);   // denominator - знаменатель
        return new Point(x / r, y / r);
    }
}
