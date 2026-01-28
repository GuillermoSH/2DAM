import 'package:flutter/material.dart';

void main() {
  runApp(const ErrorAccesibleApp());
}

class ErrorAccesibleApp extends StatelessWidget {
  const ErrorAccesibleApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: FormPage(),
    );
  }
}

class FormPage extends StatefulWidget {
  const FormPage({super.key});

  @override
  State createState() => _FormPageState();
}

class _FormPageState extends State {
  final TextEditingController _emailCtrl = TextEditingController();
  bool isValid = true;

  void validar() {
    setState(() {
      isValid = _emailCtrl.text.contains('@');
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Formulario')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text('Correo electrónico'),
            const SizedBox(height: 8),

            TextField(
              controller: _emailCtrl,
              decoration: const InputDecoration(
                border: OutlineInputBorder(),
                hintText: 'usuario@email.com',
              ),
            ),

            const SizedBox(height: 12),

            if (isValid == false)
              Row(
                children: [
                  Icon(Icons.error, color: Colors.red),
                  Text(
                    'El correo introducido no es válido',
                    style: TextStyle(color: Colors.red),
                  ),
                ],
              ),

            const SizedBox(height: 16),

            ElevatedButton(onPressed: validar, child: const Text('Enviar')),
          ],
        ),
      ),
    );
  }
}
