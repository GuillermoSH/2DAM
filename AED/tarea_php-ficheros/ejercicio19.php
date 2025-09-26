<?php
$fichero = "tweets.txt";
$dateFormat = "Y-m-d H:i";
$tweets = [
    "[" . date($dateFormat) . "] Aprendiendo PHP con ejercicios divertidos #php",
    "[" . date($dateFormat, strtotime("+1 minute")) . "] Otro día más programando 🚀",
    "[" . date($dateFormat, strtotime("+2 minutes")) . "] Probando un simulador de tweets 📱",
    "[" . date($dateFormat, strtotime("+3 minutes")) . "] Me encanta practicar con PHP 😎",
    "[" . date($dateFormat, strtotime("+4 minutes")) . "] Último tweet de ejemplo ✨",
    "[" . date($dateFormat, strtotime("+5 minutes")) . "] Bonus tweet para ver cómo se limita a 5 🔥"
];

file_put_contents($fichero, implode(PHP_EOL, $tweets));

$contenido = file($fichero, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

$ultimos = array_slice($contenido, -5);

echo "[Info] Últimos 5 tweets:\n";
foreach ($ultimos as $tweet) {
    echo $tweet . "\n";
}
