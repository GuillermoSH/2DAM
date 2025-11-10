package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio8Test {

    @Test
    public void mustFinishAndHaveWinnerOrTie() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio8 contest = new Ejercicio8();
        contest.start();

        String output = outContent.toString();

        assertTrue(contest.isTimeFinished(), "El tiempo debe haber terminado");
        assertTrue(contest.getTotalThor() > 0 && contest.getTotalHulk() > 0,
                "Ambos deben haber levantado peso");
        assertTrue(
                output.contains("Thor gana con") ||
                        output.contains("Hulk gana con") ||
                        output.contains("Empate:"),
                "Debe imprimirse el resultado final"
        );
    }
}
