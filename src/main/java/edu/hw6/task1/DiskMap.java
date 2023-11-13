package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Реализуйте класс DiskMap, который представляет собой ассоциативный массив,
 * хранящий пары ключ-значение на жестком диске. Класс должен реализовывать интерфейс Map
 * Ключи и значения должны быть сохранены на жестком диске в файле в формате "ключ:значение".
 * Класс должен поддерживать сохранение и загрузку из файла на диске.
 */

public class DiskMap implements Map<String, String> {
    private final static Logger LOGGER = LogManager.getLogger();
    private final File file;

    public DiskMap(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public int size() {
        return readFile().size();
    }

    @Override
    public boolean isEmpty() {
        return readFile().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return readFile().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return readFile().containsValue(value);
    }

    @Override
    public String get(Object key) {
        return readFile().get(key);
    }

    @Override
    public String put(String key, String value) {
        Map<String, String> map = readFile();
        String previousValue = map.put(key, value);
        writeFile(map);
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> map = readFile();
        String removedValue = map.remove(key);
        writeFile(map);
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        Map<String, String> map = readFile();
        map.putAll(m);
        writeFile(map);
    }

    @Override
    public void clear() {
        writeFile(new HashMap<>());
    }

    @Override
    public Set<String> keySet() {
        return readFile().keySet();
    }

    @Override
    public Collection<String> values() {
        return readFile().values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return readFile().entrySet();
    }

    private Map<String, String> readFile() {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(":");
                if (keyValue.length == 2) {
                    map.put(keyValue[0], keyValue[1]);
                }
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
        return map;
    }

    private void writeFile(Map<String, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey().contains(":") || entry.getValue().contains(":")) {
                    throw new IllegalArgumentException();
                }

                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
    }

    public String getFirstLineFromFile(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void main(String[] args) {
        DiskMap diskMap = new DiskMap("src/main/java/edu/hw6/task1/task1Test");
        diskMap.put("1", "2");
        diskMap.put("3", "4");
        diskMap.put("5", "6");

        try(BufferedReader r = new BufferedReader(new FileReader("src/main/java/edu/hw6/task1/task1Test"))) {
            String line;
            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}
