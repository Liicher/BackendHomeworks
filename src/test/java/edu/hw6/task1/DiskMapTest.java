package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiskMapTest {
    private static DiskMap diskMap;

    @BeforeAll
    static void init(){
        diskMap = new DiskMap("src/test/java/edu/hw6/task1/task1TestFile");
    }

    @Test
    void createFile() {
        boolean response = Files.exists(Path.of("src/test/java/edu/hw6/task1/task1TestFile"));
        assertThat(response).isTrue();
        diskMap.clear();
    }

    @Test
    void writeFile() {
        diskMap.put("key_1", "value_1");
        diskMap.put("key_2", "value_2");
        diskMap.put("key_3", "value_3");

        Map<String, String> responseMap = diskMap.readFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/java/edu/hw6/task1/task1TestFile"))){
            Map<String, String> map = new HashMap<>();
            String line;
            int size = 0;
            while ((line = bufferedReader.readLine()) != null) {
                size++;
                String[] input = line.split(":", 2);
                map.put(input[0], input[1]);
            }

            assertThat(responseMap).isEqualTo(map);
            assertThat(size).isEqualTo(diskMap.size());
        } catch (IOException e) {
	        throw new RuntimeException(e);
        }
        diskMap.clear();
    }

    @Test
    void readFile() {
        diskMap.clear();
        diskMap.put("key_1", "value_1");
        diskMap.put("key_2", "value_2");
        assertThat(diskMap.readFile()).isEqualTo(Map.of("key_1", "value_1", "key_2", "value_2"));

        diskMap.put("key_3", "value_3");
        assertThat(diskMap.readFile()).isEqualTo(Map.of("key_1", "value_1", "key_2", "value_2", "key_3", "value_3"));

        diskMap.remove("key_3");
        assertThat(diskMap.readFile()).isEqualTo(Map.of("key_1", "value_1", "key_2", "value_2"));

        diskMap.clear();
        assertThat(diskMap.readFile()).isEqualTo(Map.of());
    }
}
