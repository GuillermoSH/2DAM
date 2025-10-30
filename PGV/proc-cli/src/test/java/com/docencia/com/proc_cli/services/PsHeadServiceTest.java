package com.docencia.com.proc_cli.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.docencia.com.proc_cli.repositories.file.FileJobRepository;
import com.docencia.com.proc_cli.services.impl.PsHeadServiceImpl;

class PsHeadServiceTest {
    PsHeadServiceImpl psHeadServiceImpl;
    FileJobRepository jobRepository;
    String[] allowed_cmds;

    @BeforeEach
    void beforeEach() {
        jobRepository = new FileJobRepository();
        psHeadServiceImpl = new PsHeadServiceImpl(jobRepository);
        allowed_cmds = new String[]{"ps aux | head"};
    }

    @Test
    void validateCorrectCommandTest() {
        boolean isValid = psHeadServiceImpl.validate("ps aux | head", allowed_cmds);
        Assertions.assertTrue(isValid, "Se ha producido un error en la validación");
    }

    @Test
    void validateIncorrectCommandTest() {
        boolean isValid = psHeadServiceImpl.validate("ps aux head", allowed_cmds);
        Assertions.assertFalse(isValid, "Se ha producido un error en la validación");
    }

    @Test
    void processValidLineTest() {
        boolean isMounted = psHeadServiceImpl.processLine("ps aux | head");
        Assertions.assertTrue(isMounted);
    }

    @Test
    void processInvalidLineTest() {
        boolean isMounted = psHeadServiceImpl.processLine("ps aux head");
        Assertions.assertFalse(isMounted);
    }
}
