import 'package:flutter/material.dart';

void main() {
  runApp(const RotacionApp());
}

class RotacionApp extends StatelessWidget {
  const RotacionApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: RotacionPage(),
    );
  }
}

class RotacionPage extends StatefulWidget {
  const RotacionPage({super.key});

  @override
  State createState() => _RotacionPageState();
}

class _RotacionPageState extends State {
  double rotation = 0;

  void rotate(double rotation) {
    setState(() {
      this.rotation += rotation;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('AnimatedRotation')),
      body: Center(
        child: AnimatedRotation(
          turns: rotation,
          curve: Curves.easeInOut,
          duration: Duration(milliseconds: 400),
          child: Icon(Icons.navigation, size: 90),
        ),
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => rotate(0.25),
        icon: const Icon(Icons.rotate_right),
        label: const Text('Girar'),
      ),
    );
  }
}
