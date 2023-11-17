package edu.hw6.task4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CompositionOfOutputStreamTest {
    private static CompositionOfOutputStream composition;
    private static String filePath = null;

    @BeforeAll
    static void init() {
        filePath = "src/test/java/edu/hw6/task4/task4TestFile";
        composition = new CompositionOfOutputStream();
    }

    @Test
    void test() throws IOException {
        composition.streamComposition(filePath);
        String response = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response = line;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(response).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
        Files.delete(Path.of(filePath));
    }

}
