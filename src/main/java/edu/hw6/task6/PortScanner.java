package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.Set;
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
    private final static Map<Integer, String> POPULAR_PORTS = Map.of(
        135, "EPMAP",
        137, "Служба имен NetBIOS",
        139, "Служба сеансов NetBIOS",
        445, "Microsoft-DS Active Directory",
        843, "Adobe Flash",
        1900, "SSDP",
        3702, "Динамическое обнаружение веб-служб",
        5353, "Многоадресный DNS",
        17500, "Dropbox",
        27017, "MongoDB"
    );

    // Возвращаю количество проверенных портов, которые имеют приложение, которое их использует
    // При вводе пустого сета будут выведены все проверены все порты и выведены все использующие их приложения
    public int scan(Set<Integer> inputPorts) {
        int inputPortScannedCounter = 0;
        for (int port = PORT_MIN; port <= PORT_MAX; port++) {
            String portService = POPULAR_PORTS.getOrDefault(port, "");
            int popularPort = (inputPorts.isEmpty() || inputPorts.contains(port)) ? port : -1;

            if (!portService.isEmpty() && popularPort != -1) {
                // TCP
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    LOGGER.info("TCP\t" + port + "\t" + portService);
                } catch (IOException ignored) {
                }
                // UDP
                try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                    LOGGER.info("UDP\t" + port + "\t" + portService);
                } catch (IOException ignored) {
                }
                inputPortScannedCounter++;
            }
        }
        return inputPortScannedCounter;
    }
}
