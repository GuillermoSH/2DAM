package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface BookingJpaSpringRepository extends JpaRepository<Booking, String> {

    @Query("""
       SELECT b FROM Booking b
       WHERE b.room.id = :roomId
       AND (
            b.checkIn <= :endDate AND b.checkOut >= :startDate
       )
       """)
    List<Booking> findByRoomAndDateRange(@Param("roomId") String roomId,
                                         @Param("startDate") String startDate,
                                         @Param("endDate") String endDate);
}
