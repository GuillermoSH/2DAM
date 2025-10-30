package com.docencia.com.proc_cli.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.docencia.com.proc_cli.repositories.file.FileJobRepository;
import com.docencia.com.proc_cli.services.impl.LsofServiceImpl;

class LsofServiceTest {
    LsofServiceImpl lsofServiceImpl;
    FileJobRepository jobRepository;
    String[] allowed_cmds;

    @BeforeEach
    void beforeEach() {
        jobRepository = new FileJobRepository();
        lsofServiceImpl = new LsofServiceImpl(jobRepository);
        allowed_cmds = new String[]{"lsof -i"};
    }

    @Test
    void validateCorrectCommandTest() {
        boolean isValid = lsofServiceImpl.validate("lsof -i", allowed_cmds);
        Assertions.assertTrue(isValid, "Se ha producido un error en la validación");
    }

    @Test
    void validateIncorrectCommandTest() {
        boolean isValid = lsofServiceImpl.validate("lsof iiii", allowed_cmds);
        Assertions.assertFalse(isValid, "Se ha producido un error en la validación");
    }

    @Test
    void processValidLineTest() {
        boolean isMounted = lsofServiceImpl.processLine("lsof -i");
        Assertions.assertTrue(isMounted);
    }

    @Test
    void processInvalidLineTest() {
        boolean isMounted = lsofServiceImpl.processLine("lsof -i -a -e");
        Assertions.assertFalse(isMounted);
    }
}
