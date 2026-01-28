class Temperatura {
  double celsius;

  Temperatura(this.celsius);

  double get fahrenheit => celsius * 9 / 5 + 32;

  @override
  String toString() {
    return '$celsius °C = $fahrenheit °F';
  }
}

class Contrasenia {
  String _valor = '';

  String get valor => _valor;

  set valor(String nuevaValor) {
    if (nuevaValor.length >= 8) {
      _valor = nuevaValor;
    } else {
      print('Error: La contraseña debe tener al menos 8 caracteres.');
    }
  }

  @override
  String toString() {
    return 'Contraseña: $_valor';
  }
}

class Empleado {
  String nombre;

  Empleado(this.nombre);

  trabajar() {
    print('$nombre está trabajando.');
  }
}

class Programador extends Empleado {
  String lenguaje = '';

  Programador(String lenguaje) : super('Programador') {
    this.lenguaje = lenguaje;
  }

  @override
  trabajar() {
    print('Estoy programando en $lenguaje.');
  }
}

abstract class InstrumentoMusical {
  void tocar();
}

class Guitarra extends InstrumentoMusical {
  @override
  void tocar() {
    print('Tocando la guitarra.');
  }
}

class Piano extends InstrumentoMusical {
  @override
  void tocar() {
    print('Tocando el piano.');
  }
}

abstract class Exportable {
  void exportar();
}

class DocumentoPDF implements Exportable {
  void exportar() {
    print('Exportando documento como PDF.');
  }
}

class ImagenPNG implements Exportable {
  void exportar() {
    print('Exportando imagen como PNG.');
  }
}

mixin Recargable {
  void recargar() {
    print('Recargando...');
  }
}

class Telefono with Recargable {}

class Linterna with Recargable {}

class ColorRGB {
  final int r, g, b;

  const ColorRGB(this.r, this.g, this.b);
}

void main() {
  String hr = '-' * 50;
  print("$hr\n[Info] Ejercicio 1: ejercicio con getter\n$hr");
  Temperatura temp1 = Temperatura(25);
  Temperatura temp2 = Temperatura(0);
  Temperatura temp3 = Temperatura(100);
  print(temp1);
  print(temp2);
  print(temp3);

  print("$hr\n[Info] Ejercicio 2: ejercicio con setter\n$hr");
  Contrasenia pass = Contrasenia();
  pass.valor = "12345";
  pass.valor = "5asd3";
  pass.valor = "12345678";
  print(pass);

  print("$hr\n[Info] Ejercicio 3: ejercicio con extends\n$hr");
  Programador dev = Programador("Dart");
  dev.nombre = "Guillermo";
  dev.trabajar();

  print("$hr\n[Info] Ejercicio 4: ejercicio con clase abstracta\n$hr");
  Guitarra guitarra = Guitarra();
  Piano piano = Piano();
  List<InstrumentoMusical> instrumentos = [guitarra, piano];
  for (var instrumento in instrumentos) {
    instrumento.tocar();
  }

  print("$hr\n[Info] Ejercicio 5: ejercicio con interfaz\n$hr");
  DocumentoPDF docPDF = DocumentoPDF();
  ImagenPNG imgPNG = ImagenPNG();
  List<Exportable> exportables = [docPDF, imgPNG];
  for (var item in exportables) {
    item.exportar();
  }

  print("$hr\n[Info] Ejercicio 6: ejercicio con mixin\n$hr");
  Telefono telefono = Telefono();
  Linterna linterna = Linterna();
  telefono.recargar();
  linterna.recargar();

  print("$hr\n[Info] Ejercicio 7: ejercicio con clase const\n$hr");
  const ColorRGB rojo1 = ColorRGB(255, 0, 0);
  const ColorRGB rojo2 = ColorRGB(255, 0, 0);
  print(identical(rojo1, rojo2) ? "Son el mismo color" : "Son colores diferentes");
}