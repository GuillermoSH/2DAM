<?php
    $num = 12;
    $sum = 0;

    for ($i = 1; $i < $num; $i++) {
        if ($num % $i == 0) {
            $sum += $i;
        }
    }

    if ($sum == $num) {
        echo "$num es PERFECTO.";
    } else {
        echo "$num no es perfecto.";
    }
?>
