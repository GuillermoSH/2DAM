package org.docencia.hilos.enums;

public enum Enemy {
    SLIME_MUTANTE("Slime Mutante", 0.30),
    ESQUELETO_GUERRERO("Esqueleto Guerrero", 0.25),
    MECHA_DRAGON("Mecha-Dragón", 0.15),
    BANDIDO_DEL_DESIERTO("Bandido del Desierto", 0.20),
    LICH_SUPREMO("Lich Supremo", 0.10);

    private final String nombre;
    private final double probSpawn;

    Enemy(String nombre, double probSpawn) {
        this.nombre = nombre;
        this.probSpawn = probSpawn;
    }

    /**
     * Devuelve un enemigo evaluando su probabilidad para spawnear
     * @return un enemigo
     */
    public static Enemy randomByProbability() {
        double r = Math.random();
        double acumulado = 0;

        for (Enemy e : values()) {
            acumulado += e.probSpawn;
            if (r <= acumulado) return e;
        }

        // fallback
        return values()[values().length - 1];
    }

    /**
     * Devuelve un enemigo sin evaluar su probabilidad para spawnear
     * @return un enemigo
     */
    public static Enemy randomPure() {
        Enemy[] values = values();
        return values[(int) (Math.random() * values.length)];
    }

    public String getNombre() {
        return nombre;
    }

    public double getProbSpawn() {
        return probSpawn;
    }

    @Override
    public String toString() {
        return nombre + " (prob: " + (probSpawn * 100) + "%)";
    }
}
