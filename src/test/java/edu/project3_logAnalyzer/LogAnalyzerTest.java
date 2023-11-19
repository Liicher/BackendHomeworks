package edu.project3_logAnalyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LogAnalyzerTest {
    private LogAnalyzer logAnalyzer;
    private Path path;
    private File file;

    @BeforeEach
    void init() {
        logAnalyzer = new LogAnalyzer();
        path = Path.of("src/main/java/edu/project3_logAnalyzer/outputs/output.md");
        file = path.toFile();
    }

    @Test
    void markdownOutput() {
        logAnalyzer.analyze(new String[] {
            "java","-jar","nginx-log-stats.jar","--path",
            "src/test/java/edu/project3_logAnalyzer/testLog.txt",
            "--from","2015-05-25","--format", "markdown"});

        assertThat(file).isFile();
        assertThat(file.getName()).isEqualTo("output.md");
    }
}
