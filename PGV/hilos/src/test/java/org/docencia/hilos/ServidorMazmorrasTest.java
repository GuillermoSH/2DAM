package org.docencia.hilos;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServidorMazmorrasTest {
    @Test
    void output3ThreadsTest() {
        int nThreads = 3;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ServidorMazmorras server = new ServidorMazmorras();
        server.start(nThreads);

        String output = outContent.toString();

        assertTrue(output.contains("Servidor: todas las peticiones"), "Debe haber un mensaje de confirmacion de que se han lanzado todas las peticiones");
        assertTrue(output.contains("thread-3"), "Debe haber un mensaje donde se refleje que hay 3 hilos iniciados");
    }

    @Test
    void output10ThreadsTest() {
        int nThreads = 10;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ServidorMazmorras server = new ServidorMazmorras();
        server.start(nThreads);

        String output = outContent.toString();
        System.out.println(output);
        assertTrue(output.contains("Servidor: todas las peticiones"), "Debe haber un mensaje de confirmacion de que se han lanzado todas las peticiones");
        assertTrue(output.contains("thread-10"), "Debe haber un mensaje donde se refleje que hay 10 hilos iniciados");
    }

    @Test
    void output1ThreadTest() {
        int nThreads = 1;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ServidorMazmorras server = new ServidorMazmorras();
        server.start(nThreads);

        String output = outContent.toString();
        System.out.println(output);
        assertTrue(output.contains("Servidor: todas las peticiones"), "Debe haber un mensaje de confirmacion de que se han lanzado todas las peticiones");
        assertFalse(output.contains("thread-3"), "Debe haber un mensaje donde se refleje que hay solo 1 hilo iniciado");
    }
}
