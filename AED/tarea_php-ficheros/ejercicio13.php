<?php
    $songs = [
        "Hysteria",
        "Bohemian Rhapsody",
        "Africa"
    ];

    file_put_contents("canciones.txt", implode(PHP_EOL, $songs));
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando una cancion...\n";
    $songs = file("canciones.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $randomSong = $songs[array_rand($songs)];
    echo "La cancion seleccionada es: " . $randomSong . "\n";
?>