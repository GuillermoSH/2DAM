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

## Ejemplo de ejecución

```bash
php ejercicio1.php
```

### ops.csv

```code
z1,z2,op
3,1,suma
10,4,resta
2,8,resta
5,2,producto
7,0,producto
9,3,division
10,3,division
8,0,division
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

### Entrada (`texto.txt`)

```code
PHP es divertido. PHP es potente y divertido.
```

### Salida esperada (`estadisticas.csv`)

```code
palabra,frecuencia
php,2
es,2
divertido,2
potente,1
y,1
```


## Referencias

- https://doc.php.net/archives/php5/php_manual_en.tar.gz

## Licencia 📄

Este proyecto está bajo la Licencia (Apache 2.0) - mira el archivo [LICENSE.md]([../../../LICENSE.md](https://github.com/jpexposito/code-learn-practice/blob/main/LICENSE)) para detalles.