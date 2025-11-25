package org.docencia.hilos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculadoraDanoCriticoTest {
    @Test
    void outputThreadsTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CalculadoraDanoCritico server = new CalculadoraDanoCritico();
        server.start();

        String output = outContent.toString();

        assertTrue(output.contains("Daño total de la raid:"), "Debe haber un mensaje de confirmacion de finalizacion del programa");
        assertTrue(output.contains("thread-4"), "Debe haber un mensaje donde se refleje que hay 4 hilos iniciados");
        assertTrue(output.contains("daño: "), "Debe haber un mensaje donde se refleje que se esta ejecutando los hilos aplicando daño");
    }
}
