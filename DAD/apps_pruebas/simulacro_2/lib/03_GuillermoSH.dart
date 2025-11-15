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
      appBar: AppBar(title: Text('Widget principal')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                MyCard(icon: Icons.work, textStr: "Work and Job"),
                SizedBox(width: 24),
                MyCard(icon: Icons.health_and_safety, textStr: "Health and Safety"),
                SizedBox(width: 24),
                MyCard(icon: Icons.home, textStr: "Home"),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class MyCard extends StatelessWidget {
  final IconData? icon;
  final String? textStr;

  const MyCard({super.key, required this.icon, required this.textStr});

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 1,
      child: Padding(
        padding: const EdgeInsets.all(12),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(onPressed: () => {}, child: Icon(icon)),
              SizedBox(height: 8),
              Text(textStr ?? ""),
            ],
          ),
        ),
      ),
    );
  }
}
