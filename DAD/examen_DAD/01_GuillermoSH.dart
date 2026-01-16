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
            Text("Alaska"),
            SizedBox(height: 15),
            Text(
              "El Sol se ha ido oficialmente en Alaska, y no volverá a salir hasta el 22 de enero de 2026",
            ),
            SizedBox(height: 15),
            Image.asset("./assets/images/alaska.jpg", width: 350),
            SizedBox(height: 15),
            Image.asset("./assets/images/alaska-2.webp", width: 350),
          ],
        ),
      ),
    );
  }
}
