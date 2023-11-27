package edu.hw7.task3;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCacheService implements PersonDatabase {
    CacheService cacheService = new CacheService();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            cacheService.add(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            cacheService.delete(id);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Person findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return cacheService.findByName(name);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Person findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return cacheService.findByAddress(address);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Person findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return cacheService.findByPhone(phone);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
