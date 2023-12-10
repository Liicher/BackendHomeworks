package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

// Поиск директорий, в которых больше 1000 файлов
public class DirectorySearch extends RecursiveTask<List<File>> {
    private static final int AMOUNT = 1000;
    private static final int AMOUNT_OF_THREE_FOR_TEST = 3;
    private final File directory;
    private final List<File> directoryList;

    public DirectorySearch(File directory) {
        this.directory = directory;
        this.directoryList = new ArrayList<>();
    }

    @Override
    protected List<File> compute() {
        long countFiles = 0;

        if (directory.isDirectory()) {
            List<DirectorySearch> recursiveList = new ArrayList<>();
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        DirectorySearch search = new DirectorySearch(file);
                        recursiveList.add(search);
                        search.fork();
                    } else {
                        countFiles++;
                    }
                }
            }
            for (DirectorySearch search : recursiveList) {
                directoryList.addAll(search.join());
            }
        }
        if (countFiles >= AMOUNT_OF_THREE_FOR_TEST) {
            directoryList.add(directory);
        }

        return directoryList;
    }
}
