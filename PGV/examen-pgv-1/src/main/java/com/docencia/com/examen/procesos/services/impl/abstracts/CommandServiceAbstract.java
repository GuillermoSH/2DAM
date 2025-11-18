package com.docencia.com.examen.procesos.services.impl.abstracts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.docencia.com.examen.procesos.domain.Job;
import com.docencia.com.examen.procesos.repositories.interfaces.JobRepository;
import com.docencia.com.examen.procesos.services.interfaces.CommandService;


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

    /**
     * Metodo para ejecutar el comando especificado y si hace falta cambiar el comando
     * de entrada de la interfaz a uno que pueda ser leido por linux
     * @param command comando que se quiere ejecutar
     * @param changeCmd si se quiere cambiar el formato del comando
     * @return flag si se ha podido ejecutar el comando
     */
    @Override
    public boolean processLine(String command, boolean changeCmd) {
        boolean isExec = false;
        setCommand(command);
        if (!validate(command, this.allowedCmds)) {
            jobRepository.add(String.format("[ERR] %s This command '%s' you have entered couldn't be executed%n", getCurrentDateTime(), command));
            return isExec;
        }

        return executeProcess(new ProcessBuilder(changeCmd ? getCmdOverride().split(" ") : command.split(" ")));
    }

    /**
     * Metodo que contiene la logica de ejecucion del comando especificado
     * @param processBuilder proceso que se quiere evaluar
     * @return flag si se ha podido ejecutar el comando
     */
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

    /**
     * Metodo de validacion del comando introducido por el usuario
     * @param command introducido
     * @param allowedCmds lista de comprobacion
     * @return flag si es valido o no
     */
    private boolean validate(String command, String[] allowedCmds) {
        return Arrays.asList(allowedCmds).contains(command);
    }

    /**
     * Metodo que retorna la fecha y hora con formato "dd-MM-yyyy HH:mm:ss"
     * @return fecha y hora formateada
     */
    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return currentDateTime.format(formatter);
    }
}
