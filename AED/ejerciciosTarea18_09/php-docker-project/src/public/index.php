<?php
    declare(strict_types=1);
    function secuenciaCollatz(int $n): array {
        $sequence = array($n);

        while ($n > 1) {
            if ($n % 2 == 0) {
                $n /= 2;
            } else {
                $n = $n * 3 + 1;
            }
            array_push($sequence, $n);
        }

        return $sequence;
    }

    echo join(" => ", secuenciaCollatz(6));
?>