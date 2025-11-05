package com.docencia.persistence.jpa;

import com.docencia.domain.model.Guest;
import com.docencia.domain.repository.GuestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GuestJpaRepository extends JpaAbstractRepository<Guest, String> implements GuestRepository {

    private final GuestJpaSpringRepository guestJpaSpringRepository;

    public GuestJpaRepository(GuestJpaSpringRepository guestJpaSpringRepository) {
        super(guestJpaSpringRepository);
        this.guestJpaSpringRepository = guestJpaSpringRepository;
    }
}
