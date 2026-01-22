import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: FirstPage(),
    );
  }
}

class FirstPage extends StatelessWidget {
  const FirstPage({super.key});

  @override
  Widget build(BuildContext context) {

    // 1️⃣ Crea una lista de textos: Welcome, Willkommen, Bienvenido
    final frases = ["Welcome", "Willkommen", "Bienvenido"];


    return Scaffold(
      appBar: AppBar(title: const Text('Lista a Widgets')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: frases.map((title) => Text(title)).toList(),
        ),
      ),
    );
  }
}