package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.domain.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
