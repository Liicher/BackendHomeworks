package edu.hw8.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"ParameterAssignment", "RegexpSinglelineJava"})
public class MultiThreadCracker {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MIN_PASSWORD_SIZE = 4;
    private static final int MAX_PASSWORD_SIZE = 6;
    private static final String PASSWORDS_FILE = "src/main/java/edu/hw8/task3/test/passwords.txt";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final List<Character> ALPHABET_LIST = ALPHABET.chars().mapToObj(e -> (char) e).toList();
    private static final char NINE = ALPHABET_LIST.get(ALPHABET_LIST.size() - 1);

    private final int amountOfThreads;

    public MultiThreadCracker(int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
    }

    public void crack() {
        Thread[] threads = new Thread[amountOfThreads];
        Map<String, String> passwords = readPassword(PASSWORDS_FILE);
        Map<String, String> result = new ConcurrentHashMap<>();

        int part = (int) Math.floor((double) ALPHABET_LIST.size() / amountOfThreads);
        for (int i = 0; i < amountOfThreads; i++) {
            int fromLetter = part * i;
            int toLetter = (i == amountOfThreads - 1) ? ALPHABET_LIST.size() - 1 : part * (i + 1) - 1;

            int finalI = i;

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    String password = null;

                    char endPosChar = ALPHABET_LIST.get(toLetter);      // s
                    char startPosChar = ALPHABET_LIST.get(fromLetter);  // 9

                    String endPosString = "" + endPosChar + NINE + NINE + NINE; // 9999
                    String startPosString = "" + startPosChar + startPosChar + startPosChar + startPosChar;     // ssss

                    LEN:
                    for (int len = MIN_PASSWORD_SIZE; len <= MAX_PASSWORD_SIZE; len++) {
                        while (!passwords.isEmpty()) {
                            password = nextPassword(password, len, startPosChar, endPosString);
                            String hash = md5(password);

                            System.out.println(threads[finalI].getName() + " - " + password);

                            if (passwords.containsKey(hash)) {
                                synchronized (result) {
                                    result.put(passwords.get(hash), password);
                                    passwords.remove(hash);
                                }
                            }

                            if (password.equals(endPosString) || password.equals(endPosString + endPosChar)) {
                                password = startPosString + startPosChar;
                                continue LEN;
                            } else if (password.equals("-")) {
                                break LEN;
                            }
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

        LOGGER.info(result);
    }

    private static String nextPassword(String previousPassword, int length, char fromChar, String toString) {
        if (previousPassword == null) {
            return "" + fromChar + fromChar + fromChar + fromChar;
        } else if (length < MIN_PASSWORD_SIZE || length > MAX_PASSWORD_SIZE) {
            throw new IllegalArgumentException();
        } else if (previousPassword.equals(toString)) {
            previousPassword += fromChar;
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
        for (int i = 0; i < length; i++) {
            result.append(previous[i]);
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

    /*public static void main(String[] args) {
        MultiThreadCracker multiThreadCracker = new MultiThreadCracker(2);
        multiThreadCracker.crack();
    }*/
}
