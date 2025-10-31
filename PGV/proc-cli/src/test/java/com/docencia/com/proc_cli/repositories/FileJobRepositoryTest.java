package com.docencia.com.proc_cli.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.docencia.com.proc_cli.repositories.file.FileJobRepository;

class FileJobRepositoryTest {
    @Test
    void invalidFilePathTest() {
        FileJobRepository jobRepository = new FileJobRepository("loooooooooooger.txt");
        Assertions.assertFalse(jobRepository.add("HolA"));
    }

    @Test
    void validFilePathTest() {
        FileJobRepository jobRepository = new FileJobRepository("logger.txt");
        Assertions.assertTrue(jobRepository.add("HolA"));
    }
}
