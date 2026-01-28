import 'package:flutter/material.dart';

void main() {
  runApp(const NavigatorBasicoApp());
}

class NavigatorBasicoApp extends StatelessWidget {
  const NavigatorBasicoApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: InicioPage(),
    );
  }
}

class InicioPage extends StatelessWidget {
  const InicioPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Inicio')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ElevatedButton(onPressed: () => Navigator.push(context, MaterialPageRoute(builder: (_) => const InfoPage())), child: Text('Ir a Info')),
            SizedBox(height: 10,),
            ElevatedButton(onPressed: () => Navigator.push(context, MaterialPageRoute(builder: (_) => const ContactoPage())), child: Text('Ir a Contacto'))
          ],
        ),
      ),
    );
  }
}

class InfoPage extends StatelessWidget {
  const InfoPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Info')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text('Informacion de la app'),
            SizedBox(height: 10,),
            ElevatedButton(onPressed: () => Navigator.pop(context), child: Text('Volver')),
          ],
        ),
      ),
    );
  }
}

class ContactoPage extends StatelessWidget {
  const ContactoPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Contacto')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text('Contacto: info@ejemplo.com / 600 000 000'),
            SizedBox(height: 10,),
            ElevatedButton(onPressed: () => Navigator.pop(context), child: Text('Volver')),
          ],
        ),
      ),
    );
  }
}