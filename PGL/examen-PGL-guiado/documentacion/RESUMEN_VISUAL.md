# 🎯 RESUMEN VISUAL DEL EXAMEN

## 📊 FLUJO COMPLETO DE LA APLICACIÓN

```
┌─────────────────────────────────────────────────────────────────┐
│                    INICIO DE LA APLICACIÓN                      │
└─────────────────────────────────────────────────────────────────┘
                              ↓
                    ¿Tiene token guardado?
                              ↓
                    ┌─────────┴─────────┐
                    │                   │
                   SÍ                  NO
                    │                   │
                    ↓                   ↓
            ┌──────────────┐    ┌──────────────┐
            │    HOME      │    │    LOGIN     │
            │  (index.tsx) │    │  (login.tsx) │
            └──────────────┘    └──────────────┘
                    │                   │
                    │                   │ ¿No tiene cuenta?
                    │                   ↓
                    │           ┌──────────────┐
                    │           │   REGISTER   │
                    │           │(register.tsx)│
                    │           └──────────────┘
                    │                   │
                    │                   │ Validar contraseña
                    │                   │ (PARTE 1)
                    │                   ↓
                    │           POST /auth/register
                    │                   │
                    │                   ↓
                    │           POST /auth/login
                    │                   │
                    │                   │ Guardar token
                    │                   ↓
                    └───────────────────┤
                                        │
                                        ↓
                            ┌──────────────────┐
                            │      GRUPOS      │
                            │   (groups.tsx)   │
                            │   (PARTE 3)      │
                            └──────────────────┘
                                        │
                            ┌───────────┴───────────┐
                            │                       │
                    GET /groups              POST /groups
                    (Listar)                 (Crear)
                            │                       │
                            └───────────┬───────────┘
                                        │
                                        ↓
                            Seleccionar un grupo
                                        │
                                        ↓
                            ┌──────────────────┐
                            │     GASTOS       │
                            │  (expenses.tsx)  │
                            │   (PARTE 4)      │
                            └──────────────────┘
                                        │
                    ┌───────────────────┼───────────────────┐
                    │                   │                   │
            GET /expenses       POST /expenses      PUT /expenses
            (Listar)            (Añadir)            (Modificar)
                    │                   │                   │
                    └───────────────────┴───────────────────┘
                                        │
                                DELETE /expenses
                                  (Eliminar)
```

---

## 🔐 PARTE 1: VALIDACIÓN DE CONTRASEÑA

### Requisitos
```
┌─────────────────────────────────────────┐
│  CONTRASEÑA VÁLIDA DEBE TENER:          │
├─────────────────────────────────────────┤
│  ✓ Mínimo 8 caracteres                  │
│  ✓ Al menos 1 mayúscula (A-Z)           │
│  ✓ Al menos 1 minúscula (a-z)           │
│  ✓ Al menos 1 número (0-9)              │
│  ✓ Al menos 1 símbolo (!#$%&?)          │
└─────────────────────────────────────────┘
```

### Ejemplos
```
❌ "abc123"        → Muy corta, falta mayúscula y símbolo
❌ "Abcdefgh"      → Falta número y símbolo
❌ "ABCD1234"      → Falta minúscula y símbolo
❌ "Abc123"        → Muy corta, falta símbolo

✅ "Abc123!!"      → VÁLIDA
✅ "MiPass123#"    → VÁLIDA
✅ "Test2024?"     → VÁLIDA
```

### Código
```typescript
// Archivo: front/utils/passwordValidator.ts

export function validarPassword(password: string): boolean {
  return password.length >= 8 &&
         /[A-Z]/.test(password) &&
         /[a-z]/.test(password) &&
         /[0-9]/.test(password) &&
         /[!#$%&?]/.test(password);
}
```

---

## 🔑 PARTE 2: AUTENTICACIÓN

### Flujo de Registro
```
Usuario completa formulario
         ↓
Validar contraseña (PARTE 1)
         ↓
POST /auth/register
  Body: { username, password }
         ↓
Backend guarda usuario
         ↓
Redirigir a Login
```

### Flujo de Login
```
Usuario ingresa credenciales
         ↓
POST /auth/login
  Body: { username, password }
         ↓
Backend verifica credenciales
         ↓
Backend devuelve TOKEN
         ↓
Guardar token en SecureStore
         ↓
Usar token en futuras peticiones
```

### Formato del Token
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
               ^^^^^^ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
               Tipo   Token JWT
