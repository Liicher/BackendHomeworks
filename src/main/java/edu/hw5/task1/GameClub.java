package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Вас попросили сделать аналитику для компьютерного клуба:
 * нужно посчитать, сколько времени в среднем посетители проводят времени за один сеанс.
 * <p>
 * На вход функции даётся набор строк вида
 * " 2022-03-12, 20:20 - 2022-03-12, 23:50. "
 * <p>
 * Например, для входных данных
 * 2022-03-12, 20:20 - 2022-03-12, 23:50
 * 2022-04-01, 21:30 - 2022-04-02, 01:20
 * Вывод должен быть - 3ч 40м
 *  <p>
 * Программа не должна учитывать часовые пояса,
 * дополнительные секунды и другие особые случаи - день может длиться ровно 24 часа.
 * Для решения задания может пригодиться класс Duration.
 */

public class GameClub {
    private static final String FORM = "yyyy-MM-dd, HH:mm";

    public Duration getAveragePlayTime(List<String> input) throws DateTimeParseException {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Duration averageDuration = Duration.ZERO;
        int count = 0;
        for (String s : input) {
            Pattern session =
                Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");
            Matcher sessionMatcher = session.matcher(s);

            if (sessionMatcher.find()) {
                LocalDateTime timeBegin =
                    LocalDateTime.parse(sessionMatcher.group(1), DateTimeFormatter.ofPattern(FORM));
                LocalDateTime timeEnd =
                    LocalDateTime.parse(sessionMatcher.group(2), DateTimeFormatter.ofPattern(FORM));
                averageDuration = averageDuration.plus(Duration.between(timeBegin, timeEnd));
                count++;
            }
        }

        return Duration.ofSeconds(averageDuration.getSeconds() / count);
    }
}
