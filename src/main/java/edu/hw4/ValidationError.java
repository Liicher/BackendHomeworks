package edu.hw4;

public class ValidationError extends Throwable {
    public ValidationError(String error) {
        super(error);
    }
}
