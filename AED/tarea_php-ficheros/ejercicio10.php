<?php
    $json = json_encode("[{'nombre': 'Ana', 'edad': 25},{'nombre': 'Luis', 'edad': 30}]");
    file_put_contents("datos.json", $json);
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiento contenido:\n";
    $data = json_decode(file_get_contents("datos.json"), true);
    print_r($data);
?>