import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: ErrorHelpExample(),
    );
  }
}

class ErrorHelpExample extends StatefulWidget {
  const ErrorHelpExample({super.key});

  @override
  State createState() => _ErrorHelpExampleState();
}

class _ErrorHelpExampleState extends State {
  bool isValid = true;
  String input = "";

  void checkText(String text) {
    setState(() {
      isValid = int.tryParse(text) != null;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Heurística 9 – Ayuda con errores')),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(mainAxisSize: MainAxisSize.min, children: [
            TextFormField(
              onChanged: (text) => input = text,
              decoration: InputDecoration(
                labelText: 'Introduce un número entero',
                errorText: isValid ? null : "Error: Debes introducir un entero",
              ),
            ),
            SizedBox(height: 10),
            ElevatedButton(onPressed: () => checkText(input), child: Text("Comprobar")),
          ],
        ),
      ),
    );
  }
}