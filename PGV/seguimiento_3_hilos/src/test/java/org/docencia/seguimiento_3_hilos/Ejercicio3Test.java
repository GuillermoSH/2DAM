package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio3Test {

    @Test
    public void factoryMustFinishProperly() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio3 factory = new Ejercicio3();
        factory.startProduction();

        String output = outContent.toString();

        assertTrue(output.contains("Productor ha terminado la produccion de piezas."));
        assertTrue(output.contains("Fabrica finalizada"));
        assertTrue(output.contains("Ensamblador-1 ha terminado su trabajo."));
        assertTrue(output.contains("Ensamblador-2 ha terminado su trabajo."));
    }
}
