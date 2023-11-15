package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileClonerTest {
    private static FileCloner fileCloner;
    private static Path filePath;
    private static Path path;
    private static Path pathTwo;

    @BeforeAll
    static void init() {
        fileCloner = new FileCloner();
        filePath = Paths.get("src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt");
        path = Paths.get("src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия.txt");
        pathTwo = Paths.get("src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия (2).txt");
    }

    @Test
    void cloneFile() {
        fileCloner.cloneFile(filePath);
        fileCloner.cloneFile(filePath);
        assertTrue(Files.exists(path));
        assertTrue(Files.exists(pathTwo));
        try {
            Files.delete(path);
            Files.delete(pathTwo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
