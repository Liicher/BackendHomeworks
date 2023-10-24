package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public List<Character> stringToList(String input) {
        List<Character> output = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            output.add(ch);
        }
        return output;
    }
}
