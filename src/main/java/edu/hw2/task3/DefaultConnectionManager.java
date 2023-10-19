package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    private final static float FAILURE = 0.2f;
    // Впервые на этом курсе стал работать с тестами,
    // поэтому добавил одну статик переменную для теста на случай выброса исключения
    private static byte failureTest = 0;

    @Override
    public Connection getConnection() {
        if ((Math.random()) < FAILURE || failureTest == 0) {
            failureTest++;
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