```

---

## 👥 PARTE 3: GESTIÓN DE GRUPOS

### Operaciones
```
┌─────────────────────────────────────────┐
│  OPERACIONES CON GRUPOS                 │
├─────────────────────────────────────────┤
│  1. Listar grupos del usuario           │
│     GET /groups                         │
│     Headers: Authorization              │
│                                         │
│  2. Crear nuevo grupo                   │
│     POST /groups                        │
│     Headers: Content-Type, Authorization│
│     Body: { name: "Nombre del grupo" }  │
└─────────────────────────────────────────┘
```

### Estructura de Datos
```typescript
interface Grupo {
  id: number;        // ID único del grupo
  name: string;      // Nombre del grupo
}

// Ejemplo:
{
  id: 1,
  name: "Viaje a Madrid"
}
```

### Componente
```
┌─────────────────────────────────────────┐
│  PANTALLA DE GRUPOS                     │
├─────────────────────────────────────────┤
│  [Formulario]                           │
│  ┌─────────────────────────────────┐    │
│  │ Nombre: [____________]          │    │
│  │ [Crear Grupo]                   │    │
│  └─────────────────────────────────┘    │
│                                         │
│  [Lista de Grupos]                      │
│  ┌─────────────────────────────────┐    │
│  │ → Viaje a Madrid                │    │
│  │ → Piso compartido               │    │
│  │ → Cena de amigos                │    │
│  └─────────────────────────────────┘    │
└─────────────────────────────────────────┘
```

---

## 💰 PARTE 4: GESTIÓN DE GASTOS

### Operaciones
```
┌─────────────────────────────────────────────────────────┐
│  OPERACIONES CON GASTOS                                 │
├─────────────────────────────────────────────────────────┤
│  1. Listar gastos de un grupo                           │
│     GET /groups/{groupId}/expenses                      │
│                                                         │
│  2. Añadir gasto                                        │
│     POST /groups/{groupId}/expenses                     │
│     Body: { description, amount }                       │
│                                                         │
│  3. Modificar gasto                                     │
│     PUT /groups/{groupId}/expenses/{expenseId}          │
│     Body: { description?, amount? }                     │
│                                                         │
│  4. Eliminar gasto                                      │
│     DELETE /groups/{groupId}/expenses/{expenseId}       │
└─────────────────────────────────────────────────────────┘
```

### Estructura de Datos
```typescript
interface Gasto {
  id: number;          // ID único del gasto
  desc: string;        // Descripción
  amount: number;      // Cantidad en euros
  paid_by: number;     // ID del usuario que pagó
}

// Ejemplo:
{
  id: 1,
  desc: "Cena en restaurante",
  amount: 45.50,
  paid_by: 1
}
```

### Componente
```
┌─────────────────────────────────────────┐
│  GASTOS DEL GRUPO: Viaje a Madrid       │
├─────────────────────────────────────────┤
│  [Formulario]                           │
│  ┌─────────────────────────────────┐    │
│  │ Descripción: [____________]     │    │
│  │ Cantidad: [_____] €             │    │
│  │ [Añadir Gasto]                  │    │
│  └─────────────────────────────────┘    │
│                                         │
│  Total: 145.50 €                        │
│                                         │
│  [Lista de Gastos]                      │
│  ┌─────────────────────────────────┐    │
│  │ Cena restaurante                │    │
│  │ 45.50 €                         │    │
│  │ [Editar] [Eliminar]             │    │
│  ├─────────────────────────────────┤    │
│  │ Hotel                           │    │
│  │ 100.00 €                        │    │
│  │ [Editar] [Eliminar]             │    │
│  └─────────────────────────────────┘    │
└─────────────────────────────────────────┘
```

---

## 🔄 FLUJO DE DATOS

### Petición HTTP Completa
```
┌─────────────────────────────────────────┐
│  1. FRONTEND                            │
│  ────────────────────────────────────   │
│  const res = await fetch(url, {         │
│    method: "POST",                      │
│    headers: {                           │
│      "Content-Type": "application/json",│
│      "Authorization": "Bearer {token}"  │
│    },                                   │
│    body: JSON.stringify(datos)          │
│  });                                    │
└─────────────────────────────────────────┘
                    ↓
         [Petición HTTP por red]
                    ↓
┌─────────────────────────────────────────┐
│  2. BACKEND (Python/Flask)              │
│  ────────────────────────────────────   │
│  @jwt_required()                        │
│  def post(self):                        │
│    data = request.get_json()            │
│    # Procesar datos                     │
│    # Guardar en base de datos           │
│    return {"id": 1}, 200                │
└─────────────────────────────────────────┘
                    ↓
         [Respuesta HTTP por red]
                    ↓
┌─────────────────────────────────────────┐
│  3. FRONTEND                            │
│  ────────────────────────────────────   │
│  const data = await res.json();         │
│  if (res.ok) {                          │
│    // Éxito                             │
│  } else {                               │
│    // Error                             │
│  }                                      │
└─────────────────────────────────────────┘
```

---

## 🎨 COMPONENTES REACT NATIVE

### useState
```
┌─────────────────────────────────────────┐
│  const [valor, setValor] = useState("") │
│         ↑       ↑                       │
│      Valor   Función para               │
│      actual  cambiar valor              │
└─────────────────────────────────────────┘

