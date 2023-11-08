package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringMethod {
    public boolean isSubstring(String subStringInput, String fullStringInput) {
        if (subStringInput == null || fullStringInput == null || fullStringInput.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Pattern subStringPattern = Pattern.compile(".*" + subStringInput + ".*");
        Matcher stringMatcher = subStringPattern.matcher(fullStringInput);
        return stringMatcher.find();
    }
}
