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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SingleThreadCracker {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PASSWORDS_FILE = "src/main/java/edu/hw8/task3/test/passwords.txt";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final List<Character> ALPHABET_ARRAY = ALPHABET.chars().mapToObj(e -> (char) e).toList();
    private static final int MIN_PASSWORD_SIZE = 4;
    private static final int MAX_PASSWORD_SIZE = 6;

    public void crack() {
        Map<String, String> passwords = readPassword(PASSWORDS_FILE);
        Map<String, String> result = new HashMap<>();

        String password = null;
        LEN:
        for (int len = MIN_PASSWORD_SIZE; len <= MAX_PASSWORD_SIZE; len++) {
            while (!passwords.isEmpty()) {
                password = nextPassword(password, len);
                String hash = md5(password);
                if (passwords.containsKey(hash)) {
                    result.put(passwords.get(hash), password);
                    passwords.remove(hash);
                }

                if (password.equals("9999") || password.equals("99999")) {
                    continue LEN;
                }
            }
        }
        LOGGER.info(result);
    }

    private static String nextPassword(String previousPassword, int length) {
        if (previousPassword == null) {
            return "aaaa";
        } else if (length < MIN_PASSWORD_SIZE || length > MAX_PASSWORD_SIZE) {
            throw new IllegalArgumentException();
        }

        char[] previous = previousPassword.toCharArray();
        for (int i = previous.length - 1; i >= 0; i--) {
            if (previous[i] == '9') {
                previous[i] = 'a';
            } else {
                int alphaIndex = ALPHABET_ARRAY.indexOf(previous[i]);
                previous[i] = ALPHABET_ARRAY.get(alphaIndex + 1);
                break;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(previous[i]);
        }
        return result.toString();
    }

    private static Map<String, String> readPassword(String path) {
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
    private static String md5(String string) {
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
