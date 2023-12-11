package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;

public class SwirlTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double x = point.getX();
        double y = point.getY();
        double sinR = Math.sin(Math.pow(radius(point), 2));
        double cosR = Math.cos(Math.pow(radius(point), 2));
        return new Point(x * sinR - y * cosR, x * cosR + y * sinR);
    }
}
