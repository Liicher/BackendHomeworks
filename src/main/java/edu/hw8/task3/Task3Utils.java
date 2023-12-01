package edu.hw8.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3Utils {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final List<Character> ALPHABET_LIST = ALPHABET.chars().mapToObj(e -> (char) e).toList();
    public static final int MIN_PASSWORD_SIZE = 4;
    public static final int MAX_PASSWORD_SIZE = 6;
    public static final char FIRST_CHAR = ALPHABET_LIST.get(0);
    public static final char LAST_CHAR = ALPHABET_LIST.get(ALPHABET_LIST.size() - 1);

    private Task3Utils() {
    }

    public static String nextPassword(String previousPassword, char startPosChar, String endPosString) {
        if (previousPassword == null) {
            return "" + startPosChar + FIRST_CHAR + FIRST_CHAR + FIRST_CHAR;
        } else if (previousPassword.length() < MIN_PASSWORD_SIZE || previousPassword.length() > MAX_PASSWORD_SIZE) {
            throw new IllegalArgumentException();
        } else if (previousPassword.equals(endPosString)) {
            return String.valueOf(startPosChar).repeat(Math.max(0, previousPassword.length() + 1));
        }

        char[] previous = previousPassword.toCharArray();
        for (int i = previous.length - 1; i >= 0; i--) {
            if (previous[i] == '9') {
                previous[i] = 'a';
            } else {
                int alphaIndex = ALPHABET_LIST.indexOf(previous[i]);
                previous[i] = ALPHABET_LIST.get(alphaIndex + 1);
                break;
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : previous) {
            result.append(c);
        }
        return result.toString();
    }

    public static Map<String, String> readPassword(String path) {
        Map<String, String> passwords = new HashMap<>();
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                passwords.put(parts[1], parts[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return passwords;
    }

    @SuppressWarnings("MagicNumber")
    public static String md5(String string) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(string.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest);
            StringBuilder hash = new StringBuilder(bigInteger.toString(16));

            while (hash.length() < 32) {
                hash.insert(0, "0");
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
