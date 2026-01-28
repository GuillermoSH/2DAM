void salute(String name, [String? surname]) {
  print("Hola ${name} ${surname ?? ""}");
}

void main() {
  //salute("Guillermo", "Sicilia");
  salute("Guillermo");
}
