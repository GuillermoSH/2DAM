package org.docencia.seguimiento_3_hilos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ejercicio2_alone {
    private AtomicBoolean found = new AtomicBoolean(false);
    private volatile String winner = null;
    private final Random random = new Random();

    private void findHorrocruxes(String name, String location) {
        int time = random.nextInt(500, 2000);
        try {
            Thread.sleep(time);
            if (!found.get()) {
                found.set(true);
                winner = name;
                System.out.println(name + " encontró un Horrocrux en " + location + ". ¡Búsqueda terminada!");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public void start() {
        Thread t1 = new Thread(()-> findHorrocruxes("Harry", "Bosque Prohibido"));
        Thread t2 = new Thread(()-> findHorrocruxes("Hermione", "Biblioteca Antigua"));
        Thread t3 = new Thread(()-> findHorrocruxes("Ron", "Mazmorras del castillo"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio2_alone search = new Ejercicio2_alone();
        search.start();
    }
}
