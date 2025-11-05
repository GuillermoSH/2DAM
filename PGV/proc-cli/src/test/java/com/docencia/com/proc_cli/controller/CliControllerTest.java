package com.docencia.com.proc_cli.controller;

import com.docencia.com.proc_cli.controllers.CliController;
import com.docencia.com.proc_cli.services.impl.LsofServiceImpl;
import com.docencia.com.proc_cli.services.impl.PsHeadServiceImpl;
import com.docencia.com.proc_cli.services.impl.TopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CliControllerTest {

    private CliController cliController;
    private LsofServiceImpl lsofService;
    private TopServiceImpl topService;
    private PsHeadServiceImpl psHeadService;

    @BeforeEach
    public void setUp() {
        lsofService = new LsofServiceImpl();
        topService = new TopServiceImpl();
        psHeadService = new PsHeadServiceImpl();
        cliController = new CliController();
        cliController.lsofService = lsofService;
        cliController.topService = topService;
        cliController.psHeadService = psHeadService;
    }

    @Test
    public void testConsoleMenu_LsofCommand() {
        String input = "lsof -i\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        assertDoesNotThrow(() -> cliController.consoleMenu());
        // Aquí puedes verificar lo que se espera después de ejecutar el comando.
    }

    @Test
    public void testConsoleMenu_TopCommand() {
        String input = "top\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        assertDoesNotThrow(() -> cliController.consoleMenu());
        // Verifica el resultado esperado para el comando top.
    }

    @Test
    public void testConsoleMenu_PsCommand() {
        String input = "ps aux | head\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        assertDoesNotThrow(() -> cliController.consoleMenu());
        // Verifica el resultado esperado para el comando ps.
    }

    @Test
    public void testConsoleMenu_InvalidCommand() {
        String input = "invalid command\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        assertDoesNotThrow(() -> cliController.consoleMenu());
        // Aquí puedes verificar que no ocurre nada o se maneja correctamente un comando inválido.
    }
}
