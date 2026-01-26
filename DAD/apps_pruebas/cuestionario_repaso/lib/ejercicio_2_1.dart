import 'package:flutter/material.dart';

void main() {
  runApp(const SemaforoApp());
}

class SemaforoApp extends StatelessWidget {
  const SemaforoApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: SemaforoPageState(),
    );
  }
}

class SemaforoPageState extends StatefulWidget {
  const SemaforoPageState({super.key});

  @override
  State<StatefulWidget> createState() => _SemaforoPageState();
}

class _SemaforoPageState extends State<SemaforoPageState> {
  String color = "red";

  void changeColor() {
    setState(() {
      switch (color) {
        case "red":
          color = "yellow";
        case "yellow":
          color = "green";
        default:
          color = "red";
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Semáforo Interactivo'), centerTitle: true),
      body: Center(
        child: Column(
          children: [
            Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: Colors.black,
                borderRadius: BorderRadius.circular(18),
              ),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Luz(color: color == "red" ? Colors.red : Colors.grey),
                  SizedBox(height: 12),
                  Luz(color: color == "yellow" ? Colors.yellow : Colors.grey),
                  SizedBox(height: 12),
                  Luz(color: color == "green" ? Colors.green : Colors.grey),
                ],
              ),
            ),
            SizedBox(height: 12),
            Text(
              color == "red"
                  ? "STOP"
                  : color == "yellow"
                  ? "PRECAUCION"
                  : "AVANZA",
              style: TextStyle(
                color: color == "red"
                    ? Colors.red
                    : color == "yellow"
                    ? Colors.yellow
                    : Colors.green,
                fontWeight: FontWeight.bold,
                fontSize: 24,
              ),
            ),
            SizedBox(height: 24),
            ElevatedButton(
              onPressed: changeColor,
              child: Text("Cambiar color"),
            ),
          ],
        ),
      ),
    );
  }
}

class Luz extends StatelessWidget {
  final Color color;

  const Luz({super.key, required this.color});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 90,
      height: 90,
      decoration: BoxDecoration(shape: BoxShape.circle, color: color),
    );
  }
}
