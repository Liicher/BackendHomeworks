package edu.hw10.task1;

public record MyRecord(
    @NotNull String name,
    @Min(21) @Max(30) int age
) {
}

