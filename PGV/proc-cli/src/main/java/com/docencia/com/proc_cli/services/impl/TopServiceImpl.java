package com.docencia.com.proc_cli.services.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.services.interfaces.TopService;

@Service
public class TopServiceImpl implements TopService {
    private String[] allowed_cmds = {"top"};

    @Override
    public void processLine(String command) {
        
    }

    @Override
    public boolean validate(String command, String[] allowed_cmds) {
        return Arrays.stream(allowed_cmds).anyMatch(command::equals);
    }

}
