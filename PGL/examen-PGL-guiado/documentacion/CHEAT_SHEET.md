# 🚀 CHEAT SHEET RÁPIDO - EXAMEN SPLITEXPENSER

## 📌 ENDPOINTS DE LA API

```
BASE_URL: http://localhost:8000
```

| Método | Endpoint | Body | Headers | Descripción |
|--------|----------|------|---------|-------------|
| POST | `/auth/register` | `{username, password}` | `Content-Type: application/json` | Registrar usuario |
| POST | `/auth/login` | `{username, password}` | `Content-Type: application/json` | Iniciar sesión |
| GET | `/groups` | - | `Authorization: Bearer {token}` | Listar grupos |
| POST | `/groups` | `{name}` | `Content-Type + Authorization` | Crear grupo |
| GET | `/groups/{id}` | - | `Authorization: Bearer {token}` | Ver grupo |
| GET | `/groups/{id}/expenses` | - | `Authorization: Bearer {token}` | Listar gastos |
| POST | `/groups/{id}/expenses` | `{description, amount}` | `Content-Type + Authorization` | Crear gasto |
| PUT | `/groups/{id}/expenses/{expId}` | `{description?, amount?}` | `Content-Type + Authorization` | Modificar gasto |
| DELETE | `/groups/{id}/expenses/{expId}` | - | `Authorization: Bearer {token}` | Eliminar gasto |

---

## 🔐 PARTE 1: VALIDACIÓN DE CONTRASEÑA

### Función de Validación
```typescript
export function validarPassword(password: string): boolean {
  if (password.length < 8) return false;
  if (!/[A-Z]/.test(password)) return false;  // Mayúscula
  if (!/[a-z]/.test(password)) return false;  // Minúscula
  if (!/[0-9]/.test(password)) return false;  // Número
  if (!/[!#$%&?]/.test(password)) return false;  // Símbolo
  return true;
}
```

### Función de Error
```typescript
export function obtenerErrorPassword(password: string): string {
  if (password.length < 8) return "Mínimo 8 caracteres";
  if (!/[A-Z]/.test(password)) return "Falta mayúscula";
  if (!/[a-z]/.test(password)) return "Falta minúscula";
  if (!/[0-9]/.test(password)) return "Falta número";
  if (!/[!#$%&?]/.test(password)) return "Falta símbolo (!#$%&?)";
  return "";
}
```

### Uso en Registro
```typescript
if (!validarPassword(password)) {
  setError(obtenerErrorPassword(password));
  return;
}
```

---

## 🔑 PARTE 2: REGISTRO E INICIO DE SESIÓN

### Registro
```typescript
const res = await fetch(`${API_URL}/auth/register`, {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ username, password })
});
const data = await res.json();
```

### Login
```typescript
const res = await fetch(`${API_URL}/auth/login`, {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ username, password })
});
const data = await res.json();

if (res.ok && data.access_token) {
  await SecureStore.setItemAsync(TOKEN_KEY, data.access_token);
  setToken(data.access_token);
}
```

---

## 👥 PARTE 3: GESTIÓN DE GRUPOS

### Listar Grupos
```typescript
const res = await fetch(`${API_URL}/groups`, {
  method: "GET",
  headers: {
    "Authorization": `Bearer ${token}`
  }
});
const grupos = await res.json();
```

### Crear Grupo
```typescript
const res = await fetch(`${API_URL}/groups`, {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
    "Authorization": `Bearer ${token}`
  },
  body: JSON.stringify({ name: nombreGrupo })
});
```

### Mostrar Lista con FlatList
```typescript
<FlatList
  data={grupos}
  keyExtractor={(item) => item.id.toString()}
  renderItem={({ item }) => (
    <View>
      <Text>{item.name}</Text>
    </View>
  )}
/>
```

---

## 💰 PARTE 4: GESTIÓN DE GASTOS

### Listar Gastos
```typescript
const res = await fetch(`${API_URL}/groups/${groupId}/expenses`, {
  method: "GET",
  headers: {
    "Authorization": `Bearer ${token}`
  }
});
const gastos = await res.json();
```

