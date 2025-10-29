package com.docencia.com.proc_cli.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.repositories.interfaces.JobRepository;
import com.docencia.com.proc_cli.services.interfaces.PsHeadService;

@Service
public class PsHeadServiceImpl implements PsHeadService {
    private String[] allowed_cmds = {"ps aux | head"};

    @Autowired
    JobRepository jobRepository;

    @Override
    public boolean processLine(String command) {
        boolean isExec = false;
        if (!validate(command, this.allowed_cmds)) {
            jobRepository.add(String.format("[ERR] This command '%s' you have entered couldn't be executed\n", command));
            return isExec;
        }

        ProcessBuilder psProcessBuilder = new ProcessBuilder("ps", "aux");
        ProcessBuilder headProcessBuilder = new ProcessBuilder("head");
        BufferedReader buf;
        String line = "";
        String output = "[OUT] (ps aux | head) output stream:\n";

        try {
            Process psProcess = psProcessBuilder.start();
            Process headProcess = headProcessBuilder.start();
            psProcess.getInputStream().transferTo(headProcess.getOutputStream());
            buf = new BufferedReader(new InputStreamReader(headProcess.getInputStream()));
            while ((line = buf.readLine()) != null) {
                output += line + "\n";
            }
            psProcess.waitFor();
            headProcess.waitFor();
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
