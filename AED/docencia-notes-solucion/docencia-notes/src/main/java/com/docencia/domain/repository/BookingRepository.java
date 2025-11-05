package com.docencia.domain.repository;

import com.docencia.domain.model.Booking;

import java.util.List;

public interface BookingRepository {
    List<Booking> findByRoomAndDateRange(String roomId, String startDate, String endDate);
}
