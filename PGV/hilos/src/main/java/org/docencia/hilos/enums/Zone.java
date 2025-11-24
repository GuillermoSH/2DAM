package org.docencia.hilos.enums;

public enum Zone {
    BOSQUE_MALDITO("Bosque Maldito"),
    RUINAS_ANTIGUAS("Ruinas Antiguas"),
    PANTANO_RADIACTIVO("Pantano Radiactivo"),
    CIUDAD_CIBERNETICA("Ciudad Cibernética"),
    TEMPLO_PROHIBIDO("Templo Prohibido");

    private final String name;

    Zone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Metodo para devolver una zona aleatoria de spawn
     * @return zona aleatoria
     */
    public static Zone random() {
        Zone[] values = values();
        return values[(int)(Math.random() * values.length)];
    }
}
