package com.docencia.domain.repository;

import com.docencia.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    List<Room> findByHotelId(String hotelId);
}
