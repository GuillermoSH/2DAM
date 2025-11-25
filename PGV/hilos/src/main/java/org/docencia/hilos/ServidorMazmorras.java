package org.docencia.hilos;

import org.docencia.hilos.enums.Dungeon;
import org.docencia.hilos.enums.Player;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorMazmorras {

    public static void main(String[] args) {
        new ServidorMazmorras().start(2);
    }

    public void start(int nThreads) {
        ExecutorService gmBots = Executors.newFixedThreadPool(nThreads);
        CountDownLatch latch = new CountDownLatch(Player.values().length);

        Player[] players = Player.values();
        Dungeon[] dungeons = Dungeon.values();

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            Dungeon dungeon = dungeons[i % dungeons.length];

            gmBots.execute(() -> {
                try {
                    new DungeonRequest(player, dungeon).run();
                } finally {
                    latch.countDown();
                }
            });
        }

        gmBots.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Servidor: todas las peticiones han sido enviadas.");
    }

    static class DungeonRequest implements Runnable {
        private final Player player;
        private final Dungeon dungeon;

        public DungeonRequest(Player player, Dungeon dungeon) {
            this.player = player;
            this.dungeon = dungeon;
        }

        @Override
        public void run() {
            String hilo = Thread.currentThread().getName();
            System.out.println("[" + hilo + "] Preparando mazmorra '" +
                    dungeon.getName() + "' para " + player.getName());

            try {
                Thread.sleep(1000 + (int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                System.out.println("[" + hilo + "] Petición interrumpida para " + player.getName());
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println("[" + hilo + "] Mazmorra '" + dungeon.getName() +
                    "' lista para " + player.getName() + " 🎮");
        }
    }
}
