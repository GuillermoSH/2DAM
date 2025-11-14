import 'dart:async';

void dividir1(int a, int b) {
  if (b == 0) throw Exception("Error: división por cero");
  print(a / b);
}

void dividir5(int a, int b) {
  if (b == 0) throw DivisionPorCeroException();
  print(a / b);
}

void abrirArchivo() {
  print("📂 Archivo abierto");
  throw Exception("Error al leer archivo");
}

class DivisionPorCeroException implements Exception {
  @override
  String toString() => "🚫 División prohibida";
}

Future<void> cargarDatos() async {
  await Future.delayed(Duration(seconds: 1));
  throw TimeoutException("Fallo en la conexión");
}

void main() async {
  var lista = [1, 2, 3];

  print("\n-----------------------Ejercicio 01-----------------------");
  try {
    dividir1(10, 0);
  } catch (e) {
    print(e);
  }

  print("\n-----------------------Ejercicio 02-----------------------");
  try {
    print(lista[5]);
  } on RangeError catch (e) {
    print(e);
  }

  print("\n-----------------------Ejercicio 03-----------------------");
  try {
    var numero = int.parse("abc");
  } on FormatException {
    print("No se puede convertir una letra a un número");
  }

  print("\n-----------------------Ejercicio 04-----------------------");
  try {
    abrirArchivo();
  } on Exception catch (e) {
    print(e);
  } finally {
    print("📕 Archivo cerrado");
  }

  print("\n-----------------------Ejercicio 05-----------------------");
  try {
    dividir5(8, 0);
  } on DivisionPorCeroException catch (e) {
    print(e);
  }

  print("\n-----------------------Ejercicio 06-----------------------");
  try {
    await cargarDatos();
  } on TimeoutException catch (e) {
    print(e);
  }
}
