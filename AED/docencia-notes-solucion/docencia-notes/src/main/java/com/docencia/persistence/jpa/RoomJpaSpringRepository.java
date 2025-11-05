package com.docencia.persistence.jpa;

import com.docencia.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomJpaSpringRepository extends JpaRepository<Room, String> {
    List<Room> findByHotelId(String hotelId);
}
