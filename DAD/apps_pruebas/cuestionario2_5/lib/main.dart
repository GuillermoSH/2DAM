import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => ThemeNotifier(),
      child: const MyApp(),
    ),
  );
}

class ThemeNotifier extends ChangeNotifier {
  bool _isDark = false;
  bool get isDark => _isDark;

  void toggleTheme() {
    _isDark = !_isDark;
    notifyListeners();
  }
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    final isDark = context.watch<ThemeNotifier>().isDark;
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Tema claro/oscuro',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Color(0xFF0455BF)),
        brightness: Brightness.light,
      ),
      darkTheme: ThemeData(brightness: Brightness.dark),
      themeMode: isDark ? ThemeMode.dark : ThemeMode.light,
      routes: {
        '/': (_) => const HomePage(),
        '/pantalla2': (_) => const Pantalla2(),
      },
    );
  }
}

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    final isDark = context.watch<ThemeNotifier>().isDark;
    final toggleTheme = context.watch<ThemeNotifier>().toggleTheme;

    return Scaffold(
      appBar: AppBar(title: const Text('Pantalla A')),

      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: toggleTheme,
              child: Text('Cambiar a ${isDark ? 'claro' : 'oscuro'}'),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => {Navigator.pushNamed(context, '/pantalla2')},
              child: Text("Ir a Pantalla B"),
            ),
          ],
        ),
      ),

      floatingActionButton: FloatingActionButton(
        onPressed: toggleTheme,
        child: Icon(isDark ? Icons.dark_mode : Icons.light_mode),
      ),
    );
  }
}

class Pantalla2 extends StatelessWidget {
  const Pantalla2({super.key});

  @override
  Widget build(BuildContext context) {
    final isDark = context.watch<ThemeNotifier>().isDark;
    final toggleTheme = context.watch<ThemeNotifier>().toggleTheme;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Pantalla B'),
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: Icon(Icons.arrow_back),
        ),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: toggleTheme,
          child: Text('Cambiar a ${isDark ? 'claro' : 'oscuro'}'),
        ),
      ),
    );
  }
}
