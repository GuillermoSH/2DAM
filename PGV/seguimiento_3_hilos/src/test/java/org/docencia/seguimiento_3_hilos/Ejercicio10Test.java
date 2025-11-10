package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio10Test {

    @Test
    public void mustHaveAWinner() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio10 battle = new Ejercicio10();
        battle.start();

        String output = outContent.toString();

        assertTrue(battle.isFightEnded(), "El combate debe haber terminado");
        assertTrue(
                output.contains("gana la batalla mágica"),
                "Debe haber un mensaje de victoria"
        );

        assertTrue(
                battle.getEnergyGandalf() <= 0 || battle.getEnergySaruman() <= 0,
                "Uno de los magos debe haber perdido toda su energia"
        );
    }
}
