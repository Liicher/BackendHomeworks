package edu.project4_fractal_flame.renderers;

import edu.project4_fractal_flame.FractalImage;
import edu.project4_fractal_flame.flameParts.Rect;
import edu.project4_fractal_flame.transformations.Transformation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RenderMultiThread implements Renderer {
    private final static Logger LOGGER = LogManager.getLogger();
    private final int amountOfThreads;

    public RenderMultiThread(int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetry
    ) {
        Thread[] threads = new Thread[amountOfThreads];
        int amountOfSamples = samples / amountOfThreads;

        for (int i = 0; i < amountOfThreads; i++) {

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    new RenderSingleThread().render(
                        canvas,
                        world,
                        transformations,
                        amountOfSamples,
                        iterationsPerSample,
                        symmetry
                    );
                }
            });
            threads[i].start();
        }

        try {
            for (int i = 0; i < amountOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }
        return canvas;
    }
}
