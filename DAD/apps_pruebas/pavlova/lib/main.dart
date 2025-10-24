import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    final stars = Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        Icon(Icons.star, color: Colors.green[500]),
        Icon(Icons.star, color: Colors.green[500]),
        Icon(Icons.star, color: Colors.green[500]),
        Icon(Icons.star, color: Colors.black),
        Icon(Icons.star, color: Colors.black),
      ],
    );

    final ratings = Container(
      padding: EdgeInsets.all(20),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          stars,
          Text(
            "170 Reviews",
            style: TextStyle(
              color: Colors.black,
              fontWeight: FontWeight.w800,
              fontFamily: "Roboto",
              letterSpacing: 0.5,
              fontSize: 20,
            ),
          ),
        ],
      ),
    );

    const descTextStyle = TextStyle(
      color: Colors.black,
      fontWeight: FontWeight.w800,
      fontFamily: "Roboto",
      letterSpacing: 0.5,
      fontSize: 18,
      height: 2,
    );

    final iconList = DefaultTextStyle.merge(
      style: descTextStyle,
      child: Container(
        padding: EdgeInsets.all(20),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Column(
              children: [
                Icon(Icons.kitchen, color: Colors.green[500]),
                Text("PREP:"),
                Text("25 min"),
              ],
            ),
            Column(
              children: [
                Icon(Icons.timer, color: Colors.green[500]),
                Text("COOK:"),
                Text("1 hr"),
              ],
            ),
            Column(
              children: [
                Icon(Icons.restaurant, color: Colors.green[500]),
                Text("FEEDS:"),
                Text("4-6"),
              ],
            ),
          ],
        ),
      ),
    );

    final titleText = Padding(
      padding: EdgeInsets.all(20),
      child: Text(
        "Strawberry Pavlova",
        style: TextStyle(
          fontWeight: FontWeight.w800,
          letterSpacing: 0.5,
          fontSize: 30,
        ),
      ),
    );

    final subtitle = Text(
      "Pavlova is a meringue-based dessert named after the Russian ballerina Anna Pavlova. Pavlova features a crisp crust and soft, light inside, topped with fruit and whipped cream.",
      textAlign: TextAlign.center,
      style: TextStyle(fontFamily: "Georgia", fontSize: 25),
    );

    final leftColumn = Container(
      padding: EdgeInsets.fromLTRB(20, 30, 20, 20),
      child: Column(children: [titleText, subtitle, ratings, iconList]),
    );

    final mainImage = Image.asset("assets/images/pavlova.jpg", fit: BoxFit.cover);

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("Strawberry Pavlova Recipe")),
        body: Center(
          child: Container(
            height: 600,
            child: Card(
              shadowColor: Color.fromARGB(100, 0, 0, 0),
              elevation: 2,
              shape: RoundedRectangleBorder(borderRadius: BorderRadiusGeometry.all(Radius.circular(20))),
              margin: EdgeInsets.fromLTRB(0, 40, 0, 30),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [SizedBox(width: 440, child: leftColumn), mainImage],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
