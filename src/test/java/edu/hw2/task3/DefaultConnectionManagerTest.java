package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DefaultConnectionManagerTest {
    @Test
    @DisplayName("DefaultConnectionManagerTest")
    void defaultConnectionManagerTest() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        assertThat(defaultConnectionManager.getConnection()).isInstanceOf(FaultyConnection.class);
        assertThat(defaultConnectionManager.getConnection()).isInstanceOf(Connection.class);
    }
}
