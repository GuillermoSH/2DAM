<?php
    $file = fopen("numeros.txt", "w");
    $i=1;
    while($i <= 10) {
        fwrite($file, $i . PHP_EOL);
        $i++;
    }
    fclose($file);

    echo file_get_contents("numeros.txt");
?>