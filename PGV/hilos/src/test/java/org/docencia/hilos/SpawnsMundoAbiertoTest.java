package org.docencia.hilos;

import org.docencia.hilos.enums.Zone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpawnsMundoAbiertoTest {
    private static String regexZones;

    @BeforeAll
    static void beforeAll() {
        regexZones = String.join("|", Arrays.stream(Zone.values()).map(Zone::getName).toList());
    }

    @Test
    void outputTest() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SpawnsMundoAbierto server = new SpawnsMundoAbierto();
        server.start();

        String output = outContent.toString();

        assertTrue(output.contains("Spawn de"), "Debe aparecer al menos un mensaje de spawn.");
        assertTrue(output.matches("(?s).*en (" + regexZones + ").*"), "Debe aparecer una zona válida en el mensaje.");
        assertTrue(output.contains("Servidor de mundo abierto detenido."), "Debe aparecer el mensaje final del servidor.");
        assertTrue(output.contains("["), "Debe incluir la marca de tiempo y el nombre del hilo.");
    }
}
