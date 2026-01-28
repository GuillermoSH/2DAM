void salute(String name, [int age = 0]) {
  print("Hola ${name}, tienes ${age} años");
}

void main() {
  //salute("Guillermo");
  salute("Guillermo", 25);
}