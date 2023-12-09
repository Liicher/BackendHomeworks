package edu.project4_fractal_flame.renderers;

import edu.project4_fractal_flame.FractalImage;
import edu.project4_fractal_flame.flameParts.Rect;
import edu.project4_fractal_flame.transformations.Transformation;
import java.util.List;

public class RenderMultiThread implements Renderer {

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int seed
    ) {
        return null;
    }
}
