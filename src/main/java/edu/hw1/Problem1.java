package edu.hw1;

public class Problem1 {
        public String say(String word) {
            return switch (word) {
                case "hello" -> "word";
                case "ping" -> "pong";
                default -> throw new IllegalStateException("Unexpected value: " + word);
            };
        }
}
