package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

// Поиск файлов по предикату: размер, расширение
public class FileSearch extends RecursiveTask<List<File>> {
    private final File directory;
    private final long size;
    private final String extension;
    private final List<File> directoryList;

    public FileSearch(File directory, long size, String extension) {
        this.directory = directory;
        this.size = size;
        this.extension = extension;
        this.directoryList = new ArrayList<>();
    }

    @Override
    protected List<File> compute() {
        if (directory.isDirectory()) {
            List<FileSearch> recursiveList = new ArrayList<>();
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        FileSearch search = new FileSearch(file, size, extension);
                        recursiveList.add(search);
                        search.fork();
                    } else {
                        if (file.length() > size && file.getName().endsWith(extension)) {
                            directoryList.add(directory);
                        }
                    }
                }
            }
            for (FileSearch search : recursiveList) {
                directoryList.addAll(search.join());
            }
        }

        return directoryList;
    }
}
