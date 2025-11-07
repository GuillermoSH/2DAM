package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio2Test {

    @Test
    public void onlyOneWinnerAndOneDiscovery() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio2 hunt = new Ejercicio2();
        hunt.startSearch();

        String output = outContent.toString();

        assertTrue(hunt.isFound(), "Debe haberse encontrado un Horrocrux");
        assertNotNull(hunt.getWinner(), "Debe haber un ganador");
        assertTrue(
                hunt.getWinner().equals("Harry")
                        || hunt.getWinner().equals("Hermione")
                        || hunt.getWinner().equals("Ron"),
                "El ganador debe ser uno de los tres buscadores"
        );
        assertEquals(1, output.split("encontró un Horrocrux|encontro un Horrocrux").length - 1,
                "Debe haberse registrado un solo hallazgo");
    }
}
