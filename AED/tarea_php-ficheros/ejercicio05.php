<?php
    file_put_contents("origen.txt", "Este es el archivo original");
    copy("origen.txt", "copia.txt");
    echo "[Info] El archivo ha sido copiado...\n";
    echo "[Info] Imprimiento contenido:\n";
    echo file_get_contents("copia.txt") . "\n";
?>