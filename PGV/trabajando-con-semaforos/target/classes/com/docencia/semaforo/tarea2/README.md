# Tarea 2: Hilos y Semáforos en Java

## Estructura de la tarea:

```
tarea2/
    ├── Colors                   # Enum con colores
    ├── ColorSemaphore           # Ejercicio 1
    ├── ColorSemaphoreImproved   # Ejercicio 1 mejorado
    ├── Laboratory               # Clase Main ejercicio 2
    ├── LaboratoryImproved       # Clase Main ejercicio 2 mejorado
    ├── Student                  # Clase Estudiante ejercicio 2
    └── StudentImproved          # Clase Estudiante ejercicio 2 mejorado
```

## Enunciado ejercicio 1:
Simula un semáforo de tráfico con tres estados: **ROJO, ÁMBAR, VERDE.**

Crea un programa que:
- Muestra en consola el color actual.
- Espera un tiempo según el color (ROJO 3s, VERDE 3s, ÁMBAR 1s).
- Cambia al siguiente color y repite en bucle. -El programa principal (main) debe poder parar la simulación después de 20 segundos.

Objetivo:
- Trabajar con Thread.sleep() y controlar la vida de un hilo.

Clase ColorSemaforo:
- color: **ROJO, AMBAR, VERDE.**
- semaforo: para permitir cambiar de estado de color.

### 🎯 Mejorando la solución
Implementa una alternancia con semaforos donde el orden siempre sea __ROJO, VERDE, ÁMBAR,ROJO,...__ Para ello crea la solución en la clase ColorSemaforoMejorado.

## Enunciado ejericio 2:
Disponemos de un laboratorio con _4 equipos_ para desarrollar, pero tenemos 6 estudiantes que necesitan usar los equipos para realizar el ejercicio que le ha propuesto el profesor, pudiendo sólo 4 hacerlo al mismo tiempo por la limitación de equipos. Implementa la clase Estudiante haciendo uso de semáforos. para controlar el acceso a los equipos disponibles. Cada estudiante debe de imprimir un mensaje cuando comienza a utilizar el equipo: _El estudiante_ __1__ _ha comenzado a utilizar el equipo, y cuando finaliza: El estudiante_ __1__ _ha finalizado con el equipo. Cada estudiante hace uso del equipo entre_ __3__ _y_ __5__ _segundos._

```java
class Estudiante extends Thread {

    private String nombre;
    private Semaphore semaforo;

...
}
```

### 🎯 Mejorando la solución
Mejora la solución anterior en la clase _EstudianteMejorado_, donde se indique el equipo informático que ha usado. Para ello has uso de la función _availablePermits() + 1._