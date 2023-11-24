package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCacheService implements PersonDatabase {
    Map<Integer, Person> idCache = new HashMap<>();
    Map<String, Person> nameCache = new HashMap<>();
    Map<String, Person> addressCache = new HashMap<>();
    Map<String, Person> phoneCache = new HashMap<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        if (idCache.containsKey(person.id())) {
            throw new IllegalArgumentException();
        }
        readWriteLock.writeLock().lock();
        try {
            idCache.put(person.id(), person);
            nameCache.put(person.name(), person);
            addressCache.put(person.address(), person);
            phoneCache.put(person.phoneNumber(), person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            Person person = idCache.get(id);
            nameCache.remove(person.name());
            addressCache.remove(person.address());
            phoneCache.remove(person.phoneNumber());
            idCache.remove(id);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Person findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return nameCache.get(name);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Person findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return addressCache.get(address);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Person findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return phoneCache.get(phone);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
