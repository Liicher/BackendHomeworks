package edu.hw7.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {
    private CacheService cacheService;

    @BeforeEach
    void init() {
        cacheService = new CacheService();
    }

    @Test
    @Disabled
    void findTests() {
        Person person_1 = new Person(1, "name_1", "address_1", "phone_1");
        Person person_2 = new Person(2, "name_2", "address_2", "phone_2");
        Person person_3 = new Person(3, "name_3", "address_3", "phone_3");
        cacheService.add(person_1);
        cacheService.add(person_2);
        cacheService.add(person_3);

        assertThat(cacheService.findByName("name_1")).isEqualTo(person_1);
        assertThat(cacheService.findByAddress("address_2")).isEqualTo(person_2);
        assertThat(cacheService.findByPhone("phone_3")).isEqualTo(person_3);
    }

    @Test
    @Disabled
    void testAddPerson() {
        Person person = new Person(1, "name_1", "address_1", "phone_1");
        cacheService.add(person);
        assertThat(cacheService.findByName("name_1")).isEqualTo(person);
        assertThat(cacheService.findByAddress("address_1")).isEqualTo(person);
        assertThat(cacheService.findByPhone("phone_1")).isEqualTo(person);

        cacheService.delete(1);
        assertThat(cacheService.findByName("name_1")).isNull();
        assertThat(cacheService.findByAddress("address_1")).isNull();
        assertThat(cacheService.findByPhone("phone_1")).isNull();
    }
}
