package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.repository.HotelRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HotelJpaRepository extends JpaAbstractRepository<Hotel, String> implements HotelRepository {
    private final HotelJpaSpringRepository hotelJpaSpringRepository;

    public HotelJpaRepository(HotelJpaSpringRepository hotelJpaSpringRepository) {
        super(hotelJpaSpringRepository);
        this.hotelJpaSpringRepository = hotelJpaSpringRepository;
    }
}