### Añadir Gasto
```typescript
const res = await fetch(`${API_URL}/groups/${groupId}/expenses`, {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
    "Authorization": `Bearer ${token}`
  },
  body: JSON.stringify({
    description: descripcion,
    amount: parseFloat(cantidad)
  })
});
```

### Modificar Gasto
```typescript
const body: any = {};
if (descripcion) body.description = descripcion;
if (cantidad) body.amount = parseFloat(cantidad);

const res = await fetch(`${API_URL}/groups/${groupId}/expenses/${expenseId}`, {
  method: "PUT",
  headers: {
    "Content-Type": "application/json",
    "Authorization": `Bearer ${token}`
  },
  body: JSON.stringify(body)
});
```

### Eliminar Gasto
```typescript
const res = await fetch(`${API_URL}/groups/${groupId}/expenses/${expenseId}`, {
  method: "DELETE",
  headers: {
    "Authorization": `Bearer ${token}`
  }
});
```

### Confirmación antes de Eliminar
```typescript
Alert.alert(
  "Confirmar eliminación",
  "¿Estás seguro?",
  [
    { text: "Cancelar", style: "cancel" },
    { 
      text: "Eliminar", 
      style: "destructive",
      onPress: async () => {
        // Código para eliminar
      }
    }
  ]
);
```

---

## 🎨 COMPONENTES REACT NATIVE

### useState
```typescript
const [valor, setValor] = useState<string>("");
// valor: el valor actual
// setValor: función para cambiar el valor
```

### useEffect
```typescript
useEffect(() => {
  // Código que se ejecuta
  cargarDatos();
}, []); // [] = ejecutar solo una vez al montar
```

### useContext
```typescript
const { token, login, logout } = useContext(AuthContext);
```

### TextInput
```typescript
<TextInput
  placeholder="Texto de ejemplo"
  value={valor}
  onChangeText={setValor}
  secureTextEntry  // Para contraseñas
  keyboardType="decimal-pad"  // Para números
  autoCapitalize="none"  // Sin mayúsculas automáticas
/>
```

### Button
```typescript
<Button 
  title="Texto del botón"
  onPress={miFuncion}
  disabled={loading}
  color="#007AFF"
/>
```

### Alert
```typescript
Alert.alert("Título", "Mensaje");
```

---

## 🔧 UTILIDADES

### Convertir a JSON
```typescript
JSON.stringify({ username: "alvaro", password: "Pass123!" })
// Resultado: '{"username":"alvaro","password":"Pass123!"}'
```

### Convertir de JSON
```typescript
const data = await res.json();
```

### Verificar si respuesta es exitosa
```typescript
if (res.ok) {
  // Éxito (código 200-299)
} else {
  // Error
}
```

### Try/Catch
```typescript
try {
  const res = await fetch(url);
  const data = await res.json();
} catch (err) {
  console.error("Error:", err);
}
```

---

## 📱 NAVEGACIÓN

### Navegar a otra pantalla
```typescript
router.push("/groups");
```

### Navegar con parámetros
```typescript
router.push({
  pathname: "/expenses",
  params: { groupId: 1, groupName: "Viaje" }
});
```

### Obtener parámetros
```typescript
const { groupId, groupName } = useLocalSearchParams();
```

### Volver atrás
```typescript
router.back();
```

### Reemplazar (no se puede volver)
```typescript
router.replace("/login");
```

---

## 🎯 INTERFACES TYPESCRIPT

### Grupo
```typescript
interface Grupo {
  id: number;
  name: string;
}
```

### Gasto
```typescript
interface Gasto {
  id: number;
  desc: string;
  amount: number;
  paid_by: number;
}
```

---

## ⚡ EXPRESIONES REGULARES

```typescript
/[A-Z]/      // Cualquier mayúscula
/[a-z]/      // Cualquier minúscula
/[0-9]/      // Cualquier número
/[!#$%&?]/   // Cualquiera de estos símbolos

.test(texto) // Devuelve true si encuentra el patrón
```

