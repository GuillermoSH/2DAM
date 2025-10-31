package com.docencia.com.proc_cli.services.impl.abstracts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docencia.com.proc_cli.domain.Job;
import com.docencia.com.proc_cli.repositories.interfaces.JobRepository;
import com.docencia.com.proc_cli.services.interfaces.CommandService;

public abstract class CommandServiceAbstract implements CommandService {
    private String command;
    private String[] allowedCmds;
    private Job jobType;
    private JobRepository jobRepository;

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getAllowedCmds() {
        return allowedCmds;
    }

    public void setAllowedCmds(String[] allowedCmds) {
        this.allowedCmds = allowedCmds;
    }

    public Job getJobType() {
        return jobType;
    }

    public void setJobType(Job jobType) {
        this.jobType = jobType;
    }

    @Override
    public boolean processLine(String command) {
        boolean isExec = false;
        if (!validate(command, this.allowedCmds)) {
            jobRepository.add(String.format("[ERR] This command '%s' you have entered couldn't be executed\n", command));
            return isExec;
        }
        
        return isExec = executeProcess(new ProcessBuilder(command.split(" ")));
    }
    
    public boolean executeProcess(ProcessBuilder processBuilder) {
        String output = "[OUT] (" + command + ") output stream:\n";
        try {
            Process process = processBuilder.start();
            String line = "";
            BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = buf.readLine()) != null) {
                output += line + "\n";
            }
            process.waitFor();
            output += String.format("%s%n", "-".repeat(80));
            jobRepository.add(output);
            return true;
        } catch (Exception e) {
            jobRepository.add(String.format("[ERR] %s%n", e.getMessage()));
            return false;
        }
    }

    private boolean validate(String command, String[] allowed_cmds) {
        return Arrays.stream(allowed_cmds).anyMatch(command::equals);
    }
}
