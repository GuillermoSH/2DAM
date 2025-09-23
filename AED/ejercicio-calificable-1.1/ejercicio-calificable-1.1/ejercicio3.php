<?php

/**
 * Funcion que elimina los duplicados y devuelve un int 
 * completamente sin duplicados
 * @return int numero sin duplicados
 */
function deleteDuplications(int $n):int {
    $n = array_unique(str_split((string) $n));
    return intval(join($n));
}

echo deleteDuplications(12021203) . "\n";
echo deleteDuplications(1111) . "\n";
echo deleteDuplications(12345) . "\n";
?>