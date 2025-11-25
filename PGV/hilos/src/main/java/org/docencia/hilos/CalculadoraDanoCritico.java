package org.docencia.hilos;

import org.docencia.hilos.enums.Attacker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CalculadoraDanoCritico {

    static class TareaCalcularDano implements Callable<Integer> {
        private final Attacker attacker;

        TareaCalcularDano(Attacker attacker) {
            this.attacker = attacker;
        }

        @Override
        public Integer call() throws Exception {
            String hilo = Thread.currentThread().getName();
            System.out.println("[" + hilo + "] Calculando daño para " + attacker.name);

            boolean esCritico = Math.random() < attacker.critProbability;
            double multiplicador = esCritico ? attacker.critMultiplier : 1.0;

            Thread.sleep(500 + (int) (Math.random() * 500));

            int danoFinal = (int) (attacker.baseDmg * multiplicador);
            System.out.println("[" + hilo + "] " + attacker.name +
                    (esCritico ? " ¡CRÍTICO!" : " golpe normal") +
                    " -> daño: " + danoFinal);

            return danoFinal;
        }
    }

    public static void main(String[] args) {
        new CalculadoraDanoCritico().start();
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futuros = new ArrayList<>();

        // Ahora todos los ataques se obtienen del enum
        for (Attacker attacker : Attacker.values()) {
            Future<Integer> futuro = pool.submit(new TareaCalcularDano(attacker));
            futuros.add(futuro);
        }

        int totalRaid = 0;
        for (Future<Integer> futuro : futuros) {
            try {
                totalRaid += futuro.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Lectura interrumpida.");
            } catch (ExecutionException e) {
                System.out.println("Error calculando daño: " + e.getCause());
            }
        }

        System.out.println("Daño total de la raid: " + totalRaid);
        pool.shutdown();
    }
}
