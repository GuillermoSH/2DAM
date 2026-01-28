import 'ejercicio2.dart';
import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: const ScientistsPage(),
    );
  }
}

class Scientist {
  String name;
  String imageUrl;
  String description;

  Scientist({
    required this.name,
    required this.imageUrl,
    required this.description,
  });

  @override
  String toString() {
    return "$name $description";
  }
}

class ScientistsPage extends StatelessWidget {
  const ScientistsPage({super.key});

  @override
  Widget build(BuildContext context) {
    final List<Scientist> scientists = [
      Scientist(
        name: 'Galileo Galilei',
        imageUrl:
            'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Justus_Sustermans_-_Portrait_of_Galileo_Galilei%2C_1636.jpg/640px-Justus_Sustermans_-_Portrait_of_Galileo_Galilei%2C_1636.jpg',
        description:
            'Galileo Galilei formuló la relatividad galileana, donde no existe el movimiento absoluto.',
      ),
      Scientist(
        name: 'Isaac Newton',
        imageUrl:
            'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Portrait_of_Sir_Isaac_Newton%2C_1689.jpg/640px-Portrait_of_Sir_Isaac_Newton%2C_1689.jpg',
        description:
            'Isaac Newton usó las ideas de Galileo para crear la física clásica, en la que el espacio y el tiempo son absolutos.',
      ),
      Scientist(
        name: 'Albert Einstein',
        imageUrl:
            'https://upload.wikimedia.org/wikipedia/commons/d/d3/Albert_Einstein_Head.jpg',
        description:
            'Albert Einstein usó la física de Newton para desarrollar la relatividad, donde el espacio y el tiempo no son absolutos, sino que dependen del movimiento del observador.',
      ),
    ];

    return Scaffold(
      appBar: AppBar(title: const Text('Físicos Ilustres')),
      body: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: scientists
              .map(
                (scientist) => GestureDetector(
                  child: Hero(
                    tag: scientist.name,
                    child: SizedBox(
                      width: 200,
                      height: 200,
                      child: ClipRRect(
                        borderRadius: BorderRadius.circular(16),
                        child: Image.network(
                          scientist.imageUrl,
                          fit: BoxFit.cover,
                        ),
                      ),
                    ),
                  ),
                  onTap: () => Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) =>
                          ScientistDetailPage(scientist: scientist),
                    ),
                  ),
                ),
              )
              .toList(),
        ),
      ),
    );
  }
}

class ScientistDetailPage extends StatelessWidget {
  final Scientist scientist;

  const ScientistDetailPage({super.key, required this.scientist});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(scientist.name)),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Center(
          child: Column(
            children: [
              GestureDetector(
                child: Hero(
                  tag: scientist.name,
                  child: SizedBox(
                    height: 430,
                    width: 430,
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(16),
                      child: Image.network(
                        scientist.imageUrl,
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                ),
                onTap: () => Navigator.pop(context),
              ),

              const SizedBox(height: 20),

              Text(
                scientist.name,
                style: const TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
                textAlign: TextAlign.center,
              ),

              Text(
                scientist.description,
                style: const TextStyle(fontSize: 20),
                textAlign: TextAlign.center,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
