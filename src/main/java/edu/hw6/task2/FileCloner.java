package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Напишите функцию cloneFile(Path path), которая создает копию файла с новым именем.
 * Например, файл называется Tinkoff Bank Biggest Secret.txt.
 * Тогда новые имена файлов должны выглядеть следующим образом:
 * Tinkoff Bank Biggest Secret.txt
 * Tinkoff Bank Biggest Secret — копия.txt
 * Tinkoff Bank Biggest Secret — копия (2).txt
 * Tinkoff Bank Biggest Secret — копия (3).txt
 */

public class FileCloner {
    private final static Logger LOGGER = LogManager.getLogger();

    public void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String fileExtension = "";
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            fileExtension = fileName.substring(index);
            fileName = fileName.substring(0, index);
        }

        int copyNumber = 1;
        Path copyPath = Paths.get(path.getParent().toString(), fileName + " — копия" + fileExtension);
        while (Files.exists(copyPath)) {
            copyNumber++;
            copyPath = Paths.get(
                path.getParent().toString(), fileName + " — копия (" + copyNumber + ")" + fileExtension);
        }

        try {
            Files.copy(path, copyPath);
            LOGGER.info("File copied to: " + copyPath);
        } catch (IOException e) {
            LOGGER.info("Error copying file: " + e.getMessage());
        }
    }

    /*public static void main(String[] args) {
        Path filePath = Paths.get("src/main/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt");
        new FileCloner().cloneFile(filePath);
    }*/
}
