package edu.hw3.task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {
    private final String name;
    private final String surname;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Переопределил для понятного вывода
    @Override
    public String toString() {
        return name + ' ' + surname;
    }

    // Переопределил для тестов
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(surname, contact.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    // В первую очередь сравниваем по фамилии, после уже по имени
    @Override
    public int compareTo(@NotNull Contact o) {
        int result = this.surname.compareTo(o.surname);
        if (result == 0) {
            result = this.name.compareTo(o.name);
        }
        return result;
    }
}
