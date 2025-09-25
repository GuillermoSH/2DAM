<?php
    file_put_contents("diario.txt", "[2025-09-24 10:00] Hoy aprendi PHP con ficheros 😄" . PHP_EOL . "[2025-09-24 11:00] Almorce piza mientras programaba." . PHP_EOL);
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiento contenido:\n";
    echo file_get_contents("diario.txt");
?>