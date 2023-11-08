package edu.hw5.task8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class SomeRegexTest {
    private SomeRegex someRegex;

    @BeforeEach
    void init() {
        someRegex = new SomeRegex();
    }

    // Все проверки в одном util-методе проходят, поэтому тест для одного метода
    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> someRegex.isOddLength(null));
        assertThrows(IllegalArgumentException.class, () -> someRegex.isOddLength("null"));
        assertThrows(IllegalArgumentException.class, () -> someRegex.isOddLength(""));
        assertThrows(IllegalArgumentException.class, () -> someRegex.isOddLength("00130"));
    }

    @Test
    void isOddLength() {
        assertThat(someRegex.isOddLength("10011")).isTrue();
        assertThat(someRegex.isOddLength("1001")).isFalse();
    }

    @Test
    void isStartWithZeroAndOddOrOneAndEven() {
        assertThat(someRegex.isStartWithZeroAndOddOrOneAndEven("01101")).isTrue();
        assertThat(someRegex.isStartWithZeroAndOddOrOneAndEven("0110")).isFalse();
        assertThat(someRegex.isStartWithZeroAndOddOrOneAndEven("1001")).isTrue();
        assertThat(someRegex.isStartWithZeroAndOddOrOneAndEven("10011")).isFalse();
    }

    @Test
    void isAmountOfZerosIsThree() {
        assertThat(someRegex.isAmountOfZerosIsThree("011010")).isTrue();
        assertThat(someRegex.isAmountOfZerosIsThree("001110110101101")).isTrue();
        assertThat(someRegex.isAmountOfZerosIsThree("111110110101101")).isFalse();
        assertThat(someRegex.isAmountOfZerosIsThree("0011010110101101")).isFalse();
    }

    @Test
    void isNotDoubleOrTripleOnes() {
        assertThat(someRegex.isNotDoubleOrTripleOnes("11")).isFalse();
        assertThat(someRegex.isNotDoubleOrTripleOnes("111")).isFalse();
        assertThat(someRegex.isNotDoubleOrTripleOnes("0110")).isTrue();
    }

    @Test
    void isEveryOddEqualsOne() {
        assertThat(someRegex.isEveryOddEqualsOne("11")).isTrue();
        assertThat(someRegex.isEveryOddEqualsOne("110")).isFalse();
        assertThat(someRegex.isEveryOddEqualsOne("1110101")).isTrue();
        assertThat(someRegex.isEveryOddEqualsOne("1110100")).isFalse();
    }

    @Test
    void isContainsMoreThanTwoZerosAndLessThanTwoOnes() {
        assertThat(someRegex.isContainsMoreThanTwoZerosAndLessThanTwoOnes("1000")).isTrue();
        assertThat(someRegex.isContainsMoreThanTwoZerosAndLessThanTwoOnes("00001000")).isTrue();
        assertThat(someRegex.isContainsMoreThanTwoZerosAndLessThanTwoOnes("1011111111111")).isFalse();
        assertThat(someRegex.isContainsMoreThanTwoZerosAndLessThanTwoOnes("1")).isFalse();
        assertThat(someRegex.isContainsMoreThanTwoZerosAndLessThanTwoOnes("10")).isFalse();
    }

    @Test
    void isNotContainsRowOfOnes() {
        assertThat(someRegex.isNotContainsRowOfOnes("1000")).isTrue();
        assertThat(someRegex.isNotContainsRowOfOnes("1010")).isTrue();
        assertThat(someRegex.isNotContainsRowOfOnes("1100")).isFalse();
        assertThat(someRegex.isNotContainsRowOfOnes("01010000111001")).isFalse();
        assertThat(someRegex.isNotContainsRowOfOnes("01010000101001")).isTrue();
    }
}
