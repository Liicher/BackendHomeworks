package edu.hw8.task1.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static edu.hw8.task1.test.ServerTest.*;

public class ServerTest {
    private static final int PORT = 8888; // порт сервера
    private static final int MAX_CONNECTIONS = 5; // максимальное количество одновременных соединений

    static final String KEYWORD1 = "личности";
    static final String KEYWORD2 = "оскорбления";
    static final String KEYWORD3 = "глупый";
    static final String KEYWORD4 = "интеллект";

    // список цитат по теме
    static final String QUOTE1 = "Не переходи на личности там, где их нет";
    static final String QUOTE2 =
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
    static final String QUOTE3 =
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
    static final String QUOTE4 =
        "Чем ниже интеллект, тем громче оскорбления. Ты в этом убедился на собственном примере.";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS); // создаем пул потоков
        try (
            ServerSocket serverSocket = new ServerSocket(PORT)) { // создаем серверный сокет
            System.out.println("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept(); // ждем подключения клиента
                executorService.execute(new ClientHandlerTest(socket)); // передаем подключение клиента обработчику
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// обработчик подключения клиента
class ClientHandlerTest implements Runnable {
    private Socket socket;

    public ClientHandlerTest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String request = reader.readLine(); // читаем запрос от клиента
            String response;
            if (request.contains(KEYWORD1)) {
                response = QUOTE1;
            } else if (request.contains(KEYWORD2)) {
                response = QUOTE2;
            } else if (request.contains(KEYWORD3)) {
                response = QUOTE3;
            } else if (request.contains(KEYWORD4)) {
                response = QUOTE4;
            } else {
                response = "К сожалению, я не знаю цитат на эту тему";
            }

            writer.println(response); // отправляем ответ клиенту
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