---

## 🚨 ERRORES COMUNES

### ❌ Olvidar el token
```typescript
// MAL
headers: { "Content-Type": "application/json" }

// BIEN
headers: { 
  "Content-Type": "application/json",
  "Authorization": `Bearer ${token}`
}
```

### ❌ No convertir a JSON
```typescript
// MAL
body: { name: "Grupo" }

// BIEN
body: JSON.stringify({ name: "Grupo" })
```

### ❌ No esperar la respuesta
```typescript
// MAL
const data = res.json();

// BIEN
const data = await res.json();
```

### ❌ No validar antes de enviar
```typescript
// MAL
await fetch(url, { body: JSON.stringify({ amount: cantidad }) });

// BIEN
const cantidadNum = parseFloat(cantidad);
if (isNaN(cantidadNum) || cantidadNum <= 0) {
  setError("Cantidad inválida");
  return;
}
await fetch(url, { body: JSON.stringify({ amount: cantidadNum }) });
```

---

## 📝 ESTRUCTURA DE ARCHIVOS

```
front/
├── app/
│   ├── index.tsx          # Página principal
│   ├── login.tsx          # Login
│   ├── register.tsx       # Registro
│   ├── groups.tsx         # Grupos (CREAR)
│   ├── expenses.tsx       # Gastos (CREAR)
│   └── _layout.tsx        # Layout
├── context/
│   └── AuthContext.tsx    # Contexto de autenticación
├── utils/
│   └── passwordValidator.ts  # Validador (CREAR)
└── app.config.js          # Configuración
```

---

## ✅ CHECKLIST RÁPIDO

### Parte 1: Contraseña
- [ ] `passwordValidator.ts` creado
- [ ] `validarPassword()` funciona
- [ ] `obtenerErrorPassword()` funciona
- [ ] Integrado en `register.tsx`

### Parte 2: Auth
- [ ] Registro funciona
- [ ] Login funciona
- [ ] Token se guarda
- [ ] Token se usa en peticiones

### Parte 3: Grupos
- [ ] `groups.tsx` creado
- [ ] Listar grupos funciona
- [ ] Crear grupo funciona

### Parte 4: Gastos
- [ ] `expenses.tsx` creado
- [ ] Listar gastos funciona
- [ ] Añadir gasto funciona
- [ ] Modificar gasto funciona
- [ ] Eliminar gasto funciona
- [ ] Confirmación antes de eliminar

---

## 🎓 CONCEPTOS CLAVE

1. **TypeScript añade tipos** → Evita errores
2. **async/await** → Para operaciones que toman tiempo
3. **fetch** → Hacer peticiones HTTP
4. **Token JWT** → `Authorization: Bearer {token}`
5. **JSON.stringify()** → Objeto → Texto
6. **JSON.parse()** / **res.json()** → Texto → Objeto
7. **try/catch** → Manejar errores
8. **useState** → Guardar datos que cambian
9. **useEffect** → Ejecutar código cuando algo cambia
10. **useContext** → Acceder a datos compartidos

---

## 🔥 PLANTILLA DE PETICIÓN HTTP

```typescript
try {
  const res = await fetch(`${API_URL}/endpoint`, {
    method: "POST",  // GET, POST, PUT, DELETE
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    },
    body: JSON.stringify({ campo: valor })
  });
  
  if (res.ok) {
    const data = await res.json();
    // Éxito
  } else {
    // Error
    setError("Error en la petición");
  }
} catch (err) {
  setError("Error de conexión");
}
```

---

## 🎯 CONFIGURACIÓN API

En `app.config.js`:
```javascript
extra: {
  apiUrl: "http://localhost:8000",  // Navegador web
  // apiUrl: "http://10.0.2.2:8000",  // Emulador Android
  // apiUrl: "http://TU_IP:8000",     // Móvil físico
  tokenKey: "auth_token"
}
```

---

¡Buena suerte! 🍀
