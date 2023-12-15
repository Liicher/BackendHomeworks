package edu.hw10.task1;
@SuppressWarnings("all")
/**
 * В этом задании мы будем писать генератор объектов на основе рефлексии.
 *
 * Пример вызова может выглядеть следующим образом:
 * RandomObjectGenerator rog = ...;
 * var myClass = rog.nextObject(MyClass.class, "create");
 * var myRecord = rog.nextObject(MyRecord.class);
 *
 * Реализуйте поддержку генерации record и POJO.
 * Поддерживать создание объектов нужно только через
 * конструкторы или фабричный метод (если такой есть, например, MyClass#create).
 *
 * После этого создайте несколько аннотаций:
 * @NotNull
 * @Min(value)
 * @Max(value)
 * Сделайте так, чтобы ваш генератор учитывал эти аннотации,
 * если они присутствуют на полях конструктора или фабричного метода.
 */

public class Task1 {
    public static void main(String[] args) throws Exception {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        MyClass myClass1 = rog.nextObject(MyClass.class, "create");
        System.out.println(myClass1);

        MyClass myClass2 = rog.nextObject(MyClass.class);
        System.out.println(myClass2);

        MyRecord myRecord = rog.nextObject(MyRecord.class);
        System.out.println(myRecord);
    }
}
