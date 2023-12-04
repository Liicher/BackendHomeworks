package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.task3.Task3Utils.FIRST_CHAR;
import static edu.hw8.task3.Task3Utils.LAST_CHAR;
import static edu.hw8.task3.Task3Utils.MIN_PASSWORD_SIZE;
import static edu.hw8.task3.Task3Utils.md5;
import static edu.hw8.task3.Task3Utils.nextPassword;
import static edu.hw8.task3.Task3Utils.readPassword;
//"src/main/java/edu/hw8/task3/test/passwords.txt"

public class SingleThreadCracker {
    private static final Logger LOGGER = LogManager.getLogger();

    private final String passwordFilePath;
    private final Map<String, String> crackedPasswords;

    public SingleThreadCracker(String passwordFilePath) {
        this.passwordFilePath = passwordFilePath;
        crackedPasswords = new HashMap<>();
    }

    public void crack() {
        Map<String, String> passwords = readPassword(passwordFilePath);
        String endPosString = String.valueOf(LAST_CHAR).repeat(Math.max(0, MIN_PASSWORD_SIZE));
        String password = null;
        while (!passwords.isEmpty()) {
            password = nextPassword(password, FIRST_CHAR, endPosString);
            String hash = md5(password);

            if (passwords.containsKey(hash)) {
                crackedPasswords.put(passwords.get(hash), password);
                passwords.remove(hash);
            }

            if (password.equals(String.valueOf(FIRST_CHAR).repeat(Math.max(0, endPosString.length() + 1)))) {
                endPosString += LAST_CHAR;
            }
        }
        LOGGER.info(crackedPasswords);
    }

    public Map<String, String> getCrackedPasswords() {
        return crackedPasswords;
    }
}
