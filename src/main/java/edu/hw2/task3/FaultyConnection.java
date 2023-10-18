package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static byte FAILURE = 0;
    private final byte execution = (byte) (Math.random() * 6);
    // Впервые на этом курсе стал работать с тестами,
    // поэтому добавил одну статик переменную для теста на случай выброса исключения
    private static byte failureTest = 0;

    @Override
    public void execute(String command) {
        if (execution == FAILURE || failureTest == FAILURE) {
            LOGGER.info("Connection Error!");
            failureTest++;
            throw new ConnectionException();
        }
        LOGGER.info(command + " --- executed.");
    }

    @Override
    public void close() {
        LOGGER.info("Connection \"" + this.getClass().getName() + "\" closed.");
    }
}
