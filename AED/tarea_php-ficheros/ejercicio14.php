<?php
    $excuses = [
        "Mi perro se comió la tarea.",
        "El Wi-Fi decidió tomarse el día libre.",
        "Me abdujeron los marcianos.",
    ];

    file_put_contents("excuses.txt", implode(PHP_EOL, $excuses));
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando una excusa...\n";
    $excuses = file("excuses.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $randomExcuse = $excuses[array_rand($excuses)];
    echo "La excusa seleccionada es: " . $randomExcuse . "\n";