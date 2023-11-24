package edu.hw7.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheService();
    }

    @Test
    void findTests() {
        cacheService.add(new Person(1, "name_1", "address_1", "phone_1"));
        cacheService.add(new Person(2, "name_2", "address_2", "phone_2"));
        cacheService.add(new Person(3, "name_3", "address_3", "phone_3"));

        assertThat(cacheService.findByName("name_1")).isEqualTo(new Person(1, "name_1", "address_1", "phone_1"));
        assertThat(cacheService.findByAddress("address_2")).isEqualTo(new Person(2, "name_2", "address_2", "phone_2"));
        assertThat(cacheService.findByPhone("phone_3")).isEqualTo(new Person(3, "name_3", "address_3", "phone_3"));
    }

    @Test
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
