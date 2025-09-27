<?php
$inputFile = 'texto.txt';
$outputFile = 'estadisticas.csv';

if (!file_exists($inputFile)) {
    echo "[ERROR] No se encuentra el archivo texto.txt\n";
    exit(1);
}

$texto = file_get_contents($inputFile);
$texto = strtolower($texto);

// Eliminar todo menos letras(\p{L}), numeros(\p{N}) y espacios(\s). /u es para Unicode
$texto = preg_replace('/[^\p{L}\p{N}\s]/u', '', $texto);

// Separar palabras por uno o mas espacios
$palabras = preg_split('/\s+/', $texto, -1, PREG_SPLIT_NO_EMPTY);

$frecuencias = array_count_values($palabras);

$output = fopen($outputFile, 'w');
fputcsv($output, ['palabra', 'frecuencia']);
foreach ($frecuencias as $palabra => $frecuencia) {
    fputcsv($output, [$palabra, $frecuencia]);
    echo "$palabra,$frecuencia\n";
}
fclose($output);

echo "\n[INFO] Archivo estadisticas.csv generado correctamente.\n";
?>
