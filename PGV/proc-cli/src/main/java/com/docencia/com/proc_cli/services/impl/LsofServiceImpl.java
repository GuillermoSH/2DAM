package com.docencia.com.proc_cli.services.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.repositories.interfaces.JobRepository;
import com.docencia.com.proc_cli.services.interfaces.LsofService;

@Service
public class LsofServiceImpl implements LsofService {
    private String[] allowed_cmds = {"lsof -i"};

    @Autowired
    JobRepository jobRepository;

    @Override
    public void processLine(String command) {
        if (!validate(command, this.allowed_cmds)) jobRepository.add("[ERR] The command params you have entered doesn't exists");

    }

    @Override
    public boolean validate(String command, String[] allowed_cmds) {
        return Arrays.stream(allowed_cmds).anyMatch(command::equals);
    }
}
