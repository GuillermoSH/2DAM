import 'package:flutter/material.dart';

void main() => runApp(MiApp());

class MiApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Actividad Flutter',
      home: MyMainWidget(),
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Color(0xFF2C3E50)),
        textTheme: TextTheme(
          headlineMedium: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
          bodyLarge: TextStyle(fontSize: 18),
        ),
        iconTheme: IconThemeData(size: 50),
      ),
    );
  }
}

// 🔹 Clase principal
class MyMainWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(title: Text('Perfil minimalista')),
      body: Center(
        child: Column(
          children: [
            Icon(Icons.person),
            SizedBox(height: 20),
            Text("PERFIL DE USUARIO", style: theme.textTheme.headlineMedium),
            SizedBox(height: 20),
            TextField(
              decoration: InputDecoration(labelText: "Nombre completo"),
            ),
            SizedBox(height: 20),
            Text("Indica tu nivel de experiencia", style: theme.textTheme.bodyLarge),
            Slider(value: 50, onChanged: (value) => {}, min: 0, max: 100),
            SizedBox(height: 20),
            ElevatedButton(onPressed: () {}, child: Text("Guardar perfil")),
          ],
        ),
      ),
    );
  }
}
