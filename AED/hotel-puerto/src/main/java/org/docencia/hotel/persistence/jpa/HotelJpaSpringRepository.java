package org.docencia.hotel.persistence.jpa;

import org.docencia.hotel.domain.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelJpaSpringRepository extends JpaRepository<Hotel, String> {
}
