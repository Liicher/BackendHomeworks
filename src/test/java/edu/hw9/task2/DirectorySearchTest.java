package edu.hw9.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DirectorySearchTest {
    private File file;

    @BeforeEach
    void init() {
        file = new File("src/test/java/edu/hw9/task2");
    }

    @Test
    void dirTest() {
        DirectorySearch directorySearch = new DirectorySearch(file);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<File> response = forkJoinPool.invoke(directorySearch);
        forkJoinPool.close();

        assertThat(response.size()).isEqualTo(2);
    }

    @Test
    void filePredicateTest() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        List<File> response = forkJoinPool.invoke(new FileSearch(file, 0, ".txt"));
        assertThat(response.size()).isEqualTo(4);

        response = forkJoinPool.invoke(new FileSearch(file, 0, ".java"));
        assertThat(response.size()).isEqualTo(1);

        forkJoinPool.close();
    }
}
