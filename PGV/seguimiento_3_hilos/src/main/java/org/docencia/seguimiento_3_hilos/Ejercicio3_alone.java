package org.docencia.seguimiento_3_hilos;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio3_alone {
    private final BlockingQueue<String> assembled = new ArrayBlockingQueue<>(10);
    private AtomicInteger active = new AtomicInteger(0);
    private final Random random = new Random();

    private void Assembler() {
        try {
            for (int i = 0; i <= assembled.size(); i++) {
                Thread.sleep(random.nextInt(100, 300));
                String droid = "Droid-" + i;
                System.out.println("Ensamblado " + droid);
                assembled.put(droid);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    private void Activator() {
        int count = 0;

        try {
            while (count < assembled.size()) {
                String droid = assembled.take();
                System.out.println("Activado " + droid);
                active.getAndAdd(1);
                count++;
                Thread.sleep(random.nextInt(50, 150));
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public void start() {
        Thread tAssembler = new Thread(this::Assembler);
        Thread tActivator = new Thread(this::Activator);
        tAssembler.start();
        tActivator.start();

        try {
            tAssembler.join();
            tActivator.join();
        } catch(Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Ejercicio3_alone production = new Ejercicio3_alone();
        production.start();
    }
}
