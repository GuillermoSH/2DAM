package org.docencia.hotel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DatabaseConnectionTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void contextLoads() {
		assertThat(entityManager).isNotNull();
	}
}
