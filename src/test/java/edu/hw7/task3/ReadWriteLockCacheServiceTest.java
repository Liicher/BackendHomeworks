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
        Person person_1 = new Person(1, "name_1", "address_1", "phone_1");
        Person person_2 = new Person(2, "name_2", "address_2", "phone_2");
        Person person_3 = new Person(3, "name_3", "address_3", "phone_3");
        readWriteLockCacheService.add(person_1);
        readWriteLockCacheService.add(person_2);
        readWriteLockCacheService.add(person_3);

        assertThat(readWriteLockCacheService.findByName("name_1")).isEqualTo(person_1);
        assertThat(readWriteLockCacheService.findByAddress("address_2")).isEqualTo(person_2);
        assertThat(readWriteLockCacheService.findByPhone("phone_3")).isEqualTo(person_3);
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
