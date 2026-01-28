void operar(double num1, double num2, double Function(double, double) f) {
  print(f(num1, num2));
}

double suma(double a, double b) => a + b;
double resta(double a, double b) => a - b;
double multiplicacion(double a, double b) => a * b;
double division(double a, double b) => a / b;

void main() {
  operar(1,2,suma);
  operar(12,3,resta);
  operar(3,2,multiplicacion);
  operar(10,3,division);
}