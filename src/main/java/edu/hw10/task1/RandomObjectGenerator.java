package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;

@SuppressWarnings("MagicNumber")
public class RandomObjectGenerator {
    private final SecureRandom random;

    public RandomObjectGenerator() {
        this.random = new SecureRandom();
    }

    public <T> T nextObject(Class<T> clazz) throws Exception {
        return nextObject(clazz, null);
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) throws Exception {

        // Поиск фабричного метода по имени
        Method factoryMethod = null;
        if (factoryMethodName != null) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(factoryMethodName)) {
                    factoryMethod = method;
                    break;
                }
            }
        }

        // Если есть фабричный метод, используем его для создания объекта
        if (factoryMethod != null) {
            Parameter[] parameters = factoryMethod.getParameters();
            Object[] args = new Object[parameters.length];

            for (int i = 0; i < parameters.length; i++) {
                args[i] = generateValue(parameters[i]);
            }

            return clazz.cast(factoryMethod.invoke(null, args));
        }

        // Иначе используем конструктор
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();
            Object[] args = new Object[parameters.length];

            for (int i = 0; i < parameters.length; i++) {
                args[i] = generateValue(parameters[i]);
            }

            if (args.length > 0) {
                return clazz.cast(constructor.newInstance(args));
            }
        }

        throw new IllegalArgumentException("Unable to create object of class: " + clazz.getName());
    }

    @SuppressWarnings({"CyclomaticComplexity", "ReturnCount"})
    private Object generateValue(Parameter parameter) throws Exception {
        Class<?> type = parameter.getType();
        // Проверка аннотаций
        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);
        int minValue = (minAnnotation != null) ? minAnnotation.value() : Integer.MIN_VALUE;
        int maxValue = (maxAnnotation != null) ? maxAnnotation.value() : Integer.MAX_VALUE;

        if (type == boolean.class || type == Boolean.class) {
            return random.nextBoolean();
        } else if (type == byte.class || type == Byte.class) {
            return (byte) random.nextInt();
        } else if (type == short.class || type == Short.class) {
            return (short) random.nextInt();
        } else if (type == int.class || type == Integer.class) {
            return random.nextInt(minValue, maxValue);
        } else if (type == long.class || type == Long.class) {
            return random.nextLong(minValue, maxValue);
        } else if (type == float.class || type == Float.class) {
            return random.nextFloat(minValue, maxValue);
        } else if (type == double.class || type == Double.class) {
            return random.nextDouble(minValue, maxValue);
        } else if (type == char.class || type == Character.class) {
            return (char) (random.nextInt(26) + 'a');
        } else if (type == String.class) {
            return generateString(parameter);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type.getName());
        }
    }

    // Классная штука этот RandomStringUtils, удобно
    private String generateString(Parameter parameter) {
        for (var a : parameter.getAnnotations()) {
            if (a instanceof NotNull) {
                return RandomStringUtils.randomAlphabetic(4, 10);
            }
        }
        return random.nextBoolean() ? RandomStringUtils.randomAlphabetic(4, 10) : null;
    }
}
