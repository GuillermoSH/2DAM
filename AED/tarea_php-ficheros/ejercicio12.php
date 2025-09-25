<?php
    $ranking = [
        "Sonic: 8",
        "Mario: 5",
        "Zelda: 10",
        "Pokemon: 7"
    ];

    file_put_contents("ranking.txt", implode(PHP_EOL, $ranking));
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiento contenido:\n";

    $ranking = file("ranking.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    
    usort($ranking, function($a, $b) {
        $a_score = (int)explode(": ", $a)[1];
        $b_score = (int)explode(": ", $b)[1];
        return $b_score <=> $a_score;
    });
    
    $topThree = array_slice($ranking, 0, 3);

    echo implode(PHP_EOL, $topThree) . "\n";
?>