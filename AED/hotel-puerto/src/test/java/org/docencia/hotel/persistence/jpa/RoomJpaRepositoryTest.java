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
public class RoomJpaRepositoryTest {
    @Autowired
    private RoomJpaRepository roomRepository;
    @Autowired
    private HotelJpaSpringRepository hotelRepository;

    private Room room;
    private Room roomTest;

    @BeforeAll
    void setUp() {
        roomRepository.deleteAll();
    }

    @BeforeEach
    void beforeEach() {
        Hotel hotel = new Hotel("H1", "Juan Perez", "juan@test.com", new HashSet<>());
        hotelRepository.save(hotel);
        room = new Room("R1", "101", "single", 100f, hotel);
        roomRepository.save(room);
        Set<Room> rooms = new HashSet<>();
        rooms.add(room);
        hotel.setRooms(rooms);
        hotelRepository.save(hotel);

        roomTest = new Room("R2", "102", "single", 103f, hotel);
    }

    @Test
    void testSaveBooking() {
        roomRepository.save(roomTest);
        assertTrue(roomRepository.existsById("R2"));
    }

    @Test
    void testFindByIdBooking() {
        assertTrue(roomRepository.findById("R1").isPresent());
    }

    @Test
    void testFindAll() {
        assertFalse(roomRepository.findAll().isEmpty());
    }

    @Test
    void testDeleteBooking() {
        roomRepository.save(roomTest);
        roomRepository.delete(roomTest);
        assertFalse(roomRepository.existsById("R2"));
    }

    @Test
    void testDeleteByIdBooking() {
        roomRepository.save(roomTest);
        roomRepository.deleteById(roomTest.getId());
        assertFalse(roomRepository.existsById("R2"));
    }

    @Test
    void testFindByHotelId() {
        assertFalse(roomRepository.findByHotelId("H1").isEmpty());
    }
}
