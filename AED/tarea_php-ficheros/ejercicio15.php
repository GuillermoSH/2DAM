<?php
    $chistes = [
        "¿Sabes cuál es el colmo de un programador? Tener mala RAM-oria.",
        "Ayer vi un bit triste… estaba des-bit-ido.",
        "Yo no sudo, compilo."
    ];

    file_put_contents("chistes.txt", implode(PHP_EOL, $chistes));

    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando un chiste...\n";
    $chistes = file("chistes.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $randomChiste = $chistes[array_rand($chistes)];
    echo "El chiste seleccionado es: \n" . $randomChiste . "\n";
?>