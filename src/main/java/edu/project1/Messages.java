package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *      Messages - класс для хранения всех выводов на экран
 */
public class Messages {
    private final static String SEPARATE = "===================================";
    private final static Logger LOGGER = LogManager.getLogger();

    public void messageIntro() {
        LOGGER.info(SEPARATE);
        LOGGER.info("Hello and welcome!");
        LOGGER.info("Do you want to play\"Hangman?\"");
    }

    public void messageStart() {
        LOGGER.info(SEPARATE);
        LOGGER.info("Enter 'Y' to start");
        LOGGER.info("Enter 'Q' to quit");
    }

    public void messageIncorrectInput() {
        LOGGER.info("--------===--------");
        LOGGER.info("incorrect input!");
        LOGGER.info("-------------------");
    }

    public void messageWin(String word) {
        LOGGER.info(SEPARATE);
        LOGGER.info(word);
        LOGGER.info("Congratulations!");
        LOGGER.info("You correctly guessed the hidden word!");
    }

    public void messageLose(String word) {
        LOGGER.info(SEPARATE);
        LOGGER.info("You lost :(");
        LOGGER.info("You didn't guess the hidden word!");
        LOGGER.info("Hidden word was \"" + word + "\"");
    }

    public void messageState(String state, int attempt, int maxAttempts) {
        LOGGER.info(SEPARATE);
        LOGGER.info(state);
        LOGGER.info(attempt + " attempts out of " + maxAttempts);
        LOGGER.info((maxAttempts - attempt) + " attempts left");
        LOGGER.info("Guess the letter or type '0' to exit");
    }
}
