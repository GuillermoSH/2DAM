package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio4 {

    private static final int ROUNDS = 3;
    private static final int PLAYERS = 3;

    /**
     * Clase interna que representa a un jugador del equipo.
     */
    private static class Player implements Runnable {
        private final String name;
        private final CyclicBarrier barrier;

        public Player(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        /**
         * Metodo que simula la participacion del jugador en varias rondas.
         */
        @Override
        public void run() {
            try {
                for (int round = 1; round <= ROUNDS; round++) {
                    int time = ThreadLocalRandom.current().nextInt(500, 1501);
                    Thread.sleep(time);

                    System.out.println(name + " ha completado la ronda " + round + " en " + time + " ms.");

                    // Esperar al resto del equipo antes de continuar
                    barrier.await();

                    if (round == ROUNDS) {
                        System.out.println(name + " ha terminado la competencia!");
                    }
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Metodo principal que inicia la competencia de Quidditch.
     */
    public void startRace() {
        CyclicBarrier barrier = new CyclicBarrier(PLAYERS,
                () -> System.out.println("Todos los jugadores han completado esta ronda.\n---"));

        Thread harry = new Thread(new Player("Harry", barrier));
        Thread ron = new Thread(new Player("Ron", barrier));
        Thread hermione = new Thread(new Player("Hermione", barrier));

        harry.start();
        ron.start();
        hermione.start();

        try {
            harry.join();
            ron.join();
            hermione.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("La competencia de Quidditch ha terminado!");
    }
}
