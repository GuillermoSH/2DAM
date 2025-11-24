package org.formacion.procesos.hilos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEjemplo {
    public static void main(String[] args) {
        // Creamos un ThreadPool con 3 hilos fijos
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // Creamos 5 tareas de ejemplo
        for (int i = 1; i <= 5; i++) {
            Runnable runnable = new Job("Tarea " + i);
            threadPool.submit(runnable);  // Enviamos las tareas al ThreadPool
        }

        // Cerramos el ThreadPool de forma ordenada después de completar las tareas
        threadPool.shutdown();
    }

    static class Job implements Runnable {
        private String name;

        public Job(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " está siendo ejecutada por el hilo " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);  // Simulamos que la tarea toma 2 segundos en ejecutarse
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " ha terminado.");
        }
    }
}