# Code, Learn & Practice(Programación de Servicios y Procesos: "Concurrencia, procesos e hilos")

## Bloque 1: Conceptos básicos (introducción a php)

### Variables y Condicionales

1. **Mayor de dos números**
```php
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
```php
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
```php
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
```php
<?php
    $nota = 7;

    $messageStr = "Sobresaliente";

    if ($nota > 6 && $nota < 9) {
        $messageStr = "Notable";
    }

    if ($nota > 4 && $nota <= 6) {
        $messageStr = "Aprobado";
    }

    if ($nota < 5) {
        $messageStr = "Suspenso";
    }

    echo $messageStr;
?>
```  

---

### 2️⃣ Bucles (for, while, foreach)

5. **Contar del 1 al 100**  
```php
<?php
    for ($i = 1; $i <= 100; $i++) {
        echo $i . "\n";
    }
?>
``` 

6. **Suma acumulada**  
```php
<?php
    $i = 1;
    $sum = $i;

    while ($i <= 50) {
        $sum += $i;
        
        $i++;
    }
    
    echo $sum . "\n";
?>
```

7. **Tabla de multiplicar**  
```php
<?php
    $input = 4;
    
    for ($i = 1; $i <= 10; $i++) {
        echo $input * $i . "\n";
    }
?>
``` 

8. **Números pares**  
```php
<?php
    $i = 1;

    while ($i <= 50) {
        if ($i % 2 == 0) {
            echo $i . "\n";
        }
        $i++;
    }
?>
``` 

9. **Cuenta atrás**  
```php
<?php
    $i = 10;

    do {
        if ($i == 0) {
            echo "Fin!";
        } else {
            echo $i . "\n";
        }
        $i--;
    } while ($i >= 0);
?>
```  

10. **Factorial**  
```php
<?php
    $input = 5;

    $factorial = 1;

    for ($i = 1; $i <= $input; $i++) {
        $factorial *= $i;
    }

    echo "$input! = $factorial";
?>
```

---

### 3️⃣ Combinando Condicionales y Bucles

11. **Números primos**  
```php
<?php
    for ($num = 2; $num <= 50; $num++) {
        $isPrime = true;

        for ($j = 2; $j <= sqrt($num); $j++) {
            if ($num % $j == 0) {
                $isPrime = false;
                break;
            }
        }
        if ($isPrime) {
            echo $num . "\n";
        }
    }
?>
```

12. **Fibonacci**  
```php
<?php
    $fibonacci = [0, 1];

    for ($num = 2; $num < 20; $num++) {
        $fibonacci[$num] = $fibonacci[$num - 1] + $fibonacci[$num - 2];
    }
    
    echo implode(", ", $fibonacci);
?>
```

13. **Múltiplos de un número**  
```php
<?php
    $input = 7;

    for ($num = $input; $num <= 100; $num += $input) {
        echo $num . "\n";
    }
?>
```

14. **Suma de pares e impares**  
```php
<?php
    $oddsSum = 1;
    $evensSum = 2;

    for ($i = 3; $i <= 100; $i++) {
        ($i % 2 == 0) ? $evensSum += $i : $oddsSum += $i;
    }

    echo "La suma de pares es '$evensSum' y la de impares es '$oddsSum'.";
?>
```  

15. **Adivinar número**  
```php
<?php
session_start();

if (!isset($_SESSION["secreto"])) {
    $_SESSION["secreto"] = rand(1, 20);
}

$mensaje = "";

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $intento = intval($_POST["numero"]);

    if ($intento < $_SESSION["secreto"]) {
        $mensaje = "El número secreto es <strong>mayor</strong>.";
    } elseif ($intento > $_SESSION["secreto"]) {
        $mensaje = "El número secreto es <strong>menor</strong>.";
    } else {
        $mensaje = "🎉 ¡Correcto! El número era <strong>{$_SESSION['secreto']}</strong>.";

        unset($_SESSION["secreto"]);
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Adivina el número</title>
</head>
<body>
    <h2>Juego: Adivina el número entre 1 y 20</h2>
    <form method="post" action="">
        <input type="number" name="numero" min="1" max="20" required>
        <button type="submit">Probar</button>
    </form>

    <?php if ($mensaje): ?>
        <p><?= $mensaje ?></p>
    <?php endif; ?>
</body>
</html>
``` 

---

### 4️⃣ Construcción de Algorítmicos

16. **Número perfecto**  
```php
<?php
    $num = 12;
    $sum = 0;

    for ($i = 1; $i < $num; $i++) {
        if ($num % $i == 0) {
            $sum += $i;
        }
    }

    if ($sum == $num) {
        echo "$num es PERFECTO.";
    } else {
        echo "$num no es perfecto.";
    }
?>
``` 

17. **Invertir número**  
```php
<?php
    $num = 12;
    $inverted = 0;

    while ($num > 0) {
        $digit = (int) ($num % 10);
        $inverted = $inverted * 10 + $digit;
        $num = (int) ($num / 10);
    }

    echo "Número invertido: $inverted";
?>
```

18. **Palíndromo**  
```php
<?php
    $word = "reconocer";
    $inverted = "";

    for ($i = strlen($word) - 1; $i >= 0; $i--) {
        $inverted = $inverted . substr($word, $i, 1);
    }

    if ($word === $inverted) {
        echo "'$word' es palíndroma.";
    } else {
        echo "'$word' no es palíndroma.";
    }
?>
```

19. **Máximo común divisor (MCD)**  
```
<?php
    $num1 = 12;
    $num2 = 28;

    while ($num2 != 0) {
        $aux = $num1 % $num2;
        $num1 = $num2;
        $num2 = $aux;
    }

    echo "El MCD es: $num1";
?>
```  

20. **Triángulo de asteriscos**  
```
<?php
    $n = 5;

    for ($i = 1; $i <= $n; $i++) {
        echo str_repeat("*", $i) . "</br>";
    }
?>
```
