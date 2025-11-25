package org.docencia.hilos.enums;

public enum Attacker {
    MAGO_FUEGO("Mago del Fuego", 120, 0.30, 2.5),
    GUERRERO("Guerrero", 150, 0.15, 2.0),
    PICARO("Pícaro", 90, 0.50, 3.0),
    ARQUERA_ELFICA("Arquera Élfica", 110, 0.35, 2.2),
    INVOCADOR("Invocador", 80, 0.40, 2.8),
    PALADIN("Paladín", 130, 0.10, 1.8),
    BARBARO("Bárbaro", 160, 0.20, 2.1),
    NIGROMANTE("Nigromante", 100, 0.25, 2.3);

    public final String name;
    public final int baseDmg;
    public final double critProbability;
    public final double critMultiplier;

    Attacker(String name, int baseDmg, double critProbability, double critMultiplier) {
        this.name = name;
        this.baseDmg = baseDmg;
        this.critProbability = critProbability;
        this.critMultiplier = critMultiplier;
    }

    @Override
    public String toString() {
        return name;
    }
}

