<?php
file_put_contents("plantilla.txt", "Un [animal] viajó a [lugar] para comer [comida].");
$lugares = ["paris", "londres", "berlín", "madrid", "roma"];
file_put_contents("lugares.txt", implode(PHP_EOL, $lugares));
$plantilla = file_get_contents("plantilla.txt");

$animales  = file("animales.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
$lugares   = file("lugares.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
$comidas   = file("comidas.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

$animal = strtolower($animales[array_rand($animales)]);
$lugar  = ucfirst(strtolower($lugares[array_rand($lugares)]));
$comida = strtolower($comidas[array_rand($comidas)]);

$historia = str_replace(
    ["[animal]", "[lugar]", "[comida]"],
    [$animal, $lugar, $comida],
    $plantilla
);

echo "Historia generada:\n";
echo $historia . "\n";
