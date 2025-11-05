package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestJpaSpringRepository extends JpaRepository<Guest, String> {
}
