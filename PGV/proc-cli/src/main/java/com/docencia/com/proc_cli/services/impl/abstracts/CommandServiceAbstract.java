package com.docencia.com.proc_cli.services.impl.abstracts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.docencia.com.proc_cli.domain.Job;
import com.docencia.com.proc_cli.repositories.interfaces.JobRepository;
import com.docencia.com.proc_cli.services.interfaces.CommandService;

public abstract class CommandServiceAbstract implements CommandService {
    private String command;
    private String[] allowedCmds;
    private Job jobType;
    private JobRepository jobRepository;
    private String cmdOverride;

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public String getCmdOverride() {
        return cmdOverride;
    }

    public void setCmdOverride(String cmdOverride) {
        this.cmdOverride = cmdOverride;
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
    public boolean processLine(String command, boolean changeCmd) {
        boolean isExec = false;
        setCommand(command);
        if (!validate(command, this.allowedCmds)) {
            jobRepository
                    .add(String.format("[ERR] %s This command '%s' you have entered couldn't be executed\n", getCurrentDateTime(), command));
            return isExec;
        }

        return isExec = executeProcess(
                new ProcessBuilder(changeCmd ? getCmdOverride().split(" ") : command.split(" ")));
    }

    private boolean executeProcess(ProcessBuilder processBuilder) {
        String output = String.format("[OUT] %s (%s) output stream:%n", getCurrentDateTime(), getCommand());
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
            jobRepository.add(String.format("[ERR] %s %s%n", getCurrentDateTime(), e.getMessage()));
            return false;
        }
    }

    private boolean validate(String command, String[] allowed_cmds) {
        return Arrays.stream(allowed_cmds).anyMatch(command::equals);
    }

    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return currentDateTime.format(formatter);
    }
}
