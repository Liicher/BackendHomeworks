package edu.project4_fractal_flame;

import edu.project4_fractal_flame.flameParts.Rect;
import edu.project4_fractal_flame.gamma.GammaCorrection;
import edu.project4_fractal_flame.gamma.ImageProcessor;
import edu.project4_fractal_flame.image.ImageFormat;
import edu.project4_fractal_flame.image.ImageUtils;
import edu.project4_fractal_flame.renderers.RenderSingleThread;
import edu.project4_fractal_flame.renderers.Renderer;
import edu.project4_fractal_flame.transformations.DiamondTransformation;
import edu.project4_fractal_flame.transformations.LinearTransformation;
import edu.project4_fractal_flame.transformations.PolarTransformation;
import edu.project4_fractal_flame.transformations.Transformation;
import java.util.List;

@SuppressWarnings({"MagicNumber"})
public class FractalFlameSession {
    private static final int FHD_X = 1920;
    private static final int FHD_Y = 1080;

    public void run() {
        Renderer renderer = new RenderSingleThread();
        FractalImage fractalImage = FractalImage.create(FHD_X, FHD_Y);
        Rect rect = new Rect(-4, -3, 8, 6);
        List<Transformation> transformations = List.of(
            new LinearTransformation(),
            new DiamondTransformation(),
            new PolarTransformation()
        );

        renderer.render(fractalImage, rect, transformations, 21, 100000, 7);
        ImageProcessor imageProcessor = new GammaCorrection();
        imageProcessor.process(fractalImage);
        ImageUtils.save(fractalImage, "src/main/java/edu/project4_fractal_flame/test/test.jpeg", ImageFormat.JPEG);
        ImageUtils.save(fractalImage, "src/main/java/edu/project4_fractal_flame/test/test.png", ImageFormat.PNG);

    }
}
