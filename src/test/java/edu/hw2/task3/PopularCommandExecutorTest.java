package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PopularCommandExecutorTest {
    @Test
    @DisplayName("Fault")
    void updatePackagesFault() {
        assertThrows(ConnectionException.class, () -> {
            new PopularCommandExecutor(new FaultyConnectionManager(), 1).updatePackages();
        });
    }

	@Test
    @DisplayName("Success")
	void updatePackagesSuccessful() {
        assertDoesNotThrow(() -> new PopularCommandExecutor(new DefaultConnectionManager(), 5).updatePackages());
        assertDoesNotThrow(() -> new PopularCommandExecutor(new FaultyConnectionManager(), 50).updatePackages());
	}
}
