package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Напишите реализации для DirectoryStream.Filter, которые проверяют:
 * - атрибуты (например, readable, writable)
 * - размер файла
 * - расширения файлов
 * - имя файла с помощью регулярных выражений
 * - магические начальные идентификаторы
 */

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter other) {
        return entry -> accept(entry) && other.accept(entry);
    }

    static AbstractFilter largerThan(long size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writeable() {
        return Files::isWritable;
    }

    static AbstractFilter extensionIs(String extension) {
        return entry -> entry.getFileName().toString().endsWith("." + extension);
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> entry.getFileName().toString().matches(regex);
    }

    static AbstractFilter magicNumber(byte... bytes) {
        return entry -> {
            try {
                byte[] fileBytes = Files.readAllBytes(entry);
                if (fileBytes.length < bytes.length) {
                    return false;
                }
                for (int i = 0; i < bytes.length; i++) {
                    if (fileBytes[i] != bytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }
}
