package com.docencia.com.proc_cli.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.repositories.interfaces.JobRepository;
import com.docencia.com.proc_cli.services.interfaces.TopService;

@Service
public class TopServiceImpl implements TopService {
    private String[] allowed_cmds = {"top"};
    JobRepository jobRepository;

    @Autowired
    public TopServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public boolean processLine(String command) {
        boolean isExec = false;
        if (!validate(command, this.allowed_cmds)) {
            jobRepository.add(String.format("[ERR] This command '%s' you have entered couldn't be executed\n", command));
            return isExec;
        }
        command = "top -b -n 1";
        ProcessBuilder pb = new ProcessBuilder(command.split(" "));
        BufferedReader buf;
        String line = "";
        String output = "[OUT] (top) output stream:\n";

        try {
            Process p = pb.start();
            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = buf.readLine()) != null) {
                output += line + "\n";
            }
            p.waitFor();
            output += String.format("%s%n", "-".repeat(80));
            isExec = true;
        } catch (Exception e) {
            output = String.format("[ERR] %s%n", e.getMessage());
        }
        jobRepository.add(output);
        return isExec;
    }

    @Override
    public boolean validate(String command, String[] allowed_cmds) {
        return Arrays.stream(allowed_cmds).anyMatch(command::equals);
    }
}
