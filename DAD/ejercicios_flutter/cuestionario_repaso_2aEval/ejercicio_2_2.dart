import 'package:flutter/material.dart';

void main() {
  runApp(const EmojiApp());
}

class EmojiApp extends StatelessWidget {
  const EmojiApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: EmojiPage(),
    );
  }
}

class EmojiPage extends StatefulWidget {
  const EmojiPage({super.key});

  @override
  State<StatefulWidget> createState() => _EmojiPage();
}

class _EmojiPage extends State<EmojiPage> {
  double _scale = 1.5;

  void changeScale() {
    setState(() {
      _scale = (_scale == 1.5) ? 2.5 : 1.5;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Animacion implicita: Emoji')),
      floatingActionButton: FloatingActionButton(
        onPressed: changeScale,
        child: Icon(Icons.play_arrow),
      ),
      body: Center(
        child: AnimatedScale(
          scale: _scale,
          duration: Duration(milliseconds: 400),
          curve: Curves.easeInOut,
          child: Text('😄', style: TextStyle(fontSize: 36),),
        ),
      ),
    );
  }
}
