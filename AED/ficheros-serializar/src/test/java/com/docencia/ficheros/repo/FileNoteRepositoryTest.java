package com.docencia.ficheros.repo;

import org.junit.jupiter.api.Test;

class FileNoteRepositoryTest {
    FileNoteRepository fileNoteRepository;

    @Test
    void createFileTest(){
        fileNoteRepository = new FileNoteRepository();
    }
}
