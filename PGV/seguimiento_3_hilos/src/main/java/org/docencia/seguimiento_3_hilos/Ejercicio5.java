package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio5 {

    private static final int JEDI_COUNT = 4;
    private final Semaphore holocronAccess = new Semaphore(1);

    /**
     * Clase interna que representa a un explorador Jedi.
     */
    private class JediExplorer implements Runnable {
        private final String name;

        public JediExplorer(String name) {
            this.name = name;
        }

        /**
         * Metodo que simula la exploracion de planetas y el uso del holocron.
         */
        @Override
        public void run() {
            try {
                for (int planet = 1; planet <= 3; planet++) {
                    System.out.println(name + " esta explorando el planeta " + planet + "...");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(400, 901));

                    // Intentar acceder al holocron
                    holocronAccess.acquire();
                    try {
                        System.out.println(name + " esta registrando hallazgos en el holocron...");
                        Thread.sleep(ThreadLocalRandom.current().nextInt(300, 701));
                        System.out.println(name + " ha terminado de registrar en el holocron.");
                    } finally {
                        holocronAccess.release();
                    }
                }

                System.out.println(name + " ha completado su mision Jedi.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void startExploration() {
        Thread obiwan = new Thread(new JediExplorer("Obi-Wan"));
        Thread anakin = new Thread(new JediExplorer("Anakin"));
        Thread ahsoka = new Thread(new JediExplorer("Ahsoka"));
        Thread yoda = new Thread(new JediExplorer("Yoda"));

        obiwan.start();
        anakin.start();
        ahsoka.start();
        yoda.start();

        try {
            obiwan.join();
            anakin.join();
            ahsoka.join();
            yoda.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("La exploracion Jedi ha finalizado con exito.");
    }

    public static void main(String[] args) {
        Ejercicio5 mission = new Ejercicio5();
        mission.startExploration();
    }
}
