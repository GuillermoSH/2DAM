# 2.3 Respuestas:

- Que hasta que no acaben los 3 bots que estan usando los hilos, no se "liberan" para que otros 3 los usen.

- Que los recursos del hilo, previamente asignados, no se liberan hasta que el programa termina de ejecutarse por completo.

- Que el tiempo de ejecucion aumenta y el consumo de recursos disminuye en un pool de 1 y en el pool de 10 se invierten los casos.

# 3.3 Respuestas:

- Si cambiasemos a `execute(Runnable)`, el codigo solo imprimiria los logs pero no se podría calcular el daño total de la raid porque no devuelve valor (no podemos hacer el `future.get()`). En cambio con el `submit(Callable<V>)` si permite ejecutar codigo en paralelo y recuperar el valor calculado de otro hilo.

- La parte del codigo que nos permite lanzar en paralelo es la del bloque for con el Future:

```java
for (Ataque ataque : ataques) {
    Future<Integer> futuro = pool.submit(new TareaCalcularDano(ataque));
    futuros.add(futuro);
}
```

Y despues se puede recopilar todos estos datos con esta parte:
```java
int dano = futuros.get(i).get();
totalRaid += dano;
```

- Cuando se aumenta las probabilidades de critico o su multiplicador de daño al final se ve reflejado en el total de la RAID aumentando más la suma de lo habitual.

# 4.3 Respuestas:

- `schedule(...)` permite ejecutar una parte del codigo solo una vez en un periodo de tiempo determinado y `scheduleAtFixedRate(...)` permite ejecutar dando el periodo de tiempo que deseas entre ejecuciones y el retardo al iniciar la tarea.