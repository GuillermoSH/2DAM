package org.docencia.hotel.domain.repository;

import org.docencia.hotel.domain.model.Booking;

import java.util.List;

public interface BookingRepository {
    List<Booking> findByRoomAndDateRange(String roomId, String startDate, String endDate);
}
