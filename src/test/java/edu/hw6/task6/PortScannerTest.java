package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

class PortScannerTest {

    @Test
    void scanPorts() {
        PortScanner portScanner = new PortScanner();
        assertThatCode(portScanner::scan).doesNotThrowAnyException();
    }
}
