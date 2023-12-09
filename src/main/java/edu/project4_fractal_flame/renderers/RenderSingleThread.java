package edu.project4_fractal_flame.renderers;

import edu.project4_fractal_flame.FractalImage;
import edu.project4_fractal_flame.affine.AffineTransformation;
import edu.project4_fractal_flame.flameParts.Pixel;
import edu.project4_fractal_flame.flameParts.Point;
import edu.project4_fractal_flame.flameParts.Rect;
import edu.project4_fractal_flame.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4_fractal_flame.affine.AffineTransformation.affine;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

// Намешал иерархию из условия проекта вместе с примером из хабра
public class RenderSingleThread implements Renderer {
    private static final int START_STEP = -20;
    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;

    @Override
    public FractalImage render
        (
            FractalImage canvas,
            Rect world,
            List<Transformation> transformations,
            int samples,
            int iterationsPerSample,
            int symmetry
        ) {

        AffineTransformation[] affineTransformations = affine(samples);
        for (int num = 0; num < samples; ++num) {
            Point pw = random();

            for (int step = START_STEP; step < iterationsPerSample; ++step) {
                // Берем одно из аффинных преобразований
                int i = ThreadLocalRandom.current().nextInt(0, samples);
                AffineTransformation affine = affineTransformations[i];
                pw = affinePoint(pw, affine);

                Transformation transformation =
                    transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size()));
                pw = transformation.transform(pw);

                // Реверснул проверку, чтобы не было крупного блока
                if (step < 0) {
                    continue;
                }

                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += (Math.PI * 2 / symmetry), ++s) {
                    var pwr = rotate(pw, theta2);

                    if (!world.contains(pwr)) {
                        continue;
                    }

                    Pixel pixel = map_range(world, pwr, canvas);
                    if (pixel == null) {
                        continue;
                    }

                    // 1. делаем лок на время работы с пикселем
                    // 2. подмешиваем цвет и увеличиваем hit count
                    synchronized (pixel) {
                        pixel.setColor();
                        pixel.incrementHitCount();
                    }
                }
            }
        }
        return canvas;
    }

    /**
     * x = coeff[i].a * newX + coeff[i].b * newY + coeff[i].c;
     * y = coeff[i].d * newX + coeff[i].e * newY + coeff[i].f;
     */
    private Point affinePoint(Point point, AffineTransformation affine) {
        double x = affine.getA() * point.getX() + affine.getB() * point.getY() + affine.getC();
        double y = affine.getD() * point.getX() + affine.getE() * point.getY() + affine.getF();
        return new Point(x, y);
    }

    private Pixel map_range(Rect world, Point pwr, FractalImage canvas) {
        int x = (int) ((pwr.getX() - world.getX()) * canvas.getWidth() / world.getWidth());
        int y = (int) ((pwr.getY() - world.getY()) * canvas.getHeight() / world.getHeight());
        return canvas.getPixel(x, y);
    }

    /*
    private Pixel map_range(Rect world, Point pwr, FractalImage canvas) {
        int x = (int) ((pwr.getX() - world.getX()) * canvas.getWidth() / world.getWidth());
        int y = (int) ((pwr.getY() - world.getY()) * canvas.getHeight() / world.getHeight());
        return canvas.getPixel(x, y);
    }
    */

    /**
     * Поворот точки относительно начала координат на определенный угол осуществляется по формуле:
     * x' = x * cos(alpha) - y * sin(alpha);
     * y' = x * sin(alpha) + y * cos(alpha);
     * где alpha равен нашему theta2
     */
    private Point rotate(Point point, double theta2) {
        double x = point.getX() * cos(theta2) - point.getY() * sin(theta2);
        double y = point.getX() * sin(theta2) + point.getY() * cos(theta2);
        return new Point(x, y);
    }

    /**
     * Для изображения размером 1920х1080 можно
     * взять XMIN=-1.777,XMAX=1.777,YMIN=-1,YMAX=1
     * В этом случае в большинстве нелинейных преобразований с боков не будет оставаться черных областей
     */
    private Point random() {
        double x = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
        double y = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);
        return new Point(x, y);
    }
}
