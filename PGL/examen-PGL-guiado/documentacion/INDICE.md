# 📚 ÍNDICE GENERAL - Documentación del Examen SplitExpenser

> **Guía rápida:** Este documento te ayuda a navegar por toda la documentación del examen.

---

## 🎯 ¿Qué documento necesito ahora?

### 📖 **Para ESTUDIAR** (antes del examen)

#### 1. **GUIA_EXAMEN.md** - La Guía Completa
- **Cuándo usarlo:** Primera lectura, para aprender desde cero
- **Contenido:** 
  - Introducción a TypeScript desde cero (tipos, interfaces, funciones)
  - Explicación detallada de las 4 partes del examen
  - Conceptos de React Native (useState, useEffect, useContext)
  - Ejemplos paso a paso con explicaciones
  - Arquitectura completa del proyecto
- **Tiempo de lectura:** 2-3 horas
- **Ideal para:** Entender conceptos, aprender TypeScript, primera vez con el proyecto

---

#### 2. **RESUMEN_VISUAL.md** - Diagramas y Flujos
- **Cuándo usarlo:** Para entender cómo se conecta todo
- **Contenido:**
  - Diagramas de flujo de la aplicación
  - Esquemas visuales de cada parte
  - Estructura de datos con ejemplos
  - Flujo de peticiones HTTP ilustrado
  - Componentes React Native explicados visualmente
- **Tiempo de lectura:** 15-30 minutos
- **Ideal para:** Ver el "big picture", repasar rápidamente, entender la arquitectura

---

### 💡 **Para IMPLEMENTAR** (durante el examen)

#### 3. **SOLUCION_EXAMEN.md** - Código Completo Comentado
- **Cuándo usarlo:** Durante la implementación del examen
- **Contenido:**
  - Código completo de TODOS los archivos necesarios
  - Comentarios línea por línea explicando cada parte
  - Solución de las 4 partes del examen
  - Instrucciones de instalación y configuración
  - Configuración de `tsconfig.json` (importante)
- **Tiempo de lectura:** 1-2 horas (para entender todo)
- **Ideal para:** Copiar código directamente, entender implementación, resolver el examen

---

### ⚡ **Para CONSULTAR** (referencia rápida)

#### 4. **CHEAT_SHEET.md** - Referencia Rápida
- **Cuándo usarlo:** Durante el examen como "chuleta"
- **Contenido:**
  - Tabla completa de endpoints de la API
  - Snippets de código listos para copiar
  - Plantillas de peticiones HTTP
  - Respuestas rápidas a preguntas comunes
  - Errores comunes y cómo evitarlos
  - Configuración de la API
- **Tiempo de lectura:** 5-10 minutos
- **Ideal para:** Consulta rápida, recordar sintaxis, copiar código específico

---

### 🧪 **Para VERIFICAR** (testing)

#### 5. **GUIA_PRUEBAS.md** - Casos de Prueba
- **Cuándo usarlo:** Para probar que todo funciona correctamente
- **Contenido:**
  - 40 casos de prueba detallados
  - Datos de prueba recomendados
  - Checklist de verificación completo
  - Cómo verificar la base de datos
  - Cómo usar DevTools del navegador
- **Tiempo de lectura:** 30-60 minutos (haciendo las pruebas)
- **Ideal para:** Asegurar que todo funciona, encontrar errores, verificar requisitos

---

## 📊 Comparativa Rápida

| Documento | Páginas | Nivel | Uso Principal | Llevar al Examen |
|-----------|---------|-------|---------------|------------------|
| **GUIA_EXAMEN.md** | ~50 | Principiante | Aprender | ⭐ Opcional |
| **SOLUCION_EXAMEN.md** | ~40 | Intermedio | Implementar | ⭐⭐⭐ Sí |
| **CHEAT_SHEET.md** | ~10 | Todos | Consultar | ⭐⭐⭐ Sí |
| **RESUMEN_VISUAL.md** | ~15 | Todos | Entender | ⭐⭐ Recomendado |
| **GUIA_PRUEBAS.md** | ~20 | Intermedio | Probar | ⭐ Opcional |

