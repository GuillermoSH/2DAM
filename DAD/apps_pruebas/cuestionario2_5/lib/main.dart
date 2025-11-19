import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  static bool _isDark = true;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Tema claro/oscuro',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Color(0xFF0455BF)),
        brightness: Brightness.light,
      ),
      darkTheme: ThemeData(
        brightness: Brightness.dark,
      ),
      themeMode: _isDark ? ThemeMode.dark : ThemeMode.light,
      home: Scaffold(
        appBar: AppBar(title: const Text('Home Page')),

        body: Center(
          child: ElevatedButton(onPressed: () => {
            setState(() {_isDark = !_isDark;})
          }, child: Text('Botón')),
        ),

        floatingActionButton: FloatingActionButton(
          onPressed: () {
            
          },
          child: Icon(_isDark ? Icons.dark_mode : Icons.light_mode),
        ),
      ),
    );
  }
}