import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primaryColor: Colors.white,
        textTheme: TextTheme(
          headlineLarge: TextStyle(
            color: Colors.black,
            fontSize: 24,
            fontWeight: FontWeight.bold,
          ), // Para el título principal
          bodyLarge: TextStyle(
            color: Color(0xFF757575),
            fontSize: 16,
          ), // Para el texto normal
          bodyMedium: TextStyle(
            color: Colors.black,
            fontSize: 18,
          ), // Para subtítulos
        ),
      ),
      home: CVScreen(),
    );
  }
}

class CVScreen extends StatelessWidget {
  const CVScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        children: [
          // Sidebar
          Container(width: 300, color: Color(0xFF2D2D2D), child: Sidebar()),
          // Contenido principal
          Expanded(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: MainContent(),
            ),
          ),
        ],
      ),
    );
  }
}

class Sidebar extends StatelessWidget {
  const Sidebar({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(16.0),
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(20),
            child: CircleAvatar(
              maxRadius: 100,
              backgroundImage: NetworkImage(
                'https://images.pexels.com/photos/3785079/pexels-photo-3785079.jpeg?cs=srgb&dl=pexels-olly-3785079.jpg&fm=jpg',
              ),
            ),
          ),

          Text('Marketing Manager', style: TextStyle(color: Color(0xFF757575))),
          Text('123-456-7890', style: TextStyle(color: Colors.white)),
          Text('email@example.com', style: TextStyle(color: Colors.white)),
          SectionTitle(
            title: 'Educación',
            color: Colors.black,
            bgColor: Colors.white,
            icon: Icons.school,
          ),
          EducationList(),
          SectionTitle(
            title: 'Habilidades',
            color: Colors.black,
            bgColor: Colors.white,
            icon: Icons.psychology,
          ),
          SkillsList(),
          SectionTitle(
            title: 'Lenguajes',
            color: Colors.black,
            bgColor: Colors.white,
            icon: Icons.language,
          ),
          LanguagesList(),
        ],
      ),
    );
  }
}

class MainContent extends StatelessWidget {
  const MainContent({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        SectionTitle(
          title: 'Acerca de mí',
          color: Colors.white,
          bgColor: Colors.black,
          icon: Icons.person,
        ),
        Text('Texto descriptivo sobre la persona...'),
        SectionTitle(
          title: 'Experiencia',
          color: Colors.white,
          bgColor: Colors.black,
          icon: Icons.business_center,
        ),
        ExperienceList(),
        SectionTitle(
          title: 'Referencias',
          color: Colors.white,
          bgColor: Colors.black,
          icon: Icons.menu_book,
        ),
        ReferencesList(),
      ],
    );
  }
}

class SectionTitle extends StatelessWidget {
  final String title;
  final Color color;
  final Color bgColor;
  final IconData? icon;

  const SectionTitle({
    super.key,
    required this.title,
    this.color = Colors.black,
    this.bgColor = Colors.white,
    this.icon,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(0, 20, 0, 8),
      child: Row(
        children: [
          CircleAvatar(
            backgroundColor: bgColor,
            maxRadius: 15,
            child: Icon(icon, size: 20, color: color),
          ),
          SizedBox(width: 10),
          Text(
            title,
            style: TextStyle(
              fontSize: 24,
              fontWeight: FontWeight.bold,
              color: bgColor,
            ),
          ),
        ],
      ),
    );
  }
}

class SkillItem extends StatelessWidget {
  final IconData icon;
  final String text;

  const SkillItem({super.key, required this.icon, required this.text});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Icon(icon, size: 20, color: Colors.white),
        SizedBox(width: 8),
        Text(text, style: TextStyle(fontSize: 16, color: Colors.white)),
      ],
    );
  }
}

class EducationList extends StatelessWidget {
  const EducationList({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: 20),
      child: Column(
        children: [
          Text(
            'Boreal University',
            style: TextStyle(fontWeight: FontWeight.bold, color: Colors.white),
          ),
          Text(
            'Bachelor of Business Management, 2012 - 2016',
            style: TextStyle(color: Colors.white),
          ),
          // Añadir más entradas de educación según sea necesario
        ],
      ),
    );
  }
}

class ExperienceList extends StatelessWidget {
  const ExperienceList({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: 20),
      child: Column(
        children: [
          Text(
            'Product Design Manager, 2020 - Present',
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          Text('ABC Corp, Any City'),
          // Añadir más experiencias según sea necesario
          Text(
            'Marketing Manager, 2018 - 2020',
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          Text('XYZ Ltd, Any City'),
          // Agregar más elementos aquí si es necesario
        ],
      ),
    );
  }
}

class SkillsList extends StatelessWidget {
  const SkillsList({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: 20),
      child: Column(
        children: [
          SkillItem(icon: Icons.code, text: 'Flutter'),
          SkillItem(icon: Icons.design_services, text: 'UI/UX Design'),
          SkillItem(icon: Icons.business_center, text: 'Project Management'),
          // Agregar más habilidades según sea necesario
        ],
      ),
    );
  }
}

class LanguagesList extends StatelessWidget {
  const LanguagesList({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: 20),
      child: Column(
        children: [
          Text('Inglés - Fluido', style: TextStyle(color: Colors.white)),
          Text('Español - Conversacional', style: TextStyle(color: Colors.white)),
          // Agregar más lenguajes según sea necesario
        ],
      ),
    );
  }
}

class ReferencesList extends StatelessWidget {
  const ReferencesList({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: 20),
      child: Column(
        children: [
          Text('Harumi Kobayashi, Wordnerd Inc.'),
          Text('Bailey Dubont, Great Solutions Inc.'),
          // Agregar más referencias si es necesario
        ],
      ),
    );
  }
}
