package org.docencia.hilos.enums;

public enum Player {
    LINK("Link"),
    ZELDA("Zelda"),
    GERALT("Geralt"),
    YENNEFER("Yennefer"),
    GANDALF("Gandalf"),
    FRODO("Frodo"),
    ARAGORN("Aragorn"),
    LEIA("Leia"),
    LUKE("Luke"),
    DARTH_VADER("Darth Vader");

    private final String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

