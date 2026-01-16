import 'package:flutter/material.dart';

void main() => runApp(MiApp());

class MiApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Actividad Flutter',
      home: MyMainWidget(),
    );
  }
}

// 🔹 Clase principal
class MyMainWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Widget personalizado')),
      body: Center(
        child: Column(
          children: [
            WidgetPersonalizado(icon: Icons.work, title: "Trabajo", description: "Gestiona tus tareas y proyectos diarios"),
            WidgetPersonalizado(icon: Icons.health_and_safety, title: "Salud", description: "Consulta informacion relacionada con tu bienestar"),
            WidgetPersonalizado(icon: Icons.home, title: "Hogar", description: "Organiza todos los aspectosde tu vivienda"),
          ],
        ),
      ),
    );
  }
}

class WidgetPersonalizado extends StatelessWidget {
  final IconData icon;
  final String title;
  final String description;

  const WidgetPersonalizado({
    super.key,
    required this.icon,
    required this.title,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Icon(icon),
          SizedBox(height: 5),
          Column(crossAxisAlignment: CrossAxisAlignment.start, children: [Text(title), Text(description)]),
        ],
      ),
    );
  }
}
