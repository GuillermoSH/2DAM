package org.docencia.seguimiento_3_hilos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio1Test {
    @Test
    public void mustBeWinnerTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio1 game = new Ejercicio1();
        game.startBattle();

        String output = outContent.toString();

        assertTrue(output.contains("ha ganado la batalla!"), "Debe haber un mensaje de victoria");
        assertTrue(game.isEndGame(), "El juego debe haber terminado");
        assertTrue(game.getHpPikachu() <= 0 || game.getHpCharmander() <= 0, "Uno de los Pokémon debe tener HP <= 0");
    }
}
