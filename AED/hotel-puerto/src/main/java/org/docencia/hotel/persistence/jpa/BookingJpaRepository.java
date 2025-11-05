package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.repository.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingJpaRepository extends JpaAbstractRepository<Booking, String> implements BookingRepository {

    private final BookingJpaSpringRepository bookingJpaSpringRepository;

    public BookingJpaRepository(BookingJpaSpringRepository bookingJpaSpringRepository) {
        super(bookingJpaSpringRepository);
        this.bookingJpaSpringRepository = bookingJpaSpringRepository;
    }

    @Override
    public List<Booking> findByRoomAndDateRange(String roomId, String startDate, String endDate) {
        return bookingJpaSpringRepository.findByRoomAndDateRange(roomId, startDate, endDate);
    }
}
