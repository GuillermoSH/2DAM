package com.docencia.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class NoteJpaRepositoryTest {
    @Autowired
    private INoteRepository noteRepository;

    @Test
    public void findNoteTest() {
        Assertions.assertNotNull(noteRepository);
    }
}
