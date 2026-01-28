import 'dart:collection';

double ejercicio01(List<int> notas) {
  notas.add(1);
  print("[Info] Primera nota: ${notas.first}\n[Info] Última nota: ${notas.last}");
  double suma = 0;
  for (int nota in notas) {
    suma += nota;
  }
  return suma / notas.length;
}

void ejercicio02(Set<String> emails) {
  print("[Info] Emails originales:");
  for (String email in emails) {
    print(email);
  }

  emails.add("guillermo@mail.com");
  
  (emails.contains("luis@mail.com")) ? print("[Info] Luis está en la lista") : print("[Info] Luis no está en la lista");
}

void ejercicio03(Map<String, double> productos) {
  print("[Info] La leche esta a ${productos["leche"]} euros");
  productos.addAll({"café": 3.0});

print("[Info] Lista de productos:");
  for (String producto in productos.keys) {
    print("$producto -> ${productos[producto]}€");
  }
}

void ejercicio04(Queue<String> cola) {
  cola.removeFirst();
  print("[Info] Realizada la primera tarea, imprimiendo tareas pendientes:\n$cola");
  cola.addAll(["Pasear perro"]);
  print("[Info] Añadida la tarea de pasear perro, imprimiendo tareas pendientes:\n$cola");
}

void ejercicio05(SplayTreeSet<int> numeros) {
  numeros.addAll([5,3,9,1,4]);
  print("[Info] Numeros ordenados:\n$numeros");
  numeros.remove(3);
  print("[Info] Numeros ordenados después de eliminar el 3:\n$numeros");
}

void main() {
  List<int> notas = [7, 8, 6, 5, 9];
  print("\n----------------------Ejercicio 01----------------------");
  print("[Info] Media de notas: ${ejercicio01(notas)}");


  Set<String> emails = {"ana@mail.com", "luis@mail.com", "ana@mail.com", "pedro@mail.com"};
  print("\n----------------------Ejercicio 02----------------------");
  ejercicio02(emails);

  Map<String, double> productos = {"pan": 1.0, "leche": 1.5, "huevos": 2.0};
  print("\n----------------------Ejercicio 03----------------------");
  ejercicio03(productos);

  Queue<String> cola = Queue.from(["Lavar platos", "Hacer compra", "Estudiar"]);
  print("\n----------------------Ejercicio 04----------------------");
  ejercicio04(cola);

  SplayTreeSet<int> numeros = SplayTreeSet<int>();
  print("\n----------------------Ejercicio 05----------------------");
  ejercicio05(numeros);
}