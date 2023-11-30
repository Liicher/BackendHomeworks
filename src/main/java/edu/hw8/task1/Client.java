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
public class Client {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public void startConnection() {
        try (
            Socket socket = new Socket(HOST, PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            LOGGER.info("Введите ключевое слово: ");
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
