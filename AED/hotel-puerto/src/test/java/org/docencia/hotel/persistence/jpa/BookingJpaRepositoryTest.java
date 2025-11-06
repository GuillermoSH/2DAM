package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class BookingJpaRepositoryTest {
    @Autowired
    private BookingJpaRepository bookingRepository;
    @Autowired
    private RoomJpaSpringRepository roomRepository;
    @Autowired
    private GuestJpaSpringRepository guestRepository;

    @Autowired
    private HotelJpaSpringRepository hotelRepository;

    private Room room;
    private Guest guest;
    private Hotel hotel;
    private Booking booking;

    @BeforeAll
    void setUp() {
        guestRepository.deleteAll();
        roomRepository.deleteAll();
        bookingRepository.deleteAll();
        hotelRepository.deleteAll();
    }

    @BeforeEach
    void beforeEach() {
        hotel = new Hotel("H1", "Hotel Central", "Calle Principal 25", new HashSet<>());
        hotelRepository.save(hotel);

        guest = new Guest("G1", "Juan Perez", "juan@test.com", "666111222");
        guestRepository.save(guest);

        room = new Room("R1", "101", "single", 100f, hotel);
        roomRepository.save(room);

        booking = new Booking("B1", room, guest, "2025-01-01", "2025-01-05");
        bookingRepository.save(booking);
    }

    @Test
    void testSaveBooking() {
        Booking bookingTest = new Booking("B2", room, guest, "2025-02-01", "2025-02-04");
        bookingRepository.save(bookingTest);
        assertTrue(bookingRepository.existsById("B2"));
    }

    @Test
    void testFindByIdBooking() {
        assertTrue(bookingRepository.findById("B1").isPresent());
    }

    @Test
    void testFindAll() {
        assertFalse(bookingRepository.findAll().isEmpty());
    }

    @Test
    void testDeleteBooking() {
        Booking bookingTest = new Booking("B2", room, guest, "2025-02-01", "2025-02-04");
        bookingRepository.save(bookingTest);
        bookingRepository.delete(bookingTest);
        assertFalse(bookingRepository.existsById("B2"));
    }

    @Test
    void testDeleteByIdBooking() {
        Booking bookingTest = new Booking("B3", room, guest, "2025-03-01", "2025-03-04");
        bookingRepository.save(bookingTest);
        bookingRepository.deleteById(bookingTest.getId());
        assertFalse(bookingRepository.existsById("B3"));
    }

    @Test
    void testFindByRoomAndDateRangeTest() {
        List<Booking> bookings = bookingRepository.findByRoomAndDateRange("R1", "2025-01-01", "2025-05-01");
        assertFalse(bookings.isEmpty());
    }
}
