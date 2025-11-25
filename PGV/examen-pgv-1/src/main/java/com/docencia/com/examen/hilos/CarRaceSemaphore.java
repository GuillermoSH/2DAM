package com.docencia.com.examen.hilos;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class CarRaceSemaphore implements Runnable {
    private final int goal;
    private final String name;
    private final Semaphore semaphore = new Semaphore(1, true);
    private static final AtomicBoolean winnerDeclared = new AtomicBoolean(false);
    private int distance;

    public CarRaceSemaphore(String name, int goal) {
        this.name = name;
        this.goal = goal;
    }

    /**
     * Metodo de ejecucion del objeto donde se evalua la distancia recorrida
     * y la meta para que si llega antes que cualquier hilo lanzado, pare la
     * carrera y declare el ganador. Cada vez que obtiene el turno avanza un
     * numero aleatorio de distancia y se guardara para su proxima evaluacion
     */
    @Override
    public void run() {
        try {
            while (!winnerDeclared.get() && distance < goal) {
                semaphore.acquire();
                if (winnerDeclared.get()) {
                    semaphore.release();
                    break;
                }
                int step = ThreadLocalRandom.current().nextInt(1, 11);
                distance += step;
                System.out.println(name + " avanzó " + step + " metros. Distancia total: " + distance + " metros.");

                if (distance >= goal) {
                    if (winnerDeclared.compareAndSet(false, true)) {
                        System.out.println(name + " ha ganado la carrera!");
                    }
                }
                semaphore.release();

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200, 401));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("    🏁 CARRERA DE COCHES 🏁");
        System.out.println("   Rayo-McQueen vs Mate");

        System.out.println("═══════════════════════════════════════");
        
        int raceGoal = 100;
        
        Thread rayoMcQueen = new Thread(new CarRaceSemaphore("Rayo-McQueen", raceGoal));
        Thread mate = new Thread(new CarRaceSemaphore("Mate", raceGoal));

        rayoMcQueen.start();
        mate.start();

        try {
            rayoMcQueen.join();
            mate.join();
            
            System.out.println("\n═══════════════════════════════════════");
            System.out.println("        🏁 CARRERA TERMINADA 🏁");
            System.out.println("═══════════════════════════════════════");
            
        } catch (InterruptedException e) {
            System.out.println("La carrera fue interrumpida!");
        }
    }
    
    
}
