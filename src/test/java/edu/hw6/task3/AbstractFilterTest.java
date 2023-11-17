package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.AbstractFilter.extensionIs;
import static edu.hw6.task3.AbstractFilter.largerThan;
import static edu.hw6.task3.AbstractFilter.magicNumber;
import static edu.hw6.task3.AbstractFilter.regexContains;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AbstractFilterTest {
    private static Path path;

    @BeforeAll
    static void init() throws IOException {
        path = Files.createDirectory(Path.of("testDirectory"));
    }

    @AfterAll
    static void clear() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    void extensionFilter() throws Exception {
        try {
            Files.createFile(path.resolve("test.txt"));
            Files.createFile(path.resolve("test.jpg"));
            Files.createFile(path.resolve("test.png"));

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, extensionIs("txt"))) {
                int countTXTFiles = 0;
                for (Path entry : stream) {
                    assertThat(entry.getFileName().toString()).endsWith(".txt");
                    ++countTXTFiles;
                }
                assertThat(countTXTFiles).isEqualTo(1);
            }
        } catch (Exception e) {
            Files.deleteIfExists(path.resolve("test.txt"));
            Files.deleteIfExists(path.resolve("test.jpg"));
            Files.deleteIfExists(path.resolve("test.png"));
            Files.deleteIfExists(path);
        }
        Files.deleteIfExists(path.resolve("test.txt"));
        Files.deleteIfExists(path.resolve("test.jpg"));
        Files.deleteIfExists(path.resolve("test.png"));
    }

    @Test
    void isLargerFilter() throws IOException {
        try {
            Files.write(path.resolve("test1.txt"), new byte[200]);
            Files.write(path.resolve("test2.txt"), new byte[1000]);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, largerThan(700))) {
                int countCorrectFiles = 0;
                for (Path entry : stream) {
                    assertThat(Files.size(entry)).isGreaterThan(700);
                    countCorrectFiles++;
                }
                assertThat(countCorrectFiles).isEqualTo(1);
            }
        } catch (Exception e) {
            Files.deleteIfExists(path.resolve("test1.txt"));
            Files.deleteIfExists(path.resolve("test2.txt"));
            Files.deleteIfExists(path);
        }
        Files.deleteIfExists(path.resolve("test1.txt"));
        Files.deleteIfExists(path.resolve("test2.txt"));
    }

    @Test
    void regexNameContainFilter() throws IOException {
        try {
            Files.createFile(path.resolve("someregex.txt"));
            Files.createFile(path.resolve("test.txt"));
            String regex = "someregex";

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, regexContains(regex))) {
                int countCorrectFiles = 0;
                Pattern pattern = Pattern.compile(regex);
                for (Path entry : stream) {
                    ++countCorrectFiles;
                    Matcher matcher = pattern.matcher(entry.toString());
                    assertThat(matcher.find()).isTrue();
                }
                assertThat(countCorrectFiles).isEqualTo(1);
            }
        } catch (Exception e) {
            Files.deleteIfExists(path.resolve("someregex.txt"));
            Files.deleteIfExists(path.resolve("test.txt"));
            Files.deleteIfExists(path);
        }
        Files.deleteIfExists(path.resolve("someregex.txt"));
        Files.deleteIfExists(path.resolve("test.txt"));
    }

    @Test
    void magicNumberFilter() throws IOException {
        try {
            byte[] magicNumberForJPG = {(byte) 255, (byte) 216};
            byte[] magicNumberForPNG = {(byte) 0x89, 'P', 'N', 'G'};
            Files.write(path.resolve("test.jpg"), magicNumberForJPG);
            Files.write(path.resolve("test.png"), magicNumberForPNG);

            DirectoryStream.Filter<Path> filter = magicNumber(magicNumberForPNG);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, filter)) {
                int countCorrectFiles = 0;
                for (Path entry : stream) {
                    ++countCorrectFiles;
                    assertThat(filter.accept(entry)).isTrue();
                }
                assertThat(countCorrectFiles).isEqualTo(1);
            }
        } catch (Throwable e) {
            Files.deleteIfExists(path.resolve("test.jpg"));
            Files.deleteIfExists(path.resolve("test.png"));
            Files.deleteIfExists(path);
        }
        Files.deleteIfExists(path.resolve("test.jpg"));
        Files.deleteIfExists(path.resolve("test.png"));
    }

    @Test
    void differentFilters() throws IOException {
        try {
            Set<PosixFilePermission> permissionsForExecute = new HashSet<>();
            permissionsForExecute.add(PosixFilePermission.OTHERS_EXECUTE);
            byte[] magicNumberForJPG = {(byte) 255, (byte) 216};
            Path path1 = Files.write(path.resolve("tinkoff.jpg"), magicNumberForJPG);
            Path path2 = Files.write(path.resolve("test1.png"), magicNumberForJPG);
            Path path3 = Files.createFile(path.resolve("test2.txt"));
            Files.setPosixFilePermissions(path1, permissionsForExecute);
            Files.setPosixFilePermissions(path2, permissionsForExecute);
            Files.setPosixFilePermissions(path3, permissionsForExecute);

            DirectoryStream.Filter<Path> filter = magicNumber(magicNumberForJPG)
                .and(extensionIs("jpg"))
                .and(regexContains("tinkoff"));

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, filter)) {
                int countCorrectFiles = 0;
                for (Path entry : stream) {
                    ++countCorrectFiles;
                    assertThat(filter.accept(entry)).isTrue();
                }
                assertThat(countCorrectFiles).isEqualTo(1);
            }
        } catch (Throwable e) {
            Files.deleteIfExists(path.resolve("tinkoff.jpg"));
            Files.deleteIfExists(path.resolve("test1.png"));
            Files.deleteIfExists(path.resolve("test2.txt"));
            Files.deleteIfExists(path);
        }
        Files.deleteIfExists(path.resolve("tinkoff.jpg"));
        Files.deleteIfExists(path.resolve("test1.png"));
        Files.deleteIfExists(path.resolve("test2.txt"));
    }
}
