package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio9Test {

    @Test
    public void mustEndMission() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio9 falcon = new Ejercicio9();
        falcon.startMission();

        String output = outContent.toString();

        assertTrue(falcon.isFinished(), "La mision debe haber finalizado");

        if (falcon.isDestroyed()) {
            assertTrue(
                    output.contains("Fallo de hiperimpulsor") ||
                            output.contains("¡Escudos agotados!"),
                    "Debe mostrarse un mensaje de destruccion"
            );
        } else {
            assertTrue(
                    output.contains("¡Han y Chewie escapan!"),
                    "Debe mostrarse el mensaje de escape"
            );
            assertTrue(falcon.getSpeed() > 0, "Debe haber alguna velocidad alcanzada");
            assertTrue(falcon.getShields() > 0, "Debe haber escudos restantes");
        }
    }
}
