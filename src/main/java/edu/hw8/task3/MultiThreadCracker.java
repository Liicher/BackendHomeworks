package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.task3.Task3Utils.ALPHABET_LIST;
import static edu.hw8.task3.Task3Utils.FIRST_CHAR;
import static edu.hw8.task3.Task3Utils.LAST_CHAR;
import static edu.hw8.task3.Task3Utils.md5;
import static edu.hw8.task3.Task3Utils.nextPassword;
import static edu.hw8.task3.Task3Utils.readPassword;

@SuppressWarnings({"ParameterAssignment"})
public class MultiThreadCracker {
    private static final Logger LOGGER = LogManager.getLogger();

    private final String passwordFilePath;
    private final Map<String, String> crackedPasswords;
    private final int amountOfThreads;

    public MultiThreadCracker(String passwordFilePath, int amountOfThreads) {
        this.passwordFilePath = passwordFilePath;
        this.amountOfThreads = amountOfThreads;
        crackedPasswords = new HashMap<>();
    }

    public void crack() {
        Thread[] threads = new Thread[amountOfThreads];
        Map<String, String> passwords = readPassword(passwordFilePath);

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
}
