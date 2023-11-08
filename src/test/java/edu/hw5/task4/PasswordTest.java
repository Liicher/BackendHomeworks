package edu.hw5.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {
    private Password password;

    @BeforeEach
    void init() {
        password = new Password();
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> password.isAllowedPassword(null));
        assertThrows(IllegalArgumentException.class, () -> password.isAllowedPassword(""));
    }

    @Test
    void isAllowedPassword() {
        assertThat(password.isAllowedPassword("1234567")).isFalse();
        assertThat(password.isAllowedPassword("~!@#$%^&*|")).isTrue();
        assertThat(password.isAllowedPassword("1234567!")).isTrue();
    }
}
