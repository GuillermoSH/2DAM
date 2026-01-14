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
  bool isEmailValid = true;
  bool isPassValid = true;

  void checkEmail(String email) {
    setState(() {
      isEmailValid = RegExp(r'^[^@\s]+@[^@\s]+\.[^@\s]+$').hasMatch(email);
    });
  }

  void checkPass(String pass) {
    setState(() {
      isPassValid = pass.isNotEmpty && pass.length >= 6;
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
              onChanged: (text) => checkEmail(text),
              decoration: InputDecoration(
                labelText: 'Email',
                errorText: isEmailValid ? null : 'Formato de email no válido. Revisa el "@" del dominio',
              ),
            ),
            SizedBox(height: 10),
            TextFormField(
              onChanged: (text) => checkPass(text),
              decoration: InputDecoration(
                labelText: 'Contraseña',
                errorText: isEmailValid ? null : 'Formato de email no válido. Revisa el "@" del dominio',
              ),
            ),
            SizedBox(height: 10),
            ElevatedButton(onPressed: null, child: Text("Entrar")),
          ],
        ),
      ),
    );
  }
}