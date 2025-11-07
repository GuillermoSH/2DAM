package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio1 {

    private volatile boolean endGame = false;
    private int hpPikachu = 100;
    private int hpCharmander = 100;
    private String turn = "Pikachu";

    private final Lock lock = new ReentrantLock();
    private final Condition turnChange = lock.newCondition();

    /**
     * Metodo para asignar DMG a un pokemon o a otro dependiendo del atacante
     * @param attacker pokemon que inflige DMG al otro
     */
    private void attack(String attacker) {
        int damage = ThreadLocalRandom.current().nextInt(5, 21);

        if (attacker.equals("Pikachu")) {
            setHpCharmander(hpCharmander - damage);
            System.out.println("Pikachu ataca con " + damage + " de daño. HP rival: " + hpCharmander);
            if (hpCharmander <= 0 && !endGame) {
                endGame = true;
                System.out.println("Pikachu ha ganado la batalla!");
            }
        } else {
            setHpPikachu(hpPikachu - damage);
            System.out.println("Charmander ataca con " + damage + " de daño. HP rival: " + hpPikachu);
            if (hpPikachu <= 0 && !endGame) {
                endGame = true;
                System.out.println("Charmander ha ganado la batalla!");
            }
        }

        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(200, 601));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Runnable de Pikachu para atacar y cambiar de turno a Charmander
     */
    private void runPikachu() {
        while (!isEndGame()) {
            lock.lock();
            try {
                while (!turn.equals("Pikachu") && !isEndGame()) {
                    turnChange.await();
                }
                if (isEndGame()) break;

                attack("Pikachu");
                changeTurn("Charmander");
                turnChange.signal();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Runnable de Charmander para atacar y cambiar de turno a Pikachu
     */
    private void runCharmander() {
        while (!isEndGame()) {
            lock.lock();
            try {
                while (!turn.equals("Charmander") && !isEndGame()) {
                    turnChange.await();
                }
                if (isEndGame()) break;

                attack("Charmander");
                changeTurn("Pikachu");
                turnChange.signal();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void startBattle() {
        Thread t1 = new Thread(this::runPikachu);
        Thread t2 = new Thread(this::runCharmander);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio1 newBattle = new Ejercicio1();
        newBattle.startBattle();
    }

    public boolean isEndGame() {
        return endGame;
    }

    public int getHpPikachu() {
        return hpPikachu;
    }

    public void setHpPikachu(int hpPikachu) {
        this.hpPikachu = hpPikachu;
    }

    public int getHpCharmander() {
        return hpCharmander;
    }

    public void setHpCharmander(int hpCharmander) {
        this.hpCharmander = hpCharmander;
    }

    public void changeTurn(String newTurn) {
        this.turn = newTurn;
    }
}

