package com.concurrente.hilos;

public class Main2 {
    public static void main(String[] args) {
        MiThread thread = new MiThread();
        System.out.println("Arrancamos el hilo");
        thread.start();
        try {
            System.out.println("Me fui a dormir");
            thread.sleep(5000);
            System.out.println("Me levanto");
        } catch (Exception e) {
            System.out.println("El hilo fue interrumpido");
        }
    }
}
