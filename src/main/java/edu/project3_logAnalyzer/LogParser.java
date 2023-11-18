package edu.project3_logAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//  '$remote_addr - $remote_user [$time_local] ' ' "$request" $status $body_bytes_sent ' '"$http_referer" "$http_user_agent"'
//  93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
public class LogParser {
    private final static Logger LOGGER = LogManager.getLogger();

    public void parseLogs(File logFile, LocalDate fromDate, LocalDate toDate) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logFile))){
            String log;
            while ((log = bufferedReader.readLine()) != null) {
                LocalDate logDate = parseDate(log);
            }
        }  catch (IOException e) {
	        LOGGER.info(e);
        }
    }

    private LocalDate parseDate(String log) {
        LocalDate logDate = null;
        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(log);
        if (matcher.find()) {
            String date = matcher.group();
            logDate = LocalDate.parse(date);
        }
        return logDate;
    }

    private void parseAddress(String log) {
        Map<String, Integer> response = new HashMap<>();
        Pattern pattern = Pattern.compile("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})");
        Matcher matcher = pattern.matcher(log);
        if (matcher.find()) {
            String address = matcher.group().trim();
            response.merge(address, 1, Integer::sum);
        }
    }

    public void parse(String inputLog) {
        String[] logString = new String[8];

        Pattern logPattern = Pattern.compile(
            "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - " +
                "( - )" +
                "\\[(.*?)] " +
                "\"([A-Z]+.*? HTTP/\\d\\.\\d)\" " +
                "(\\d{3}) " +
                "(\\d+) " +
                "\"(.*?)\" " +
                "\"(.*?)\"$");

        Matcher logMatcher = logPattern.matcher(inputLog);

        for (int i = 0; i < logString.length; i++) {
            logString[i] = logMatcher.group(i);
        }
    }
}
