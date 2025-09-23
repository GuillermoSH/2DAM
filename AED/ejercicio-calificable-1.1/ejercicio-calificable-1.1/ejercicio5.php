<?php
declare(strict_types=1);
function stringCount(string $phrase) {
    $strJustLetters = str_replace([" ", ",", "!", "¡"], "", $phrase);
    $consonants= strlen(str_replace(["a","e","i","o","u", " "], "", $strJustLetters));
    $vocals= abs($consonants - strlen($strJustLetters));

    return [$consonants, $vocals];
}

$result = stringCount("Hola, mundo!");
printf("['letras'] => %s, 'vocales' => %s]", $result[0], $result[1]);
?>