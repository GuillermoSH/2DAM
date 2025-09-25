<?php
    $words = [
        "programador",
        "computadora",
        "teclado",
        "ratón",
        "monitor",
        "impresora",
        "tarjeta gráfica",
        "tarjeta de sonido",
        "procesador",
        "RAM",
        "disco duro",
        "sistema operativo",
        "redes",
        "cable",
        "switch",
        "router",
        "modem",
        "firewall",
        "antivirus"
    ];

    file_put_contents("palabras.txt", implode(PHP_EOL, $words));

    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando una palabra...\n";

    $words = file("palabras.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    
    echo "[Info] Eligiendo 2 letras de la palabra...\n";
    
    $randomWord = $words[array_rand($words)];
    $letters = substr($randomWord, 0, 2);

    echo "[Info] Las 2 letras seleccionadas son: \n" . $letters . "\n";
    echo "[Info] Adivina la palabra...\n";

    $guess = readline("Ingresa tu adivinanza: ");

    while (strtolower($guess) !== strtolower($randomWord)) {
        echo "[Info] ¡Has fallado! Intenta de nuevo...\n";
        $guess = readline("Ingresa tu adivinanza: ");
    }
    
    echo "[Info] ¡Has adivinado la palabra!\n";
?>