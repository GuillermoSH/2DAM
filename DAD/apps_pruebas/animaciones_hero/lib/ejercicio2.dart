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
              SizedBox(height: 30),
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
                        "assets/images/berlin.png",
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                ),
                onTap: () => Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => SecondPage()),
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
        child: Column(
          children: [
            Hero(
              tag: "image",
              child: SizedBox(
                height: 430,
                width: double.infinity,
                child: ClipRRect(
                  borderRadius: BorderRadiusGeometry.all(Radius.circular(16)),
                  child: Image.asset(
                    "assets/images/berlin.png",
                    fit: BoxFit.cover,
                  ),
                ),
              ),
            ),
            Text(
              "Konrad Schumann",
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 24),
            ),
            const Text(
              'La imagen muestra a Konrad Schumann, un joven guardia de Alemania Oriental, '
              'en el instante en que salta sobre el alambre fronterizo que marcaba el '
              'inicio de la construcción del Muro de Berlín, levantado por la Alemania comunista, '
              'para escapar hacia Berlín Oeste en 1961.',
              textAlign: TextAlign.justify,
            ),
            OutlinedButton(
              onPressed: () => Navigator.pop(context),
              child: Text("Volver"),
            ),
          ],
        ),
      ),
    );
  }
}
