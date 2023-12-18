package edu.hw11.task1;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * При помощи библиотеки ByteBuddy создайте новый класс, метод toString которого выводит "Hello, ByteBuddy!".
 */
class Task1Test {

    @Test
    @SneakyThrows
    void helloByteBuddy() {
        String expected = "Hello, ByteBuddy!";

        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        assertThat(dynamicType.newInstance().toString()).isEqualTo(expected);
    }
}
