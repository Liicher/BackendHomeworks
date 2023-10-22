package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ConsoleHangmanTest {

    @Test
    public void uncorrectedGuess() {
        Session session = new Session();
        ConsoleHangman consoleHangman = new ConsoleHangman();
        consoleHangman.tryGuess(session, "z");
        assertThat(session.getAttempts()).isEqualTo(1);
        consoleHangman.tryGuess(session, "aa");
        assertThat(session.getAttempts()).isEqualTo(1);
        consoleHangman.tryGuess(session, " ");
        assertThat(session.getAttempts()).isEqualTo(1);
        consoleHangman.tryGuess(session, "");
        assertThat(session.getAttempts()).isEqualTo(1);
        consoleHangman.tryGuess(session, null);
        assertThat(session.getAttempts()).isEqualTo(1);
    }

    @Test
    public void correctedGuess() {
        Session session = new Session();
        ConsoleHangman consoleHangman = new ConsoleHangman();
        consoleHangman.tryGuess(session, "a");
        assertThat(session.getAttempts()).isEqualTo(0);
        assertThat(session.getUserAnswer()).contains('a');
    }

    @Test
    public void exitGuess() {
        Session session = new Session();
        ConsoleHangman consoleHangman = new ConsoleHangman();
        consoleHangman.tryGuess(session, "a");
        consoleHangman.tryGuess(session, "0");
        assertThat(session.getAttempts()).isEqualTo(0);
    }
}
