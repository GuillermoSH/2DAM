package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ejercicio6 {

    private final AtomicBoolean destinationReached = new AtomicBoolean(false);
    private volatile String winningEra = null;

    /**
     * Runnable que representa un viaje temporal de la TARDIS.
     * @param era Nombre de la era destino
     */
    private Runnable createTravel(String era) {
        return () -> {
            try {
                int time = ThreadLocalRandom.current().nextInt(500, 2001);
                Thread.sleep(time);
                if (!destinationReached.get()) {
                    destinationReached.set(true);
                    winningEra = era;
                    System.out.println("La TARDIS llego primero a " + era);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    /**
     * Metodo principal que lanza los viajes de la TARDIS.
     */
    public void travel() {
        String[] eras = {"Roma Antigua", "Futuro Lejano", "Era Victoriana", "Ano 3000"};
        Thread[] threads = new Thread[eras.length];

        for (int i = 0; i < eras.length; i++) {
            threads[i] = new Thread(createTravel(eras[i]));
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Ejercicio6 tardis = new Ejercicio6();
        tardis.travel();
    }

    public boolean isDestinationReached() {
        return destinationReached.get();
    }

    public String getWinningEra() {
        return winningEra;
    }
}