---

## 🗂️ Estructura del Proyecto

```
Examen pgl/
│
├── 📄 INDICE.md                    ← Estás aquí (este archivo)
├── 📄 README.md                    ← Índice anterior (más detallado)
│
├── 📘 GUIA_EXAMEN.md              ← Guía completa de estudio
├── 💡 SOLUCION_EXAMEN.md          ← Código completo con comentarios
├── ⚡ CHEAT_SHEET.md              ← Referencia rápida
├── 🎨 RESUMEN_VISUAL.md           ← Diagramas y flujos
├── 🧪 GUIA_PRUEBAS.md             ← Casos de prueba
│
├── 📁 back/                        ← Backend (Python/Flask)
│   ├── main.py                     ← API completa
│   ├── pyproject.toml
│   └── test_main.py
│
└── 📁 front/                       ← Frontend (React Native)
    ├── app/
    │   ├── index.tsx               ← Home
    │   ├── login.tsx               ← Login
    │   ├── register.tsx            ← Registro (modificado)
    │   ├── groups.tsx              ← Grupos (creado) ✨
    │   ├── expenses.tsx            ← Gastos (creado) ✨
    │   └── _layout.tsx             ← Layout
    ├── context/
    │   └── AuthContext.tsx         ← Autenticación
    ├── utils/
    │   └── passwordValidator.ts    ← Validador (creado) ✨
    ├── tsconfig.json               ← Configuración TypeScript
    └── package.json
```

---

## 🎯 Ruta de Aprendizaje Recomendada

### 📅 **Día 1-2: Entender**
1. Leer **GUIA_EXAMEN.md** completa
2. Ver **RESUMEN_VISUAL.md** para entender el flujo
3. Ejecutar el proyecto existente
4. Explorar el código del backend

### 📅 **Día 3-4: Implementar**
1. Abrir **SOLUCION_EXAMEN.md**
2. Crear los archivos nuevos
3. Copiar el código con comentarios
4. Entender cada parte mientras copias

### 📅 **Día 5: Probar**
1. Usar **GUIA_PRUEBAS.md**
2. Probar todos los casos de prueba
3. Practicar sin mirar la solución
4. Tener **CHEAT_SHEET.md** a mano

### 📅 **Día del Examen:**
1. Llevar **CHEAT_SHEET.md** impreso ⭐⭐⭐
2. Llevar **SOLUCION_EXAMEN.md** impreso ⭐⭐⭐
3. Tener **GUIA_PRUEBAS.md** para verificar ⭐

---

## 🚀 Inicio Rápido

### Si tienes 10 minutos:
1. Lee **RESUMEN_VISUAL.md** (diagramas)
2. Hojea **CHEAT_SHEET.md** (referencia)

### Si tienes 1 hora:
1. Lee **RESUMEN_VISUAL.md** (15 min)
2. Lee **SOLUCION_EXAMEN.md** - Parte 1 y 2 (45 min)

### Si tienes 3 horas:
1. Lee **GUIA_EXAMEN.md** completa (2 horas)
2. Practica con **SOLUCION_EXAMEN.md** (1 hora)

### Si tienes 1 semana:
- Sigue la **Ruta de Aprendizaje Recomendada** arriba ☝️

---

## 📝 Resumen de Cada Parte del Examen

### 🔐 **PARTE 1: Validación de Contraseña**
- **Archivo:** `front/utils/passwordValidator.ts`
- **Qué hacer:** Crear funciones para validar contraseñas
- **Requisitos:** 8+ caracteres, mayúscula, minúscula, número, símbolo
- **Documentación:** Todas las guías tienen esta parte

