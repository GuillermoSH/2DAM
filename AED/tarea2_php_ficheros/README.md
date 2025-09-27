# <img src=../../../../../images/computer.png width="40"> Code, Learn & Practice(Acceso a Datos: "Ficheros")

## ✅ Preparación

```bash
php -v
```

Ejecuta:

```bash
php ejercicioX.php
```

## Ejercicio1: Operaciones con números naturales en PHP

### Enunciado

Dado un fichero `ops.csv` con columnas:

```code
z1,z2,op
```

donde:

- `op ∈ {suma, resta, producto, division}`  

Se debe calcular `z1 (op) z2` y generar como salida un fichero `resultado.csv`.

---

## Entrada (ejemplo)

Archivo: `ops.csv`

```csv
3,1,suma
5,2,producto
```

---

## Salida esperada

Archivo: `resultado.csv`

```csv
z1,z2,op,resultado
3,1,suma,4
5,2,producto,10
```

- `resultado` → número natural resultante de la operación.  
- Si ocurre una **división por 0**, escribir `ERROR` en la columna `resultado`.

---

## Archivos esperados

- **Entrada:** `ops.csv`  
- **Salida:** `resultado.csv`  

---

## Codigo PHP

```php
<?php
$inputFile = 'ops.csv';
$outputFile = 'resultado.csv';

if (!file_exists($inputFile)) {
    echo "[ERROR] No se encuentra el archivo ops.csv\n";
    exit(1);
}

$input = fopen($inputFile, 'r');
$output = fopen($outputFile, 'w');

fputcsv($output, ['z1', 'z2', 'op', 'resultado']);

while (($row = fgetcsv($input)) !== false) {
    $z1 = (int)$row[0];
    $z2 = (int)$row[1];
    $op = strtolower($row[2]);
    $resultado = '';

    switch ($op) {
        case 'suma':
            $resultado = $z1 + $z2;
            break;
        case 'resta':
            $resultado = $z1 - $z2;
            break;
        case 'producto':
            $resultado = $z1 * $z2;
            break;
        case 'division':
            if ($z2 == 0) {
                $resultado = 'ERROR';
            } else {
                $resultado = $z1 / $z2;
            }
            break;
        default:
            $resultado = 'ERROR';
    }
    fputcsv($output, [$z1, $z2, $op, $resultado]);
    
    echo "$z1,$z2,$op,$resultado\n";
}

fclose($input);
fclose($output);

echo "\n[INFO] Archivo resultado.csv generado correctamente.\n";
?>
```

## Ejercicio 2: Estadísticas de palabras en PHP

### Enunciado

Dado un fichero `texto.txt`, contar cuántas palabras hay en total y cuántas veces aparece cada palabra.  

La salida se debe guardar en `estadisticas.csv` con el formato:

```code
palabra,frecuencia
```

### Reglas:

- Ignorar mayúsculas/minúsculas (ejemplo: `PHP` y `php` cuentan como la misma palabra).  
- Ignorar signos de puntuación.  

---

## Archivos de ejemplo

### Entrada (`texto.txt`)

```code
Zeus gobernaba desde el Olimpo, lanzando rayos y truenos. 
Atenea, diosa de la sabiduría, protegía a los héroes. 
Hércules realizaba sus doce trabajos, mientras Poseidón agitaba los mares con su tridente. 
Hades reinaba en el inframundo, y Afrodita inspiraba el amor entre los mortales.
```

### Salida esperada (`estadisticas.csv`)

```code
palabra,frecuencia
zeus,1
gobernaba,1
desde,1
el,3
olimpo,1
lanzando,1
rayos,1
y,2
truenos,1
atenea,1
diosa,1
de,1
la,1
sabiduría,1
protegía,1
a,1
los,3
héroes,1
hércules,1
realizaba,1
sus,1
doce,1
trabajos,1
mientras,1
poseidón,1
agitaba,1
mares,1
con,1
su,1
tridente,1
hades,1
reinaba,1
en,1
inframundo,1
afrodita,1
inspiraba,1
amor,1
entre,1
mortales,1
```

### Codigo PHP

```php
<?php
$inputFile = 'texto.txt';
$outputFile = 'estadisticas.csv';

if (!file_exists($inputFile)) {
    echo "[ERROR] No se encuentra el archivo texto.txt\n";
    exit(1);
}

$texto = file_get_contents($inputFile);
$texto = strtolower($texto);

// Eliminar todo menos letras(\p{L}), numeros(\p{N}) y espacios(\s). /u es para Unicode
$texto = preg_replace('/[^\p{L}\p{N}\s]/u', '', $texto);

// Separar palabras por uno o mas espacios
$palabras = preg_split('/\s+/', $texto, -1, PREG_SPLIT_NO_EMPTY);

$frecuencias = array_count_values($palabras);

$output = fopen($outputFile, 'w');
fputcsv($output, ['palabra', 'frecuencia']);
foreach ($frecuencias as $palabra => $frecuencia) {
    fputcsv($output, [$palabra, $frecuencia]);
    echo "$palabra,$frecuencia\n";
}
fclose($output);

echo "\n[INFO] Archivo estadisticas.csv generado correctamente.\n";
?>
```
