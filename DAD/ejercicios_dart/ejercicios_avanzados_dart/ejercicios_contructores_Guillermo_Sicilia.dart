class Pelicula {
    String titulo;
    int anio;

    Pelicula(this.titulo, this.anio);

    @override
    String toString() {
        return 'Título: $titulo, Año: $anio';
    }
}

class Tarea {
    String descripcion;
    bool completada;

    Tarea(this.descripcion, this.completada);

    Tarea.incompleta(String descripcion) : this.descripcion = descripcion, this.completada = false;

    @override
    String toString() {
        return 'Tarea: $descripcion, Completada: $completada';
    }
}

class Circulo {
    double radio;
    double area;

    Circulo(this.radio) : area = 3.14159 * radio * radio;

    @override
    String toString() {
        return 'Círculo con radio $radio y área $area';
    }
}

class Mensaje {
    String texto;
    String prioridad;

    Mensaje({required this.texto, this.prioridad = 'normal'});

    @override
    String toString() {
        return 'Mensaje: $texto, Prioridad: $prioridad';
    }
}

class Moneda {
    final String codigo;
    final String simbolo;

    const Moneda(this.codigo, this.simbolo);

    @override
    String toString() {
        return 'Moneda: $codigo, Símbolo: $simbolo';
    }
}

class Alumno {
    String nombre;
    int nota;

    Alumno(this.nombre, this.nota);

    Alumno.aprobado(this.nombre) : nota = 5;

    @override
    String toString() {
        return 'Alumno: $nombre, Nota: $nota';
    }
}

class Sesion {
    static Sesion? _instancia;

    Sesion._interna();

    factory Sesion() {
        _instancia ??= Sesion._interna();
        return _instancia!;
    }
}

void main() {
    String hr = '-' * 50;
    print("$hr\n[Info] Ejercicio 1: constructor normal\n$hr");
    Pelicula pelicula1 = Pelicula('Inception', 2010);
    Pelicula pelicula2 = Pelicula('The Matrix', 1999);
    print(pelicula1.toString());
    print(pelicula2.toString());

    print("$hr\n[Info] Ejercicio 2: constructor nombrado\n$hr");
    Tarea tarea1 = Tarea('Comprar leche', true);
    Tarea tarea2 = Tarea.incompleta('Estudiar para el examen');
    print(tarea1.toString());
    print(tarea2.toString());

    print("$hr\n[Info] Ejercicio 3: constructor inicializador\n$hr");
    Circulo circulo1 = Circulo(5.0);
    print(circulo1.toString());

    print("$hr\n[Info] Ejercicio 4: constructor con parámetros nombrados\n$hr");
    Mensaje mensaje1 = Mensaje(texto: 'Hola, ¿cómo estás?');
    Mensaje mensaje2 = Mensaje(texto: 'Recordatorio: Entregar el informe', prioridad: 'urgente');
    print(mensaje1.toString());
    print(mensaje2.toString());

    print("$hr\n[Info] Ejercicio 5: constructor const\n$hr");
    const Moneda moneda1 = Moneda('USD', '\$');
    const Moneda moneda2 = Moneda('USD', '\$');
    print("Son las monedas identicas? " + ((identical(moneda1, moneda2)) ? "Sí" : "No"));

    print("$hr\n[Info] Ejercicio 6: constructor con redirección\n$hr");
    Alumno alumno1 = Alumno('Juan', 8);
    Alumno alumno2 = Alumno.aprobado('María');
    print(alumno1.toString());
    print(alumno2.toString());

    print("$hr\n[Info] Ejercicio 7: constructor de fábrica\n$hr");
    Sesion sesion1 = Sesion();
    Sesion sesion2 = Sesion();
    print("Son las sesiones identicas? " + ((identical(sesion1, sesion2)) ? "Sí" : "No"));
}
