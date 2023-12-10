package edu.project4_fractal_flame.renderers;

import edu.project4_fractal_flame.FractalFlameSession;
import edu.project4_fractal_flame.FractalImage;
import edu.project4_fractal_flame.gamma.GammaCorrection;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class RendererTest {
    private FractalFlameSession session;

    @BeforeEach
    void init() {
        session = new FractalFlameSession();
    }

    @AfterEach
    void clear() {
        new File("src/test/java/edu/project4_fractal_flame/renderers/image.png").delete();
    }

    @Test
    void singleThread() {
        session.run(
            new RenderSingleThread(),
            new GammaCorrection(),
            "src/test/java/edu/project4_fractal_flame/renderers/image.png"
        );

        Path path = Paths.get("src/test/java/edu/project4_fractal_flame/renderers/image.png");
        assertThat(path).exists();
    }

    @Test
    void multiThread() {
        session.run(
            new RenderMultiThread(8),
            new GammaCorrection(),
            "src/test/java/edu/project4_fractal_flame/renderers/image.png"
        );

        Path path = Paths.get("src/test/java/edu/project4_fractal_flame/renderers/image.png");
        assertThat(path).exists();
    }

    @Test
    void timeTest() {
        double timeSingleStart = System.nanoTime();
        session.run(
            new RenderSingleThread(),
            new GammaCorrection(),
            "src/test/java/edu/project4_fractal_flame/renderers/image.png"
        );
        double singleTimeEnd = System.nanoTime();
        double singleTimeResult = (singleTimeEnd - timeSingleStart) / 1_000_000_000;

        double timeMultiStart = System.nanoTime();
        session.run(
            new RenderMultiThread(8),
            new GammaCorrection(),
            "src/test/java/edu/project4_fractal_flame/renderers/image.png"
        );
        double multiTimeEnd = System.nanoTime();
        double multiTimeResult = (multiTimeEnd - timeMultiStart) / 1_000_000_000;
        System.out.println(singleTimeResult + " " + multiTimeResult);
        assertThat(multiTimeResult).isLessThan(singleTimeResult);
    }
}
