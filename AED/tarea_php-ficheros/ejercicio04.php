<?php
    $nombres = array("Ana", "Luis", "Marta", "Carlos", "Julia");
    $file = fopen("nombres.txt", "rw");

    for($i=0; $i<=count($nombres); $i++) {
        fwrite($file, $nombres[$i] . PHP_EOL);
    }

    $fileNombres = [];
    
    
    array_push($fileNombres, fgets($file));
    

    fclose($file);
    
    var_dump($fileNombres);
?>