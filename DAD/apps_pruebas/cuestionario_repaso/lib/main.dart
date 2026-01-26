import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: NumberCheckerPage(),
    );
  }
}

class NumberCheckerPage extends StatefulWidget {
  const NumberCheckerPage({super.key});

  @override
  State createState() => _NumberCheckerPageState();
}

class _NumberCheckerPageState extends State {
  String result = '';
  String input = '';
  bool isValid = false;
  String errorText = '';

  void checkNumber(String input) {
    setState(() {
      this.input = input;
      isValid = int.tryParse(input) != null;

      errorText = (input == '') ? 'Escribe un numero (ej. 123)' : 'Solo numeros, sin letras ni espacios';
    });
  }

  void submitNumber() {
    setState(() {
      errorText = '';
      result = isValid ? input : '';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Comprobar número')),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextFormField(
              onChanged: (input) => checkNumber(input),
              decoration: InputDecoration(
                labelText: 'Introduce un número',
                errorText: isValid
                    ? null
                    : errorText,
                helperText: 'Ejemplo: 123',
              ),
            ),
            const SizedBox(height: 20),

            ElevatedButton(
              onPressed: isValid ? submitNumber : null,
              child: const Text('Comprobar'),
            ),

            const SizedBox(height: 20),
            if (result != '') Text('Numero correcto: $result'),
          ],
        ),
      ),
    );
  }
}
