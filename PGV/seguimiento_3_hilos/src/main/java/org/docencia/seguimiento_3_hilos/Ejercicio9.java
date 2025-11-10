package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio9 {

    private final AtomicBoolean finished = new AtomicBoolean(false);
    private final AtomicBoolean destroyed = new AtomicBoolean(false);
    private final int missionTimeMS = 4000;
    private long startTime;

    private final AtomicInteger speed = new AtomicInteger(0);
    private final AtomicInteger shields = new AtomicInteger(100);

    /**
     * Runnable que representa a Han Solo manejando la velocidad.
     */
    private Runnable createHanSolo() {
        return () -> {
            while (!finished.get()) {
                speed.addAndGet(ThreadLocalRandom.current().nextInt(5, 16));

                if (ThreadLocalRandom.current().nextInt(1, 101) <= 5) {
                    destroyed.set(true);
                    finished.set(true);
                    System.out.println("Fallo de hiperimpulsor. ¡La nave se destruye!");
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (System.currentTimeMillis() - startTime >= missionTimeMS) {
                    finished.set(true);
                }
            }
        };
    }

    /**
     * Runnable que representa a Chewbacca reparando los escudos.
     */
    private Runnable createChewbacca() {
        return () -> {
            while (!finished.get()) {
                int delta = ThreadLocalRandom.current().nextInt(-10, 6);
                shields.addAndGet(delta);

                if (shields.get() <= 0) {
                    destroyed.set(true);
                    finished.set(true);
                    System.out.println("¡Escudos agotados! La nave se destruye!");
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (System.currentTimeMillis() - startTime >= missionTimeMS) {
                    finished.set(true);
                }
            }
        };
    }

    /**
     * Metodo principal que inicia la simulacion.
     */
    public void startMission() {
        startTime = System.currentTimeMillis();

        Thread han = new Thread(createHanSolo());
        Thread chewie = new Thread(createChewbacca());

        han.start();
        chewie.start();

        try {
            han.join();
            chewie.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (!destroyed.get()) {
            System.out.println("¡Han y Chewie escapan! Vel=" + speed.get() + ", Escudos=" + shields.get());
        }
    }

    public static void main(String[] args) {
        Ejercicio9 falcon = new Ejercicio9();
        falcon.startMission();
    }

    public boolean isDestroyed() {
        return destroyed.get();
    }

    public boolean isFinished() {
        return finished.get();
    }

    public int getSpeed() {
        return speed.get();
    }

    public int getShields() {
        return shields.get();
    }
}
