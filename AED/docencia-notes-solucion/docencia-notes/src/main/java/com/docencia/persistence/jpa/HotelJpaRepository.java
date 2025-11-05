package com.docencia.persistence.jpa;

import com.docencia.domain.model.Hotel;
import com.docencia.domain.repository.HotelRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HotelJpaRepository extends JpaAbstractRepository<Hotel, String> implements HotelRepository {
    private final HotelJpaSpringRepository hotelJpaSpringRepository;

    public HotelJpaRepository(HotelJpaSpringRepository hotelJpaSpringRepository) {
        super(hotelJpaSpringRepository);
        this.hotelJpaSpringRepository = hotelJpaSpringRepository;
    }
}
