package org.docencia.hotel.domain.repository;

import org.docencia.hotel.domain.model.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> findByHotelId(String hotelId);
}
