package edu.project1;

/**
 *      Messages - класс для хранения всех выводов на экран
 */
@SuppressWarnings({"RegexpSinglelineJava"})
public class Messages {
    private final static String SEPARATE = "===================================";

    public void messageIntro() {
        System.out.println(SEPARATE);
        System.out.println("Hello and welcome!");
        System.out.println("Do you want to play\"Hangman?\"");
    }

    public void messageStart() {
        System.out.println(SEPARATE);
        System.out.println("Enter 'Y' to start");
        System.out.println("Enter 'Q' to quit");
    }

    public void messageExit() {
        System.out.println(SEPARATE);
        System.out.println("Thanks for the game! :D");
        System.out.println(SEPARATE);
    }

    public void messageIncorrectInput() {
        System.out.println("--------===--------");
        System.out.println("incorrect input!");
        System.out.println("-------------------");
    }

    public void messageWin(String word) {
        System.out.println(SEPARATE);
        System.out.println(word);
        System.out.println("Congratulations!");
        System.out.println("You correctly guessed the hidden word!");
    }

    public void messageLose(String word) {
        System.out.println(SEPARATE);
        System.out.println("You lost :(");
        System.out.println("You didn't guess the hidden word!");
        System.out.println("Hidden word was \"" + word + "\"");
    }

    public void messageState(String state, int attempt, int maxAttempts) {
        System.out.println(SEPARATE);
        System.out.println(state);
        System.out.println(attempt + " attempts out of " + maxAttempts);
        System.out.println((maxAttempts - attempt) + " attempts left");
        System.out.println("Guess the letter or type '0' to exit");
    }
}
