package edu.hw10.task1;

@SuppressWarnings("RedundantModifier")
class MyClass {
    private String name;
    private int age;

    public MyClass(
        @NotNull String name,
        @Min(1) @Max(10) int age
    ) {
        this.name = name;
        this.age = age;
    }

    public static MyClass create(@NotNull String name, @Min(11) @Max(20) int age) {
        return new MyClass(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

