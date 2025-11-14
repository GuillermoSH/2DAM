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
            SizedBox(
              width: 400,
              child: Card(
                elevation: 20,
                child: Padding(
                  padding: const EdgeInsets.all(15),
                  child: Column(
                    children: [
                      Container(
                        decoration: BoxDecoration(
                          border: Border(
                            bottom: BorderSide(color: Colors.black12, width: 1),
                          ),
                        ),
                        child: Padding(
                          padding: const EdgeInsets.all(24.0),
                          child: IconRow(icon: Icons.person, iconSize: 40, textStr: "Perfil de usuario"),
                        )
                      ),
                      Container(
                        decoration: BoxDecoration(
                          border: Border(
                            bottom: BorderSide(color: Colors.black12, width: 1),
                          ),
                        ),
                        child: Padding(
                          padding: const EdgeInsets.all(24.0),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              IconRow(icon: Icons.star, iconSize: 24, textStr: "Puntos: 120",),
                              SizedBox(height: 5),
                              IconRow(icon: Icons.notifications, iconSize: 24, textStr: "Notificaciones: 5",),
                            ],
                          ),
                        ),
                      ),
                      Container(
                        decoration: BoxDecoration(
                          border: Border(
                            bottom: BorderSide(color: Colors.black12, width: 1),
                          ),
                        ),
                        child: Padding(
                          padding: const EdgeInsets.all(24.0),
                          child: Center(child: ElevatedButton(onPressed: () => {}, child: Text("Editar perfil")),),
                        )
                      ),
                      SizedBox(height: 24,),
                      Column(
                        children: [
                          Text("Opciones rápidas"),
                          SizedBox(height: 8,),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              IconColumn(icon: Icons.folder, iconSize: 24, textStr: "Archivos"),
                              SizedBox(width: 24,),
                              IconColumn(icon: Icons.settings, iconSize: 24, textStr: "Ajustes"),
                              SizedBox(width: 24,),
                              IconColumn(icon: Icons.help_outline, iconSize: 24, textStr: "Ayuda")
                            ],
                          )
                        ],
                      )
                    ],
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class IconRow extends StatelessWidget {
  final IconData? icon;
  final String? textStr;
  final double? iconSize;

  const IconRow({super.key, required this.icon, required this.iconSize, required this.textStr});
  @override
  Widget build(BuildContext context) {
    return Row(mainAxisAlignment: MainAxisAlignment.center, children: [
      Icon(icon, color: Colors.black, size: iconSize),
      SizedBox(width: 5,),
      Text(textStr ?? ""),
    ],);
  }
}

class IconColumn extends StatelessWidget {
  final IconData? icon;
  final String? textStr;
  final double? iconSize;

  const IconColumn({super.key, required this.icon, required this.iconSize, required this.textStr});
  @override
  Widget build(BuildContext context) {
    return Column(mainAxisAlignment: MainAxisAlignment.center, children: [
      Icon(icon, color: Colors.black, size: iconSize),
      Text(textStr ?? ""),
    ],);
  }
}
