package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio6Test {

    @Test
    public void mustHaveOneWinningEra() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio6 tardis = new Ejercicio6();
        tardis.travel();

        String output = outContent.toString();

        assertTrue(tardis.isDestinationReached(), "Debe haberse alcanzado un destino");
        assertNotNull(tardis.getWinningEra(), "Debe existir una era ganadora");
        assertTrue(output.contains("La TARDIS llego primero a"), "Debe haber un mensaje de llegada");
        assertEquals(1, output.split("La TARDIS llego primero a").length - 1, "Debe haber solo una llegada");
    }
}