Ejemplo:
const [username, setUsername] = useState("");
setUsername("alvaro");  // Cambia username a "alvaro"
```

### useEffect
```
┌─────────────────────────────────────────┐
│  useEffect(() => {                      │
│    // Código que se ejecuta             │
│  }, [dependencias]);                    │
│      ↑                                  │
│      └─ [] = solo al montar             │
│         [x] = cuando x cambia           │
└─────────────────────────────────────────┘

Ejemplo:
useEffect(() => {
  cargarGrupos();
}, []); // Se ejecuta una vez al inicio
```

### FlatList
```
┌─────────────────────────────────────────┐
│  <FlatList                              │
│    data={array}                         │
│    keyExtractor={(item) => item.id}     │
│    renderItem={({ item }) => (          │
│      <View>                             │
│        <Text>{item.name}</Text>         │
│      </View>                            │
│    )}                                   │
│  />                                     │
└─────────────────────────────────────────┘
```

---

## 🗂️ ESTRUCTURA DE ARCHIVOS

```
front/
├── app/
│   ├── _layout.tsx         ← Layout general (ya existe)
│   ├── index.tsx           ← Home (modificar)
│   ├── login.tsx           ← Login (modificar)
│   ├── register.tsx        ← Registro (modificar)
│   ├── groups.tsx          ← Grupos (CREAR) ✨
│   └── expenses.tsx        ← Gastos (CREAR) ✨
│
├── context/
│   └── AuthContext.tsx     ← Autenticación (ya existe)
│
└── utils/
    └── passwordValidator.ts ← Validador (CREAR) ✨
```

---

## ✅ CHECKLIST PASO A PASO

### Preparación
```
□ Backend corriendo en http://localhost:8000
□ Frontend instalado (npm install)
□ Frontend corriendo (npx expo start)
```

### Parte 1: Validación
```
□ Crear carpeta utils/
□ Crear passwordValidator.ts
□ Implementar validarPassword()
□ Implementar obtenerErrorPassword()
□ Modificar register.tsx
□ Probar con contraseña inválida
□ Probar con contraseña válida
```

### Parte 2: Autenticación
```
□ Probar registro con usuario nuevo
□ Verificar que se guarda en BD
□ Probar login con credenciales correctas
□ Verificar que se recibe token
□ Verificar que se guarda token
□ Verificar que se redirige a home
```

### Parte 3: Grupos
```
□ Crear groups.tsx
□ Implementar cargarGrupos()
□ Implementar crearGrupo()
□ Probar listar grupos vacío
□ Probar crear grupo
□ Probar listar grupos con datos
□ Verificar navegación a gastos
```

### Parte 4: Gastos
```
□ Crear expenses.tsx
□ Implementar cargarGastos()
□ Implementar añadirGasto()
□ Implementar modificarGasto()
□ Implementar eliminarGasto()
□ Probar añadir gasto
□ Probar modificar descripción
□ Probar modificar cantidad
□ Probar eliminar con confirmación
□ Verificar cálculo de total
```

---

## 🎯 PUNTOS CLAVE PARA RECORDAR

```
┌─────────────────────────────────────────────────────────┐
│  1. SIEMPRE incluir token en peticiones autenticadas    │
│     Authorization: Bearer {token}                       │
│                                                         │
│  2. SIEMPRE usar JSON.stringify() al enviar datos       │
│     body: JSON.stringify({ campo: valor })              │
│                                                         │
│  3. SIEMPRE usar await con fetch y res.json()           │
│     const res = await fetch(...)                        │
│     const data = await res.json()                       │
│                                                         │
│  4. SIEMPRE validar datos antes de enviar               │
│     if (!campo.trim()) return;                          │
│                                                         │
│  5. SIEMPRE manejar errores con try/catch               │
│     try { ... } catch (err) { ... }                     │
│                                                         │
│  6. SIEMPRE mostrar feedback al usuario                 │
│     setError("mensaje") o Alert.alert()                 │
│                                                         │
│  7. SIEMPRE recargar datos después de modificar         │
│     await crearGrupo(); cargarGrupos();                 │
└─────────────────────────────────────────────────────────┘
```

---

## 🚀 COMANDOS RÁPIDOS

### Iniciar Backend
```bash
cd back
uv run python main.py
```

### Iniciar Frontend
```bash
cd front
npx expo start
# Presionar 'w' para web
```

### Ver API Documentation
```
http://localhost:8000/
```

---

¡Éxito en el examen! 🎓
