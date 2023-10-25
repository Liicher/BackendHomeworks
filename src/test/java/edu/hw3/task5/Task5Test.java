package edu.hw3.task5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {
    private static Task5 task5;

    @BeforeAll
    static void init() {
        task5 = new Task5();
    }

    @Test
    void parseContactsExamples() {
        assertThat(task5.parseContacts(
            new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC"))
            .isEqualTo(new Contact[] {new Contact("Thomas", "Aquinas"),
                new Contact("Rene", "Descartes"), new Contact("David", "Hume"),
                new Contact("John", "Locke")}).isInstanceOf(Contact[].class);

        assertThat(task5.parseContacts(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"))
            .isEqualTo(new Contact[] {new Contact("Carl", "Gauss"),
                new Contact("Leonhard", "Euler"), new Contact("Paul", "Erdos")})
            .isInstanceOf(Contact[].class);

        assertThat(task5.parseContacts(new String[] {}, "DESC")).isEqualTo(new Contact[] {}).isInstanceOf(Contact[].class);
        assertThat(task5.parseContacts(null, "DESC")).isEqualTo(new Contact[] {}).isInstanceOf(Contact[].class);
    }

    @Test
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> {
            task5.parseContacts(new String[] {"Paul Erdos"}, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            task5.parseContacts(new String[] {"Paul Erdos"}, "Hello");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            task5.parseContacts(new String[] {"Paul Erdos"}, "");
        });
    }
}
