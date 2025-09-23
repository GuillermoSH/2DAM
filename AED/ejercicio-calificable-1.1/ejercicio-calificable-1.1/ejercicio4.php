<?php
declare(strict_types=1);
/**
 * Funcion para evaluar un array y devolver su min, max y suma
 * @return array
 */
function statisticCalcs(array $numbers): array {
    $min = $numbers[0];
    $max = $numbers[0];
    $sum = array_sum($numbers);

    for ($i=1; $i<count($numbers); $i++) {
        if ($min > $numbers[$i]) {
            $min = $numbers[$i];
        }

        if ($max < $numbers[$i]) {
            $max = $numbers[$i];
        }
    }

    return [$min, $max, $sum, ($sum/count($numbers))];
}

$numbers = [5,2,9];
printf("[%s]\n", join(",", statisticCalcs($numbers)));
$numbers = [10,-3,7,0];
printf("[%s]\n", join(",", statisticCalcs($numbers)));
?>