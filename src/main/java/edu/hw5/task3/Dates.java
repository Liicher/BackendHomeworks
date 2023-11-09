package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Существует много способов указать дату, например:
 * [2020-10-10]
 * [2020-12-2]
 * [1/3/1976]
 * [1/3/20]
 * [tomorrow]
 * [today]
 * [yesterday]
 * [1 day ago]
 * [2234 days ago]
 * Напишите метод Optional LocalDate parseDate(String string), который распознает перечисленные выше форматы.
 * Если строка передана в одном из форматов, то функция должна преобразовать ее в LocalDate и вернуть в Optional.
 * Если ни один из форматов не подошёл, то возвращается Optional.empty().
 */

public class Dates {
    private static final String TOMORROW = "tomorrow";
    private static final String TODAY = "today";
    private static final String YESTERDAY = "yesterday";

    private static final DateTimeFormatter[] DTF = {DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("yyyy-MM-d"),
        DateTimeFormatter.ofPattern("M/d/yyyy"),
        DateTimeFormatter.ofPattern("M/d/yy")
    };

    @SuppressWarnings("InnerAssignment")
    public Optional<LocalDate> parseDate(String string) {
        if (string == null || string.isEmpty()) {
            return Optional.empty();
        }

        Optional<LocalDate> dateResult = Optional.empty();
        LocalDate date = LocalDate.now();
        for (DateTimeFormatter dateTimeFormatter : DTF) {
            try {
                date = LocalDate.parse(string, dateTimeFormatter);
                dateResult = Optional.of(date);
            } catch (Exception ignored) {
            }
        }

        switch (string) {
            case YESTERDAY -> dateResult = Optional.of(date.minusDays(1));
            case TODAY -> dateResult = Optional.of(date);
            case TOMORROW -> dateResult = Optional.of(date.plusDays(1));
            default -> {
            }
        }

        Pattern pattern = Pattern.compile("^(\\d+) days? ago$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            dateResult = Optional.of(date.minusDays(Integer.parseInt(matcher.group(1))));
        }

        return dateResult;
    }
}
