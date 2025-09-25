<?php
    $adjectives = [
        "Increible",
        "Rapido",
        "Misterioso"
    ];

    $animals = [
        "Tigre",
        "Águila",
        "Lobo"
    ];

    file_put_contents("adjetivos.txt", implode(PHP_EOL, $adjectives));
    file_put_contents("animales.txt", implode(PHP_EOL, $animals));

    $adjectives = file("adjetivos.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $animals = file("animales.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

    $randomAdjective = $adjectives[array_rand($adjectives)];
    $randomAnimal = $animals[array_rand($animals)];
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiendo nombre de superheroe:\n";
    echo $randomAdjective . " " . $randomAnimal . "\n";
?>