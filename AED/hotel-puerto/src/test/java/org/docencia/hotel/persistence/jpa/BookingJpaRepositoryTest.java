package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BookingJpaRepositoryTest {
    @Autowired
    private static BookingJpaSpringRepository bookingRepository;
    @Autowired
    private static RoomJpaSpringRepository roomRepository;
    @Autowired
    private static GuestJpaSpringRepository guestRepository;

    @Autowired
    private static HotelJpaSpringRepository hotelRepository;

    private Room room;
    private Guest guest;
    private Hotel hotel;
    private Booking booking;

    @BeforeAll
    static void setUp() {
        guestRepository.deleteAll();
        roomRepository.deleteAll();
        bookingRepository.deleteAll();
        hotelRepository.deleteAll();
    }

    @BeforeEach
    void beforeEach() {
        hotel = new Hotel("H1", "Hotel Central", "Calle Principal 25", null);
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
        assertTrue(bookingRepository.findById("B2").isPresent());
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
        assertTrue(bookingRepository.findById("B2").isEmpty());
    }
}
