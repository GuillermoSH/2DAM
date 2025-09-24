import "dart:math";

List<double> generateSqrtList(int n) =>
    List.generate(n, (index) => sqrt((index + 1).toDouble()));

void main() {
  const int n = 12;
  final List<double> squareRoots = generateSqrtList(n);

  int i = 1;
  squareRoots.forEach((number) {
    print("sqrt($i) = ${number.toStringAsFixed(2)}");
    i++;
  });
}
