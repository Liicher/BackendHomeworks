package edu.hw2.task3;

import edu.hw2.task3.exceptions.LimitOfAttemptsException;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int attempt = 1;
        while (attempt <= maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (Exception exception) {
                attempt++;
                if (attempt > maxAttempts) {
                    throw new LimitOfAttemptsException("Limit of attempts", exception.getCause());
                }
            }
        }
    }
}
