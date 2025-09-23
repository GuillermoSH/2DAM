<?php
declare(strict_types=1);

/**
 * Funcion para imprimir una piramide de altura N
 */
function printPyramid(int $n):void {
    for ($i=1, $j=0; $i<=$n;$i++) {
        printf("%s%s\n", str_repeat(" ", $n - $i), str_repeat("*", $i + $j));
        $j++;
    }
}

printPyramid(10);
?>