package com.docencia.tasks.adapters.out.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskJpaEntityTest {
    TaskJpaEntity jpaEntity = null;
    private final static Long ID = 1L;
    private final static String TITLE = "Title";
    private final static String DESCRIPTION = "Description";
    private final static Boolean COMPLETED = false;

    @BeforeEach
    void setUp() {
        jpaEntity = new TaskJpaEntity();
    }

    @Test
    @Order(1)
    @DisplayName("NOT NULL: test that the entity is not null")
    void jpaEntityNotNullTest() {
        assertNotNull(jpaEntity, "Value can't be null");
    }

    @Test
    @Order(2)
    @DisplayName("GETS SETS: test all getter and setters of the entity")
    void jpaEntityGetsSetsTest() {
        jpaEntity.setId(ID);
        jpaEntity.setTitle(TITLE);
        jpaEntity.setDescription(DESCRIPTION);
        jpaEntity.setCompleted(COMPLETED);

        assertEquals(ID, jpaEntity.getId());
        assertEquals(TITLE, jpaEntity.getTitle());
        assertEquals(DESCRIPTION, jpaEntity.getDescription());
        assertFalse(jpaEntity.isCompleted());
    }
}
