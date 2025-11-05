package com.docencia.com.proc_cli.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.docencia.com.proc_cli.domain.Job;

import com.docencia.com.proc_cli.services.impl.TopServiceImpl;

class TopServiceTest {
    String[] allowed_cmds;
    TopServiceImpl topServiceImpl;

    @BeforeEach
    void beforeEach() {
        topServiceImpl = new TopServiceImpl();
        allowed_cmds = new String[]{"top"};
    }

    @Test
    void processValidLineTest() {
        boolean isMounted = topServiceImpl.processLine("top", true);
        Assertions.assertTrue(isMounted);
    }

    @Test
    void processInvalidLineTest() {
        boolean isMounted = topServiceImpl.processLine("htop", true);
        Assertions.assertFalse(isMounted);
    }

    @Test
    void getAllowedCmdsTest() {
        Assertions.assertTrue(allowed_cmds[0] == topServiceImpl.getAllowedCmds()[0]);
    }

    @Test
    void getJobTypeTest() {
        Assertions.assertEquals(Job.TOP, topServiceImpl.getJobType());
    }
}
