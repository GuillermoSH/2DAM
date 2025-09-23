<?php
declare(strict_types=1);

/**
 * Funcion para evaluar si el numero es Fizz o Buzz dependiendo de las entradas
 * @return string con el valor
 */
function evaluate(int $n, int $a, int $b): string {
    if ($n%$a==0 && $n%$b==0) {
        return "'FizzBuzz'";
    } elseif ($n%$a==0) {
        return "'Fizz'";
    } elseif ($n%$b==0) {
        return "'Buzz'";
    } else {
        return (string) $n;
    }
}

/**
 * Funcion para llamar a la evaluacion de los numeros y construir
 * un array con la salida del programa
 * @return array con la evaluacion de cada numero
 */
function fizzBuzz(int $n, int $a, int $b): array {
    $fizzBuzz = array();

    for ($i = 1; $i <= $n; $i++) {
        array_push($fizzBuzz, evaluate($i, $a, $b));
    }

    return $fizzBuzz;
}

printf("[%s]", join(", ", fizzBuzz(15,3,5)));
?>