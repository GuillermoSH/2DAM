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
        textTheme: TextTheme(
          headlineMedium: TextStyle(fontSize: 26, color: Colors.pink),
          bodyLarge: TextStyle(fontSize: 16, color: Colors.blue),
        ),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ButtonStyle(
            backgroundColor: WidgetStateProperty.all(Colors.red),
            foregroundColor: WidgetStateProperty.all(Colors.white),
          ),
        ),
      ),
    );
  }
}

class Material3Page extends StatelessWidget {
  const Material3Page({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Material 3")),
      floatingActionButton: FloatingActionButton(
        onPressed: () {},
        child: const Icon(Icons.add),
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(onPressed: () => {}, child: Text("Botón")),
            ],
          ),
        ),
      ),
    );
  }
}
