package edu.hw6.task6;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PortScannerTest {
    private Set<Integer> portsMap;

    @BeforeEach
    void init() {
        portsMap = Set.of(135, 137, 139, 445, 843, 1900, 3702, 5353, 17500, 27017);
    }

    @Test
    void scanPorts() {
        PortScanner portScanner = new PortScanner();
        int response = portScanner.scan(portsMap);
        assertThat(response).isEqualTo(portsMap.size());

        response = portScanner.scan(Set.of());
        assertThat(response).isEqualTo(0);

        // Этого порта нет в нашей библиотеке "популярных" портов, поэтому мы не можем узнать кем он используется
        response = portScanner.scan(Set.of(111));
        assertThat(response).isEqualTo(0);
    }
}
