package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio7 {

    private int victims = 100;
    private final Lock rescueLock = new ReentrantLock();

    /**
     * Runnable que representa el trabajo de rescate de un superheroe.
     * @param name Nombre del superheroe
     */
    private Runnable createHero(String name) {
        return () -> {
            while (getVictims() > 0) {
                rescueLock.lock();
                try {
                    if (victims > 0) {
                        int rescued = ThreadLocalRandom.current().nextInt(1, 11);
                        victims -= rescued;
                        if (victims < 0) victims = 0;
                        System.out.println(name + " rescato a " + rescued + " personas. Quedan " + victims);
                    }
                } finally {
                    rescueLock.unlock();
                }

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(100, 301));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println(name + " termino su turno de rescate.");
        };
    }

    /**
     * Metodo principal que lanza a los superheroes al rescate.
     */
    public void rescue() {
        String[] heroes = {"Superman", "Batman"};
        Thread[] threads = new Thread[heroes.length];

        for (int i = 0; i < heroes.length; i++) {
            threads[i] = new Thread(createHero(heroes[i]));
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Todos los superheroes han terminado su turno.");
    }

    public static void main(String[] args) {
        Ejercicio7 city = new Ejercicio7();
        city.rescue();
    }

    public int getVictims() {
        return victims;
    }
}
