import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: const FirstPage(),
    );
  }
}

class FirstPage extends StatelessWidget {
  const FirstPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Fotos históricas')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Center(
          child: Column(
            children: [
              Text(
                "Bienvenido",
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
              Text("Toca la tarjeta para ver más detalles en otra pantalla"),
              GestureDetector(
                child: Hero(
                  tag: "image",
                  child: SizedBox(
                    height: 130,
                    width: 300,
                    child: ClipRRect(
                      borderRadius: BorderRadiusGeometry.all(
                        Radius.circular(16),
                      ),
                      child: Image.asset(
                        "assets/images/berlin.avif",
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class SecondPage extends StatelessWidget {
  const SecondPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Detalle de la tarjeta')),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Hero(
          tag: "image",
          child: SizedBox(
            height: 430,
            width: double.infinity,
            child: ClipRRect(
              borderRadius: BorderRadiusGeometry.all(Radius.circular(16)),
              child: Image.asset(
                "assets/images/berlin.avif",
                fit: BoxFit.cover,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
