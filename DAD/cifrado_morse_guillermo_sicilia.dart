const Map<String, String> MORSE_CODE = {
  "A": ".-", "B": "-...", "C": "-.-.", "D": "-..", "E": ".", "F": "..-.",
  "G": "--.", "H": "....", "I": "..", "J": ".---", "K": "-.-", "L": ".-..",
  "M": "--", "N": "-.", "O": "---", "P": ".--.", "Q": "--.-", "R": ".-.",
  "S": "...", "T": "-", "U": "..-", "V": "...-", "W": ".--", "X": "-..-",
  "Y": "-.--", "Z": "--..",
  "1": ".----", "2": "..---", "3": "...--", "4": "....-", "5": ".....",
  "6": "-....", "7": "--...", "8": "---..", "9": "----.", "0": "-----",
  ",": "--..--", ".": ".-.-.-", "?": "..--.."
};

// Codigo morse invertido para decodificar
final Map<String, String> DECODE_MORSE = MORSE_CODE.map((k, v) => MapEntry(v, k));

/**
 * Decodes a Morse code message into plain text.
 * 
 * Returns:
 * It splits the message into words using " / " as a delimiter, then splits each word into letters 
 * using " " as a delimiter. It then maps each Morse code letter to its corresponding decoded character
 * using the `DECODE_MORSE` map, and joins the decoded letters back together to form the decoded message.
 */
String decodeMorse(String msg) {
  return msg.split(" / ").map(
    (word) => word.split(" ").map((letter) => DECODE_MORSE[letter] ?? "").join()
  ).join(" ");
}

void main() {
  String encodedMsg = "- --- -.-. / - --- -.-. / - --- -.-. --..-- / .--. . -. -. -.--";
  print(decodeMorse(encodedMsg));
}