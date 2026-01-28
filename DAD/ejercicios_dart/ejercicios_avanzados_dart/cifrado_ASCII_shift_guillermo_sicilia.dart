String descifradoASCII(String msg) {
  return String.fromCharCodes(
    msg.split(" ").map((letter)=> int.parse(letter) - 1)
  );
}

void main() {
  String msg = '82 118 102 32 109 98 32 103 118 102 115 98 32 117 102 32 98 110 112 106 122 111 98 111 102';
  print(descifradoASCII(msg));
}