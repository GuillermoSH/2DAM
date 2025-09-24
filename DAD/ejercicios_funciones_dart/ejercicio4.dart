int sum(List<int> numbers) {
  int sum = 0;
  numbers.forEach((number) => sum += number);
  return sum;
}

void main() {
  print(sum([12, 3, 67, 9, 3, 1]));
}
