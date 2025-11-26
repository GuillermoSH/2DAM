import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => ErrorsNotifier(),
      child: const EscapeRoomApp(),
    ),
  );
}

class ErrorsNotifier extends ChangeNotifier {
  int _errorCount = 5;
  int get errorCount => _errorCount;

  void addError() {
    _errorCount = _errorCount - 1;
    notifyListeners();
  }

  void resetErrors() {
    _errorCount = 5;
    notifyListeners();
  }
}

class EscapeRoomApp extends StatelessWidget {
  const EscapeRoomApp({super.key});

  @override
  Widget build(BuildContext context) {
    
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Escape Room - Hogwarts',
      initialRoute: '/',
      routes: {
        '/': (_) => const SalaInicial(),
        '/pregunta1': (_) => const Pista1(),
        '/pregunta2': (_) => const Pista2(),
        '/fallo': (_) => const Fallo(),
        '/atrapado': (_) => const Atrapado(),
        '/victoria': (_) => const Victoria(),
        // Añade aquí las rutas que faltan
      },
    );
  }
}

// Aquí debes crear las clases:
// - SalaInicial
// - Pista1
// - Victoria
// - Atrapado

class SalaInicial extends StatelessWidget {
  const SalaInicial({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Escape Room - Hogwarts')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/pregunta1'),
              child: Text('¡Empezar el escape room!'),
            ),
          ],
        ),
      ),
    );
  }
}

class Pista1 extends StatelessWidget {
  const Pista1({super.key});
  final String validGuess = "Gryffindor";

  @override
  Widget build(BuildContext context) {
    final errors = context.watch<ErrorsNotifier>();

    void guess(guess) {
      if (guess == validGuess) {
        Navigator.pushNamed(context, '/pregunta2');
      } else {
        errors.addError();
        Navigator.pushNamed(context, '/fallo');
      }

      if (errors.errorCount < 1) {
        Navigator.pushNamed(context, '/atrapado');
        return;
      }
    }

    return Scaffold(
      appBar: AppBar(title: const Text('Pregunta 1')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Estas en una sala secreta de Hogwarts', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
            SizedBox(height: 30),
            Text('Primera pregunta:'),
            SizedBox(height: 30),
            Text('¿Cuál es la casa a la que pertenece Harry Potter?'),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Hufflepuff"),
              child: Text("Hufflepuff"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Gryffindor"),
              child: Text("Gryffindor"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Slytherin"),
              child: Text("Slytherin"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Ravenclaw"),
              child: Text("Ravenclaw"),
            ),
          ],
        ),
      ),
    );
  }
}

class Pista2 extends StatelessWidget {
  const Pista2({super.key});
  final String validGuess = "Andén 9 ¾";

  @override
  Widget build(BuildContext context) {
    final errors = context.watch<ErrorsNotifier>();

    void guess(guess) {
      if (guess == validGuess) {
        Navigator.pushNamed(context, '/victoria');
      } else {
        errors.addError();
        Navigator.pushNamed(context, '/fallo');
      }

      if (errors.errorCount < 1) {
        Navigator.pushNamed(context, '/atrapado');
        return;
      }
    }

    return Scaffold(
      appBar: AppBar(title: const Text('Pregunta 2')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('¡Has acertado!', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
            SizedBox(height: 30),
            Text('Segunda pregunta:'),
            SizedBox(height: 30),
            Text(
              '¿Cómo se llama la estacion de tren que da acceso a Hogwarts?',
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Andén 9 ¾"),
              child: Text("Andén 9 ¾"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Estación Central de Londres"),
              child: Text("Estación Central de Londres"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Andén 7 ½"),
              child: Text("Andén 7 ½"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => guess("Estación Hogsmeade Express"),
              child: Text("Estación Hogsmeade Express"),
            ),
          ],
        ),
      ),
    );
  }
}

class Fallo extends StatelessWidget {
  const Fallo({super.key});

  @override
  Widget build(BuildContext context) {
    final errors = context.watch<ErrorsNotifier>();
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 204, 136, 136),
      appBar: AppBar(title: const Text('Has fallado la pregunta')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('😵‍💫 Has fallado la pregunta', style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold)),
            SizedBox(height: 20),
            Text('Te quedan ${errors.errorCount} intentos', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => Navigator.pop(context),
              child: Text("Intentar de nuevo"),
            ),
          ],
        ),
      ),
    );
  }
}

class Victoria extends StatelessWidget {
  const Victoria({super.key});

  @override
  Widget build(BuildContext context) {
    final errors = context.watch<ErrorsNotifier>();
    void goHome() {
      errors.resetErrors();
      Navigator.pushNamed(context, '/');
    }
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 147, 204, 136),
      appBar: AppBar(title: const Text('¡Victoria!')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('🤩 Has escapado con éxito', style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold)),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => goHome(),
              child: Text("Reiniciar el escape room"),
            ),
          ],
        ),
      ),
    );
  }
}

class Atrapado extends StatelessWidget {
  const Atrapado({super.key});

  @override
  Widget build(BuildContext context) {
    final errors = context.watch<ErrorsNotifier>();
    void goHome() {
      errors.resetErrors();
      Navigator.pushNamed(context, '/');
    }
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 204, 136, 136),
      appBar: AppBar(title: const Text('Game Over')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Te has quedado sin intentos', style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => goHome(),
              child: Text("Reiniciar el escape room"),
            ),
          ],
        ),
      ),
    );
  }
}
