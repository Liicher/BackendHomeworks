package edu.project3_logAnalyzer.adocOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

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

/*
|===
| Метрика | Значение
| Файл(-ы) | access.log
| Начальная дата | 31.08.2023
| Конечная дата | -
| Количество запросов | 10_000
| Средний размер ответа | 500b
|===
*/

@SuppressWarnings("MultipleStringLiterals")
public class PrintAdoc {
    private static final String DATE = "dd.MM.yyyy";
    private static final int TOP = 5;

    public File print(
        File logFile,
        LocalDate fromDate,
        LocalDate toDate,
        int amountOfRequests,
        int averageResponseSize
    ) {
        File file = new File("src/main/java/edu/project3_logAnalyzer/outputs/output.adoc");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            String from = "-";
            String to = "-";
            if (fromDate != LocalDate.MIN) {
                from = fromDate.format(DateTimeFormatter.ofPattern(DATE));
            }
            if (toDate != LocalDate.MAX) {
                to = toDate.format(DateTimeFormatter.ofPattern(DATE));
            }

            writer.print("#### Общая информация\n");
            writer.print("[cols=2]\n");
            writer.print("|===\n");
            writer.print("| Метрика | Значение\n");
            writer.print("| Файл(-ы) |" + logFile.getName() + "\n");
            writer.print("| Начальная дата | " + from + "\n");
            writer.print("| Конечная дата | " + to + "\n");
            writer.print("| Количество запросов | " + amountOfRequests + "\n");
            writer.print("| Средний размер ответа |" + averageResponseSize + "\n");
            writer.print("|===\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public void printAmountOfAddressesRequestsMD(File file, Map<String, Integer> amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.print("#### Адреса\n");
            writer.print("[cols=2]\n");
            writer.print("|===\n");
            writer.print("|        Адрес        | Количество запросов\n");

            Map<String, Integer> sortedMap = new LinkedHashMap<>();
            amount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> sortedMap.put(e.getKey(), e.getValue()));

            int count = TOP;
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                if (count == 0) {
                    break;
                }
                String ip = entry.getKey();
                int requests = entry.getValue();
                writer.print("|\t" + ip);
                writer.print("|\t" + requests + "\n");
                count--;
            }
            writer.print("|===\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMostRequestedStatistic(File file, Map<String, Integer> amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.print("#### Ресурсы\n");
            writer.print("[cols=2]\n");
            writer.print("|===\n");
            writer.print("|Ресурс   |Количество\n");

            Map<String, Integer> sortedMap = new LinkedHashMap<>();
            amount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> sortedMap.put(e.getKey(), e.getValue()));

            int count = TOP;
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                if (count == 0) {
                    break;
                }
                String resource = entry.getKey();
                int requests = entry.getValue();
                writer.print("|\t" + resource);
                writer.print("|\t" + requests + "\n");
                count--;
            }
            writer.print("|===\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMostCodeStatusStatistic(File file, Map<String, Integer> amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.print("#### Коды\n");
            writer.print("[cols=2]\n");
            writer.print("|===\n");
            writer.print("|Код  |Количество\n");

            Map<String, Integer> sortedMap = new LinkedHashMap<>();
            amount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> sortedMap.put(e.getKey(), e.getValue()));

            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                String code = entry.getKey();
                int requests = entry.getValue();
                writer.print("|\t" + code);
                writer.print("|\t" + requests + "\n");
            }
            writer.print("|===\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
