# 📚 Guía Completa para el Examen - SplitExpenser

## 📋 Índice
1. [Introducción al Proyecto](#introducción-al-proyecto)
2. [Conceptos Básicos de TypeScript](#conceptos-básicos-de-typescript)
3. [Arquitectura del Proyecto](#arquitectura-del-proyecto)
4. [Parte 1: Verificación de Contraseña](#parte-1-verificación-de-contraseña)
5. [Parte 2: Registro e Inicio de Sesión](#parte-2-registro-e-inicio-de-sesión)
6. [Parte 3: Gestión de Grupos](#parte-3-gestión-de-grupos)
7. [Parte 4: Gestión de Gastos](#parte-4-gestión-de-gastos)
8. [Conceptos Clave para Entender](#conceptos-clave-para-entender)
9. [Checklist del Examen](#checklist-del-examen)

---

## 🎯 Introducción al Proyecto

**SplitExpenser** es una aplicación para gestionar y dividir gastos entre grupos de personas. Tiene dos partes:

- **Backend (back/)**: API REST hecha en Python con Flask que maneja la lógica del servidor
- **Frontend (front/)**: Aplicación móvil hecha con React Native y Expo usando TypeScript

### ¿Qué es TypeScript?
TypeScript es como JavaScript pero con "tipos". Imagina que JavaScript es escribir en un cuaderno sin reglas, y TypeScript es escribir en un formulario donde cada campo tiene instrucciones claras de qué poner.

**Ejemplo:**
```javascript
// JavaScript (sin tipos)
let nombre = "Juan";
nombre = 123; // Esto funciona pero puede causar errores

// TypeScript (con tipos)
let nombre: string = "Juan";
nombre = 123; // ¡ERROR! TypeScript te avisa que nombre debe ser texto
```

---

## 📖 Conceptos Básicos de TypeScript

### 1. Tipos Básicos

```typescript
// Texto (string)
let usuario: string = "alvaro";

// Número (number)
let edad: number = 25;

// Verdadero/Falso (boolean)
let estaLogueado: boolean = true;

// Nulo o indefinido
let token: string | null = null; // Puede ser texto O null

// Array (lista)
let numeros: number[] = [1, 2, 3];
let nombres: string[] = ["Ana", "Luis"];
```

### 2. Interfaces y Tipos

Las interfaces son como "plantillas" que definen la forma de un objeto:

```typescript
// Definimos cómo debe verse un usuario
interface Usuario {
  id: number;
  username: string;
  password: string;
}

// Ahora podemos crear usuarios que sigan esta plantilla
const usuario: Usuario = {
  id: 1,
  username: "alvaro",
  password: "MiPass123!"
};
```

### 3. Funciones con Tipos

```typescript
// Función que recibe dos strings y devuelve un objeto
async function login(username: string, password: string): Promise<any> {
  // código aquí
  return { access_token: "abc123" };
}

// Función que no devuelve nada (void)
async function logout(): Promise<void> {
  // código aquí
}
```

### 4. Conceptos de React Native con TypeScript

```typescript
// useState: para guardar datos que cambian
const [username, setUsername] = useState<string>("");
// username es el valor actual
// setUsername es la función para cambiar el valor

// useEffect: para ejecutar código cuando algo cambia
useEffect(() => {
  // Este código se ejecuta cuando 'token' cambia
  if (!token) {
    router.replace("/login");
  }
}, [token]); // [token] indica "ejecuta esto cuando token cambie"

// useContext: para acceder a datos compartidos
const { token, login, logout } = useContext(AuthContext);
```

---

## 🏗️ Arquitectura del Proyecto

### Backend (Python/Flask)
```
back/
├── main.py          # Toda la API está aquí
├── pyproject.toml   # Configuración del proyecto
└── test_main.py     # Tests
```

### Frontend (React Native/TypeScript)
```
front/
├── app/
│   ├── index.tsx      # Página principal (Home)
│   ├── login.tsx      # Página de login
│   ├── register.tsx   # Página de registro
│   └── _layout.tsx    # Layout general
├── context/
│   └── AuthContext.tsx # Maneja autenticación
└── package.json       # Dependencias
```

### Endpoints de la API (Backend)

| Método | Endpoint | Descripción | Requiere Auth |
|--------|----------|-------------|---------------|
| POST | `/auth/register` | Registrar usuario | ❌ |
| POST | `/auth/login` | Iniciar sesión | ❌ |
| GET | `/groups` | Listar grupos del usuario | ✅ |
| POST | `/groups` | Crear un grupo | ✅ |
| GET | `/groups/{id}` | Ver detalles de un grupo | ✅ |
| GET | `/groups/{id}/expenses` | Listar gastos de un grupo | ✅ |
| POST | `/groups/{id}/expenses` | Crear un gasto | ✅ |
| PUT | `/groups/{id}/expenses/{expense_id}` | Modificar un gasto | ✅ |
| DELETE | `/groups/{id}/expenses/{expense_id}` | Eliminar un gasto | ✅ |

---

## 🔐 Parte 1: Verificación de Contraseña

### ¿Qué te piden?
Crear una función que verifique que una contraseña cumple estos requisitos:
- Al menos 1 mayúscula
- Al menos 1 minúscula
- Al menos 1 número
- Al menos 1 símbolo (!, #, $, %, &, ?)
- Mínimo 8 caracteres

### Solución Paso a Paso

#### Opción 1: Función Simple (Recomendada para el examen)

```typescript
// Función para validar contraseña
function validarPassword(password: string): boolean {
  // Verificar longitud mínima
  if (password.length < 8) {
    return false;
  }
  
  // Verificar mayúscula
  const tieneMayuscula = /[A-Z]/.test(password);
  
  // Verificar minúscula
  const tieneMinuscula = /[a-z]/.test(password);
  
  // Verificar número
  const tieneNumero = /[0-9]/.test(password);
  
  // Verificar símbolo
  const tieneSimbolo = /[!#$%&?]/.test(password);
  
  // Todas las condiciones deben cumplirse
  return tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo;
}

// Función para obtener mensaje de error específico
function obtenerErrorPassword(password: string): string {
  if (password.length < 8) {
    return "La contraseña debe tener al menos 8 caracteres";
  }
  if (!/[A-Z]/.test(password)) {
    return "La contraseña debe tener al menos una mayúscula";
  }
  if (!/[a-z]/.test(password)) {
    return "La contraseña debe tener al menos una minúscula";
  }
  if (!/[0-9]/.test(password)) {
    return "La contraseña debe tener al menos un número";
  }
  if (!/[!#$%&?]/.test(password)) {
    return "La contraseña debe tener al menos un símbolo (!#$%&?)";
  }
  return "";
}
```

#### ¿Qué significan esos símbolos raros? (Expresiones Regulares)

```typescript
/[A-Z]/     // Busca cualquier letra mayúscula de A a Z
/[a-z]/     // Busca cualquier letra minúscula de a a z
/[0-9]/     // Busca cualquier número del 0 al 9
/[!#$%&?]/  // Busca cualquiera de estos símbolos: ! # $ % & ?

.test(password)  // Comprueba si el patrón existe en password
                 // Devuelve true o false
```

#### Opción 2: Función con Mensajes Detallados

```typescript
interface ValidacionPassword {
  esValida: boolean;
  errores: string[];
}

function validarPasswordDetallado(password: string): ValidacionPassword {
  const errores: string[] = [];
  
  if (password.length < 8) {
    errores.push("Mínimo 8 caracteres");
  }
  if (!/[A-Z]/.test(password)) {
    errores.push("Falta una mayúscula");
  }
  if (!/[a-z]/.test(password)) {
    errores.push("Falta una minúscula");
  }
  if (!/[0-9]/.test(password)) {
    errores.push("Falta un número");
  }
  if (!/[!#$%&?]/.test(password)) {
    errores.push("Falta un símbolo (!#$%&?)");
  }
  
  return {
    esValida: errores.length === 0,
    errores: errores
  };
}
```

#### Cómo Usar la Validación en el Registro

```typescript
// En register.tsx
const handleRegister = async () => {
  // Validar contraseña ANTES de enviar al servidor
  if (!validarPassword(password)) {
    const error = obtenerErrorPassword(password);
    setError(error);
    return; // No continuar si la contraseña no es válida
  }
  
  // Si llegamos aquí, la contraseña es válida
  const res = await register(username, password);
  if (res.ok) {
    router.replace("/login");
  } else {
    setError(res.msg || "Error al registrar");
  }
};
```

### Ejemplos de Contraseñas

```typescript
// ❌ INVÁLIDAS
"abc123"        // Falta mayúscula y símbolo, muy corta
"ABCDEFGH"      // Falta minúscula, número y símbolo
"Abcdefgh"      // Falta número y símbolo
"Abcdefg1"      // Falta símbolo

// ✅ VÁLIDAS
"Abc123!!"      // Tiene todo
"MiPass123#"    // Tiene todo
"Segura1$"      // Tiene todo
"Test2024!"     // Tiene todo
```

---

## 🔑 Parte 2: Registro e Inicio de Sesión

### Entendiendo el Flujo de Autenticación

```
1. Usuario se registra → Backend guarda usuario en BD
2. Usuario hace login → Backend devuelve TOKEN
3. Usuario guarda TOKEN → Se usa para futuras peticiones
4. Peticiones con TOKEN → Backend verifica identidad
```

### ¿Qué es un TOKEN?
Un token es como una "tarjeta de identificación digital". Cuando haces login, el servidor te da un token que debes incluir en todas las peticiones futuras para demostrar quién eres.

**Ejemplo de token:**
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

### Registro de Usuario

#### Código del Backend (Python)
```python
# POST /auth/register
@auth_ns.route("/register")
class Register(Resource):
    def post(self):
        # 1. Obtener datos del JSON
        data = request.get_json()
        username = data.get("username")
        password = data.get("password")
        
        # 2. Validar que existan
        if not username or not password:
            return {"msg": "Faltan campos"}, 400
        
        # 3. Verificar si el usuario ya existe
        if User.query.filter_by(username=username).first():
            return {"msg": "Usuario ya existe"}, 409
        
        # 4. Encriptar contraseña y guardar
        hashed_pw = generate_password_hash(password)
        user = User(username=username, password=hashed_pw)
        db.session.add(user)
        db.session.commit()
        
        return {"msg": "User registered"}
```

#### Código del Frontend (TypeScript)

```typescript
// En AuthContext.tsx
const register = async (username: string, password: string) => {
  try {
    // 1. Hacer petición POST al backend
    const res = await fetch(`${API_URL}/auth/register`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });
    
    // 2. Convertir respuesta a JSON
    return await res.json();
  } catch (err) {
    return { ok: false, msg: "Network error" };
  }
};
```

#### Explicación Detallada del Fetch

```typescript
fetch(url, opciones)
// fetch es una función para hacer peticiones HTTP

// URL: dirección del endpoint
const url = "http://localhost:8000/auth/register";

// Opciones:
{
  method: "POST",  // Tipo de petición (GET, POST, PUT, DELETE)
  
  headers: {
    "Content-Type": "application/json"  // Decimos que enviamos JSON
  },
  
  body: JSON.stringify({ username, password })
  // JSON.stringify convierte un objeto JavaScript a texto JSON
  // { username: "alvaro", password: "Pass123!" }
  // se convierte en:
  // '{"username":"alvaro","password":"Pass123!"}'
}
```

### Inicio de Sesión (Login)

#### Código del Backend (Python)
```python
# POST /auth/login
@auth_ns.route("/login")
class Login(Resource):
    def post(self):
        # 1. Obtener credenciales
        data = request.get_json()
        username = data.get("username")
        password = data.get("password")
        
        # 2. Buscar usuario en BD
        user = User.query.filter_by(username=username).first()
        
        # 3. Verificar contraseña
        if user and check_password_hash(user.password, password):
            # 4. Crear y devolver token
            token = create_access_token(identity=str(user.id))
            return {"access_token": token}, 200
        
        return {"msg": "Bad credentials"}, 401
```

#### Código del Frontend (TypeScript)

```typescript
// En AuthContext.tsx
const login = async (username: string, password: string) => {
  try {
    // 1. Hacer petición al backend
    const res = await fetch(`${API_URL}/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });
    
    // 2. Obtener respuesta
    const data = await res.json();
    
    // 3. Si el login fue exitoso, guardar token
    if (res.ok && data.access_token) {
      // Guardar token de forma segura en el dispositivo
      await SecureStore.setItemAsync(TOKEN_KEY, data.access_token);
      setToken(data.access_token);
    }
    
    return data;
  } catch (err) {
    return { ok: false, msg: "Network error" };
  }
};
```

### Componente de Registro Completo

```typescript
// register.tsx
import { useRouter } from "expo-router";
import React, { useContext, useState } from "react";
import { Button, Text, TextInput, View } from "react-native";
import { AuthContext } from "../context/AuthContext";

export default function Register() {
  // 1. Obtener función de registro del contexto
  const { register } = useContext(AuthContext);
  const router = useRouter();
  
  // 2. Estados para los campos del formulario
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");

  // 3. Función que se ejecuta al presionar "Registrar"
  const handleRegister = async () => {
    // Validar contraseña (Parte 1)
    if (!validarPassword(password)) {
      setError(obtenerErrorPassword(password));
      return;
    }
    
    // Llamar a la función de registro
    const res = await register(username, password);
    
    // Si fue exitoso, ir a login
    if (res.ok) {
      router.replace("/login");
    } else {
      // Si hubo error, mostrarlo
      setError(res.msg || "Error al registrar");
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: "center", padding: 20 }}>
      {/* Campo de usuario */}
      <TextInput
        placeholder="Usuario"
        value={username}
        onChangeText={setUsername}
        style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
      />
      
      {/* Campo de contraseña */}
      <TextInput
        placeholder="Contraseña"
        value={password}
        onChangeText={setPassword}
        secureTextEntry  // Oculta el texto
        style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
      />
      
      {/* Mostrar error si existe */}
      {error ? <Text style={{ color: "red" }}>{error}</Text> : null}
      
      {/* Botón de registro */}
      <Button title="Registrar" onPress={handleRegister} />
      
      {/* Botón para volver al login */}
      <Button title="Volver al login" onPress={() => router.push("/login")} />
    </View>
  );
}
```

### Componente de Login Completo

```typescript
// login.tsx
export default function Login() {
  const { login } = useContext(AuthContext);
  const router = useRouter();
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");

  const handleLogin = async () => {
    const res = await login(username, password);
    
    // Si recibimos un token, el login fue exitoso
    if (res.access_token) {
      router.replace("/");  // Ir a la página principal
    } else {
      setError(res.msg || "Login fallido");
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: "center", padding: 20 }}>
      <TextInput
        placeholder="Usuario"
        value={username}
        onChangeText={setUsername}
        style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
      />
      <TextInput
        placeholder="Contraseña"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
      />
      {error ? <Text style={{ color: "red" }}>{error}</Text> : null}
      <Button title="Login" onPress={handleLogin} />
      <Button title="Registrarse" onPress={() => router.push("/register")} />
    </View>
  );
}
```

---

## 👥 Parte 3: Gestión de Grupos

### ¿Qué es un Grupo?
Un grupo es una colección de usuarios que comparten gastos. Por ejemplo: "Viaje a Madrid", "Piso compartido", "Cena de amigos".

### Estructura de Datos

```typescript
// Interfaz para un grupo
interface Grupo {
  id: number;
  name: string;
  members?: string[];  // Lista de nombres de usuario (opcional)
}

// Ejemplo:
const grupo: Grupo = {
  id: 1,
  name: "Viaje a Madrid",
  members: ["alvaro", "maria", "juan"]
};
```

### Crear un Grupo

#### Backend (Python)
```python
# POST /groups
@groups_ns.route("")
class GroupList(Resource):
    @jwt_required()  # Requiere estar autenticado
    def post(self):
        # 1. Obtener nombre del grupo
        data = request.get_json()
        name = data.get("name")
        
        # 2. Obtener ID del usuario autenticado
        user_id = get_jwt_identity()
        user = db.session.get(User, user_id)
        
        # 3. Crear grupo y añadir usuario
        group = Group(name=name)
        group.users.append(user)
        db.session.add(group)
        db.session.commit()
        
        # 4. Devolver grupo creado
        return {"id": group.id, "name": group.name}
```

#### Frontend (TypeScript)

```typescript
// Función para crear un grupo
async function crearGrupo(nombre: string, token: string) {
  try {
    const res = await fetch(`${API_URL}/groups`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`  // ¡IMPORTANTE! Incluir token
      },
      body: JSON.stringify({ name: nombre })
    });
    
    return await res.json();
  } catch (err) {
    return { error: "Error de red" };
  }
}
```

#### ¿Qué es "Bearer" en Authorization?
```typescript
"Authorization": `Bearer ${token}`
// "Bearer" significa "portador" en inglés
// Es el formato estándar para enviar tokens JWT
// Ejemplo completo:
// "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### Listar Grupos del Usuario

#### Backend (Python)
```python
# GET /groups
@groups_ns.route("")
class GroupList(Resource):
    @jwt_required()
    def get(self):
        # 1. Obtener usuario autenticado
        user_id = get_jwt_identity()
        user = db.session.get(User, user_id)
        
        # 2. Devolver lista de grupos del usuario
        return [{"id": g.id, "name": g.name} for g in user.groups]
```

#### Frontend (TypeScript)

```typescript
// Función para obtener grupos
async function obtenerGrupos(token: string): Promise<Grupo[]> {
  try {
    const res = await fetch(`${API_URL}/groups`, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`
      }
    });
    
    return await res.json();
  } catch (err) {
    return [];
  }
}
```

### Componente Completo de Gestión de Grupos

```typescript
// groups.tsx (archivo nuevo que debes crear)
import React, { useContext, useEffect, useState } from "react";
import { Button, FlatList, Text, TextInput, View } from "react-native";
import { AuthContext } from "../context/AuthContext";
import Constants from "expo-constants";

const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";

interface Grupo {
  id: number;
  name: string;
}

export default function Groups() {
  const { token } = useContext(AuthContext);
  const [grupos, setGrupos] = useState<Grupo[]>([]);
  const [nombreGrupo, setNombreGrupo] = useState<string>("");
  const [error, setError] = useState<string>("");

  // Cargar grupos al iniciar
  useEffect(() => {
    cargarGrupos();
  }, []);

  // Función para cargar grupos del servidor
  const cargarGrupos = async () => {
    try {
      const res = await fetch(`${API_URL}/groups`, {
        headers: {
          "Authorization": `Bearer ${token}`
        }
      });
      const data = await res.json();
      setGrupos(data);
    } catch (err) {
      setError("Error al cargar grupos");
    }
  };

  // Función para crear un nuevo grupo
  const crearGrupo = async () => {
    if (!nombreGrupo.trim()) {
      setError("El nombre no puede estar vacío");
      return;
    }

    try {
      const res = await fetch(`${API_URL}/groups`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ name: nombreGrupo })
      });

      if (res.ok) {
        setNombreGrupo("");  // Limpiar campo
        cargarGrupos();      // Recargar lista
        setError("");
      } else {
        setError("Error al crear grupo");
      }
    } catch (err) {
      setError("Error de red");
    }
  };

  return (
    <View style={{ flex: 1, padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Mis Grupos</Text>

      {/* Formulario para crear grupo */}
      <TextInput
        placeholder="Nombre del grupo"
        value={nombreGrupo}
        onChangeText={setNombreGrupo}
        style={{ borderWidth: 1, padding: 8, marginBottom: 10 }}
      />
      <Button title="Crear Grupo" onPress={crearGrupo} />

      {/* Mostrar error si existe */}
      {error ? <Text style={{ color: "red" }}>{error}</Text> : null}

      {/* Lista de grupos */}
      <FlatList
        data={grupos}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={{ padding: 10, borderBottomWidth: 1 }}>
            <Text>{item.name}</Text>
          </View>
        )}
        style={{ marginTop: 20 }}
      />
    </View>
  );
}
```

#### Explicación de FlatList

```typescript
<FlatList
  data={grupos}  // Array de datos a mostrar
  
  keyExtractor={(item) => item.id.toString()}
  // Función que devuelve un ID único para cada elemento
  // React necesita esto para optimizar el renderizado
  
  renderItem={({ item }) => (
    // Cómo se muestra cada elemento
    <View>
      <Text>{item.name}</Text>
    </View>
  )}
/>
```

---

## 💰 Parte 4: Gestión de Gastos

### ¿Qué es un Gasto?
Un gasto es un registro de dinero gastado dentro de un grupo. Ejemplo: "Cena en restaurante - 45€".

### Estructura de Datos

```typescript
interface Gasto {
  id: number;
  desc: string;      // Descripción (en el backend se llama "description")
  amount: number;    // Cantidad en euros
  paid_by: number;   // ID del usuario que pagó
}

// Ejemplo:
const gasto: Gasto = {
  id: 1,
  desc: "Cena en restaurante",
  amount: 45.50,
  paid_by: 1
};
```

### Añadir un Gasto

#### Backend (Python)
```python
# POST /groups/{group_id}/expenses
@expenses_ns.route("/<int:group_id>/expenses")
class ExpenseList(Resource):
    @jwt_required()
    def post(self, group_id):
        # 1. Obtener datos
        data = request.get_json()
        description = data.get("description")
        amount = data.get("amount")
        
        # 2. Validar
        if not description or amount is None:
            return {"msg": "Faltan campos"}, 400
        
        # 3. Obtener usuario autenticado
        user_id = get_jwt_identity()
        
        # 4. Crear gasto
        expense = Expense(
            description=description,
            amount=float(amount),
            group_id=group_id,
            paid_by=user_id
        )
        db.session.add(expense)
        db.session.commit()
        
        return {"id": expense.id}
```

#### Frontend (TypeScript)

```typescript
async function añadirGasto(
  grupoId: number,
  descripcion: string,
  cantidad: number,
  token: string
) {
  try {
    const res = await fetch(`${API_URL}/groups/${grupoId}/expenses`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify({
        description: descripcion,
        amount: cantidad
      })
    });
    
    return await res.json();
  } catch (err) {
    return { error: "Error de red" };
  }
}
```

### Modificar un Gasto

#### Backend (Python)
```python
# PUT /groups/{group_id}/expenses/{expense_id}
@expenses_ns.route("/<int:group_id>/expenses/<int:expense_id>")
class ExpenseDetail(Resource):
    @jwt_required()
    def put(self, group_id, expense_id):
        data = request.get_json()
        expense = db.session.get(Expense, expense_id)
        
        # Actualizar campos si están presentes
        if "description" in data:
            expense.description = data["description"]
        if "amount" in data:
            expense.amount = float(data["amount"])
        
        db.session.commit()
        return {"msg": "Expense updated"}
```

#### Frontend (TypeScript)

```typescript
async function modificarGasto(
  grupoId: number,
  gastoId: number,
  descripcion?: string,  // Opcional
  cantidad?: number,     // Opcional
  token: string
) {
  // Crear objeto solo con los campos que se van a actualizar
  const body: any = {};
  if (descripcion !== undefined) body.description = descripcion;
  if (cantidad !== undefined) body.amount = cantidad;
  
  try {
    const res = await fetch(
      `${API_URL}/groups/${grupoId}/expenses/${gastoId}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(body)
      }
    );
    
    return await res.json();
  } catch (err) {
    return { error: "Error de red" };
  }
}
```

### Eliminar un Gasto

#### Backend (Python)
```python
# DELETE /groups/{group_id}/expenses/{expense_id}
@expenses_ns.route("/<int:group_id>/expenses/<int:expense_id>")
class ExpenseDetail(Resource):
    @jwt_required()
    def delete(self, group_id, expense_id):
        expense = db.session.get(Expense, expense_id)
        
        if not expense or expense.group_id != group_id:
            return {"msg": "Expense not found"}, 404
        
        db.session.delete(expense)
        db.session.commit()
        return {"msg": "Expense deleted"}
```

#### Frontend (TypeScript)

```typescript
async function eliminarGasto(
  grupoId: number,
  gastoId: number,
  token: string
) {
  try {
    const res = await fetch(
      `${API_URL}/groups/${grupoId}/expenses/${gastoId}`,
      {
        method: "DELETE",
        headers: {
          "Authorization": `Bearer ${token}`
        }
      }
    );
    
    return await res.json();
  } catch (err) {
    return { error: "Error de red" };
  }
}
```

### Componente Completo de Gestión de Gastos

```typescript
// expenses.tsx (archivo nuevo)
import React, { useContext, useEffect, useState } from "react";
import { Button, FlatList, Text, TextInput, View, Alert } from "react-native";
import { AuthContext } from "../context/AuthContext";
import { useLocalSearchParams } from "expo-router";
import Constants from "expo-constants";

const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";

interface Gasto {
  id: number;
  desc: string;
  amount: number;
  paid_by: number;
}

export default function Expenses() {
  const { token } = useContext(AuthContext);
  
  // Obtener ID del grupo de los parámetros de la URL
  const { groupId } = useLocalSearchParams();
  
  const [gastos, setGastos] = useState<Gasto[]>([]);
  const [descripcion, setDescripcion] = useState<string>("");
  const [cantidad, setCantidad] = useState<string>("");
  const [editandoId, setEditandoId] = useState<number | null>(null);

  useEffect(() => {
    cargarGastos();
  }, []);

  const cargarGastos = async () => {
    try {
      const res = await fetch(
        `${API_URL}/groups/${groupId}/expenses`,
        {
          headers: { "Authorization": `Bearer ${token}` }
        }
      );
      const data = await res.json();
      setGastos(data);
    } catch (err) {
      Alert.alert("Error", "No se pudieron cargar los gastos");
    }
  };

  const añadirGasto = async () => {
    if (!descripcion.trim() || !cantidad) {
      Alert.alert("Error", "Completa todos los campos");
      return;
    }

    try {
      const res = await fetch(
        `${API_URL}/groups/${groupId}/expenses`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          },
          body: JSON.stringify({
            description: descripcion,
            amount: parseFloat(cantidad)
          })
        }
      );

      if (res.ok) {
        setDescripcion("");
        setCantidad("");
        cargarGastos();
      }
    } catch (err) {
      Alert.alert("Error", "No se pudo añadir el gasto");
    }
  };

  const modificarGasto = async (id: number) => {
    try {
      const body: any = {};
      if (descripcion) body.description = descripcion;
      if (cantidad) body.amount = parseFloat(cantidad);

      const res = await fetch(
        `${API_URL}/groups/${groupId}/expenses/${id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          },
          body: JSON.stringify(body)
        }
      );

      if (res.ok) {
        setDescripcion("");
        setCantidad("");
        setEditandoId(null);
        cargarGastos();
      }
    } catch (err) {
      Alert.alert("Error", "No se pudo modificar el gasto");
    }
  };

  const eliminarGasto = async (id: number) => {
    Alert.alert(
      "Confirmar",
      "¿Eliminar este gasto?",
      [
        { text: "Cancelar", style: "cancel" },
        {
          text: "Eliminar",
          style: "destructive",
          onPress: async () => {
            try {
              const res = await fetch(
                `${API_URL}/groups/${groupId}/expenses/${id}`,
                {
                  method: "DELETE",
                  headers: { "Authorization": `Bearer ${token}` }
                }
              );

              if (res.ok) {
                cargarGastos();
              }
            } catch (err) {
              Alert.alert("Error", "No se pudo eliminar el gasto");
            }
          }
        }
      ]
    );
  };

  const iniciarEdicion = (gasto: Gasto) => {
    setEditandoId(gasto.id);
    setDescripcion(gasto.desc);
    setCantidad(gasto.amount.toString());
  };

  const cancelarEdicion = () => {
    setEditandoId(null);
    setDescripcion("");
    setCantidad("");
  };

  return (
    <View style={{ flex: 1, padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Gastos del Grupo</Text>

      {/* Formulario */}
      <TextInput
        placeholder="Descripción"
        value={descripcion}
        onChangeText={setDescripcion}
        style={{ borderWidth: 1, padding: 8, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Cantidad (€)"
        value={cantidad}
        onChangeText={setCantidad}
        keyboardType="numeric"
        style={{ borderWidth: 1, padding: 8, marginBottom: 10 }}
      />

      {editandoId ? (
        <View style={{ flexDirection: "row", gap: 10 }}>
          <Button
            title="Guardar Cambios"
            onPress={() => modificarGasto(editandoId)}
          />
          <Button title="Cancelar" onPress={cancelarEdicion} />
        </View>
      ) : (
        <Button title="Añadir Gasto" onPress={añadirGasto} />
      )}

      {/* Lista de gastos */}
      <FlatList
        data={gastos}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View
            style={{
              padding: 10,
              borderBottomWidth: 1,
              flexDirection: "row",
              justifyContent: "space-between",
              alignItems: "center"
            }}
          >
            <View>
              <Text style={{ fontWeight: "bold" }}>{item.desc}</Text>
              <Text>{item.amount.toFixed(2)} €</Text>
            </View>
            <View style={{ flexDirection: "row", gap: 10 }}>
              <Button title="Editar" onPress={() => iniciarEdicion(item)} />
              <Button
                title="Eliminar"
                color="red"
                onPress={() => eliminarGasto(item.id)}
              />
            </View>
          </View>
        )}
        style={{ marginTop: 20 }}
      />
    </View>
  );
}
```

---

## 🔑 Conceptos Clave para Entender

### 1. Async/Await

```typescript
// Async/Await es para operaciones que toman tiempo (como peticiones HTTP)

// ❌ SIN async/await (no funciona bien)
function obtenerDatos() {
  const res = fetch(url);  // Esto no espera, devuelve una Promise
  return res.json();       // ¡ERROR! res aún no tiene datos
}

// ✅ CON async/await (correcto)
async function obtenerDatos() {
  const res = await fetch(url);  // Espera a que termine la petición
  return await res.json();       // Espera a convertir a JSON
}

// Usar la función
const datos = await obtenerDatos();  // También necesita await
```

### 2. Promesas (Promises)

```typescript
// Una Promise es como un "pagaré" de un valor futuro

// Crear una Promise
const miPromise = new Promise((resolve, reject) => {
  // Simular operación que toma tiempo
  setTimeout(() => {
    resolve("¡Datos obtenidos!");  // Éxito
    // o
    reject("Error al obtener datos");  // Error
  }, 1000);
});

// Usar la Promise
miPromise
  .then(resultado => console.log(resultado))  // Si tuvo éxito
  .catch(error => console.log(error));        // Si hubo error

// O con async/await (más limpio)
try {
  const resultado = await miPromise;
  console.log(resultado);
} catch (error) {
  console.log(error);
}
```

### 3. Try/Catch

```typescript
// Try/Catch es para manejar errores

try {
  // Código que puede fallar
  const res = await fetch(url);
  const data = await res.json();
  return data;
} catch (error) {
  // Si algo falla, ejecuta esto
  console.log("Hubo un error:", error);
  return null;
}
```

### 4. JSON

```typescript
// JSON es un formato de texto para intercambiar datos

// Objeto JavaScript
const usuario = {
  nombre: "Alvaro",
  edad: 25
};

// Convertir a JSON (texto)
const json = JSON.stringify(usuario);
// Resultado: '{"nombre":"Alvaro","edad":25}'

// Convertir de JSON a objeto
const objeto = JSON.parse(json);
// Resultado: { nombre: "Alvaro", edad: 25 }
```

### 5. HTTP Methods

```typescript
// GET: Obtener datos (no modifica nada)
fetch(url, { method: "GET" })

// POST: Crear algo nuevo
fetch(url, { method: "POST", body: JSON.stringify(datos) })

// PUT: Actualizar algo existente
fetch(url, { method: "PUT", body: JSON.stringify(datos) })

// DELETE: Eliminar algo
fetch(url, { method: "DELETE" })
```

### 6. Códigos de Estado HTTP

```typescript
// 200-299: Éxito
200  // OK - Todo bien
201  // Created - Recurso creado

// 400-499: Error del cliente
400  // Bad Request - Petición mal formada
401  // Unauthorized - No autenticado
404  // Not Found - No encontrado
409  // Conflict - Conflicto (ej: usuario ya existe)

// 500-599: Error del servidor
500  // Internal Server Error - Error del servidor

// Verificar si fue exitoso
if (res.ok) {  // res.ok es true si el código está entre 200-299
  // Éxito
} else {
  // Error
}
```

---

## ✅ Checklist del Examen

### Parte 1: Verificación de Contraseña ✓
- [ ] Crear función `validarPassword(password: string): boolean`
- [ ] Verificar longitud mínima (8 caracteres)
- [ ] Verificar al menos una mayúscula
- [ ] Verificar al menos una minúscula
- [ ] Verificar al menos un número
- [ ] Verificar al menos un símbolo (!#$%&?)
- [ ] Crear función `obtenerErrorPassword(password: string): string` para mensajes
- [ ] Integrar validación en el componente de registro

### Parte 2: Registro e Inicio de Sesión ✓
- [ ] Implementar función `register` en AuthContext
- [ ] Implementar función `login` en AuthContext
- [ ] Crear componente `register.tsx`
- [ ] Crear componente `login.tsx`
- [ ] Probar registro con usuario y contraseña válidos
- [ ] Probar login y verificar que se recibe el token
- [ ] Guardar token en SecureStore
- [ ] Verificar que el token se incluye en peticiones futuras

### Parte 3: Gestión de Grupos ✓
- [ ] Crear componente para listar grupos
- [ ] Implementar función para obtener grupos del usuario
- [ ] Crear formulario para crear un nuevo grupo
- [ ] Implementar función para crear grupo
- [ ] Mostrar lista de grupos con FlatList
- [ ] Verificar que el token se envía en el header Authorization

### Parte 4: Gestión de Gastos ✓
- [ ] Crear componente para listar gastos de un grupo
- [ ] Implementar función para obtener gastos
- [ ] Crear formulario para añadir gasto
- [ ] Implementar función para añadir gasto
- [ ] Crear interfaz para modificar gasto
- [ ] Implementar función para modificar gasto (PUT)
- [ ] Implementar función para eliminar gasto (DELETE)
- [ ] Añadir confirmación antes de eliminar
- [ ] Recargar lista después de cada operación

### Verificaciones Generales ✓
- [ ] Todos los endpoints usan el token correcto
- [ ] Los errores se muestran al usuario
- [ ] Los formularios se limpian después de operaciones exitosas
- [ ] La navegación funciona correctamente
- [ ] El código está comentado y es legible

---

## 🚀 Cómo Ejecutar el Proyecto

### Backend (Python/Flask)

```bash
# 1. Navegar a la carpeta del backend
cd back

# 2. Ejecutar el servidor
uv run python main.py

# El servidor estará en: http://localhost:8000
# Documentación API en: http://localhost:8000/
```

### Frontend (React Native/Expo)

```bash
# 1. Navegar a la carpeta del frontend
cd front

# 2. Instalar dependencias (solo la primera vez)
npm install

# 3. Iniciar la aplicación
npx expo start

# 4. Opciones:
# - Presiona 'w' para abrir en navegador web
# - Presiona 'a' para abrir en emulador Android
# - Presiona 'i' para abrir en simulador iOS
# - Escanea el QR con Expo Go en tu móvil
```

### Configurar la URL de la API

```javascript
// En front/app.config.js
export default {
  expo: {
    extra: {
      apiUrl: "http://localhost:8000",  // Cambiar si es necesario
      tokenKey: "auth_token"
    }
  }
};
```

---

## 📝 Consejos para el Examen

1. **Lee bien los requisitos**: Asegúrate de entender qué se pide exactamente.

2. **Empieza por lo básico**: Primero haz que funcione, luego mejora el código.

3. **Prueba cada parte**: No avances sin probar que lo anterior funciona.

4. **Usa console.log**: Para ver qué datos recibes:
   ```typescript
   console.log("Datos recibidos:", data);
   console.log("Token:", token);
   ```

5. **Maneja errores**: Siempre usa try/catch y muestra mensajes al usuario.

6. **Comenta tu código**: Explica qué hace cada parte importante.

7. **Revisa los tipos**: TypeScript te ayuda a evitar errores, úsalo a tu favor.

8. **Endpoints correctos**: Verifica que las URLs sean exactas.

9. **Headers correctos**: No olvides `Content-Type` y `Authorization`.

10. **Formato JSON**: Usa `JSON.stringify()` para enviar datos.

---

## 🎓 Resumen Final

### Lo Más Importante

1. **TypeScript añade tipos a JavaScript** para evitar errores
2. **Async/Await** se usa para operaciones que toman tiempo
3. **Fetch** se usa para hacer peticiones HTTP
4. **Token JWT** se envía en el header `Authorization: Bearer {token}`
5. **JSON.stringify()** convierte objetos a texto JSON
6. **JSON.parse()** convierte texto JSON a objetos
7. **Try/Catch** maneja errores
8. **useState** guarda datos que cambian
9. **useEffect** ejecuta código cuando algo cambia
10. **useContext** accede a datos compartidos

### Estructura de una Petición HTTP

```typescript
const res = await fetch(url, {
  method: "POST",                          // GET, POST, PUT, DELETE
  headers: {
    "Content-Type": "application/json",    // Tipo de contenido
    "Authorization": `Bearer ${token}`     // Token de autenticación
  },
  body: JSON.stringify(datos)              // Datos a enviar
});

const data = await res.json();             // Convertir respuesta a objeto
```

¡Buena suerte en el examen! 🍀
