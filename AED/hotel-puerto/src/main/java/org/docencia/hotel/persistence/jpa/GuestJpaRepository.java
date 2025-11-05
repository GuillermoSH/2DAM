package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.repository.GuestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GuestJpaRepository extends JpaAbstractRepository<Guest, String> implements GuestRepository {

    private final GuestJpaSpringRepository guestJpaSpringRepository;

    public GuestJpaRepository(GuestJpaSpringRepository guestJpaSpringRepository) {
        super(guestJpaSpringRepository);
        this.guestJpaSpringRepository = guestJpaSpringRepository;
    }
}
