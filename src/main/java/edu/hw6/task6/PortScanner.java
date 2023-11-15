package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Напишите программу, которая сканирует порты и определяет заняты они или нет.
 * Для этого нужно зарегистрировать ServerSocket и DatagramSocket на всех TCP/UDP-портах от 0 до 49151.
 * В случае успеха порт свободен, в противном случае он занят.
 * Дополнительно выведите информацию о потенциальном приложении, которое использует этот порт
 * Выберите несколько, не нужно брать всё
 */

public class PortScanner {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int PORT_MIN = 0;
    private final static int PORT_MAX = 49151;

    // Буду возвращать мапу
    // Ключ - TCP/UDP + приложение
    // Значение - порт
    public Map<List<String>, Integer> scan(Map<Integer, String> inputPorts) {
        Map<List<String>, Integer> result = new HashMap<>();
        for (int port = PORT_MIN; port <= PORT_MAX; port++) {
            String portService = inputPorts.getOrDefault(port, "");

            if (!portService.isEmpty()) {
                // TCP
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    LOGGER.info("TCP\t" + port + "\t" + portService);
                    result.put(List.of("TCP", portService), port);
                } catch (IOException ignored) {
                }
                // UDP
                try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                    LOGGER.info("UDP\t" + port + "\t" + portService);
                    result.put(List.of("UDP", portService), port);
                } catch (IOException ignored) {
                }
            }
        }
        return result;
    }
}
