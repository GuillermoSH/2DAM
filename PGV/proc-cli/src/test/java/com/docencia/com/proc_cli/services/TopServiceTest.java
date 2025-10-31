package com.docencia.com.proc_cli.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.docencia.com.proc_cli.repositories.file.FileJobRepository;
import com.docencia.com.proc_cli.services.impl.TopServiceImpl;

class TopServiceTest {
    String[] allowed_cmds;
    TopServiceImpl topServiceImpl;

    @BeforeEach
    void beforeEach() {
        allowed_cmds = new String[]{"top"};
    }

    @Test
    void processValidLineTest() {
        boolean isMounted = topServiceImpl.processLine("top");
        Assertions.assertTrue(isMounted);
    }

    @Test
    void processInvalidLineTest() {
        boolean isMounted = topServiceImpl.processLine("htop");
        Assertions.assertFalse(isMounted);
    }
}
