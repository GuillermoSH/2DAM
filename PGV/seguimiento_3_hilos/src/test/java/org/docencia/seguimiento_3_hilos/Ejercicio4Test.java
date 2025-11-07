package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio4Test {

    @Test
    public void allPlayersMustFinishCompetition() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio4 quidditch = new Ejercicio4();
        quidditch.startRace();

        String output = outContent.toString();

        assertTrue(output.contains("Harry ha terminado la competencia!"));
        assertTrue(output.contains("Ron ha terminado la competencia!"));
        assertTrue(output.contains("Hermione ha terminado la competencia!"));
        assertTrue(output.contains("La competencia de Quidditch ha terminado!"));
    }
}
