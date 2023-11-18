package edu.project3_logAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.project3_logAnalyzer.mdOutput.PrintMD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("LineLength")
//  '$remote_addr - $remote_user [$time_local] ' ' "$request" $status $body_bytes_sent ' '"$http_referer" "$http_user_agent"'
//  93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
public class LogParser {
    private final static Logger LOGGER = LogManager.getLogger();

    public void parse(File logFile, LocalDate fromDate, LocalDate toDate, String format) {
        List<String> logList = filterLogsByDate(logFile, fromDate, toDate);
        String outFormat = parseFormat(format);

        int amountOfRequests = logList.size();                                      // Общее количество запросов
        Map<String, Integer> addressesStatistic = parseAddress(logList);            // Количество запросов по адресам
        Map<String, Integer> mostRequestedStatistic = countRequests(logList);       // Часто запрашиваемы ресурсы
        Map<String, Integer> mostCodeStatusStatistic = countCodeStatus(logList);    // Частые коды ответа
        int averageResponseSize = countResponseSize(logList);                       // Средний размер ответа

        switch (outFormat) {
            case ".md" -> new PrintMD().print();
            default -> {
                return;
            }
        }

        LOGGER.info(amountOfRequests);
        LOGGER.info(addressesStatistic.toString());
        LOGGER.info(mostRequestedStatistic.toString());
        LOGGER.info(mostCodeStatusStatistic.toString());
        LOGGER.info(averageResponseSize);
    }

    private int countResponseSize(List<String> logList) {
        int averageResponseSize = 0;
        int counter = 0;
        for (String string : logList) {
            Pattern pattern = Pattern.compile("\\s[012345]\\d{2}(\\s\\d+\\s)");
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                String sizeString = matcher.group(1).trim();
                if ("0".equals(sizeString)) {
                    continue;
                }
                averageResponseSize += Integer.parseInt(sizeString);
                counter++;
            }
        }
        return counter == 0 ? 0 : (averageResponseSize / counter);
    }

    private Map<String, Integer> countCodeStatus(List<String> logList) {
        Map<String, Integer> statistic = new HashMap<>();
        Pattern pattern = Pattern.compile("\\s[012345]\\d{2}\\s");
        for (String string : logList) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                String code = matcher.group().trim();
                statistic.merge(code, 1, Integer::sum);
            }
        }
        return statistic;
    }

    private Map<String, Integer> countRequests(List<String> logList) {
        Map<String, Integer> statistic = new HashMap<>();
        Pattern pattern = Pattern.compile("\"([A-Z]+.*? HTTP/\\d\\.\\d)\" ");
        for (String string : logList) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                String request = matcher.group().trim();
                statistic.merge(request, 1, Integer::sum);
            }
        }
        return statistic;
    }

    private Map<String, Integer> parseAddress(List<String> logList) {
        Map<String, Integer> statistic = new HashMap<>();
        Pattern pattern = Pattern.compile("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})");
        for (String string : logList) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                String address = matcher.group().trim();
                statistic.merge(address, 1, Integer::sum);
            }
        }
        return statistic;
    }

    private String parseFormat(String format) {
        switch (format) {
            case "markdown" -> {
                return ".md";
            }
            case "adoc" -> {
                return ".adoc";
            }
            default -> {
                return null;
            }
        }
    }

    private List<String> filterLogsByDate(File logFile, LocalDate fromDate, LocalDate toDate) {
        List<String> filteredLogList = new ArrayList<>();
        // Отсеем по дате
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logFile))) {
            String log;
            while ((log = bufferedReader.readLine()) != null) {
                LocalDate logDate = parseDate(log);
                if (logDate.isAfter(fromDate) && logDate.isBefore(toDate)) {
                    filteredLogList.add(log);
                }
            }
        }  catch (IOException e) {
            LOGGER.info(e);
        }

        return filteredLogList;
    }

    // [17/May/2015:08:05:23 +0000]
    private LocalDate parseDate(String log) {
        LocalDate logDate = null;
        Pattern pattern = Pattern.compile("\\[(.*?)\\+");
        Matcher matcher = pattern.matcher(log);
        if (matcher.find()) {
            String date = matcher.group(1).trim();
            DateTimeFormatter dateTimeFormatter =
                new DateTimeFormatterBuilder()
                    .appendPattern("dd/MMM/yyyy:HH:mm:ss")
                    .toFormatter(Locale.ENGLISH);
            logDate = LocalDate.parse(date, dateTimeFormatter);
        }
        return logDate;
    }
}
