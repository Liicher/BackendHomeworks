package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressWarnings({"MultipleStringLiterals", "MissingSwitchDefault", "LineLength"})
public class ClientHandler implements Runnable {
    private static final String[] WORDS = {"личности", "оскорбления", "глупый", "интеллект"};
    private static final String[] QUOTES = {"Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления. Ты в этом убедился на собственном примере."
    };

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
            String response;
            while (!"exit".equals((line = reader.readLine()))) {
                response = getResponse(line);
                writer.println(response);
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(String input) {
        String response = null;
        for (String insult : WORDS) {
            if (input != null && input.contains(insult)) {
                switch (insult) {
                    case "личности":
                        response = QUOTES[0];
                        break;
                    case "оскорбления":
                        response = QUOTES[1];
                        break;
                    case "глупый":
                        response = QUOTES[2];
                        break;
                    case "интеллект":
                        response = QUOTES[3];
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
