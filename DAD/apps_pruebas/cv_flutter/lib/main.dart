import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'CV Samira Hadid',
      theme: ThemeData(
        fontFamily: 'Roboto',
        primaryColor: const Color.fromARGB(255, 33, 46, 65),
        secondaryHeaderColor: const Color.fromARGB(255, 38, 38, 38),
        scaffoldBackgroundColor: Colors.white,
        textTheme: const TextTheme(
          headlineLarge: TextStyle(
            fontSize: 36,
            fontWeight: FontWeight.bold,
            color: Color.fromARGB(255, 33, 46, 65),
            letterSpacing: 3,
          ),
          bodyLarge: TextStyle(fontSize: 16, color: Colors.black87),
          bodyMedium: TextStyle(fontSize: 14, color: Colors.black54),
        ),
      ),
      home: const CVScreen(),
    );
  }
}

class CVScreen extends StatelessWidget {
  const CVScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        children: const [
          SizedBox(width: 400, child: LeftPanel()),
          Expanded(child: RightPanel()),
        ],
      ),
    );
  }
}

class LeftPanel extends StatelessWidget {
  const LeftPanel({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Theme.of(context).primaryColor,
      padding: const EdgeInsets.all(60),
      child: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const CircleAvatar(
              radius: 100,
              backgroundImage: NetworkImage(
                'https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg',
              ),
            ),
            const SizedBox(height: 20),
            _buildContactSection(),
            const SizedBox(height: 20),
            _buildAboutMe(),
            const SizedBox(height: 20),
            _buildLanguages(),
            const SizedBox(height: 20),
            _buildSkills(),
          ],
        ),
      ),
    );
  }

  Widget _buildContactSection() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: const [
        SectionHeader(title: 'CONTACT'),
        SizedBox(height: 10),
        ContactInfo(icon: Icons.phone, text: '+123-456-7890'),
        ContactInfo(icon: Icons.email, text: 'hello@reallygreatsite.com'),
        ContactInfo(icon: Icons.language, text: 'www.reallygreatsite.com'),
        ContactInfo(icon: Icons.location_on, text: '123 Anywhere St, Any City'),
      ],
    );
  }

  Widget _buildAboutMe() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: const [
        SectionHeader(title: 'ABOUT ME'),
        SizedBox(height: 10),
        Text(
          "I bring a dynamic blend of strategic vision, hands-on execution, and a results-centric mindset. As a Strategic Marketing Dynamo, I not only survive but thrive in the fast-paced and dynamic landscape of the marketing realm.",
          style: TextStyle(color: Colors.white70, fontSize: 14, height: 1.5),
        ),
      ],
    );
  }

  Widget _buildLanguages() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const SectionHeader(title: 'LANGUAGES'),
        const SizedBox(height: 10),
        const LanguageItem(language: 'English', level: 0.95),
        const LanguageItem(language: 'Arabic', level: 0.8),
        const LanguageItem(language: 'German', level: 0.65),
        const LanguageItem(language: 'French', level: 0.5),
      ],
    );
  }

  Widget _buildSkills() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const SectionHeader(title: 'SKILLS'),
        const SizedBox(height: 10),
        const SkillBar(text: 'Professional', value: 0.9),
        const SkillBar(text: 'Teamwork', value: 0.8),
        const SkillBar(text: 'Flexibility', value: 0.85),
        const SkillBar(text: 'Creativity', value: 0.75),
        const SkillBar(text: 'Management', value: 0.8),
        const SkillBar(text: 'Organization', value: 0.9),
      ],
    );
  }
}

class RightPanel extends StatelessWidget {
  const RightPanel({super.key});

  @override
  Widget build(BuildContext context) {
    final Color primaryColor = Theme.of(context).primaryColor;
    final Color secondaryHeaderColor = Theme.of(context).secondaryHeaderColor;
    return Padding(
      padding: const EdgeInsets.all(60),
      child: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 50),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Text(
                    'SAMIRA HADID',
                    style: Theme.of(context).textTheme.headlineLarge,
                  ),
                  Text(
                    'Marketing Manager',
                    style: TextStyle(color: secondaryHeaderColor, fontSize: 18),
                  ),
                  const SizedBox(height: 10),
                  Container(height: 8, width: 80, color: primaryColor),
                ],
              ),
            ),
            const SizedBox(height: 30),
            const SectionHeader(title: 'EDUCATION', darkMode: false),
            const EducationItem(
              institution: 'Wardiere University',
              department: 'Business School',
              period: '2034 - 2036',
              degree: 'Master Degree in Business Management',
              gpa: '3.92 / 4.0',
            ),
            const EducationItem(
              institution: 'Wardiere University',
              department: 'Business School',
              period: '2030 - 2034',
              degree: 'Bachelor Degree in Business Management',
              gpa: '3.79 / 4.0',
            ),
            const SizedBox(height: 30),
            const SectionHeader(title: 'WORK EXPERIENCE', darkMode: false),
            const ExperienceItem(
              company: 'Borcelle Studio',
              period: '2044 - Now',
              role: 'Marketing Manager',
              description:
                  'Lead, mentor, and manage a high performing marketing team, fostering a collaborative and intent work environment.',
            ),
            const ExperienceItem(
              company: 'Fauget Studio',
              period: '2034 - 2044',
              role: 'Marketing Manager',
              description:
                  'Lead, mentor, and manage a high performing marketing team, fostering a collaborative and intent work environment.',
            ),
            const ExperienceItem(
              company: 'Studio Showde',
              period: '2030 - 2034',
              role: 'Marketing Manager',
              description:
                  'Lead, mentor, and manage a high performing marketing team, fostering a collaborative and intent work environment.',
            ),
            const SizedBox(height: 30),
            const SectionHeader(title: 'AWARDS', darkMode: false),
            const AwardItem(
              title: 'Excellence Award',
              date: 'June 2040',
              description:
                  'I am happily honored for my dedication and collaborative approach in each campaign.',
            ),
            const AwardItem(
              title: 'Excellence Award',
              date: 'June 2035',
              description:
                  'I am happily honored for my dedication and collaborative approach in each campaign.',
            ),
          ],
        ),
      ),
    );
  }
}

