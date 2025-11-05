package com.docencia.persistence.jpa;

import com.docencia.domain.model.Room;
import com.docencia.domain.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomJpaRepository extends JpaAbstractRepository<Room, String> implements RoomRepository {

    private final RoomJpaSpringRepository roomJpaSpringRepository;

    public RoomJpaRepository(RoomJpaSpringRepository roomJpaSpringRepository) {
        super(roomJpaSpringRepository);
        this.roomJpaSpringRepository = roomJpaSpringRepository;
    }

    @Override
    public List<Room> findByHotelId(String hotelId) {
        return roomJpaSpringRepository.findByHotelId(hotelId);
    }
}
