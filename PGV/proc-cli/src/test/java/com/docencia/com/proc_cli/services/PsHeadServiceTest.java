package com.docencia.com.proc_cli.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.docencia.com.proc_cli.services.impl.PsHeadServiceImpl;

class PsHeadServiceTest {
    String[] allowed_cmds;
    PsHeadServiceImpl psHeadServiceImpl;

    @BeforeEach
    void beforeEach() {
        psHeadServiceImpl = new PsHeadServiceImpl();
        allowed_cmds = new String[]{"ps aux | head"};
    }

    @Test
    void processValidLineTest() {
        boolean isMounted = psHeadServiceImpl.processLine("ps aux | head", false);
        Assertions.assertTrue(isMounted);
    }

    @Test
    void processInvalidLineTest() {
        boolean isMounted = psHeadServiceImpl.processLine("ps aux head", false);
        Assertions.assertFalse(isMounted);
    }
}
