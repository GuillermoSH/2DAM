<?php
    file_put_contents("frase.txt", "Hola PHP");
    $strInverted = strrev(file_get_contents("frase.txt"));
    echo "[Info] Invirtiendo el contenido del fichero..." . "\n";
    file_put_contents("frase_invertida.txt", $strInverted);
    echo "[Info] El archivo ha sido invertido\n";
    echo "[Info] Imprimiento contenido:\n";
    echo file_get_contents("frase_invertida.txt") . "\n";
?>