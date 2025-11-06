package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class HotelJpaRepositoryTest {
    @Autowired
    private HotelJpaRepository hotelRepository;
    @Autowired
    private RoomJpaSpringRepository roomRepository;

    private Hotel hotel;
    private Hotel hotelTest;

    @BeforeAll
    void setUp() {
        hotelRepository.deleteAll();
    }

    @BeforeEach
    void beforeEach() {
        hotel = new Hotel("H1", "Juan Perez", "juan@test.com", new HashSet<>());
        hotelRepository.save(hotel);
        Room room = new Room("R1", "101", "single", 100f, hotel);
        roomRepository.save(room);
        Set<Room> rooms = new HashSet<>();
        rooms.add(room);
        hotel.setRooms(rooms);
        hotelRepository.save(hotel);

        hotelTest = new Hotel("H2", "Jose Pereira", "jose@test.com", new HashSet<>());
    }

    @Test
    void testSaveBooking() {
        hotelRepository.save(hotelTest);
        assertTrue(hotelRepository.existsById("H2"));
    }

    @Test
    void testFindByIdBooking() {
        assertTrue(hotelRepository.findById("H1").isPresent());
    }

    @Test
    void testFindAll() {
        assertFalse(hotelRepository.findAll().isEmpty());
    }

    @Test
    void testDeleteBooking() {
        hotelRepository.save(hotelTest);
        hotelRepository.delete(hotelTest);
        assertFalse(hotelRepository.existsById("H2"));
    }

    @Test
    void testDeleteByIdBooking() {
        hotelRepository.save(hotelTest);
        hotelRepository.deleteById(hotelTest.getId());
        assertFalse(hotelRepository.existsById("H2"));
    }
}