class SectionHeader extends StatelessWidget {
  final String title;
  final bool darkMode;

  const SectionHeader({super.key, required this.title, this.darkMode = true});

  @override
  Widget build(BuildContext context) {
    final Color secondaryHeaderColor = Theme.of(context).secondaryHeaderColor;
    final Color lineColor = darkMode ? Colors.white : secondaryHeaderColor;
    final Color textColor = darkMode ? Colors.white : secondaryHeaderColor;

    return Padding(
      padding: const EdgeInsets.only(top: 20, bottom: 10),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            '// $title',
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.bold,
              color: textColor,
              letterSpacing: 1.2,
            ),
          ),
          const SizedBox(height: 10),
          Container(height: 3, width: double.infinity, color: lineColor),
        ],
      ),
    );
  }
}

class ContactInfo extends StatelessWidget {
  final IconData icon;
  final String text;

  const ContactInfo({super.key, required this.icon, required this.text});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 6),
      child: Row(
        children: [
          Icon(icon, color: Colors.white, size: 18),
          const SizedBox(width: 8),
          Expanded(
            child: Text(
              text,
              style: const TextStyle(color: Colors.white70, fontSize: 14),
            ),
          ),
        ],
      ),
    );
  }
}

class LanguageItem extends StatelessWidget {
  final String language;
  final double level;

  const LanguageItem({super.key, required this.language, required this.level});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 6),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Expanded(
            child: Text(
              "• $language",
              style: const TextStyle(color: Colors.white, fontSize: 14),
            ),
          ),
          const SizedBox(
            width: 10,
          ), // Add spacing between Text and LinearProgressIndicator
          SizedBox(
            width: 100, // Adjust as needed
            child: LinearProgressIndicator(
              value: level,
              color: Colors.white,
              backgroundColor: Colors.white24,
              minHeight: 6,
            ),
          ),
        ],
      ),
    );
  }
}

class SkillBar extends StatelessWidget {
  final String text;
  final double value;

  const SkillBar({super.key, required this.text, required this.value});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 8),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Expanded(
            child: Text(
              "• $text",
              style: const TextStyle(color: Colors.white, fontSize: 14),
            ),
          ),
          const SizedBox(width: 10),
          SizedBox(
            width: 100, // Adjust as needed
            child: LinearProgressIndicator(
              value: value,
              color: Colors.white,
              backgroundColor: Colors.white24,
              minHeight: 6,
            ),
          ),
        ],
      ),
    );
  }
}

class EducationItem extends StatelessWidget {
  final String institution;
  final String department;
  final String period;
  final String degree;
  final String gpa;

  const EducationItem({
    super.key,
    required this.institution,
    required this.department,
    required this.period,
    required this.degree,
    required this.gpa,
  });

  @override
  Widget build(BuildContext context) {
    final Color secondaryHeaderColor = Theme.of(context).secondaryHeaderColor;

    return Padding(
      padding: const EdgeInsets.only(bottom: 14),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  institution,
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: secondaryHeaderColor,
                  ),
                ),
                Text(
                  period,
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: secondaryHeaderColor,
                  ),
                ),
              ],
            ),
          ),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  department,
                  style: TextStyle(
                    color: secondaryHeaderColor,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Text(degree, style: TextStyle(color: secondaryHeaderColor)),
                Row(
                  children: [
                    Text(
                      "GPA:",
                      style: TextStyle(
                        color: secondaryHeaderColor,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    SizedBox(width: 5),
                    Text(gpa, style: TextStyle(color: secondaryHeaderColor)),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class ExperienceItem extends StatelessWidget {
  final String company;
  final String period;
  final String role;
  final String description;

  const ExperienceItem({
    super.key,
    required this.company,
    required this.period,
    required this.role,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 14),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            '$company  ($period)',
            style: const TextStyle(fontWeight: FontWeight.bold),
          ),
          Text(role, style: const TextStyle(fontSize: 14)),
          Text(
            description,
            style: const TextStyle(color: Colors.black54, height: 1.5),
          ),
        ],
      ),
    );
  }
}

class AwardItem extends StatelessWidget {
  final String title;
  final String date;
  final String description;

  const AwardItem({
    super.key,
    required this.title,
    required this.date,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 10),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            '$title  •  $date',
            style: const TextStyle(fontWeight: FontWeight.bold),
          ),
          Text(description, style: const TextStyle(color: Colors.black54)),
        ],
      ),
    );
  }
}
