package org.docencia.seguimiento_3_hilos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio4_alone {
    private final AtomicBoolean isSnitchCatched = new AtomicBoolean(false);
    private final Lock lock = new ReentrantLock();
    private final Random random = new Random();
    private int teamAPoints = 0;
    private int teamBPoints = 0;

    private class PlayerTeamA implements Runnable {
        @Override
        public void run() {
            try {
                while (!isSnitchCatched.get()) {
                    Thread.sleep(random.nextInt(200, 501));
                    int score = random.nextInt(0, 2) * 10;
                    if (score > 0) {
                        lock.lock();
                        teamAPoints += score;
                        lock.unlock();
                        System.out.println("Equipo A anota 10. Total A=" + teamAPoints);
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class PlayerTeamB implements Runnable {
        @Override
        public void run() {
            try {
                while (!isSnitchCatched.get()) {
                    Thread.sleep(random.nextInt(200, 501));
                    int score = random.nextInt(0, 2) * 10;
                    if (score > 0) {
                        lock.lock();
                        teamBPoints += score;
                        lock.unlock();
                        System.out.println("Equipo B anota 10. Total B=" + teamBPoints);
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class Searcher implements Runnable {
        @Override
        public void run() {
            try {
                while (!isSnitchCatched.get()) {
                    Thread.sleep(random.nextInt(300, 701));
                    if (random.nextInt(1, 101) <= 15) {
                        isSnitchCatched.set(true);
                        System.out.println("¡Snitch dorada atrapada!");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void start() {
        Thread tA = new Thread(new PlayerTeamA());
        Thread tB = new Thread(new PlayerTeamB());
        Thread tS = new Thread(new Searcher());

        tA.start();
        tB.start();
        tS.start();

        try {
            tA.join();
            tB.join();
            tS.join();
            System.out.println("Marcador final: A=" + teamAPoints + " B=" + teamBPoints);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio4_alone search = new Ejercicio4_alone();
        search.start();
    }
}
