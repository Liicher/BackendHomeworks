package edu.hw6.task5;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Задание
 * Создайте класс HackerNews.
 * Реализуйте метод long[] hackerNewsTopStories(), который будет
 * делать HTTP-запрос при помощи HttpClient к "https://hacker-news.firebaseio.com/v0/topstories.json"
 * конвертировать возвращаемый JSON в long[]
 * В общем случае для чтения JSON используются специальные парсеры,
 * но т.к. структура массива очень простая, то можем обойтись без них.
 * В реальном приложении, конечно, мы бы использовали библиотеку, например, Jackson.
 * В случае ошибки ввода-вывода должен быть возвращен пустой массив.
 * Напишите метод String news(long id), который возвращает название новости.
 * Для получения имени новости используйте регулярное выражение.
 */

public class HackerNews {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String HACKER_NEWS_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String HACKER_NEWS_URL_MESSAGE = "https://hacker-news.firebaseio.com/v0/item/37570037.json";

    public long[] hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(HACKER_NEWS_URL))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Gson gson = new Gson();
            return gson.fromJson(responseBody, long[].class);
        } catch (IOException | InterruptedException e) {
            LOGGER.info(e);
            return new long[0];
        }
    }

    public String news(long id) {
        String newsUrl = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(newsUrl))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(responseBody);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.info(e);
        }
        return "TITLE NOT FOUND!";
    }
}

