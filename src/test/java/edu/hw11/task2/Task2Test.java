package edu.hw11.task2;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * При помощи библиотеки ByteBuddy напишите функцию, которая изменяет поведение существующего класса на лету.
 * Например, в классе:
 * class ArithmeticUtils {
 *     public int sum(int a, int b) {
 *         return a + b;
 *     }
 * }
 * вместо + будет производиться операция *.
 */

public class Task2Test {
    ByteBuddy byteBuddy = new ByteBuddy();

    @Test
    @SneakyThrows
    void replaceableSign() throws InstantiationException, IllegalAccessException {
        // Изначальное поведение класса
        int expected = ArithmeticUtils.sum(3, 4);
        assertThat(expected).isEqualTo(7);

        ByteBuddyAgent.install();
        Class<? extends ArithmeticUtils> dynamicClass = byteBuddy
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum")) // Выбираем метод sum
            .intercept(MethodDelegation.to(TargetDelegatingClass.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        // Новое поведение класса
        int expected2 = ArithmeticUtils.sum(3, 4);
        assertThat(expected2).isEqualTo(12);
    }

    public static class TargetDelegatingClass {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    public static class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }
}
