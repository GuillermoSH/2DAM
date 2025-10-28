package com.docencia.com.proc_cli.services.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.services.interfaces.PsHeadService;

@Service
public class PsHeadServiceImpl implements PsHeadService {
    private String[] allowed_cmds = {"ps aux | head"};

    @Override
    public void processLine(String command) {
        
    }

    @Override
    public boolean validate(String command, String[] allowed_cmds) {
        return Arrays.stream(allowed_cmds).anyMatch(command::equals);
    }
}
