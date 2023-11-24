package edu.hw7.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadWriteLockCacheServiceTest {
    private ReadWriteLockCacheService readWriteLockCacheService;

    @BeforeEach
    void init() {
        readWriteLockCacheService = new ReadWriteLockCacheService();
    }

    @Test
    void findTests() {
        readWriteLockCacheService.add(new Person(1, "name_1", "address_1", "phone_1"));
        readWriteLockCacheService.add(new Person(2, "name_2", "address_2", "phone_2"));
        readWriteLockCacheService.add(new Person(3, "name_3", "address_3", "phone_3"));

        assertThat(readWriteLockCacheService.findByName("name_1")).isEqualTo(new Person(1, "name_1", "address_1", "phone_1"));
        assertThat(readWriteLockCacheService.findByAddress("address_2")).isEqualTo(new Person(2, "name_2", "address_2", "phone_2"));
        assertThat(readWriteLockCacheService.findByPhone("phone_3")).isEqualTo(new Person(3, "name_3", "address_3", "phone_3"));
    }

    @Test
    void testAddPerson() {
        Person person = new Person(1, "name_1", "address_1", "phone_1");
        readWriteLockCacheService.add(person);
        assertThat(readWriteLockCacheService.findByName("name_1")).isEqualTo(person);
        assertThat(readWriteLockCacheService.findByAddress("address_1")).isEqualTo(person);
        assertThat(readWriteLockCacheService.findByPhone("phone_1")).isEqualTo(person);

        readWriteLockCacheService.delete(1);
        assertThat(readWriteLockCacheService.findByName("name_1")).isNull();
        assertThat(readWriteLockCacheService.findByAddress("address_1")).isNull();
        assertThat(readWriteLockCacheService.findByPhone("phone_1")).isNull();
    }
}
