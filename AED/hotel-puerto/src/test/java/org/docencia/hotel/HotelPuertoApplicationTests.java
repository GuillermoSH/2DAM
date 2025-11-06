package org.docencia.hotel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class HotelPuertoApplicationTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void contextLoads() {}

	@Test
	void mainRunsWithoutErrors() {
		assertDoesNotThrow(() -> HotelPuertoApplication.main(new String[] {}));
	}
}
