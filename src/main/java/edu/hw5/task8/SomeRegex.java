package edu.hw5.task8;

/**
 * Напишите регулярные выражения для строк из алфавита {0, 1}:
 * 1 - нечетной длины
 * 2 - начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
 * 3 - количество 0 кратно 3
 * 4 - любая строка, кроме 11 или 111
 * 5 - каждый нечетный символ равен 1
 * 6 - содержит не менее двух 0 и не более одной 1
 * 7 - нет последовательных 1
 */

public class SomeRegex {

    // нечетной длины
    public boolean isOddLength(String input) {
        inputCheck(input);
        return input.matches("^([0|1]{2})*[0|1]$");
    }

    // начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public boolean isStartWithZeroAndOddOrOneAndEven(String input) {
        inputCheck(input);
        return input.matches("^(1[0|1]([01]{2})*|(0[0|1]([01]{2})*[01]))$");
    }

    // количество 0 кратно 3
    public boolean isAmountOfZerosIsThree(String input) {
        inputCheck(input);
        return input.matches("^(1*01*01*01*)*$");
    }

    // любая строка, кроме 11 или 111
    public boolean isNotDoubleOrTripleOnes(String input) {
        inputCheck(input);
        return !input.matches("^(11|111)$");
    }

    // каждый нечетный символ равен 1
    public boolean isEveryOddEqualsOne(String input) {
        inputCheck(input);
        return input.matches("^((1[0|1])+1?)$");
    }

    // содержит не менее двух 0 и не более одной 1
    public boolean isContainsMoreThanTwoZerosAndLessThanTwoOnes(String input) {
        inputCheck(input);
        return input.matches("^(0+1?0+)|(0{2,}1?0*)|(0*1?0{2,})$");
    }

    // нет последовательных 1
    public boolean isNotContainsRowOfOnes(String input) {
        inputCheck(input);
        return !input.matches(".*11.*");
    }

    private void inputCheck(String input) {
        if (input == null || input.isEmpty() || !input.matches("[0|1]+")) {
            throw new IllegalArgumentException();
        }
    }
}
