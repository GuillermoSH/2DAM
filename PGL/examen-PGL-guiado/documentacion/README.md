# 📚 ÍNDICE GENERAL - DOCUMENTACIÓN DEL EXAMEN

## 🎯 ¿Qué archivo necesito?

Esta es tu guía rápida para saber qué documento usar según lo que necesites.

---

## 📖 ANTES DEL EXAMEN (Para estudiar)

### 1. **GUIA_EXAMEN.md** 📘
**Cuándo usar:** Para aprender desde cero

**Contenido:**
- Introducción completa a TypeScript
- Explicación detallada de cada parte del examen
- Conceptos básicos explicados paso a paso
- Ejemplos con explicaciones
- Arquitectura del proyecto

**Ideal para:**
- Primera lectura
- Entender conceptos
- Aprender TypeScript desde cero

---

### 2. **SOLUCION_EXAMEN.md** 💡
**Cuándo usar:** Para ver el código completo con comentarios

**Contenido:**
- Código completo de TODOS los archivos
- Comentarios detallados línea por línea
- Explicación de cada función
- Solución completa de las 4 partes
- Instrucciones de instalación

**Ideal para:**
- Copiar código directamente
- Entender cómo funciona cada parte
- Ver la solución completa

---

### 3. **RESUMEN_VISUAL.md** 🎨
**Cuándo usar:** Para entender el flujo general

**Contenido:**
- Diagramas de flujo
- Esquemas visuales
- Estructura de datos
- Flujo de peticiones HTTP
- Componentes React Native explicados visualmente

**Ideal para:**
- Entender cómo se conecta todo
- Ver el "big picture"
- Repasar rápidamente

---

## 🚀 DURANTE EL EXAMEN (Referencia rápida)

### 4. **CHEAT_SHEET.md** ⚡
**Cuándo usar:** Durante el examen como referencia rápida

**Contenido:**
- Tabla de endpoints
- Snippets de código listos para copiar
- Plantillas de peticiones HTTP
- Respuestas rápidas a preguntas comunes
- Errores comunes y cómo evitarlos

**Ideal para:**
- Consulta rápida durante el examen
- Copiar código específico
- Recordar sintaxis
- Ver ejemplos concretos

---

### 5. **GUIA_PRUEBAS.md** 🧪
**Cuándo usar:** Para probar que todo funciona

**Contenido:**
- 40 casos de prueba
- Datos de prueba recomendados
- Checklist de verificación
- Cómo verificar base de datos
- Cómo usar DevTools

**Ideal para:**
- Probar tu solución
- Verificar que todo funciona
- Encontrar errores
- Asegurarte de que cumples todos los requisitos

---

## 💻 ARCHIVOS DE CÓDIGO (Para copiar)

### Archivos que YA EXISTEN (modificar):
- `front/app/register.tsx` - Ya modificado con validación
- `front/app/login.tsx` - Versión original (puedes mejorarla)
- `front/app/index.tsx` - Versión original (puedes mejorarla)
- `front/context/AuthContext.tsx` - Ya existe

### Archivos que DEBES CREAR:
- `front/utils/passwordValidator.ts` - ✅ Ya creado
- `front/app/groups.tsx` - ✅ Ya creado
- `front/app/expenses.tsx` - ✅ Ya creado

---

## 🗺️ RUTA DE APRENDIZAJE RECOMENDADA

### Día 1-2: Entender el proyecto
```
1. Leer GUIA_EXAMEN.md completa
2. Ver RESUMEN_VISUAL.md para entender el flujo
3. Ejecutar el proyecto existente
4. Explorar el código del backend
```

### Día 3-4: Implementar la solución
```
1. Abrir SOLUCION_EXAMEN.md
2. Crear los archivos nuevos
3. Copiar el código con los comentarios
4. Entender cada parte mientras copias
```

### Día 5: Probar y practicar
```
1. Usar GUIA_PRUEBAS.md
2. Probar todos los casos de prueba
3. Practicar sin mirar la solución
4. Tener CHEAT_SHEET.md a mano
```

