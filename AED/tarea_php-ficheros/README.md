# <img src=../../../../../images/computer.png width="40"> Code, Learn & Practice(Acceso a Datos: "Ficheros")

## ✅ Preparación

```bash
php -v
```

Ejecuta:

```bash
php ejercicioX.php
```

Crea un fichero por ejercicio: `ejercicio01.php` … `ejercicio20.php`.

---

### 1) Hola fichero (crear y leer)

**Enunciado:** Crea un fichero `datos.txt` con el texto *"Hola Mundo desde PHP"* y, a continuación, léelo y muestra su contenido por pantalla.  
**Funciones/Comandos sugeridos:** `file_put_contents`, `file_get_contents`  

```php
<?php
    file_put_contents("datos.txt", "Hola Mundo desde PHP");
    echo file_get_contents("datos.txt");
?>
```

---

### 2) Guardar números en un fichero

**Enunciado:** Almacena en `numeros.txt` los números del 1 al 10 (cada número en una línea). Luego léelo y muestra todos los números.  
**Funciones sugeridas:** `fopen`, `fwrite`, `fclose`, `file`  

```php
<?php
    $file = fopen("numeros.txt", "w");
    $i=1;
    while($i <= 10) {
        fwrite($file, $i . PHP_EOL);
        $i++;
    }
    fclose($file);

    echo file_get_contents("numeros.txt");
?>
```

---

### 3) Contar palabras en un fichero

**Enunciado:** Escribe un texto en `texto.txt`, luego haz que tu programa cuente cuántas palabras contiene ese archivo.  
**Funciones sugeridas:** `file_get_contents`, `str_word_count`  

```php
<?php
    file_put_contents("texto.txt", "PHP es muy divertido y potente");
    echo "Numero de palabras: " . str_word_count(file_get_contents("texto.txt")) . "\n";
?>
```

---

### 4) Escribir y leer array en fichero

**Enunciado:** Guarda un array de nombres en `nombres.txt` (uno por línea). Después, léelo y muéstralos en lista numerada.  
**Funciones sugeridas:** `fopen`, `fwrite`, `fgets`  

```php
<?php
    $nombres = array("Ana", "Luis", "Marta", "Carlos", "Julia");
    $file = fopen("nombres.txt", "w");

    for($i=0; $i<count($nombres); $i++) {
        fwrite($file, $nombres[$i] . PHP_EOL);
    }

    $fileNombres = file("nombres.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    
    fclose($file);
    
    print_r($fileNombres);
?>
```

---

### 5) Copiar contenido de un fichero a otro

**Enunciado:** Copia el contenido de `origen.txt` en un archivo `copia.txt`.  
**Funciones sugeridas:** `copy`, `file_get_contents`, `file_put_contents`  

```php
<?php
    file_put_contents("origen.txt", "Este es el archivo original");
    copy("origen.txt", "copia.txt");
    echo "[Info] El archivo ha sido copiado...\n";
    echo "[Info] Imprimiento contenido:\n";
    echo file_get_contents("copia.txt") . "\n";
?>
```

---

### 6) Invertir el contenido de un fichero

**Enunciado:** Lee `frase.txt`, invierte el texto y guarda el resultado en `frase_invertida.txt`.  
**Funciones sugeridas:** `file_get_contents`, `strrev`, `file_put_contents`  

```php
<?php
    file_put_contents("frase.txt", "Hola PHP");
    $strInverted = strrev(file_get_contents("frase.txt"));
    echo "[Info] Invirtiendo el contenido del fichero..." . "\n";
    file_put_contents("frase_invertida.txt", $strInverted);
    echo "[Info] El archivo ha sido invertido\n";
    echo "[Info] Imprimiento contenido:\n";
    echo file_get_contents("frase_invertida.txt") . "\n";
?>
```

---

### 7) Calcular suma desde fichero

**Enunciado:** Guarda números separados por comas en `datos_numericos.txt`. Léelos y calcula su suma.  
**Funciones sugeridas:** `file_get_contents`, `explode`, `array_sum`  

```php
<?php
    $numbers = [1, 20, 12, 31, 67, 4];
    file_put_contents("datos_numericos.txt", implode(",", $numbers));

    echo "[Info] Leyendo el contenido del fichero..." . "\n";
    $data = file_get_contents("datos_numericos.txt");
    $sum = array_sum(explode(",", $data));
    echo "[Info] La suma de los números es: $sum\n";
?>
```

---

### 8) Crear fichero de multiplicaciones

