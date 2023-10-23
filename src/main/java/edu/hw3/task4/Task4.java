package edu.hw3.task4;

/**
 * Создать функцию, которая принимает арабское
 * число и преобразует его в римское.
 */
public class Task4 {
    private final static int CONST = 3999;
    private final static int THOUSAND = 1000;
    private final static int HUNDRED = 100;
    private final static int TEN = 10;

    public String convertToRoman(int input) {
        if (input < 1 || input > CONST) {
            throw new IllegalArgumentException();
        }
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[input / THOUSAND] + hundreds[(input % THOUSAND) / HUNDRED]
            + tens[(input % HUNDRED) / TEN] + units[input % TEN];
    }
}
