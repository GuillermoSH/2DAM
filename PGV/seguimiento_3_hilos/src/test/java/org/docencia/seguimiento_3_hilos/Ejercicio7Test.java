package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio7Test {

    @Test
    public void mustRescueAllVictims() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio7 city = new Ejercicio7();
        city.rescue();

        String output = outContent.toString();

        assertTrue(output.contains("Superman termino su turno de rescate."),
                "Superman debe terminar su turno");
        assertTrue(output.contains("Batman termino su turno de rescate."),
                "Batman debe terminar su turno");
        assertTrue(output.contains("Todos los superheroes han terminado su turno."),
                "Debe imprimirse el mensaje final");
        assertEquals(0, city.getVictims(),
                "No deben quedar victimas por rescatar");
    }
}