**Enunciado:** Genera la tabla del 5 y guárdala en `tabla5.txt`. Luego muéstrala.  
**Funciones sugeridas:** `fopen`, `fwrite`, `file`  

```php
<?php
    $tabla = "";
    
    echo "[Info] Generando tabla de multiplicar..." . "\n";
    for($i=1; $i<=10; $i++) {
        $tabla .= "5 x " . $i . " = " . $i*5 . "\n";
    }

    file_put_contents("tabla5.txt", $tabla);
    echo "[Info] Leyendo el contenido del fichero:" . "\n";
    print_r(file_get_contents("tabla5.txt"));
?>
```

---

### 9) Registrar entradas de usuario

**Enunciado:** Guarda los nombres recibidos en un formulario dentro de `usuarios.txt` (cada nombre en una línea). Luego muéstralos.  
**Funciones sugeridas:** `fopen`, `fwrite`, `file`  

```php
<?php
    $nombres = array("Ana", "Pedro", "Lucia");
    $file = fopen("usuarios.txt", "w");

    for($i=0; $i<count($nombres); $i++) {
        fwrite($file, $nombres[$i] . PHP_EOL);
    }

    $fileNombres = file("usuarios.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    
    fclose($file);
    
    print_r($fileNombres);
?>
```

---

### 10) Leer JSON desde fichero

**Enunciado:** Crea `datos.json` con información de personas (nombre y edad). Haz que el programa lo lea y muestre los datos.  
**Funciones sugeridas:** `file_get_contents`, `json_decode`  

```php
<?php
    $json = json_encode("[{'nombre': 'Ana', 'edad': 25},{'nombre': 'Luis', 'edad': 30}]");
    file_put_contents("datos.json", $json);
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiento contenido:\n";
    $data = json_decode(file_get_contents("datos.json"), true);
    print_r($data);
?>
```

---

### 11) Diario personal secreto

**Enunciado:** Guarda entradas con fecha y hora en `diario.txt`. Luego muéstralas todas.  

```php
<?php
    file_put_contents("diario.txt", "[2025-09-24 10:00] Hoy aprendi PHP con ficheros 😄" . PHP_EOL . "[2025-09-24 11:00] Almorce piza mientras programaba." . PHP_EOL);
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiento contenido:\n";
    echo file_get_contents("diario.txt");
?>
```

---

### 12) Ranking de videojuegos

**Enunciado:** Guarda juegos con puntuaciones en `ranking.txt`, ordénalos y muestra el top 3.  

```php
<?php
    $ranking = [
        "Sonic: 8",
        "Mario: 5",
        "Zelda: 10",
        "Pokemon: 7"
    ];

    file_put_contents("ranking.txt", implode(PHP_EOL, $ranking));
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiento contenido:\n";

    $ranking = file("ranking.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    
    usort($ranking, function($a, $b) {
        $a_score = (int)explode(": ", $a)[1];
        $b_score = (int)explode(": ", $b)[1];
        return $b_score <=> $a_score;
    });
    
    $topThree = array_slice($ranking, 0, 3);

    echo implode(PHP_EOL, $topThree) . "\n";
?>
```

---

### 13) Canción aleatoria

**Enunciado:** Guarda títulos en `canciones.txt` y muestra uno al azar.  

```php
<?php
    $songs = [
        "Hysteria",
        "Bohemian Rhapsody",
        "Africa"
    ];

    file_put_contents("canciones.txt", implode(PHP_EOL, $songs));
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando una cancion...\n";
    $songs = file("canciones.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $randomSong = $songs[array_rand($songs)];
    echo "La cancion seleccionada es: " . $randomSong . "\n";
?>
```

---

### 14) Generador de excusas divertidas

**Enunciado:** Lee excusas desde `excusas.txt` y muestra una aleatoria.  

```php
<?php
    $excuses = [
        "Mi perro se comió la tarea.",
        "El Wi-Fi decidió tomarse el día libre.",
        "Me abdujeron los marcianos.",
    ];

    file_put_contents("excuses.txt", implode(PHP_EOL, $excuses));
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando una excusa...\n";
    $excuses = file("excuses.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $randomExcuse = $excuses[array_rand($excuses)];
    echo "La excusa seleccionada es: " . $randomExcuse . "\n";
?>
```

---

### 15) Lista de chistes (rotativos)

**Enunciado:** Muestra un chiste distinto en cada ejecución desde `chistes.txt`.  

