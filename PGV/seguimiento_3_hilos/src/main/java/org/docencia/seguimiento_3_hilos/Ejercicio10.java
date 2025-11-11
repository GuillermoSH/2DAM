package org.docencia.seguimiento_3_hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio10 {

    private int energyGandalf = 120;
    private int energySaruman = 120;
    private final AtomicBoolean fightEnded = new AtomicBoolean(false);
    private final Lock lock = new ReentrantLock();

    /**
     * Metodo para realizar un ataque magico de un mago a otro.
     * @param attacker Nombre del mago atacante
     * @param isGandalfTarget true si el objetivo es Gandalf, false si es Saruman
     */
    private void castSpell(String attacker, boolean isGandalfTarget) {
        int damage = ThreadLocalRandom.current().nextInt(8, 26);

        if (isGandalfTarget) {
            energyGandalf -= damage;
            System.out.println(attacker + " lanza hechizo por " + damage + ". Energía rival: " + energyGandalf);
            if (energyGandalf <= 0 && !fightEnded.get()) {
                fightEnded.set(true);
                System.out.println(attacker + " gana la batalla mágica.");
            }
        } else {
            energySaruman -= damage;
            System.out.println(attacker + " lanza hechizo por " + damage + ". Energía rival: " + energySaruman);
            if (energySaruman <= 0 && !fightEnded.get()) {
                fightEnded.set(true);
                System.out.println(attacker + " gana la batalla mágica.");
            }
        }
    }

    /**
     * Runnable de Gandalf para atacar a Saruman.
     */
    private Runnable createGandalf() {
        return () -> {
            while (!fightEnded.get()) {
                lock.lock();
                try {
                    if (!fightEnded.get()) {
                        castSpell("Gandalf", false);
                    }
                } finally {
                    lock.unlock();
                }

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200, 601));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }

    /**
     * Runnable de Saruman para atacar a Gandalf.
     */
    private Runnable createSaruman() {
        return () -> {
            while (!fightEnded.get()) {
                lock.lock();
                try {
                    if (!fightEnded.get()) {
                        castSpell("Saruman", true);
                    }
                } finally {
                    lock.unlock();
                }

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200, 601));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }

    /**
     * Metodo principal que inicia la simulacion de la batalla.
     */
    public void start() {
        Thread gandalf = new Thread(createGandalf());
        Thread saruman = new Thread(createSaruman());

        gandalf.start();
        saruman.start();

        try {
            gandalf.join();
            saruman.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio10 battle = new Ejercicio10();
        battle.start();
    }

    public boolean isFightEnded() {
        return fightEnded.get();
    }

    public int getEnergyGandalf() {
        return energyGandalf;
    }

    public int getEnergySaruman() {
        return energySaruman;
    }
}
