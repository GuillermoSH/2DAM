<?php
$respuestas = ["pizza", "sushi", "pizza", "pasta", "tacos", "pizza", "sushi"];

file_put_contents("comidas.txt", implode(PHP_EOL, $respuestas));

$comidas = file("comidas.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

$conteo = array_count_values($comidas);

arsort($conteo);

echo "[Info] Ranking de comidas favoritas:\n";
$pos = 1;
foreach ($conteo as $comida => $veces) {
    echo "$pos. $comida ($veces votos)\n";
    $pos++;
}
?>