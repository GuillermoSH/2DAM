package org.docencia.hilos.enums;

public enum Dungeon {
    CATACUMBAS_HYRULE("Catacumbas de Hyrule"),
    TORRE_OSCURA("Torre Oscura"),
    MORIA("Moria"),
    ESTRELLA_MUERTE("Estrella de la Muerte"),
    NIDO_DRAGON("Nido de Dragón");

    private final String name;

    Dungeon(String name) {
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

