package edu.project3_logAnalyzer;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LogAnalyzerTest {
    private String testLogPath = null;
    private LogAnalyzer logAnalyzer;
    private Path pathToMd;
    private Path pathToAdoc;
    private File fileMd;
    private File fileAdoc;

    @BeforeEach
    void init() {
        logAnalyzer = new LogAnalyzer();
        testLogPath = "src/test/java/edu/project3_logAnalyzer/testLog.txt";

        pathToMd = Path.of("src/main/java/edu/project3_logAnalyzer/outputs/output.md");
        fileMd = pathToMd.toFile();

        pathToAdoc = Path.of("src/main/java/edu/project3_logAnalyzer/outputs/output.adoc");
        fileAdoc = pathToAdoc.toFile();
    }

    @Test
    @Disabled
    void markdownPlusFromOutput() {
        logAnalyzer.analyze(new String[] {
            "java", "-jar", "nginx-log-stats.jar", "--path",
            testLogPath, "--from", "2015-05-25", "--format", "markdown"
        });

        assertThat(fileMd).isFile();
        assertThat(fileMd.getName()).isEqualTo("output.md");
    }

    @Test
    @Disabled
    void adocPlusToOutput() {
        logAnalyzer.analyze(new String[] {
            "java", "-jar", "nginx-log-stats.jar", "--path",
            testLogPath, "--to", "2015-05-25", "--format", "adoc"
        });

        assertThat(fileAdoc).isFile();
        assertThat(fileAdoc.getName()).isEqualTo("output.adoc");
    }

    @Test
    @Disabled
    void markdownPlusFromToToOutput() {
        logAnalyzer.analyze(new String[] {
            "java", "-jar", "nginx-log-stats.jar", "--path",
            testLogPath, "--from", "2015-05-24", "--to", "2015-05-26", "--format", "markdown"
        });

        assertThat(fileMd).isFile();
        assertThat(fileMd.getName()).isEqualTo("output.md");
    }

    @Test
    @Disabled
    void markdownOutput() {
        logAnalyzer.analyze(new String[] {
            "java", "-jar", "nginx-log-stats.jar", "--path",
            testLogPath, "--format", "markdown"
        });

        assertThat(fileMd).isFile();
        assertThat(fileMd.getName()).isEqualTo("output.md");
    }
}
