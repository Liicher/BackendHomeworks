package edu.hw8.task1.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest {
    private static final String HOST = "localhost"; // адрес сервера
    private static final int PORT = 8888; // порт сервера

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
            System.out.println("Введите ключевое слово:");
            String keyword = reader.readLine();             // читаем ключевое слово от пользователя
            writer.println(keyword);                        // отправляем запрос на сервер
            writer.flush();
            String response = serverReader.readLine();      // читаем ответ от сервера
            System.out.println(response);                   // выводим ответ на экран
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
