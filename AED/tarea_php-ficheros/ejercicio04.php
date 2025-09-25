<?php
    $nombres = array("Ana", "Luis", "Marta", "Carlos", "Julia");
    $file = fopen("nombres.txt", "w");

    for($i=0; $i<count($nombres); $i++) {
        fwrite($file, $nombres[$i] . PHP_EOL);
    }

    $fileNombres = file("nombres.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    
    fclose($file);
    
    print_r($fileNombres);
?>