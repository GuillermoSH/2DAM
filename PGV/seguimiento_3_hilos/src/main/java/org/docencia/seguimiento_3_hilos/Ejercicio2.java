package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ejercicio2 {

    private final AtomicBoolean found = new AtomicBoolean(false);
    private volatile String winner = null;

    /**
     * Runnable que representa a un buscador intentando encontrar el Horrocrux.
     * @param name nombre del buscador
     * @param location lugar donde busca
     */
    private void search(String name, String location) {
        int time = ThreadLocalRandom.current().nextInt(500, 2001);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (found.compareAndSet(false, true)) {
            winner = name;
            System.out.println(name + " encontro un Horrocrux en " + location + ". ¡Busqueda terminada!");
        }
    }

    /**
     * Metodo principal que inicia la simulacion.
     */
    public void startSearch() {
        Thread t1 = new Thread(() -> search("Harry", "Bosque Prohibido"));
        Thread t2 = new Thread(() -> search("Hermione", "Biblioteca Antigua"));
        Thread t3 = new Thread(() -> search("Ron", "Mazmorras del castillo"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio2 hunt = new Ejercicio2();
        hunt.startSearch();
    }

    public boolean isFound() {
        return found.get();
    }

    public String getWinner() {
        return winner;
    }
}
