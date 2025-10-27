//import 'dart:nativewrappers/_internal/vm/lib/internal_patch.dart';

import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      // ← ?? Lo entenderemos más adelante
      create: (context) => MyAppState(),
      child: MaterialApp(
        title: 'Namer App',
        theme: ThemeData(
          useMaterial3: true,
          colorScheme: ColorScheme.fromSeed(
            seedColor: Colors.deepOrange,
          ), // ← Los colores del tema son deepOrange
        ),
        home: MyHomePage(),
      ),
    );
  }
}

class BigCard extends StatelessWidget {
  const BigCard({super.key, required this.pair});

  final WordPair pair; // Es un atributo (campo) de la clase

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textStyle = theme.textTheme.displayMedium!.copyWith(
      color: theme.colorScheme.onPrimary,
    );

    return Card(
      color: theme.colorScheme.primary,
      child: Padding(
        padding: const EdgeInsets.all(20),
        child: Text(
          pair.asLowerCase,
          style: textStyle,
          semanticsLabel: "${pair.first} ${pair.second}",
        ),
      ),
    );
  }
}

class MyAppState extends ChangeNotifier {
  var current =
      WordPair.random(); // ← Crea una palabra aleatoria formada por una pareja de palabras

  void getNext() {
    current = WordPair.random();
    notifyListeners();
  }
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var appState = context
        .watch<MyAppState>(); // ← ?? Lo entenderemos más adelante
    final pair = appState.current;

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('A random idea:'),
            BigCard(pair: pair), // ← Muestra la palabra en minúsculas
            SizedBox(height: 10),
            ElevatedButton(
              onPressed: () => appState.getNext(),
              child: Text("Siguiente"),
            ),
          ],
        ),
      ),
    );
  }
}
