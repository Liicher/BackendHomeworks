package edu.hw10.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class RandomObjectGeneratorTest {
    private RandomObjectGenerator rog;

    @BeforeEach
    void init() {
        rog = new RandomObjectGenerator();
    }

    @Test
    void constructorRealisation() throws Exception {
        MyClass myClass = rog.nextObject(MyClass.class);

        assertThat(myClass.getAge()).isBetween(1, 10);
        assertThat(myClass.getName()).isNotNull();
        assertThat(myClass.getName()).isNotEmpty();
    }

    @Test
    void factoryRealisation() throws Exception {
        MyClass myClass = rog.nextObject(MyClass.class, "create");

        assertThat(myClass.getAge()).isBetween(11, 20);
        assertThat(myClass.getName()).isNotNull();
        assertThat(myClass.getName()).isNotEmpty();
    }

    @Test
    void recordRealisation() throws Exception {
        MyRecord myClass = rog.nextObject(MyRecord.class);

        assertThat(myClass.age()).isBetween(21, 30);
        assertThat(myClass.name()).isNotNull();
        assertThat(myClass.name()).isNotEmpty();
    }
}
