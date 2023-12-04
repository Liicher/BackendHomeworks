package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    private final ExecutorService executor;
    private final int port;
    private final int maxConnections;

    public Server(int port, int maxConnections) {
        this.port = port;
        this.maxConnections = maxConnections;
        executor = Executors.newFixedThreadPool(maxConnections);
    }

    @Override
    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(port)
            ) {
            int clients = 0;
            while (clients < maxConnections) {
                Socket socket = serverSocket.accept();
                executor.execute(new ClientHandler(socket));
                clients++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
