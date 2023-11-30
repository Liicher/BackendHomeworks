package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("InnerAssignment")
public class Client extends Thread {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String HOST = "localhost";
    private final int port;

    public Client(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (
            Socket socket = new Socket(HOST, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            LOGGER.info("Enter message: ");
            String keyword;
            while (!"close".equals(keyword = reader.readLine())) {
                writer.println(keyword);
                writer.flush();
                LOGGER.info("Server: {}", serverReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
