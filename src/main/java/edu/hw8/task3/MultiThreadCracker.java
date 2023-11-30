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
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"ParameterAssignment"})
public class MultiThreadCracker {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MIN_PASSWORD_SIZE = 4;
    private static final int MAX_PASSWORD_SIZE = 6;
    private static final String PASSWORDS_FILE = "src/main/java/edu/hw8/task3/test/passwords.txt";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final List<Character> ALPHABET_LIST = ALPHABET.chars().mapToObj(e -> (char) e).toList();
    private static final char LAST_CHAR = ALPHABET_LIST.get(ALPHABET_LIST.size() - 1);
    private static final char FIRST_CHAR = ALPHABET_LIST.get(0);

    private final int amountOfThreads;
    private final String passwordFilePath;
    private final Map<String, String> crackedPasswords;

    public MultiThreadCracker(String passwordFilePath, int amountOfThreads) {
        this.passwordFilePath = passwordFilePath;
        this.amountOfThreads = amountOfThreads;
        crackedPasswords = new HashMap<>();
    }

    public void crack() {
        Thread[] threads = new Thread[amountOfThreads];
        Map<String, String> passwords = readPassword(passwordFilePath);
        Map<String, String> result = new ConcurrentHashMap<>();

        int part = (int) Math.floor((double) ALPHABET_LIST.size() / amountOfThreads);
        for (int i = 0; i < amountOfThreads; i++) {
            int fromLetter = part * i;
            int toLetter = (i == amountOfThreads - 1) ? ALPHABET_LIST.size() - 1 : part * (i + 1) - 1;

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    String password = null;

                    char endPosChar = ALPHABET_LIST.get(toLetter);
                    char startPosChar = ALPHABET_LIST.get(fromLetter);
                    String endPosString = "" + endPosChar + LAST_CHAR + LAST_CHAR + LAST_CHAR;
                    String startPosString = "" + startPosChar + FIRST_CHAR + FIRST_CHAR + FIRST_CHAR;

                    while (!passwords.isEmpty()) {
                        password = nextPassword(password, startPosChar, endPosString);
                        String hash = md5(password);

                        if (passwords.containsKey(hash)) {
                            synchronized (crackedPasswords) {
                                synchronized (passwords) {
                                    crackedPasswords.put(passwords.get(hash), password);
                                    passwords.remove(hash);
                                }
                            }
                        }

                        if (password.equals(startPosString + startPosChar)) {
                            endPosString += LAST_CHAR;
                        }
                    }
                }
            });
            threads[i].start();
        }

        try {
            for (int i = 0; i < amountOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info(crackedPasswords);
    }

    public Map<String, String> getCrackedPasswords() {
        return crackedPasswords;
    }

    private static String nextPassword(String previousPassword, char startPosChar, String endPosString) {
        if (previousPassword == null) {
            return "" + startPosChar + FIRST_CHAR + FIRST_CHAR + FIRST_CHAR;
        } else if (previousPassword.equals(endPosString)) {
            return String.valueOf(startPosChar).repeat(Math.max(0, previousPassword.length() + 1));
        } else if (previousPassword.length() < MIN_PASSWORD_SIZE || previousPassword.length() > MAX_PASSWORD_SIZE) {
            throw new IllegalArgumentException();
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

    private static Map<String, String> readPassword(String path) {
        Map<String, String> passwords = new ConcurrentHashMap<>();
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
}
