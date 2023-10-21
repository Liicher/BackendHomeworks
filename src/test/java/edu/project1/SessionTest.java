package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {
    Session session = new Session();

    @Test
    void checks() {
        assertThat(session.checkUserInputCharacter("a")).isEqualTo(true);
        assertThat(session.checkWinnable()).isEqualTo(false);
        assertThat(session.checkAttempts()).isEqualTo(true);
    }

    @Test
    void guessesTest() {
        session.guess("a");
        assertThat(session.getAttempts()).isEqualTo(0);
        session.guess("z");
        assertThat(session.getAttempts()).isEqualTo(1);
    }
}
