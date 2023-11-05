package edu.hw4;

public class ValidationError {
    private final TypeOfError error;

    public ValidationError(TypeOfError typeOfError) {
        this.error = typeOfError;
    }

    public String getError() {
        return error.errorType;
    }

    enum TypeOfError {
        AGE("Invalid age!"),
        HEIGHT("Invalid height!"),
        WEIGHT("Invalid weight!");

        private final String errorType;

        TypeOfError(String error) {
            this.errorType = error;
        }
    }
}
