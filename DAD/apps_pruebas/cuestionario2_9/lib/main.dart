import 'package:flutter/material.dart';
import 'dart:math';
import 'dart:async';

void main() {
  runApp(const BotonSaltarinApp());
}

class BotonSaltarinApp extends StatelessWidget {
  const BotonSaltarinApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: JuegoBotonSaltarin(),
    );
  }
}

// ---------------- CONTROLADOR ----------------

class GameController {
  int _points = 0;
  int _time = 15;
  final int _pointsGoal = 15;
  final Random random = Random();

  int get time => _time;
  set time(time) {
    _time = time;
  }

  int get points => _points;
  set points(points) {
    _points = points;
  }

  double get newHorizontalPosition => random.nextDouble() * 900;
  double get newVerticalPosition => random.nextDouble() * 500;
  int get pointsGoal => _pointsGoal;
  bool get isTimeFinished => _time == 0;
  bool get isWinner => _points == _pointsGoal;

  late double top = newVerticalPosition;
  late double left = newHorizontalPosition;

  void moveButton() {
    top = newVerticalPosition;
    left = newHorizontalPosition;
    points++;
  }
}

// --------------- WIDGET PRINCIPAL ----------------

class JuegoBotonSaltarin extends StatefulWidget {
  const JuegoBotonSaltarin({super.key});

  @override
  State<JuegoBotonSaltarin> createState() => _JuegoBotonSaltarinState();
}

class _JuegoBotonSaltarinState extends State<JuegoBotonSaltarin> {
  final GameController game = GameController();

  late Timer timer;

  @override
  void initState() {
    super.initState();
    Timer.periodic(Duration(seconds: 1), (Timer? timer) {
      setState(() {
        if (game.isTimeFinished) {
          dispose();
        } else {
          game.time--;
        }
      });
    });
  }

  void pulsarBoton() {
    setState(() {
      if (game.isWinner || game.isTimeFinished) {
        if (timer.isActive) dispose();
      } else {
        game.moveButton();
      }
    });
  }

  @override
  void dispose() {
    timer.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Botón saltarín')),
      body: Stack(
        children: [
          // Información del juego
          Positioned(
            top: 60,
            left: 80,
            child: Text(
              'Tiempo: ${game.time}  |  Puntos: ${game.points}',
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
          ),

          // BOTÓN QUE SE MUEVE
          Positioned(
            top: game.top,
            left: game.left,
            child: ElevatedButton(
              onPressed: pulsarBoton,
              child: Text('Púlsame'),
            ),
          ),

          Positioned(
            top: 30,
            left: 20,
            child: Text(
              ((game.isTimeFinished && !game.isWinner)
                  ? 'Se te ha acabado el tiempo'
                  : (game.isWinner && !game.isTimeFinished)
                  ? 'Has ganado'
                  : '¡Corre, pulsa el botón al menos ${game.pointsGoal} veces antes de que acabe el tiempo!'),
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
          ),
        ],
      ),
    );
  }
}
