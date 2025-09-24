final List<String> names = ["Alejandro", "Maria", "Jorge", "Antonio", "Elena"];

void exec_conversion(List<String> names, String Function(String) function) {
  names.forEach((name) => print(function(name)));
}

void main() {
  exec_conversion(names, (name) => name.toUpperCase());
}
