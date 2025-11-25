package com.docencia.com.examen.hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarRace implements Runnable {
    private final String name;
    private final int goal;
    private final Lock lock = new ReentrantLock();
    private final Condition turnChange = lock.newCondition();
    private static final AtomicBoolean winnerDeclared = new AtomicBoolean(false);
    private int distance = 0;

    public CarRace(String name, int goal) {
        this.name = name;
        this.goal = goal;
    }

    public static void main(String[] args) {
        int goal = 100;
        Thread rayoMcQueen = new Thread(new CarRace("Rayo McQueen", goal));
        Thread mate = new Thread(new CarRace("Mate", goal));

        rayoMcQueen.start();
        mate.start();

        try {
            rayoMcQueen.join();
            mate.join();
        } catch (Exception ignored) {
            Thread.currentThread().interrupt();
        }
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
                lock.lock();
                if (winnerDeclared.get()) {
                    turnChange.signal();
                    break;
                }
                int step = ThreadLocalRandom.current().nextInt(1, 11);
                distance += step;
                System.out.println(name + " avanzo " + step + ". Distancia total recorrida es: " + distance);

                if (distance >= goal) {
                    winnerDeclared.compareAndSet(false, true);
                    System.out.println("¡" + name + " gano la carrera!");
                }

                turnChange.signal();

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200, 401));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } catch (Exception ignored) {
            Thread.currentThread().interrupt();
            turnChange.signal();
        } finally {
            lock.unlock();
        }
    }
}
