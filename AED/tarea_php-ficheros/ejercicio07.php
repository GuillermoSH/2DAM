<?php
    $numbers = [1, 20, 12, 31, 67, 4];
    file_put_contents("datos_numericos.txt", implode(",", $numbers));

    echo "[Info] Leyendo el contenido del fichero..." . "\n";
    $data = file_get_contents("datos_numericos.txt");
    $sum = array_sum(explode(",", $data));
    echo "[Info] La suma de los números es: $sum\n";
?>