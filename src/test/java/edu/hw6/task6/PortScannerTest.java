package edu.hw6.task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;

class PortScannerTest {
    private Map<List<String>, Integer> expectedMap;
    private Map<Integer, String> portsMap;

    @BeforeEach
    void init() {
        portsMap = Map.of(
            135, "EPMAP",
            137, "Служба имен NetBIOS",
            139, "Служба сеансов NetBIOS",
            445, "Microsoft-DS Active Directory",
            843, "Adobe Flash",
            1900, "SSDP",
            3702, "Динамическое обнаружение веб-служб",
            5353, "Многоадресный DNS",
            17500, "Dropbox",
            27017, "MongoDB");

        expectedMap = new HashMap<>();
        expectedMap.put(List.of("UDP", "EPMAP"), 135);
        expectedMap.put(List.of("TCP", "Служба имен NetBIOS"), 137);
        expectedMap.put(List.of("UDP", "Служба сеансов NetBIOS"), 139);
        expectedMap.put(List.of("UDP", "Microsoft-DS Active Directory"), 445);
        expectedMap.put(List.of("TCP", "Adobe Flash"), 843);
        expectedMap.put(List.of("UDP", "Adobe Flash"), 843);
        expectedMap.put(List.of("TCP", "SSDP"), 1900);
        expectedMap.put(List.of("TCP", "Динамическое обнаружение веб-служб"), 3702);
        expectedMap.put(List.of("TCP", "Многоадресный DNS"), 5353);
        expectedMap.put(List.of("TCP", "Dropbox"), 17500);
        expectedMap.put(List.of("UDP", "Dropbox"), 17500);
        expectedMap.put(List.of("TCP", "MongoDB"), 27017);
        expectedMap.put(List.of("UDP", "MongoDB"), 27017);
    }

    @Test
    void scanPorts() {
        PortScanner portScanner = new PortScanner();
        Map<List<String>, Integer>  response = portScanner.scan(portsMap);
        assertThat(response).isEqualTo(expectedMap);
    }
}
