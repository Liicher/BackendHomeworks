package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * При построении цепочек такого типа всегда следует начинать с самого нижнего уровня:
 * - Создайте файл (Files.new*(...)) и получите из него OutputStream
 * - Добавьте к нему CheckedOutputStream для проверки записи при помощи контрольной суммы
 * - Для буферизации данных добавьте BufferedOutputStream
 * - Чтобы не работать с сырыми байтами добавьте OutputStreamWriter, и включите поддержку UTF-8.
 * - Добавьте финальный PrintWriter и запишите в файл текст:
 * "Programming is learned by writing programs. ― Brian Kernighan"
 * Не забудьте закрыть ресурсы с помощью try-with-resources.
 */

public class CompositionOfOutputStream {
    private final static Logger LOGGER = LogManager.getLogger();

    public void streamComposition(String path) throws IOException {
        try (
            OutputStream fileOutputStream =
                 new FileOutputStream(path);

             CheckedOutputStream checkedOutputStream =
                 new CheckedOutputStream(fileOutputStream, new CRC32());

             BufferedOutputStream bufferedOutputStream =
                 new BufferedOutputStream(checkedOutputStream);

             OutputStreamWriter outputStreamWriter =
                 new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);

             PrintWriter printWriter =
                 new PrintWriter(outputStreamWriter)) {

            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");

        } catch (IOException e) {
            LOGGER.info("Error: " + e + e.getMessage());
        }
    }

    /*public static void main(String[] args) throws IOException {
        CompositionOfOutputStream c = new CompositionOfOutputStream();
        c.streamComposition(Path.of("src/main/java/edu/hw6/task4/task4Test"));
    }*/
}
