package com.docencia.com.proc_cli.controllers;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.services.impl.LsofServiceImpl;
import com.docencia.com.proc_cli.services.impl.PsHeadServiceImpl;
import com.docencia.com.proc_cli.services.impl.TopServiceImpl;
import com.docencia.com.proc_cli.services.interfaces.CommandService;

@Service
public class CliController {
    @Autowired
    LsofServiceImpl lsofService;
    
    @Autowired
    TopServiceImpl topService;
    
    @Autowired
    PsHeadServiceImpl psHeadService;


    public void consoleMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Lanzador de Procesos (CLI) Linux ===\n" +
                "Comandos:\n" +
                "  lsof -i\n" +
                "  top\n" +
                "  ps aux | head\n");
        String commandStr = scanner.nextLine().toUpperCase();

        if (commandStr.startsWith("LSOF")) lsofService.processLine(commandStr);
        if (commandStr.startsWith("TOP")) topService.processLine(commandStr);
        if (commandStr.startsWith("PS")) psHeadService.processLine(commandStr);

        scanner.close();
    }
}
