package com.docencia.persistence.jpa;

import com.docencia.domain.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelJpaSpringRepository extends JpaRepository<Hotel, String> {
}
