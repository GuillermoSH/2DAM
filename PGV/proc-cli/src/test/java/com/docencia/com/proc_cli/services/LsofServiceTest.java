package com.docencia.com.proc_cli.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.docencia.com.proc_cli.services.impl.LsofServiceImpl;

class LsofServiceTest {
    String[] allowed_cmds;
    LsofServiceImpl lsofServiceImpl;

    @BeforeEach
    void beforeEach() {
        lsofServiceImpl = new LsofServiceImpl();
        allowed_cmds = new String[]{"lsof -i"};
    }

    @Test
    void processValidLineTest() {
        boolean isMounted = lsofServiceImpl.processLine("lsof -i", false);
        Assertions.assertTrue(isMounted);
    }

    @Test
    void processInvalidLineTest() {
        boolean isMounted = lsofServiceImpl.processLine("lsof -i -a -e", false);
        Assertions.assertFalse(isMounted);
    }
}
