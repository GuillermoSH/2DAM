import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const CafeteriaApp());
}

class CarritoModel extends ChangeNotifier {
  int items = 0;
  double total = 0.0;

  void add(double precio) {
    items++;
    total += precio;
    notifyListeners();
  }

  void clear() {
    items = 0;
    total = 0;
    notifyListeners();
  }
}

class CafeteriaApp extends StatelessWidget {
  const CafeteriaApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => CarritoModel(),
      child: const MaterialApp(
        debugShowCheckedModeBanner: false,
        home: CafeteriaPage(),
      ),
    );
  }
}

class CafeteriaPage extends StatelessWidget {
  const CafeteriaPage({super.key});

  @override
  Widget build(BuildContext context) {
    final model = context.watch<CarritoModel>();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Cafetería (dinámico)'),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            Container(
              padding: const EdgeInsets.all(12),
              decoration: BoxDecoration(
                border: Border.all(color: Colors.black12),
                borderRadius: BorderRadius.circular(10),
              ),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text('Items: ${model.items}', style: TextStyle(fontSize: 18)),
                  Text('Total: ${model.total.toStringAsFixed(2)} €', style: TextStyle(fontSize: 18)),
                ],
              ),
            ),

            const SizedBox(height: 16),

            _Producto(nombre: 'Café', precio: 1.20),
            _Producto(nombre: 'Tostada', precio: 1.80),
            _Producto(nombre: 'Zumo', precio: 2.10),

            const Spacer(),

            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                onPressed: model.clear,
                child: Text('Vaciar carrito'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class _Producto extends StatelessWidget {
  final String nombre;
  final double precio;

  const _Producto({required this.nombre, required this.precio});

  @override
  Widget build(BuildContext context) {
    final model = context.watch<CarritoModel>();
    return Card(
      child: ListTile(
        title: Text(nombre),
        subtitle: Text('${precio.toStringAsFixed(2)} €'),
        trailing: ElevatedButton(onPressed: () => model.add(precio), child: const Text('Añadir')),
      ),
    );
  }
}
