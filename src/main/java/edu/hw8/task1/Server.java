package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    private static final int PORT = 8080;
    private static final int MAX_CONNECTIONS = 5;

    public void startServer() {
        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                ExecutorService executor = Executors.newFixedThreadPool(MAX_CONNECTIONS)
            ) {
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new ClientHandler(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
