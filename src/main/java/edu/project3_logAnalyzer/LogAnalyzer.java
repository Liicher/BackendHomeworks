package edu.project3_logAnalyzer;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;

// {0}java {1}-jar {2}nginx-log-stats.jar {3}--path {4}logs/2023*
// {5}--from {6}2023-08-31 {7}--to {8}time {9}--format {10}{length-1}markdown
// Проблемы свитча
@SuppressWarnings({"MissingSwitchDefault", "InnerAssignment"})
public class LogAnalyzer {
    private final LogParser logParser = new LogParser();

    public void analyze(String[] args) {
        Path pathToLogFile = null;      // Путь до лога
        LocalDate from = LocalDate.MIN; // Дата "с"
        LocalDate to = LocalDate.MAX;   // Дата "до"
        String formatType = null;       // Тип вывода статистики

        // Пройдемся по всем входным аргументам
        for (int i = 0; i < args.length; i++) {
            String value = args[i];
            switch (value) {
                case "--path" -> pathToLogFile = Path.of(args[i + 1]);
                case "--from" -> from = LocalDate.parse(args[i + 1]);
                case "--to" -> to = LocalDate.parse(args[i + 1]);
                case "--format" -> formatType = args[i + 1];
            }
        }

        if (pathToLogFile == null) {
            throw new IllegalArgumentException();
        }
        File logFile = pathToLogFile.toFile();
        logParser.parse(logFile, from, to, formatType);
    }
}
