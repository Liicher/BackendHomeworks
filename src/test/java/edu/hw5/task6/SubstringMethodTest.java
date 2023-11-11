package edu.hw5.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class SubstringMethodTest {
    private SubstringMethod substringMethod;

    @BeforeEach
    void init() {
        substringMethod = new SubstringMethod();
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> substringMethod.isSubstring(null, "Hello"));
        assertThrows(IllegalArgumentException.class, () -> substringMethod.isSubstring("Hello", null));
    }

    @Test
    void isSubstring() {
        assertThat(substringMethod.isSubstring("el", "Hello")).isTrue();
        assertThat(substringMethod.isSubstring("elo", "Hello")).isFalse();
        assertThat(substringMethod.isSubstring("Hello", "Hello")).isTrue();
        assertThat(substringMethod.isSubstring("", "Hello")).isTrue();
    }
}
