import 'dart:math';

int toIntSeguro(String valor) {
  if (int.tryParse(valor) == null)
    throw FormatException("Valor inválido: $valor");
  return int.parse(valor);
}

void sumarLista(List<dynamic> datos) {
  int suma = 0;
  List<dynamic> failed = [];
  for (var elemento in datos) {
    if (elemento is int) {
      suma += elemento;
    } else {
      failed.add(elemento);
    }
  }
  print(suma);
  if (failed.isNotEmpty) throw ElementoNoEnteroException(failed);
}

class ElementoNoEnteroException implements Exception {
  var valor;

  ElementoNoEnteroException(this.valor);

  @override
  String toString() {
    if (valor.length > 1) {
      return "🚫 Los elementos '${valor.join(", ")}' no son enteros";
    } else {
      return "🚫 El elemento '${valor.first}' no es un entero";
    }
  }
}

void multiplicar(int? a, int? b) {
  if (a == null || b == null)
    throw ArgumentError("Ambos argumentos deben ser enteros");
  if (a < 0 || b < 0)
    throw Exception("Ambos argumentos deben ser enteros positivos");
  print(a * b);
}

void conectar() {
  Random random = Random();
  if (random.nextBool()) {
    throw Exception("Fallo de red");
  }
  print("Conexión exitosa");
}

Future<String> cargarArchivo(String nombre) {
  if (nombre != "config.txt") throw Exception("Archivo no encontrado");
  return Future.value("Archivo cargado");
}

void main() async {
  print("\n-----------------------Ejercicio 01-----------------------");
  try {
    for (String numeroStr in ["123", "abc", "45"]) {
      print(toIntSeguro(numeroStr));
    }
  } on FormatException catch (e) {
    print(e);
  }

  print("\n-----------------------Ejercicio 02-----------------------");
  try {
    sumarLista([10, 20, "hola", 5]);
  } on ElementoNoEnteroException catch (e) {
    print(e);
  }

  print("\n-----------------------Ejercicio 03-----------------------");
  try {
    multiplicar(-5, null);
  } on ArgumentError catch (e) {
    print(e);
  } catch (e) {
    print(e);
  }

  print("\n-----------------------Ejercicio 04-----------------------");
  for (int i = 0; i < 3; i++) {
    if (i == 2) throw Exception("Conexion fallida tras 3 intentos");
    try {
      conectar();
      break;
    } on Exception catch (e) {
      print(e);
    }
  }

  print("\n-----------------------Ejercicio 05-----------------------");
  print(await cargarArchivo("confg.txt").catchError((e) => print(e)));
}
