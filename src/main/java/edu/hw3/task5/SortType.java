package edu.hw3.task5;

public enum SortType {
    ASC("ASC"),
    DESC("DESC");

    private final String title;
    SortType(String title) {
        this.title = title;
    }

    static boolean isReverseOrder(String input) {
        SortType enumSortType = SortType.valueOf(input);
        switch (enumSortType) {
            case ASC -> {
                return true;
            }
            case DESC -> {
                return false;
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
