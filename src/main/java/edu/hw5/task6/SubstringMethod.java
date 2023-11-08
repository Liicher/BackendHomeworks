package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringMethod {
    public boolean isSubstring(String S, String T) {
        if (S == null || T == null || T.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Pattern subStringPattern = Pattern.compile(".*" + S + ".*");
        Matcher stringMatcher = subStringPattern.matcher(T);
        return stringMatcher.find();
    }
}
