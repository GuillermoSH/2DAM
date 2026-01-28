import 'package:flutter/material.dart';

void main() {
  runApp(const MarcadorApp());
}

class MarcadorApp extends StatelessWidget {
  const MarcadorApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: MarcadorPage(),
    );
  }
}

class MarcadorPage extends StatefulWidget {
  const MarcadorPage({super.key});

  @override
  State createState() => _MarcadorPageState();
}

class _MarcadorPageState extends State {
  int pointsA = 0;
  int pointsB = 0;
  String result = "Empate";

  void addPoint(String team, int points) {
    setState(() {
      if (team == "A") {
        pointsA+=points;
      } else {
        pointsB+=points;
      }
      result = (pointsA>pointsB) ? "Va ganando A" : (pointsA==pointsB) ? "Empate" : "Va ganando B";
    });
  }

  void reset() {
    setState(() {
      pointsA = 0;
      pointsB = 0;
      result = "Empate";
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Marcador dinámico')),
      body: Center(
        child: Column(
          children: [
            Text('Equipo A: $pointsA'),
            Text('Equipo B: $pointsB'),
            Text(result),
            ElevatedButton(onPressed: () => addPoint("A", 1), child: Text("+1 A")),
            ElevatedButton(onPressed: () => addPoint("B", 1), child: Text("+1 B")),
            ElevatedButton(onPressed: () => reset(), child: Text("Reiniciar")),
          ],
        ),
      ),
    );
  }
}