```php
<?php
    $chistes = [
        "¿Sabes cuál es el colmo de un programador? Tener mala RAM-oria.",
        "Ayer vi un bit triste… estaba des-bit-ido.",
        "Yo no sudo, compilo."
    ];

    file_put_contents("chistes.txt", implode(PHP_EOL, $chistes));

    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando un chiste...\n";
    $chistes = file("chistes.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $randomChiste = $chistes[array_rand($chistes)];
    echo "El chiste seleccionado es: \n" . $randomChiste . "\n";
?>
```

---

### 16) Adivina la palabra

**Enunciado:** Elige una palabra de `palabras.txt` y muestra solo 2 letras, el usuario debe adivinarla.  

```php
<?php
    $words = [
        "programador",
        "computadora",
        "teclado",
        "ratón",
        "monitor",
        "impresora",
        "tarjeta gráfica",
        "tarjeta de sonido",
        "procesador",
        "RAM",
        "disco duro",
        "sistema operativo",
        "redes",
        "cable",
        "switch",
        "router",
        "modem",
        "firewall",
        "antivirus"
    ];

    file_put_contents("palabras.txt", implode(PHP_EOL, $words));

    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Seleccionando una palabra...\n";

    $words = file("palabras.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    
    echo "[Info] Eligiendo 2 letras de la palabra...\n";
    
    $randomWord = $words[array_rand($words)];
    $letters = substr($randomWord, 0, 2);

    echo "[Info] Las 2 letras seleccionadas son: \n" . $letters . "\n";
    echo "[Info] Adivina la palabra...\n";

    $guess = readline("Ingresa tu adivinanza: ");
?>
```

---

### 17) Generador de nombres para superhéroes

**Enunciado:** Combina palabras de `adjetivos.txt` y `animales.txt`.  

```php
<?php
    $adjectives = [
        "Increible",
        "Rapido",
        "Misterioso"
    ];

    $animals = [
        "Tigre",
        "Águila",
        "Lobo"
    ];

    file_put_contents("adjetivos.txt", implode(PHP_EOL, $adjectives));
    file_put_contents("animales.txt", implode(PHP_EOL, $animals));

    $adjectives = file("adjetivos.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $animals = file("animales.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

    $randomAdjective = $adjectives[array_rand($adjectives)];
    $randomAnimal = $animals[array_rand($animals)];
    
    echo "[Info] El archivo ha sido guardado...\n";
    echo "[Info] Imprimiendo nombre de superheroe:\n";
    echo $randomAdjective . " " . $randomAnimal . "\n";
?>
```

---

### 18) Encuesta de comidas favoritas

**Enunciado:** Guarda respuestas de usuarios en `comidas.txt` y genera ranking.  

```php
<?php
$respuestas = ["pizza", "sushi", "pizza", "pasta", "tacos", "pizza", "sushi"];

file_put_contents("comidas.txt", implode(PHP_EOL, $respuestas));

$comidas = file("comidas.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

$conteo = array_count_values($comidas);

arsort($conteo);

echo "[Info] Ranking de comidas favoritas:\n";
$pos = 1;
foreach ($conteo as $comida => $veces) {
    echo "$pos. $comida ($veces votos)\n";
    $pos++;
}
?>
```

---

### 19) Simulador de tweets

**Enunciado:** Guarda tweets en `tweets.txt` con fecha y hora, muestra los últimos 5.  

```php
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
?>
```

---

### 20) Historias locas (Mad Libs)

**Enunciado:** Reemplaza placeholders en `plantilla.txt` con palabras aleatorias de otros ficheros.  

```php
<?php
file_put_contents("plantilla.txt", "Un [animal] viajó a [lugar] para comer [comida].");
$lugares = ["paris", "londres", "berlín", "madrid", "roma"];
file_put_contents("lugares.txt", implode(PHP_EOL, $lugares));
$plantilla = file_get_contents("plantilla.txt");

$animales  = file("animales.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
$lugares   = file("lugares.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
$comidas   = file("comidas.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

$animal = strtolower($animales[array_rand($animales)]);
$lugar  = ucfirst(strtolower($lugares[array_rand($lugares)]));
$comida = strtolower($comidas[array_rand($comidas)]);

$historia = str_replace(
    ["[animal]", "[lugar]", "[comida]"],
    [$animal, $lugar, $comida],
    $plantilla
);

echo "Historia generada:\n";
echo $historia . "\n";
?>
```

---

## Licencia 📄

Este proyecto está bajo la Licencia (Apache 2.0) - mira el archivo [LICENSE.md]([../../../LICENSE.md](https://github.com/jpexposito/code-learn-practice/blob/main/LICENSE)) para detalles.
