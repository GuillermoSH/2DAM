String decodeCesar(String msg) {
  return String.fromCharCodes(
    msg.split("").map((letter)=> letter.codeUnitAt(0) - 3)
  );
}

void main() {
  String encodedMsg = "Zlqjduglxp#Ohylrvd";
  print(decodeCesar(encodedMsg));
}