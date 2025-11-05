package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class BookingJpaRepositoryTest {
    @Autowired
    private BookingJpaSpringRepository bookingRepository;
    @Autowired
    private RoomJpaSpringRepository roomRepository;
    @Autowired
    private GuestJpaSpringRepository guestRepository;

    private Room testRoom;
    private Guest testGuest;
    private Hotel testHotel;

    @BeforeEach
    void setUp() {
        guestRepository.deleteAll();
        roomRepository.deleteAll();
        bookingRepository.deleteAll();

        testHotel = new Hotel();
        testHotel.setId("H1");
        testHotel.setName("Hotel Central");
        testHotel.setAddress("Calle Principal 23");

        testGuest = new Guest("g1", "Juan Perez", "juan@test.com", "666111222");
        guestRepository.save(testGuest);

        testRoom = new Room("r1", "101", "single", 100f, testHotel);
        roomRepository.save(testRoom);
    }

    @Test
    void testSaveBooking() {
        Booking booking = new Booking();
        booking.setId("b1");
        booking.setGuest(testGuest);
        booking.setRoom(testRoom);
        booking.setCheckIn("2025-01-01");
        booking.setCheckOut("2025-01-05");

        bookingRepository.save(booking);

        assertThat(bookingRepository.findById("b1")).isPresent();
    }
}
