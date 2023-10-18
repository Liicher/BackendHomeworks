package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    private final static byte FAILURE = 0;
    private final byte execution = (byte) (Math.random() * 6);

    @Override
    public Connection getConnection() {
        if (execution == FAILURE) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
