package edu.hw3.task5;

import java.util.Arrays;

/**
 * Напишите функцию сортировки, которая принимает массив имен,
 * сортирует их по фамилии по возрастанию/убыванию (ASC/DESC) и
 * возвращает новый массив контактов с заданной сортировкой.
 */
public class Task5 {

    public Contact[] parseContacts(String[] input, String sortType) {
        // Проверки
        if (input == null) {
            return new Contact[] {};
        } else if (sortType == null) {
            throw new IllegalArgumentException();
        }

        // Проверка на тип сортировки
        SortType enumSortType = SortType.valueOf(sortType);
        boolean type;
        switch (enumSortType) {
            case ASC -> type = true;
            case DESC -> type = false;
            default -> throw new IllegalArgumentException();
        }

        // Создаем массив "Контактов", инициализируем из входящего массива Контакты и создаем из них новый массив
        Contact[] contacts = new Contact[input.length];
        for (int i = 0; i < input.length; i++) {
            String name = input[i].substring(0, input[i].indexOf(' '));
            String surname = input[i].substring(input[i].indexOf(' ') + 1);
            Contact contact = new Contact(name, surname);
            contacts[i] = contact;
        }
        // Сортируем
        Arrays.sort(contacts);

        // В случае обратной сортировки, меняю банальным способом через temp
        if (!type) {
            for (int i = 0; i < contacts.length / 2; i++) {
                Contact temp = contacts[i];
                contacts[i] = contacts[contacts.length - 1 - i];
                contacts[contacts.length - 1 - i] = temp;
            }
        }
        return contacts;
    }
}
