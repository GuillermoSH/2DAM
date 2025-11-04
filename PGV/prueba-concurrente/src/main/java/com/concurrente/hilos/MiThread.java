package com.concurrente.hilos;

public class MiThread extends Thread{
    @Override
    public void run() {
        System.out.println("Soy un hilo desde Thread");
    }
}
