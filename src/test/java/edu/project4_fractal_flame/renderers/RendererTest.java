package edu.project4_fractal_flame.renderers;

import edu.project4_fractal_flame.FractalFlameSession;
import edu.project4_fractal_flame.gamma.GammaCorrection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class RendererTest {

    @AfterEach
    void clear() {
        new File("src/test/java/edu/project4_fractal_flame/renderers/image.png").delete();
    }

    @Test
    void singleThread() {
        FractalFlameSession session = new FractalFlameSession();
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
        FractalFlameSession session = new FractalFlameSession();
        session.run(
            new RenderMultiThread(8),
            new GammaCorrection(),
            "src/test/java/edu/project4_fractal_flame/renderers/image.png"
        );

        Path path = Paths.get("src/test/java/edu/project4_fractal_flame/renderers/image.png");
        assertThat(path).exists();
    }
}
