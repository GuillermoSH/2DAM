package com.docencia.com.proc_cli.controllers;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.services.impl.LsofServiceImpl;
import com.docencia.com.proc_cli.services.impl.PsHeadServiceImpl;
import com.docencia.com.proc_cli.services.impl.TopServiceImpl;

@Service
public class CliController {
    @Autowired
    public LsofServiceImpl lsofService;
    
    @Autowired
    public TopServiceImpl topService;
    
    @Autowired
    public PsHeadServiceImpl psHeadService;


    public void consoleMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Lanzador de Procesos (CLI) Linux ===\n" +
                "Comandos:\n" +
                "  lsof -i\n" +
                "  top\n" +
                "  ps aux | head\n");
        String commandStr = scanner.nextLine();

        if (commandStr.toUpperCase().startsWith("LSOF")) lsofService.processLine(commandStr, false);
        if (commandStr.toUpperCase().startsWith("TOP")) topService.processLine(commandStr, true);
        if (commandStr.toUpperCase().startsWith("PS")) psHeadService.processLine(commandStr, false);

        scanner.close();
    }
}
