package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.pi;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;
import static edu.project4_fractal_flame.transformations.TransformationUtils.theta;

public class DiskTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double t = theta(point);
        double r = radius(point);
        double statement = t / pi;
        return new Point(statement * Math.sin(pi * r), statement * Math.cos(pi * r));
    }
}
