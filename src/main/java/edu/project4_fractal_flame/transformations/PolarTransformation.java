package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.pi;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;
import static edu.project4_fractal_flame.transformations.TransformationUtils.theta;

public class PolarTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double r = radius(point);
        double t = theta(point);
        return new Point(t / pi, r - 1);
    }
}