### Día del examen:
```
1. Llevar CHEAT_SHEET.md impreso
2. Llevar SOLUCION_EXAMEN.md impreso
3. Tener GUIA_PRUEBAS.md para verificar
```

---

## 📋 RESUMEN DE CADA PARTE DEL EXAMEN

### PARTE 1: Validación de Contraseña
**Archivo principal:** `passwordValidator.ts`
**Documentación:** SOLUCION_EXAMEN.md (Parte 1)
**Referencia rápida:** CHEAT_SHEET.md (Parte 1)

**Qué hacer:**
1. Crear función `validarPassword()`
2. Crear función `obtenerErrorPassword()`
3. Integrar en `register.tsx`

---

### PARTE 2: Registro e Inicio de Sesión
**Archivos principales:** `register.tsx`, `login.tsx`, `AuthContext.tsx`
**Documentación:** SOLUCION_EXAMEN.md (Parte 2)
**Referencia rápida:** CHEAT_SHEET.md (Parte 2)

**Qué hacer:**
1. Mejorar componente de registro
2. Mejorar componente de login
3. Verificar que el token se guarda
4. Verificar que el token se usa en peticiones

---

### PARTE 3: Gestión de Grupos
**Archivo principal:** `groups.tsx`
**Documentación:** SOLUCION_EXAMEN.md (Parte 3)
**Referencia rápida:** CHEAT_SHEET.md (Parte 3)

**Qué hacer:**
1. Crear pantalla de grupos
2. Implementar listar grupos
3. Implementar crear grupo
4. Implementar navegación a gastos

---

### PARTE 4: Gestión de Gastos
**Archivo principal:** `expenses.tsx`
**Documentación:** SOLUCION_EXAMEN.md (Parte 4)
**Referencia rápida:** CHEAT_SHEET.md (Parte 4)

**Qué hacer:**
1. Crear pantalla de gastos
2. Implementar listar gastos
3. Implementar añadir gasto
4. Implementar modificar gasto
5. Implementar eliminar gasto

---

## 🎯 ESTRATEGIA PARA EL EXAMEN

### Si tienes MUCHO tiempo (3+ horas):
```
1. Leer GUIA_EXAMEN.md completa
2. Implementar siguiendo SOLUCION_EXAMEN.md
3. Probar con GUIA_PRUEBAS.md
4. Usar CHEAT_SHEET.md para dudas
```

### Si tienes TIEMPO NORMAL (2-3 horas):
```
1. Leer RESUMEN_VISUAL.md rápido
2. Copiar código de SOLUCION_EXAMEN.md
3. Probar casos básicos de GUIA_PRUEBAS.md
4. Usar CHEAT_SHEET.md constantemente
```

### Si tienes POCO tiempo (1-2 horas):
```
1. Ir directo a SOLUCION_EXAMEN.md
2. Copiar todo el código
3. Probar que funciona básicamente
4. Tener CHEAT_SHEET.md abierto todo el tiempo
```

---

## 📱 ARCHIVOS POR DISPOSITIVO

### Para llevar IMPRESOS al examen:
1. **CHEAT_SHEET.md** (IMPRESCINDIBLE)
2. **SOLUCION_EXAMEN.md** (Muy recomendado)
3. **GUIA_PRUEBAS.md** (Opcional pero útil)

### Para tener en el ORDENADOR durante el examen:
1. **SOLUCION_EXAMEN.md** (abierto en un monitor)
2. **CHEAT_SHEET.md** (abierto en otro monitor o ventana)
3. **GUIA_PRUEBAS.md** (para verificar)

### Para estudiar en CASA:
1. **GUIA_EXAMEN.md** (lectura completa)
2. **RESUMEN_VISUAL.md** (para entender)
3. **SOLUCION_EXAMEN.md** (para practicar)

---

## 🔍 BÚSQUEDA RÁPIDA

