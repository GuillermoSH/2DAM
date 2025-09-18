<?php
    $word = "reconocer";
    $inverted = "";

    for ($i = strlen($word) - 1; $i >= 0; $i--) {
        $inverted = $inverted . substr($word, $i, 1);
    }

    if ($word === $inverted) {
        echo "'$word' es palíndroma.";
    } else {
        echo "'$word' no es palíndroma.";
    }
?>
