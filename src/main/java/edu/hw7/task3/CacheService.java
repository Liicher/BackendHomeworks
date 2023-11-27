package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;

public class CacheService implements PersonDatabase {
    Map<Integer, Person> idCache = new HashMap<>();
    Map<String, Person> nameCache = new HashMap<>();
    Map<String, Person> addressCache = new HashMap<>();
    Map<String, Person> phoneCache = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        // Считаем что все объекты "человек" уникальны.
        if (idCache.containsKey(person.id())) {
            throw new IllegalArgumentException();
        }
        idCache.put(person.id(), person);
        setName(person.id(), person.name());
        setAddress(person.id(), person.address());
        setPhone(person.id(), person.phoneNumber());
    }

    // Проверка, что человек с таким id существует в базе
    private void checkIdForChangingData(int id) {
        if (!idCache.containsKey(id)) {
            throw new IllegalArgumentException();
        }
    }

    // Классы для добавления/изменения данных в кэшах
    public synchronized void setName(int id, String name) {
        checkIdForChangingData(id);
        Person person = idCache.get(id);
        if (nameCache.containsKey(name)) {
            throw new IllegalArgumentException();
        } else {
            nameCache.put(name, person);
        }
    }

    public synchronized void setAddress(int id, String address) {
        checkIdForChangingData(id);
        Person person = idCache.get(id);
        if (addressCache.containsKey(address)) {
            throw new IllegalArgumentException();
        } else {
            addressCache.put(address, person);
        }
    }

    public synchronized void setPhone(int id, String phoneNumber) {
        checkIdForChangingData(id);
        Person person = idCache.get(id);
        if (phoneCache.containsKey(phoneNumber)) {
            throw new IllegalArgumentException();
        } else {
            phoneCache.put(phoneNumber, person);
        }
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idCache.get(id);
        nameCache.remove(person.name());
        addressCache.remove(person.address());
        phoneCache.remove(person.phoneNumber());
        idCache.remove(id);
    }

    @Override
    public synchronized Person findByName(String name) {
        return nameCache.get(name);
    }

    @Override
    public synchronized Person findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    public synchronized Person findByPhone(String phone) {
        return phoneCache.get(phone);
    }
}
