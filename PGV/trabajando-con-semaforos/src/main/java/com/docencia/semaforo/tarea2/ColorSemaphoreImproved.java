package com.docencia.semaforo.tarea2;

import java.util.concurrent.Semaphore;

public class ColorSemaphoreImproved implements Runnable {
    private final Colors color;
    private static volatile boolean isActive = true;

    private static final Semaphore turnoRojo = new Semaphore(1);
    private static final Semaphore turnoVerde = new Semaphore(0);
    private static final Semaphore turnoAmbar = new Semaphore(0);

    public ColorSemaphoreImproved(Colors color) {
        this.color = color;
    }

    @Override
    public void run() {
        int timer = (color == Colors.AMBER) ? 1000 : 3000;

        Semaphore myTurn;
        Semaphore nextTurn;

        switch (color) {
            case RED -> {
                myTurn = turnoRojo;
                nextTurn = turnoVerde;
            }
            case GREEN -> {
                myTurn = turnoVerde;
                nextTurn = turnoAmbar;
            }
            case AMBER -> {
                myTurn = turnoAmbar;
                nextTurn = turnoRojo;
            }
            default -> throw new IllegalStateException("Color no válido");
        }

        try {
            while (isActive) {

                myTurn.acquire();

                if (!isActive) {
                    nextTurn.release();
                    break;
                }

                System.out.println("Color actual: " + color.nameToUpper());

                Thread.sleep(timer);

                nextTurn.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            nextTurn.release();
        }
    }

    public static void switchOff() {
        isActive = false;
        turnoRojo.release();
        turnoVerde.release();
        turnoAmbar.release();
    }

    public static void main(String[] args) {

        Thread tRojo = new Thread(new ColorSemaphore(Colors.RED));
        Thread tVerde = new Thread(new ColorSemaphore(Colors.GREEN));
        Thread tAmbar = new Thread(new ColorSemaphore(Colors.AMBER));

        tRojo.start();
        tVerde.start();
        tAmbar.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        switchOff();

        tRojo.interrupt();
        tVerde.interrupt();
        tAmbar.interrupt();

        System.out.println("[INFO] Semáforo apagado ----------");
    }
}
