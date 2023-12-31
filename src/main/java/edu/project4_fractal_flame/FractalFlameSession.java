package edu.project4_fractal_flame;

import edu.project4_fractal_flame.flameParts.Rect;
import edu.project4_fractal_flame.gamma.GammaCorrection;
import edu.project4_fractal_flame.gamma.ImageProcessor;
import edu.project4_fractal_flame.image.ImageFormat;
import edu.project4_fractal_flame.image.ImageUtils;
import edu.project4_fractal_flame.renderers.RenderMultiThread;
import edu.project4_fractal_flame.renderers.Renderer;
import edu.project4_fractal_flame.transformations.LinearTransformation;
import edu.project4_fractal_flame.transformations.Transformation;
import edu.project4_fractal_flame.transformations.TransformationUtils;
import java.util.List;

@SuppressWarnings({"MagicNumber"})
public class FractalFlameSession {
    private static final int FHD_X = 1920;
    private static final int FHD_Y = 1080;

    public void run() {
        Renderer renderer = new RenderMultiThread(12);
        ImageProcessor imageProcessor = new GammaCorrection();

        FractalImage fractalImage = FractalImage.create(FHD_X, FHD_Y);
        // Сократил 1920 и 1080 на одинаковые коэффициенты, пока фрактал не стал располагаться на середине изображения
        Rect rect = new Rect(-4, -3, 8, 6);
        List<Transformation> transformations = List.of(new LinearTransformation());

        renderer.render(fractalImage, rect, transformations, 25, 10000, 11);
        imageProcessor.process(fractalImage);

        ImageUtils.save(fractalImage, "src/main/java/edu/project4_fractal_flame/test/test.jpeg", ImageFormat.JPEG);
        ImageUtils.save(fractalImage, "src/main/java/edu/project4_fractal_flame/test/test.png", ImageFormat.PNG);
    }

    public void run(
        Renderer renderer,
        ImageProcessor imageProcessor,
        String path
    ) {
        FractalImage fractalImage = FractalImage.create(FHD_X, FHD_Y);
        Rect rect = new Rect(-4, -3, 8, 6);
        List<Transformation> transformations = TransformationUtils.getAllTransformations();

        renderer.render(fractalImage, rect, transformations, 25, 1000, 5);
        imageProcessor.process(fractalImage);
        ImageUtils.save(fractalImage, path, ImageFormat.PNG);
    }
}
