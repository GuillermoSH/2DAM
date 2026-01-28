import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  final Color color = Colors.blue;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const Material3Page(),
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.blue,
          brightness: Brightness.light,
        ),
      ),
    );
  }
}

class Material3Page extends StatelessWidget {
  const Material3Page({super.key});

  @override
  Widget build(BuildContext context) {
    final palette = Theme.of(context).colorScheme;
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
              Text("Hola", style: TextStyle(color: palette.primary)),
              Icon(Icons.star, color: palette.primary),
              ElevatedButton(onPressed: () => {}, child: Text("Enviar")),
              Theme(
                data: Theme.of(context).copyWith(
                  elevatedButtonTheme: ElevatedButtonThemeData(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.red,
                      foregroundColor: Colors.white,
                    ),
                  ),
                ),
                child: ElevatedButton(onPressed: () => {}, child: Text("Hola")),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
