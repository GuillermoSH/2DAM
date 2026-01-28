import 'package:flutter/material.dart';

void main() {
  runApp(const MiniJuegoEstado());
}

class MiniJuegoEstado extends StatefulWidget {
  const MiniJuegoEstado({super.key});

  @override
  State<MiniJuegoEstado> createState() => _MiniJuegoEstadoState();
}

class _MiniJuegoEstadoState extends State<MiniJuegoEstado> {
  int energia = 50;
  bool isDark = false;

  void increaseEnergy() {
    if (energia < 100) {
      setState(() {
        energia = energia + 10;
      });
    }
  }

  void decreaseEnergy() {
    if (energia > 0) {
      setState(() {
        energia = energia - 10;
      });
    }
  }

  void resetEnergy() {
    setState(() {
      energia = 50;
    });
  }

  void toggleTheme() {
    setState(() {
      isDark = !isDark;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        brightness: isDark ? Brightness.dark : Brightness.light,
        useMaterial3: true,
      ),
      home: Scaffold(
        appBar: AppBar(title: const Text("Mini-juego: Gestión de estado")),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                "ENERGÍA",
                style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
              ),
              SizedBox(height: 20),
              Text(
                "$energia",
                style: TextStyle(fontSize: 100, fontWeight: FontWeight.bold, color: (energia < 30) ? Colors.red : (energia > 70) ? Colors.green : Colors.orange),
              ),
              SizedBox(height: 20),
              switch (energia) {
                0 => Text("😵 Has perdido toda tu energía"),
                50 => Text("⚡ Estas a mitad de camino"),
                100 => Text("🎉 ¡Has escapado!"),
                _ => Text("🔄 Sigue ajustando la energía..."),
              },
              SizedBox(height: 30),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: increaseEnergy,
                    child: Text("➕ Aumentar"),
                  ),
                  SizedBox(width: 20),
                  ElevatedButton(
                    onPressed: decreaseEnergy,
                    child: Text("➖ Disminuir"),
                  ),
                  SizedBox(width: 20),
                  ElevatedButton(
                    onPressed: resetEnergy,
                    child: Text("🔄 Reiniciar"),
                  ),
                ],
              ),
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: toggleTheme,
          child: Icon(isDark ? Icons.dark_mode : Icons.light_mode),
        ),
      ),
    );
  }
}
