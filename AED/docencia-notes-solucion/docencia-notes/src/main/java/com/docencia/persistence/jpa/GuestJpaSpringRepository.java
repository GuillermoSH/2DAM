package com.docencia.persistence.jpa;

import com.docencia.domain.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestJpaSpringRepository extends JpaRepository<Guest, String> {
}
