package edu.hw8.task1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import static edu.hw8.task1.ClientHandler.getLastOutput;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServerTest {
    private static final String QUOTE = "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";

    @Test
    void acceptTest () throws InterruptedException, IOException {
        Server server = new Server(1234, 2);
        server.start();
        assertTrue(server.isAlive());

        Thread.sleep(1000);

        Client client = new Client(1234);
        client.start();
        assertTrue(client.isAlive());

        String message = "глупый" + System.lineSeparator() + "close" + System.lineSeparator();
        InputStream inputStream = new ByteArrayInputStream(message.getBytes());
        System.setIn(inputStream);

        Thread.sleep(1000);

        String response = getLastOutput();
        assertThat(response).isEqualTo(QUOTE);

        inputStream.close();
        client.interrupt();
        server.shutdown();
    }
}
