package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class InfoGeneratorTest {
    Session session = new Session();

    @Test
    void listsChecks() {
        assertThat(session.getAnswerString()).hasSizeGreaterThan(3);
        assertThat(session.getUserAnswer()).containsOnly('*');
        assertThat(session.getAnswerString()).doesNotContain("*");
    }
}