### 🔑 **PARTE 2: Registro e Inicio de Sesión**
- **Archivos:** `register.tsx`, `login.tsx`, `AuthContext.tsx`
- **Qué hacer:** Implementar registro y login con JWT
- **Endpoints:** `POST /auth/register`, `POST /auth/login`
- **Documentación:** SOLUCION_EXAMEN.md (Parte 2)

### 👥 **PARTE 3: Gestión de Grupos**
- **Archivo:** `front/app/groups.tsx` (crear nuevo)
- **Qué hacer:** Listar y crear grupos
- **Endpoints:** `GET /groups`, `POST /groups`
- **Documentación:** SOLUCION_EXAMEN.md (Parte 3)

### 💰 **PARTE 4: Gestión de Gastos**
- **Archivo:** `front/app/expenses.tsx` (crear nuevo)
- **Qué hacer:** CRUD completo de gastos
- **Endpoints:** GET, POST, PUT, DELETE `/groups/{id}/expenses`
- **Documentación:** SOLUCION_EXAMEN.md (Parte 4)

---

## ⚙️ Configuración Importante

### ⚠️ **tsconfig.json**
**Problema común:** Error `Cannot use JSX unless the '--jsx' flag is provided`

**Solución:** Asegúrate de que `tsconfig.json` tenga:
```json
{
  "compilerOptions": {
    "jsx": "react-native"  // ← Esta línea es NECESARIA
  }
}
```

Luego reinicia TypeScript: `Ctrl+Shift+P` → `TypeScript: Restart TS Server`

**Documentación:** SOLUCION_EXAMEN.md (sección tsconfig.json)

---

### 🌐 **app.config.js**
**Configurar URL de la API según dónde ejecutes:**

```javascript
extra: {
  apiUrl: "http://localhost:8000",      // Navegador web
  // apiUrl: "http://10.0.2.2:8000",    // Emulador Android
  // apiUrl: "http://TU_IP:8000",       // Móvil físico
}
```

**Documentación:** CHEAT_SHEET.md (Configuración API)

---

## 🎓 Consejos Finales

### ✅ **Antes del Examen:**
- [ ] He leído al menos GUIA_EXAMEN.md o RESUMEN_VISUAL.md
- [ ] He practicado con SOLUCION_EXAMEN.md
- [ ] Tengo CHEAT_SHEET.md impreso o accesible
- [ ] Sé cómo ejecutar backend y frontend

### ✅ **Durante el Examen:**
- [ ] Backend corriendo (`uv run python main.py`)
- [ ] Frontend corriendo (`npx expo start`)
- [ ] CHEAT_SHEET.md abierto
- [ ] SOLUCION_EXAMEN.md abierto
- [ ] DevTools abierto (F12) para debug

### ✅ **Después del Examen:**
- [ ] He probado con GUIA_PRUEBAS.md
- [ ] Todo funciona correctamente
- [ ] He verificado la base de datos

---

## 🔍 Búsqueda Rápida

**¿Necesitas...?**

- **Entender TypeScript desde cero** → GUIA_EXAMEN.md
- **Ver cómo se conecta todo** → RESUMEN_VISUAL.md
- **Código completo para copiar** → SOLUCION_EXAMEN.md
- **Sintaxis de una petición HTTP** → CHEAT_SHEET.md
- **Validar que funciona** → GUIA_PRUEBAS.md
- **Tabla de endpoints** → CHEAT_SHEET.md
- **Configurar tsconfig.json** → SOLUCION_EXAMEN.md
- **Ejemplos de contraseñas válidas** → Cualquier guía

---

## 💡 Recuerda

> **Puedes llevar TODA esta documentación al examen.**
> 
> Los archivos más importantes son:
> 1. **CHEAT_SHEET.md** (referencia rápida)
> 2. **SOLUCION_EXAMEN.md** (código completo)
> 3. **GUIA_PRUEBAS.md** (para verificar)

---

¡Buena suerte en el examen! 🍀

**Última actualización:** Enero 2026
