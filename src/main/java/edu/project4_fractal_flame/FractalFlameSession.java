package edu.project4_fractal_flame;

import edu.project4_fractal_flame.flameParts.Rect;
import edu.project4_fractal_flame.image.ImageFormat;
import edu.project4_fractal_flame.image.ImageUtils;
import edu.project4_fractal_flame.renderers.RenderSingleThread;
import edu.project4_fractal_flame.renderers.Renderer;
import edu.project4_fractal_flame.transformations.CylinderTransformation;
import edu.project4_fractal_flame.transformations.DiamondTransformation;
import edu.project4_fractal_flame.transformations.DiskTransformation;
import edu.project4_fractal_flame.transformations.ExTransformation;
import edu.project4_fractal_flame.transformations.HandkerchiefTransformation;
import edu.project4_fractal_flame.transformations.HorseshoeTransformation;
import edu.project4_fractal_flame.transformations.HyperbolicTransformation;
import edu.project4_fractal_flame.transformations.LinearTransformation;
import edu.project4_fractal_flame.transformations.PolarTransformation;
import edu.project4_fractal_flame.transformations.SinTransformation;
import edu.project4_fractal_flame.transformations.SphericalTransformation;
import edu.project4_fractal_flame.transformations.SpiralTransformation;
import edu.project4_fractal_flame.transformations.SwirlTransformation;
import edu.project4_fractal_flame.transformations.Transformation;
import edu.project4_fractal_flame.transformations.HeartTransformation;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class FractalFlameSession {

    FractalFlameSession() {

    }

    public void run() {
        //userInputs();

        //FractalFlame fractalFlame = new FractalFlame(1920, 1080);
        //fractalFlame.gammaCorrection();

        //GraphicUserInterface gui = new GraphicUserInterface();
        //gui.runFractalFlameWindow();

        Renderer renderer = new RenderSingleThread();
        FractalImage fractalImage = new FractalImage(1920, 1080);
        Rect rect = new Rect(-4, -3, 8, 6);
        Rect rect2 = new Rect(-4, -3, 8, 6);
        List<Transformation> transformations = List.of(
            new HeartTransformation()
        );

        renderer.render(fractalImage, rect, transformations, 7, 10000, 1);
        ImageUtils.save(fractalImage, "src/main/java/edu/project4_fractal_flame/test/test.jpeg", ImageFormat.JPEG);
        ImageUtils.save(fractalImage, "src/main/java/edu/project4_fractal_flame/test/test.png", ImageFormat.PNG);

    }

    private void userInputs() {

    }
}
