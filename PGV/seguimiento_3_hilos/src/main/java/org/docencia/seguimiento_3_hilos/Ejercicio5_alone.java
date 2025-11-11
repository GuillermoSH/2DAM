package org.docencia.seguimiento_3_hilos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ejercicio5_alone {
    private final AtomicBoolean isClueFound = new AtomicBoolean(false);
    private volatile String winner = null;
    private final Random random = new Random();

    private class Jedi implements Runnable {
        private final String name;
        private final String planet;

        public Jedi(String name, String planet) {
            this.name = name;
            this.planet = planet;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(400, 1501));
                if (!isClueFound.get()) {
                    isClueFound.set(true);
                    winner = name;
                    System.out.println(name + " halló una pista en " + planet + ". Fin de búsqueda.");
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        Thread t1 = new Thread(new Jedi("Kenobi", "Tatooine"));
        Thread t2 = new Thread(new Jedi("Skywalker", "Dagobah"));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio5_alone exploration = new Ejercicio5_alone();
        exploration.start();
    }
}
