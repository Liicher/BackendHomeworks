package edu.project3_logAnalyzer.mdOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void print(
        File file,
        LocalDate fromDate,
        LocalDate toDate,
        int amountOfRequests,
        int averageResponseSize
    ) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            String from = "-";
            String to = "-";
            if (fromDate != LocalDate.MIN) {
                from = fromDate.format(DateTimeFormatter.ofPattern(DATE));
            }
            if (toDate != LocalDate.MAX) {
                to = fromDate.format(DateTimeFormatter.ofPattern(DATE));
            }

            writer.print("#### Общая информация\n");
            writer.print("|        Метрика        |");
            writer.print("     Значение |\n");
            writer.print("|:---------------------:|");
            writer.print("-------------:|\n");
            writer.print("|       Файл(-ы)        |");
            writer.print(file.getName() + " |\n");
            writer.print("|    Начальная дата     |");
            writer.print(from + "|\n");
            writer.print("|     Конечная дата     |");
            writer.print(to + "|\n");
            writer.print("|  Количество запросов  |");
            writer.print(amountOfRequests + "|\n");
            writer.print("| Средний размер ответа |");
            writer.print(averageResponseSize + "b|\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
