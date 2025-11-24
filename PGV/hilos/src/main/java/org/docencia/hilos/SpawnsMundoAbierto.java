package org.docencia.hilos;

import org.docencia.hilos.enums.Enemy;
import org.docencia.hilos.enums.Zone;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpawnsMundoAbierto {

    public static void main(String[] args) {
        SpawnsMundoAbierto server = new SpawnsMundoAbierto();
        server.start();
    }

    public void start() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.scheduleAtFixedRate(new SpawnTarea(), 0, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(12000);

            System.out.println("Deteniendo spawns...");
            scheduler.shutdown();

            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Forzando parada.");
                scheduler.shutdownNow();
            }
        } catch (Exception ignored) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Servidor de mundo abierto detenido.");
    }

    static class SpawnTarea implements Runnable {

        @Override
        public void run() {
            String hilo = Thread.currentThread().getName();

            Zone zona = Zone.random();
            Enemy enemigo = Enemy.randomByProbability();

            System.out.println("[" + LocalTime.now() + "][" + hilo + "] Spawn de " +
                    enemigo.getNombre() + " en " + zona.getName());
        }
    }
}
