# Code, Learn & Practice(Programación de Servicios y Procesos: "Concurrencia, procesos e hilos")

## Bloque 1: Conceptos básicos (introducción a php)

### Variables y Condicionales

1. **Mayor de dos números**
```
<?php
    $num1 = 10;
    $num2 = 20;

    $messageStr = " es mayor que ";

    if ($num1 < $num2) {
        $messageStr = " es menor que ";
    }

    if ($num1 == $num2) {
        $messageStr = " es igual a ";
    }

    echo $num1 . $messageStr . $num2;
?>
```

2. **Edad permitida**  
```
<?php
    $edad = 8;

    $messageStr = "Eres mayor de edad";

    if ($edad < 18) {
        $messageStr = "Eres menor de edad";
    }

    echo $messageStr;
?>
```

3. **Positivo, negativo o cero**  
```
<?php
    $num1 = 10;

    $messageStr = "El número es positivo";

    if ($num1 < $num2) {
        $messageStr = "El número es negativo";
    }

    if ($num1 == $num2) {
        $messageStr = "El número es igual a 0";
    }

    echo $messageStr;
?>
```

4. **Nota final**  
   Pide la nota de un alumno y muestra:  
   - `"Suspenso"` (< 5), `"Aprobado"` (5–6), `"Notable"` (7–8), `"Sobresaliente"` (9–10).  

---

### 2️⃣ Bucles (for, while, foreach)

5. **Contar del 1 al 100**  
   Muestra los números del 1 al 100 en pantalla.  

6. **Suma acumulada**  
   Calcula la suma de los números del 1 al 50 usando un bucle.  

7. **Tabla de multiplicar**  
   Pide un número y genera su tabla de multiplicar del 1 al 10.  

8. **Números pares**  
   Muestra todos los números pares entre 1 y 50.  

9. **Cuenta atrás**  
   Haz un bucle que cuente de 10 a 1 y luego muestre `"¡Fin!"`.  

10. **Factorial**  
    Calcula el factorial de un número introducido (ejemplo: `5! = 120`).  

---

### 3️⃣ Combinando Condicionales y Bucles

11. **Números primos**  
    Escribe un algoritmo que muestre los números primos entre 1 y 50.  

12. **Fibonacci**  
    Genera los primeros 20 términos de la secuencia de Fibonacci.  

13. **Múltiplos de un número**  
    Pide un número `n` y muestra sus múltiplos hasta 100.  

14. **Suma de pares e impares**  
    Calcula la suma de los números pares e impares entre 1 y 100 por separado.  

15. **Adivinar número**  
    Genera un número aleatorio entre 1 y 20.  
    Pide al usuario que lo adivine y usa un bucle con condicionales para dar pistas: `"Mayor"` o `"Menor"`.  

---

### 4️⃣ Construcción de Algorítmicos

16. **Número perfecto**  
    Comprueba si un número es perfecto (la suma de sus divisores propios es igual al número).  

17. **Invertir número**  
    Escribe un algoritmo que invierta los dígitos de un número (ejemplo: `123 → 321`).  

18. **Palíndromo**  
    Comprueba si una palabra almacenada en una variable es palíndroma.  

19. **Máximo común divisor (MCD)**  
    Escribe un algoritmo que calcule el MCD de dos números.  

20. **Triángulo de asteriscos**  
    Muestra en pantalla un triángulo de altura `n` usando `*`.  
    Ejemplo con `n = 5`:  
    ```
    *
    **
    ***
    ****
    *****
    ```

---

## Licencia 📄

Este proyecto está bajo la Licencia (Apache 2.0) - mira el archivo [LICENSE.md]([../../../LICENSE.md](https://github.com/jpexposito/code-learn-practice/blob/main/LICENSE)) para detalles.
