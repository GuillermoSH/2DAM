package org.docencia.seguimiento_3_hilos;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio1_alone {
    private volatile boolean endGame = false;
    private int hpPikachu = 100;
    private int hpCharmander = 100;
    private String turn = "Pikachu";

    private final Lock lock = new ReentrantLock();
    private final Condition turnChange = lock.newCondition();
    private final Random random = new Random();

    private void attack(String attacker) {
        int hpObjective;
        int dmg = random.nextInt(5, 20);

        if (attacker.equals("Pikachu")) {
            hpCharmander -= dmg;
            hpObjective = hpCharmander;
        } else {
            hpPikachu -= dmg;
            hpObjective = hpPikachu;
        }

        System.out.println(attacker + " ataca con " + dmg + " de daño. HP rival: " + (attacker.equals("Pikachu") ? hpCharmander : hpPikachu));

        if (hpObjective <= 0 && !endGame) {
            endGame = true;
            System.out.println(attacker + " ha ganado la batalla!");
        }

        try {
            Thread.sleep(random.nextInt(200, 601));
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pikachu() {
        while (!endGame) {
            lock.lock();
            try {
                while (!turn.equals("Pikachu") && !endGame) {
                    turnChange.await();
                }
                if (endGame) break;
                attack("Pikachu");
                turn = "Charmander";
                turnChange.signal();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    private void charmander() {
        while (!endGame) {
            lock.lock();
            try {
                while (!turn.equals("Charmander") && !endGame) {
                    turnChange.await();
                }
                if (endGame) break;
                attack("Charmander");
                turn = "Pikachu";
                turnChange.signal();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void start() {
        Thread pikachu = new Thread(this::pikachu);
        Thread charmander = new Thread(this::charmander);
        pikachu.start();
        charmander.start();
        try {
            pikachu.join();
            charmander.join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio1_alone battle = new Ejercicio1_alone();
        battle.start();
    }
}
