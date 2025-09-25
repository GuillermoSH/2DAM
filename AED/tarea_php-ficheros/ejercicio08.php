<?php
    $tabla = "";
    
    echo "[Info] Generando tabla de multiplicar..." . "\n";
    for($i=1; $i<=10; $i++) {
        $tabla .= "5 x " . $i . " = " . $i*5 . "\n";
    }

    file_put_contents("tabla5.txt", $tabla);
    echo "[Info] Leyendo el contenido del fichero:" . "\n";
    print_r(file_get_contents("tabla5.txt"));
?>