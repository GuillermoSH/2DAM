package com.docencia.com.proc_cli.controllers;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docencia.com.proc_cli.services.interfaces.LsofService;
import com.docencia.com.proc_cli.services.interfaces.PsHeadService;
import com.docencia.com.proc_cli.services.interfaces.TopService;

@Service
public class CliController {
    @Autowired
    LsofService lsofService;
    
    @Autowired
    TopService topService;
    
    @Autowired
    PsHeadService psHeadService;


    public void menuConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Lanzador de Procesos (CLI) Linux ===\n" +
                "Comandos:\n" +
                "  lsof -i\n" +
                "  top\n" +
                "  ps aux | head\n");
        String commandStr = scanner.nextLine();

        if (commandStr.toUpperCase().startsWith("LSOF")) {
            lsofService.processLine(commandStr);
        } else if (commandStr.toUpperCase().startsWith("TOP")) {
            topService.processLine(commandStr);
        } else {
            psHeadService.processLine(commandStr);
        }

        scanner.close();
    }
}
