# <img src=../../../../images/computer.png width="40"> Code, Learn & Practice("Introducción a Php, uso de funciones")

## Número capicúa (palíndromo numérico)

```php
<?php
    declare(strict_types=1);

    function esCapicua(int $n): bool {
        return $n == intval(strrev((string) $n));
    }
    
    
    if (esCapicua(1234321)) {
        echo "Es capicua";
    } else {
        echo "No es capicua";
    }
?>
```

## Escalera de asteriscos

```php
<?php
    declare(strict_types=1);

    function montañaAsteriscos(int $n, int $m): void {
        for ($i=1; $i <= $n; $i++) {
            if ($i < $n) {
                printf("%s%s%s</br>", str_repeat("*", $i), str_repeat("&nbsp;&nbsp;", $n*$m - $i*2), str_repeat("*", $i));
            } else {
                printf("%s", str_repeat("*", $n*$m));
            }
        }
    }

    montañaAsteriscos(4,2);
?>
```

## Suma de dígitos

```php
<?php
    declare(strict_types=1);
    function sumaDigitos(int $n): int {
        $deconstructedStr = mb_str_split(strval($n));
        $sum = 0;

        foreach($deconstructedStr as $number) {
            $sum += intval($number);
        }

        return $sum;
    }

    echo sumaDigitos(2025);
?>
```

## Número secreto (múltiplos de 3 o 5)

```php
<?php
    declare(strict_types=1);
    $sumaMultiplos = 0;

    function multiplosTresOCinco(int $n): array {
        $multiplos = array();
        for ($i = 3; $i < $n; $i++) {
            if ($i % 3 == 0 || $i % 5 == 0) {
                array_push($multiplos, $i);
                $GLOBALS["sumaMultiplos"]+=$i;
            }
        }

        return $multiplos;
    }

    echo join(", ", multiplosTresOCinco(10));
    echo "Suma = $sumaMultiplos";
?>
```

## Secuencia de Collatz

```php
<?php
    declare(strict_types=1);
    function secuenciaCollatz(int $n): array {
        $sequence = array($n);

        while ($n > 1) {
            if ($n % 2 == 0) {
                $n /= 2;
            } else {
                $n = $n * 3 + 1;
            }
            array_push($sequence, $n);
        }

        return $sequence;
    }

    echo join(" => ", secuenciaCollatz(0));
?>
```