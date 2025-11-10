package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio8 {

    private final int durationMS = 5000;
    private final AtomicBoolean timeFinished = new AtomicBoolean(false);
    private final AtomicInteger totalThor = new AtomicInteger(0);
    private final AtomicInteger totalHulk = new AtomicInteger(0);

    /**
     * Runnable que representa el temporizador de la competencia.
     * Espera la duracion establecida y luego marca el fin del tiempo.
     */
    private Runnable createTimer() {
        return () -> {
            try {
                Thread.sleep(durationMS);
                timeFinished.set(true);
                System.out.println("¡Tiempo!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    /**
     * Runnable que representa a Thor levantando peso.
     */
    private Runnable createThor() {
        return () -> {
            while (!timeFinished.get()) {
                int weight = ThreadLocalRandom.current().nextInt(5, 21);
                totalThor.addAndGet(weight);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(50, 121));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }

    /**
     * Runnable que representa a Hulk levantando peso.
     */
    private Runnable createHulk() {
        return () -> {
            while (!timeFinished.get()) {
                int weight = ThreadLocalRandom.current().nextInt(5, 21);
                totalHulk.addAndGet(weight);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(50, 121));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }

    /**
     * Metodo principal que lanza los hilos y determina el ganador.
     */
    public void start() {
        Thread timer = new Thread(createTimer());
        Thread thor = new Thread(createThor());
        Thread hulk = new Thread(createHulk());

        timer.start();
        thor.start();
        hulk.start();

        try {
            timer.join();
            thor.join();
            hulk.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int thorTotal = totalThor.get();
        int hulkTotal = totalHulk.get();

        if (thorTotal > hulkTotal) {
            System.out.println("Thor gana con " + thorTotal + " vs " + hulkTotal);
        } else if (hulkTotal > thorTotal) {
            System.out.println("Hulk gana con " + hulkTotal + " vs " + thorTotal);
        } else {
            System.out.println("Empate: " + thorTotal);
        }
    }

    public static void main(String[] args) {
        Ejercicio8 contest = new Ejercicio8();
        contest.start();
    }

    public int getTotalThor() {
        return totalThor.get();
    }

    public int getTotalHulk() {
        return totalHulk.get();
    }

    public boolean isTimeFinished() {
        return timeFinished.get();
    }
}