### "¿Cómo valido una contraseña?"
→ CHEAT_SHEET.md - Parte 1

### "¿Cómo hago una petición POST?"
→ CHEAT_SHEET.md - Plantilla de petición HTTP

### "¿Qué endpoints existen?"
→ CHEAT_SHEET.md - Tabla de endpoints

### "¿Cómo funciona useState?"
→ GUIA_EXAMEN.md - Conceptos de React Native
→ RESUMEN_VISUAL.md - Componentes React Native

### "¿Qué archivos debo crear?"
→ Este archivo - Sección "Archivos de código"

### "¿Cómo pruebo que funciona?"
→ GUIA_PRUEBAS.md - Checklist completo

### "¿Qué es un token JWT?"
→ GUIA_EXAMEN.md - Parte 2

### "¿Código completo de groups.tsx?"
→ SOLUCION_EXAMEN.md - Parte 3

### "¿Código completo de expenses.tsx?"
→ SOLUCION_EXAMEN.md - Parte 4

---

## 📊 TABLA COMPARATIVA

| Documento | Páginas | Nivel | Cuándo usar | Tiempo lectura |
|-----------|---------|-------|-------------|----------------|
| GUIA_EXAMEN.md | ~50 | Principiante | Estudiar | 2-3 horas |
| SOLUCION_EXAMEN.md | ~40 | Intermedio | Implementar | 1-2 horas |
| CHEAT_SHEET.md | ~10 | Todos | Examen | 5-10 min |
| RESUMEN_VISUAL.md | ~15 | Todos | Repasar | 15-30 min |
| GUIA_PRUEBAS.md | ~20 | Intermedio | Probar | 30-60 min |

---

## ✅ CHECKLIST FINAL

### Antes del examen:
- [ ] He leído GUIA_EXAMEN.md
- [ ] He entendido RESUMEN_VISUAL.md
- [ ] He practicado con SOLUCION_EXAMEN.md
- [ ] He probado con GUIA_PRUEBAS.md
- [ ] Tengo CHEAT_SHEET.md impreso

### Durante el examen:
- [ ] Backend corriendo
- [ ] Frontend corriendo
- [ ] CHEAT_SHEET.md a mano
- [ ] SOLUCION_EXAMEN.md abierto
- [ ] DevTools abierto para debug

### Después del examen:
- [ ] He probado todos los casos de GUIA_PRUEBAS.md
- [ ] Todo funciona correctamente
- [ ] He verificado la base de datos

---

## 🎓 CONSEJOS FINALES

1. **No memorices, entiende**: Los documentos están para consultarlos
2. **Practica antes**: No llegues al examen sin haber probado el código
3. **Ten todo a mano**: Imprime o ten abiertos los documentos clave
4. **Prueba constantemente**: Usa GUIA_PRUEBAS.md para verificar
5. **Usa CHEAT_SHEET.md**: Es tu mejor amigo durante el examen

---

## 📞 ESTRUCTURA DE LOS DOCUMENTOS

```
📁 Examen pgl/
├── 📄 README.md (este archivo)
├── 📘 GUIA_EXAMEN.md (Guía completa de estudio)
├── 💡 SOLUCION_EXAMEN.md (Código completo con comentarios)
├── ⚡ CHEAT_SHEET.md (Referencia rápida)
├── 🎨 RESUMEN_VISUAL.md (Diagramas y flujos)
├── 🧪 GUIA_PRUEBAS.md (Casos de prueba)
│
├── 📁 back/ (Backend Python/Flask)
│   ├── main.py
│   └── ...
│
└── 📁 front/ (Frontend React Native)
    ├── app/
    │   ├── register.tsx (✅ Modificado)
    │   ├── groups.tsx (✅ Creado)
    │   └── expenses.tsx (✅ Creado)
    └── utils/
        └── passwordValidator.ts (✅ Creado)
```

---

¡Mucha suerte en el examen! 🍀

Recuerda: **Todos estos documentos puedes llevarlos al examen**. Úsalos sabiamente.
