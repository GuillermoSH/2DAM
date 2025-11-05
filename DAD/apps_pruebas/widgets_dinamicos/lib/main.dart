import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() => runApp(
  ChangeNotifierProvider(
    create: (context) => VolumenProvider(),
    child: MiApp(),
  ),
);

class MiApp extends StatelessWidget {
  const MiApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Actividad Flutter',
      home: ControlVolumen(),
    );
  }
}

class VolumenProvider extends ChangeNotifier {
  double _volumen = 50;
  double get volumen => _volumen;

  set volumen(double nuevoVolumen) {
    _volumen = nuevoVolumen;
    notifyListeners();
  }
}

class ControlVolumen extends StatelessWidget {
  const ControlVolumen({super.key});

  @override
  Widget build(BuildContext context) {
    double volumen = context.watch<VolumenProvider>().volumen;
    VolumenProvider provider = context.read<VolumenProvider>();
    return Scaffold(
      appBar: AppBar(title: Text('Ejemplo StatefulWidget')),
      body: Center(
        child: Column(
          children: [
            Text("Volumen: ${volumen.toStringAsFixed(0)}%"),
            SizedBox(height: 20),
            Icon(
              volumen > 50
                  ? Icons.volume_up
                  : volumen != 0
                  ? Icons.volume_down
                  : Icons.volume_off,
              size: 100,
            ),
            SizedBox(height: 20),
            Slider(
              value: volumen,
              onChanged: (value) => provider.volumen = value,
              min: 0,
              max: 100,
            ),
          ],
        ),
      ),
    );
  }
}
