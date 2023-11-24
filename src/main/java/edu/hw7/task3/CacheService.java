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
        nameCache.put(person.name(), person);
        addressCache.put(person.address(), person);
        phoneCache.put(person.phoneNumber(), person);
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
