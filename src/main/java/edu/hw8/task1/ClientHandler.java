package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressWarnings({"MultipleStringLiterals", "MissingSwitchDefault", "LineLength"})
public class ClientHandler implements Runnable {
    private static final String[] WORDS = {"личности", "оскорбления", "глупый", "интеллект"};
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String response = getResponse(line);
                writer.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(String input) {
        String response = null;
        for (String insult : WORDS) {
            if (input.contains(insult)) {
                switch (insult) {
                    case "глупый":
                        response = "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
                        break;
                    case "интеллект":
                        response = "Чем ниже интеллект, тем громче оскорбления.";
                        break;
                    case "личности":
                        response = "Не переходи на личности там, где их нет.";
                        break;
                    case "оскорбления":
                        response = "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.";
                        break;
                }
            }
            if (response != null) {
                return response;
            }
        }
        return "Ничего не найдено.";
    }
}
