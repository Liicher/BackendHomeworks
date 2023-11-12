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

public class DiskMap implements Map<String, String> {
    private final static Logger LOGGER = LogManager.getLogger();
    private File file;

    public DiskMap(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public int size() {
        return readMap().size();
    }

    @Override
    public boolean isEmpty() {
        return readMap().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return readMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return readMap().containsValue(value);
    }

    @Override
    public String get(Object key) {
        return readMap().get(key);
    }

    @Override
    public String put(String key, String value) {
        Map<String, String> map = readMap();
        String previousValue = map.put(key, value);
        writeMap(map);
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> map = readMap();
        String removedValue = map.remove(key);
        writeMap(map);
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        Map<String, String> map = readMap();
        map.putAll(m);
        writeMap(map);
    }

    @Override
    public void clear() {
        writeMap(new HashMap<>());
    }

    @Override
    public Set<String> keySet() {
        return readMap().keySet();
    }

    @Override
    public Collection<String> values() {
        return readMap().values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return readMap().entrySet();
    }

    private Map<String, String> readMap() {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
        return map;
    }

    private void writeMap(Map<String, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
    }

    public static String getFirstLineFromFile(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
