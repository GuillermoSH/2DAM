package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio3 {

    private final BlockingQueue<String> piecesQueue = new ArrayBlockingQueue<>(10);
    private volatile boolean productionFinished = false;

    /**
     * Clase interna que representa al productor de piezas.
     */
    private class Producer implements Runnable {
        private final int totalPieces;

        public Producer(int totalPieces) {
            this.totalPieces = totalPieces;
        }

        /**
         * Metodo que simula la produccion de piezas de manera aleatoria.
         */
        @Override
        public void run() {
            try {
                for (int i = 1; i <= totalPieces; i++) {
                    String piece = "Pieza-" + i;
                    piecesQueue.put(piece);
                    System.out.println("Productor ha creado " + piece);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200, 501));
                }
                productionFinished = true;
                System.out.println("Productor ha terminado la produccion de piezas.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Clase interna que representa a un ensamblador de droides.
     */
    private class Assembler implements Runnable {
        private final String name;

        public Assembler(String name) {
            this.name = name;
        }

        /**
         * Metodo que simula el ensamblaje de droides usando piezas.
         */
        @Override
        public void run() {
            try {
                while (!productionFinished || !piecesQueue.isEmpty()) {
                    String piece = piecesQueue.poll();
                    if (piece != null) {
                        System.out.println(name + " esta ensamblando un droide con " + piece);
                        Thread.sleep(ThreadLocalRandom.current().nextInt(300, 801));
                        System.out.println(name + " ha ensamblado un droide con " + piece);
                    } else {
                        Thread.sleep(100); // Esperar si no hay piezas disponibles momentaneamente
                    }
                }
                System.out.println(name + " ha terminado su trabajo.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Metodo principal que inicia la simulacion de la fabrica.
     */
    public void startProduction() {
        Thread producerThread = new Thread(new Producer(10));
        Thread assembler1 = new Thread(new Assembler("Ensamblador-1"));
        Thread assembler2 = new Thread(new Assembler("Ensamblador-2"));

        producerThread.start();
        assembler1.start();
        assembler2.start();

        try {
            producerThread.join();
            assembler1.join();
            assembler2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Fabrica finalizada: todos los droides han sido ensamblados.");
    }

    public static void main(String[] args) {
        Ejercicio3 factory = new Ejercicio3();
        factory.startProduction();
    }
}
