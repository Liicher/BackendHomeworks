package edu.project3_logAnalyzer.mdOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import static edu.project3_logAnalyzer.LogParser.sortStat;

/*
#### Общая информация

|        Метрика        |     Значение |
|:---------------------:|-------------:|
|       Файл(-ы)        | `access.log` |
|    Начальная дата     |   31.08.2023 |
|     Конечная дата     |            - |
|  Количество запросов  |       10_000 |
| Средний размер ответа |         500b |
*/

@SuppressWarnings("MultipleStringLiterals")
public class PrintMD {
    private static final String DATE = "dd.MM.yyyy";
    private static final int TOP = 5;
    private static final String GENERAL_TEMPLATE =
        "#### Общая информация\n"
            + "| Метрика                | Значение |\n"
            + "|:---------------------:|-------------:|\n"
            + "| Файл(-ы)               | %s |\n"
            + "| Начальная дата         | %s |\n"
            + "| Конечная дата          | %s |\n"
            + "| Количество запросов    | %d |\n"
            + "| Средний размер ответа  | %db |\n";
    private static final String TABLE_OPEN =
        "#### %s\n"
            + "| %s | Количество\n"
            + "|:---------------------:|-------------:|\n";
    private static final String TABLE_ROW = "| %s | %d |\n";

    public File print(
        File logFile,
        LocalDate fromDate,
        LocalDate toDate,
        int amountOfRequests,
        int averageResponseSize
    ) {
        File file = new File("src/main/java/edu/project3_logAnalyzer/outputs/output.md");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            String from = "-";
            String to = "-";
            if (fromDate != LocalDate.MIN) {
                from = fromDate.format(DateTimeFormatter.ofPattern(DATE));
            }
            if (toDate != LocalDate.MAX) {
                to = toDate.format(DateTimeFormatter.ofPattern(DATE));
            }
            writer.printf(GENERAL_TEMPLATE, logFile.getName(), from, to, amountOfRequests, averageResponseSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public void printAmountOfAddressesRequestsMD(File file, Map<String, Integer> amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.printf(TABLE_OPEN, "Адреса", "Адрес");
            Map<String, Integer> sortedMap = sortStat(amount);
            int count = TOP;
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                if (count == 0) {
                    break;
                }
                writer.printf(TABLE_ROW, entry.getKey(), entry.getValue());
                count--;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMostRequestedStatistic(File file, Map<String, Integer> amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.printf(TABLE_OPEN, "Ресурсы", "Ресурс");
            Map<String, Integer> sortedMap = sortStat(amount);
            int count = TOP;
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                if (count == 0) {
                    break;
                }
                writer.printf(TABLE_ROW, entry.getKey(), entry.getValue());
                count--;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMostCodeStatusStatistic(File file, Map<String, Integer> amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.printf(TABLE_OPEN, "Коды", "Код");
            Map<String, Integer> sortedMap = sortStat(amount);
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                writer.printf(TABLE_ROW, entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
