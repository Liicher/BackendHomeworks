package edu.hw5.task7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BinaryRegexTest {
    private BinaryRegex binaryRegex;

    @BeforeEach
    void init() {
        binaryRegex = new BinaryRegex();
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isContainsThreeSymbolsAndThirdIsZero(null));
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isContainsThreeSymbolsAndThirdIsZero("1465406780"));
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isContainsThreeSymbolsAndThirdIsZero(""));

        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isFirstAndLastSymbolsAreTheSame(null));
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isFirstAndLastSymbolsAreTheSame("1465406780"));
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isFirstAndLastSymbolsAreTheSame(""));

        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isLengthMoreThanOneAndLessThanThree(null));
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isLengthMoreThanOneAndLessThanThree("1465406780"));
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isLengthMoreThanOneAndLessThanThree("12"));
        assertThrows(IllegalArgumentException.class, () -> binaryRegex.isLengthMoreThanOneAndLessThanThree(""));
    }

    @Test
    void isContainsThreeSymbolsAndThirdIsZero() {
        assertThat(binaryRegex.isContainsThreeSymbolsAndThirdIsZero("10011101010")).isTrue();
        assertThat(binaryRegex.isContainsThreeSymbolsAndThirdIsZero("101100010101")).isFalse();
        assertThat(binaryRegex.isContainsThreeSymbolsAndThirdIsZero("10")).isFalse();
    }

    @Test
    void isFirstAndLastSymbolsAreTheSame() {
        assertThat(binaryRegex.isFirstAndLastSymbolsAreTheSame("0110110")).isTrue();
        assertThat(binaryRegex.isFirstAndLastSymbolsAreTheSame("101101")).isTrue();
        assertThat(binaryRegex.isFirstAndLastSymbolsAreTheSame("10011010")).isFalse();
    }

    @Test
    void isLengthMoreThanOneAndLessThanThree() {
        assertThat(binaryRegex.isLengthMoreThanOneAndLessThanThree("010")).isTrue();
        assertThat(binaryRegex.isLengthMoreThanOneAndLessThanThree("10")).isTrue();
        assertThat(binaryRegex.isLengthMoreThanOneAndLessThanThree("0")).isTrue();
        assertThat(binaryRegex.isLengthMoreThanOneAndLessThanThree("0110110")).isFalse();
    }
}
