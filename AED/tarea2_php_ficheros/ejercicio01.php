<?php
$inputFile = 'ops.csv';
$outputFile = 'resultado.csv';

if (!file_exists($inputFile)) {
    echo "[ERROR] No se encuentra el archivo ops.csv\n";
    exit(1);
}

$input = fopen($inputFile, 'r');
$output = fopen($outputFile, 'w');

fputcsv($output, ['z1', 'z2', 'op', 'resultado']);

while (($row = fgetcsv($input)) !== false) {
    $z1 = (int)$row[0];
    $z2 = (int)$row[1];
    $op = strtolower($row[2]);
    $resultado = '';

    switch ($op) {
        case 'suma':
            $resultado = $z1 + $z2;
            break;
        case 'resta':
            $resultado = $z1 - $z2;
            break;
        case 'producto':
            $resultado = $z1 * $z2;
            break;
        case 'division':
            if ($z2 == 0) {
                $resultado = 'ERROR';
            } else {
                $resultado = $z1 / $z2;
            }
            break;
        default:
            $resultado = 'ERROR';
    }
    fputcsv($output, [$z1, $z2, $op, $resultado]);
    
    echo "$z1,$z2,$op,$resultado\n";
}

fclose($input);
fclose($output);

echo "\n[INFO] Archivo resultado.csv generado correctamente.\n";
?>