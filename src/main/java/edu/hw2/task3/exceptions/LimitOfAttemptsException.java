package edu.hw2.task3.exceptions;

public class LimitOfAttemptsException extends RuntimeException {
    public LimitOfAttemptsException(String message, Throwable cause) {
        super(message, cause);
    }
}
