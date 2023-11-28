package edu.hw8.task1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static edu.hw8.task1.QuoteServer.KEYWORDS;
import static edu.hw8.task1.QuoteServer.QUOTES;

class QuoteServer {
    private static final int PORT = 1234;
    private static final int MAX_CONNECTIONS = 5;

    public static final String[] KEYWORDS = {"личности", "оскорбления", "глупый", "интеллект"};
    public static final String[] QUOTES = {"Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления. Интеллектуальные люди не нуждаются в них."};

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        try (
            ServerSocket serverSocket = new ServerSocket(PORT)
        ) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ClientHandler2(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler2 implements Runnable {
    private final Socket socket;

    public ClientHandler2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("Welcome to Quote Server!");
            String input;
            while ((input = socket.getInputStream().toString()) != null) {
                for (int i = 0; i < KEYWORDS.length; i++) {
                    if (input.contains(KEYWORDS[i])) {
                        out.println(QUOTES[i]);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class QuoteClient {
    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(HOST, PORT);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println(in.nextLine());
            Scanner scanner = new Scanner(System.in);
            String input;
            while (!(input = scanner.nextLine()).equals("exit")) {
                out.println(input);
                System.out.println(in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


