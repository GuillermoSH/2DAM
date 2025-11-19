import 'package:flutter/material.dart';

void main() {
  runApp(const Material3Page());
}

class Material3Page extends StatelessWidget {
  const Material3Page({super.key});
  final Color color = Colors.blue;
  static const colorScheme = ColorScheme(
    brightness: Brightness.light,
    primary: Color(0xFF2F7F5E),
    onPrimary: Color(0xFFFFFFFF),
    secondary: Color(0xFF4A4A4A),
    onSecondary: Color(0xFFFFFFFF),
    surface: Color(0xFFF7F7F7),
    onSurface: Color(0xFF1A1A1A),
    error: Color(0xFF1A1A1A),
    onError: Color(0xFFFFFFFF),
  );

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const HomePage(),
      theme: ThemeData(useMaterial3: true, colorScheme: colorScheme),
    );
  }
}

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Material 3")),
      floatingActionButton: FloatingActionButton(
        onPressed: () {},
        child: const Icon(Icons.arrow_back),
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text("Hola"),
              Icon(Icons.star),
              ElevatedButton(onPressed: () => {}, child: Text("Enviar")),
            ],
          ),
        ),
      ),
    );
  }
}
