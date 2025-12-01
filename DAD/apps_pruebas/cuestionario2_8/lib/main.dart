import 'package:flutter/material.dart';
import 'dart:async';

void main() {
  runApp(const TimerApp());
}

class TimerApp extends StatelessWidget {
  const TimerApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: CuentaAtras(),
    );
  }
}

class CuentaAtras extends StatefulWidget {
  const CuentaAtras({super.key});

  @override
  State<CuentaAtras> createState() => _CuentaAtrasState();
}

class _CuentaAtrasState extends State<CuentaAtras> {
  int tiempo = 10;
  late Timer _timer;

  @override
  void initState() {
    super.initState();
    Timer.periodic(Duration(seconds: 1), (timer) {
      setState(() {
        if (tiempo == 0) {
          timer.cancel();
        } else {
          tiempo--;
        }
      });
    });
  }

  @override
  void dispose() {
    _timer.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Cuenta atrás con Timer')),
      body: Center(
        child: Text(
          tiempo.toString(),
          style: const TextStyle(fontSize: 60, fontWeight: FontWeight.bold),
        ),
      ),
    );
  }
}
