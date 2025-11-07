package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio5Test {

    @Test
    public void allJediMustCompleteMission() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio5 mission = new Ejercicio5();
        mission.startExploration();

        String output = outContent.toString();

        assertTrue(output.contains("Obi-Wan ha completado su mision Jedi."));
        assertTrue(output.contains("Anakin ha completado su mision Jedi."));
        assertTrue(output.contains("Ahsoka ha completado su mision Jedi."));
        assertTrue(output.contains("Yoda ha completado su mision Jedi."));
        assertTrue(output.contains("La exploracion Jedi ha finalizado con exito."));
    }
}
