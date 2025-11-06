package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class GuestJpaRepositoryTest {
    @Autowired
    private GuestJpaRepository guestRepository;

    private Guest guest;
    private Guest guestTest;

    @BeforeAll
    void setUp() {
        guestRepository.deleteAll();
    }

    @BeforeEach
    void beforeEach() {
        guest = new Guest("G1", "Juan Perez", "juan@test.com", "666111222");
        guestRepository.save(guest);

        guestTest = new Guest("G2", "Jose Pereira", "jose@test.com", "666222333");
    }

    @Test
    void testSaveGuest() {
        guestRepository.save(guestTest);
        assertTrue(guestRepository.existsById("G2"));
    }

    @Test
    void testFindByIdGuest() {
        assertTrue(guestRepository.findById("G1").isPresent());
    }

    @Test
    void testFindAll() {
        assertFalse(guestRepository.findAll().isEmpty());
    }

    @Test
    void testDeleteGuest() {
        guestRepository.save(guestTest);
        guestRepository.delete(guestTest);
        assertFalse(guestRepository.existsById("G2"));
    }

    @Test
    void testDeleteByIdGuest() {
        guestRepository.save(guestTest);
        guestRepository.deleteById(guestTest.getId());
        assertFalse(guestRepository.existsById("G2"));
    }
}
