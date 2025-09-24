int max_number(List<int> numbers) {
  numbers.sort();
  return numbers.last;
}

void main() {
  print(max_number([12, 3, 0, 89, 3, 7, 120]));
}
