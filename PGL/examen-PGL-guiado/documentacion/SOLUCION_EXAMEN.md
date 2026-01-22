# 🎯 SOLUCIÓN COMPLETA DEL EXAMEN - SplitExpenser

## 📋 Índice Rápido
1. [Parte 1: Validación de Contraseña](#parte-1-validación-de-contraseña)
2. [Parte 2: Registro e Inicio de Sesión](#parte-2-registro-e-inicio-de-sesión)
3. [Parte 3: Gestión de Grupos](#parte-3-gestión-de-grupos)
4. [Parte 4: Gestión de Gastos](#parte-4-gestión-de-gastos)
5. [Código Completo de Todos los Archivos](#código-completo-de-todos-los-archivos)

---

## 🔐 PARTE 1: Validación de Contraseña

### Archivo: `front/utils/passwordValidator.ts`

Crea este archivo nuevo en la carpeta `front/utils/` (si no existe la carpeta, créala).

```typescript
/**
 * ============================================
 * VALIDADOR DE CONTRASEÑAS
 * ============================================
 * Este archivo contiene las funciones para validar
 * que una contraseña cumpla los requisitos de seguridad
 */

/**
 * Valida si una contraseña cumple todos los requisitos
 * @param password - La contraseña a validar
 * @returns true si es válida, false si no
 */
export function validarPassword(password: string): boolean {
  // 1. Verificar longitud mínima de 8 caracteres
  if (password.length < 8) {
    return false;
  }
  
  // 2. Verificar que tenga al menos una mayúscula (A-Z)
  // /[A-Z]/ es una expresión regular que busca letras mayúsculas
  // .test() devuelve true si encuentra el patrón
  const tieneMayuscula = /[A-Z]/.test(password);
  
  // 3. Verificar que tenga al menos una minúscula (a-z)
  const tieneMinuscula = /[a-z]/.test(password);
  
  // 4. Verificar que tenga al menos un número (0-9)
  const tieneNumero = /[0-9]/.test(password);
  
  // 5. Verificar que tenga al menos un símbolo (!#$%&?)
  const tieneSimbolo = /[!#$%&?]/.test(password);
  
  // 6. Todas las condiciones deben cumplirse (operador &&)
  // Si alguna es false, el resultado será false
  return tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo;
}

/**
 * Obtiene un mensaje de error específico según qué requisito falle
 * @param password - La contraseña a validar
 * @returns Mensaje de error o string vacío si es válida
 */
export function obtenerErrorPassword(password: string): string {
  // Verificamos cada requisito en orden y devolvemos el primer error
  
  if (password.length < 8) {
    return "La contraseña debe tener al menos 8 caracteres";
  }
  
  if (!/[A-Z]/.test(password)) {
    return "La contraseña debe tener al menos una mayúscula (A-Z)";
  }
  
  if (!/[a-z]/.test(password)) {
    return "La contraseña debe tener al menos una minúscula (a-z)";
  }
  
  if (!/[0-9]/.test(password)) {
    return "La contraseña debe tener al menos un número (0-9)";
  }
  
  if (!/[!#$%&?]/.test(password)) {
    return "La contraseña debe tener al menos un símbolo (!#$%&?)";
  }
  
  // Si llegamos aquí, la contraseña es válida
  return "";
}

/**
 * Obtiene un objeto con el estado de cada requisito
 * Útil para mostrar indicadores visuales en tiempo real
 * @param password - La contraseña a validar
 * @returns Objeto con el estado de cada requisito
 */
export function obtenerEstadoPassword(password: string) {
  return {
    longitudMinima: password.length >= 8,
    tieneMayuscula: /[A-Z]/.test(password),
    tieneMinuscula: /[a-z]/.test(password),
    tieneNumero: /[0-9]/.test(password),
    tieneSimbolo: /[!#$%&?]/.test(password),
  };
}

/**
 * EJEMPLOS DE USO:
 * 
 * // Validar contraseña
 * const esValida = validarPassword("MiPass123!");  // true
 * const esValida2 = validarPassword("abc123");     // false
 * 
 * // Obtener mensaje de error
 * const error = obtenerErrorPassword("abc123");
 * // Devuelve: "La contraseña debe tener al menos 8 caracteres"
 * 
 * // Obtener estado detallado
 * const estado = obtenerEstadoPassword("Abc123");
 * // Devuelve: {
 * //   longitudMinima: false,
 * //   tieneMayuscula: true,
 * //   tieneMinuscula: true,
 * //   tieneNumero: true,
 * //   tieneSimbolo: false
 * // }
 */
```

---

## 🔑 PARTE 2: Registro e Inicio de Sesión

### Archivo: `front/app/register.tsx`

```typescript
/**
 * ============================================
 * PANTALLA DE REGISTRO
 * ============================================
 * Permite a un usuario crear una cuenta nueva
 */

import { useRouter } from "expo-router";
import React, { useContext, useState } from "react";
import { Button, Text, TextInput, View, StyleSheet, ScrollView } from "react-native";
import { AuthContext } from "../context/AuthContext";
import { validarPassword, obtenerErrorPassword, obtenerEstadoPassword } from "../utils/passwordValidator";

export default function Register() {
  // ===== HOOKS =====
  // useContext: obtiene funciones del contexto de autenticación
  const { register } = useContext(AuthContext);
  
  // useRouter: permite navegar entre pantallas
  const router = useRouter();
  
  // useState: guarda el estado de los campos del formulario
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  /**
   * Función que se ejecuta al presionar el botón "Registrar"
   */
  const handleRegister = async () => {
    // 1. Limpiar errores previos
    setError("");
    
    // 2. Validar que los campos no estén vacíos
    if (!username.trim()) {
      setError("El nombre de usuario es obligatorio");
      return; // Detener ejecución
    }
    
    if (!password) {
      setError("La contraseña es obligatoria");
      return;
    }
    
    // 3. VALIDAR CONTRASEÑA (Parte 1 del examen)
    if (!validarPassword(password)) {
      // Si no es válida, obtener el mensaje de error específico
      const mensajeError = obtenerErrorPassword(password);
      setError(mensajeError);
      return; // No continuar si la contraseña no es válida
    }
    
    // 4. Si llegamos aquí, todo está correcto
    setLoading(true); // Mostrar indicador de carga
    
    try {
      // 5. Llamar a la función de registro del contexto
      const res = await register(username, password);
      
      // 6. Verificar la respuesta
      if (res.ok || res.msg === "User registered") {
        // Éxito: ir a la pantalla de login
        router.replace("/login");
      } else {
        // Error: mostrar mensaje
        setError(res.msg || "Error al registrar usuario");
      }
    } catch (err) {
      // Error de red u otro error inesperado
      setError("Error de conexión. Verifica tu red.");
    } finally {
      // Siempre ejecutar esto al final (éxito o error)
      setLoading(false);
    }
  };

  // Obtener estado de la contraseña para mostrar indicadores
  const estadoPassword = obtenerEstadoPassword(password);

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Crear Cuenta</Text>
      
      {/* ===== CAMPO DE USUARIO ===== */}
      <TextInput
        placeholder="Nombre de usuario"
        value={username}
        onChangeText={setUsername}
        style={styles.input}
        autoCapitalize="none" // No poner mayúsculas automáticas
        autoCorrect={false}   // No autocorregir
      />
      
      {/* ===== CAMPO DE CONTRASEÑA ===== */}
      <TextInput
        placeholder="Contraseña"
        value={password}
        onChangeText={setPassword}
        secureTextEntry // Ocultar el texto (mostrar puntos)
        style={styles.input}
        autoCapitalize="none"
      />
      
      {/* ===== INDICADORES DE REQUISITOS DE CONTRASEÑA ===== */}
      <View style={styles.requisitosContainer}>
        <Text style={styles.requisitosTitle}>Requisitos de la contraseña:</Text>
        
        <Text style={estadoPassword.longitudMinima ? styles.requisitoOk : styles.requisitoError}>
          {estadoPassword.longitudMinima ? "✓" : "✗"} Mínimo 8 caracteres
        </Text>
        
        <Text style={estadoPassword.tieneMayuscula ? styles.requisitoOk : styles.requisitoError}>
          {estadoPassword.tieneMayuscula ? "✓" : "✗"} Al menos una mayúscula (A-Z)
        </Text>
        
        <Text style={estadoPassword.tieneMinuscula ? styles.requisitoOk : styles.requisitoError}>
          {estadoPassword.tieneMinuscula ? "✓" : "✗"} Al menos una minúscula (a-z)
        </Text>
        
        <Text style={estadoPassword.tieneNumero ? styles.requisitoOk : styles.requisitoError}>
          {estadoPassword.tieneNumero ? "✓" : "✗"} Al menos un número (0-9)
        </Text>
        
        <Text style={estadoPassword.tieneSimbolo ? styles.requisitoOk : styles.requisitoError}>
          {estadoPassword.tieneSimbolo ? "✓" : "✗"} Al menos un símbolo (!#$%&?)
        </Text>
      </View>
      
      {/* ===== MENSAJE DE ERROR ===== */}
      {error ? <Text style={styles.error}>{error}</Text> : null}
      
      {/* ===== BOTÓN DE REGISTRO ===== */}
      <Button 
        title={loading ? "Registrando..." : "Registrar"} 
        onPress={handleRegister}
        disabled={loading} // Deshabilitar mientras carga
      />
      
      {/* ===== BOTÓN PARA IR A LOGIN ===== */}
      <Button 
        title="Ya tengo cuenta - Ir a Login" 
        onPress={() => router.push("/login")}
        color="#666"
      />
    </ScrollView>
  );
}

// ===== ESTILOS =====
const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    justifyContent: "center",
    padding: 20,
    backgroundColor: "#f5f5f5",
  },
  title: {
    fontSize: 28,
    fontWeight: "bold",
    marginBottom: 30,
    textAlign: "center",
  },
  input: {
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 8,
    padding: 12,
    marginBottom: 15,
    backgroundColor: "white",
    fontSize: 16,
  },
  requisitosContainer: {
    backgroundColor: "white",
    padding: 15,
    borderRadius: 8,
    marginBottom: 15,
  },
  requisitosTitle: {
    fontWeight: "bold",
    marginBottom: 10,
  },
  requisitoOk: {
    color: "green",
    marginBottom: 5,
  },
  requisitoError: {
    color: "red",
    marginBottom: 5,
  },
  error: {
    color: "red",
    marginBottom: 15,
    textAlign: "center",
    fontWeight: "bold",
  },
});
```

### Archivo: `front/app/login.tsx`

```typescript
/**
 * ============================================
 * PANTALLA DE LOGIN
 * ============================================
 * Permite a un usuario iniciar sesión
 */

import { useRouter } from "expo-router";
import React, { useContext, useState } from "react";
import { Button, Text, TextInput, View, StyleSheet, ScrollView } from "react-native";
import { AuthContext } from "../context/AuthContext";

export default function Login() {
  // ===== HOOKS =====
  const { login } = useContext(AuthContext);
  const router = useRouter();
  
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  /**
   * Función que se ejecuta al presionar el botón "Iniciar Sesión"
   */
  const handleLogin = async () => {
    // 1. Limpiar errores previos
    setError("");
    
    // 2. Validar campos
    if (!username.trim() || !password) {
      setError("Por favor completa todos los campos");
      return;
    }
    
    // 3. Intentar hacer login
    setLoading(true);
    
    try {
      // Llamar a la función de login del contexto
      const res = await login(username, password);
      
      // 4. Verificar si recibimos un token
      if (res.access_token) {
        // Éxito: el token ya se guardó en el contexto
        // Navegar a la página principal
        router.replace("/");
      } else {
        // Error: mostrar mensaje
        setError(res.msg || "Credenciales incorrectas");
      }
    } catch (err) {
      setError("Error de conexión. Verifica tu red.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Iniciar Sesión</Text>
      
      {/* ===== CAMPO DE USUARIO ===== */}
      <TextInput
        placeholder="Nombre de usuario"
        value={username}
        onChangeText={setUsername}
        style={styles.input}
        autoCapitalize="none"
        autoCorrect={false}
      />
      
      {/* ===== CAMPO DE CONTRASEÑA ===== */}
      <TextInput
        placeholder="Contraseña"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        style={styles.input}
        autoCapitalize="none"
      />
      
      {/* ===== MENSAJE DE ERROR ===== */}
      {error ? <Text style={styles.error}>{error}</Text> : null}
      
      {/* ===== BOTÓN DE LOGIN ===== */}
      <Button 
        title={loading ? "Iniciando sesión..." : "Iniciar Sesión"} 
        onPress={handleLogin}
        disabled={loading}
      />
      
      {/* ===== BOTÓN PARA IR A REGISTRO ===== */}
      <Button 
        title="No tengo cuenta - Registrarme" 
        onPress={() => router.push("/register")}
        color="#666"
      />
    </ScrollView>
  );
}

// ===== ESTILOS =====
const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    justifyContent: "center",
    padding: 20,
    backgroundColor: "#f5f5f5",
  },
  title: {
    fontSize: 28,
    fontWeight: "bold",
    marginBottom: 30,
    textAlign: "center",
  },
  input: {
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 8,
    padding: 12,
    marginBottom: 15,
    backgroundColor: "white",
    fontSize: 16,
  },
  error: {
    color: "red",
    marginBottom: 15,
    textAlign: "center",
    fontWeight: "bold",
  },
});
```

---

## 👥 PARTE 3: Gestión de Grupos

### Archivo: `front/app/groups.tsx`

```typescript
/**
 * ============================================
 * PANTALLA DE GESTIÓN DE GRUPOS
 * ============================================
 * Permite crear grupos y ver la lista de grupos del usuario
 */

import React, { useContext, useEffect, useState } from "react";
import { 
  Button, 
  FlatList, 
  Text, 
  TextInput, 
  View, 
  StyleSheet,
  TouchableOpacity,
  Alert 
} from "react-native";
import { AuthContext } from "../context/AuthContext";
import { useRouter } from "expo-router";
import Constants from "expo-constants";

// Obtener la URL de la API desde la configuración
const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";

// ===== INTERFACES =====
// Define la estructura de un grupo
interface Grupo {
  id: number;
  name: string;
}

export default function Groups() {
  // ===== HOOKS =====
  const { token, logout } = useContext(AuthContext);
  const router = useRouter();
  
  // Estado para la lista de grupos
  const [grupos, setGrupos] = useState<Grupo[]>([]);
  
  // Estado para el formulario de crear grupo
  const [nombreGrupo, setNombreGrupo] = useState<string>("");
  
  // Estado para errores y carga
  const [error, setError] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  /**
   * useEffect: se ejecuta cuando el componente se monta
   * Carga los grupos del usuario
   */
  useEffect(() => {
    cargarGrupos();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []); // [] significa "ejecutar solo una vez al montar"

  /**
   * Función para cargar los grupos del servidor
   */
  const cargarGrupos = async () => {
    try {
      // Hacer petición GET al endpoint /groups
      const res = await fetch(`${API_URL}/groups`, {
        method: "GET",
        headers: {
          // IMPORTANTE: Incluir el token en el header Authorization
          "Authorization": `Bearer ${token}`
        }
      });
      
      // Verificar si la respuesta fue exitosa
      if (res.ok) {
        // Convertir la respuesta a JSON
        const data = await res.json();
        // Actualizar el estado con los grupos
        setGrupos(data);
        setError("");
      } else {
        // Si hubo error, verificar si es 401 (no autorizado)
        if (res.status === 401) {
          Alert.alert("Sesión expirada", "Por favor inicia sesión nuevamente");
          logout();
        } else {
          setError("Error al cargar grupos");
        }
      }
    } catch (err) {
      setError("Error de conexión");
      console.error("Error al cargar grupos:", err);
    }
  };

  /**
   * Función para crear un nuevo grupo
   */
  const crearGrupo = async () => {
    // 1. Validar que el nombre no esté vacío
    if (!nombreGrupo.trim()) {
      setError("El nombre del grupo no puede estar vacío");
      return;
    }
    
    // 2. Iniciar carga
    setLoading(true);
    setError("");
    
    try {
      // 3. Hacer petición POST al endpoint /groups
      const res = await fetch(`${API_URL}/groups`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        // Enviar el nombre del grupo en formato JSON
        body: JSON.stringify({ name: nombreGrupo })
      });
      
      // 4. Verificar respuesta
      if (res.ok) {
        // Éxito: limpiar el campo y recargar la lista
        setNombreGrupo("");
        cargarGrupos();
        Alert.alert("Éxito", "Grupo creado correctamente");
      } else {
        const data = await res.json();
        setError(data.msg || "Error al crear grupo");
      }
    } catch (err) {
      setError("Error de conexión");
      console.error("Error al crear grupo:", err);
    } finally {
      setLoading(false);
    }
  };

  /**
   * Función para navegar a la pantalla de gastos de un grupo
   */
  const verGastos = (grupoId: number, nombreGrupo: string) => {
    // Navegar a la pantalla de gastos pasando el ID del grupo
    router.push({
      pathname: "/expenses",
      params: { groupId: grupoId, groupName: nombreGrupo }
    });
  };

  return (
    <View style={styles.container}>
      {/* ===== ENCABEZADO ===== */}
      <View style={styles.header}>
        <Text style={styles.title}>Mis Grupos</Text>
        <Button title="Cerrar Sesión" onPress={() => logout()} color="#d9534f" />
      </View>
      
      {/* ===== FORMULARIO PARA CREAR GRUPO ===== */}
      <View style={styles.formContainer}>
        <Text style={styles.subtitle}>Crear Nuevo Grupo</Text>
        
        <TextInput
          placeholder="Nombre del grupo (ej: Viaje a Madrid)"
          value={nombreGrupo}
          onChangeText={setNombreGrupo}
          style={styles.input}
        />
        
        <Button 
          title={loading ? "Creando..." : "Crear Grupo"} 
          onPress={crearGrupo}
          disabled={loading}
        />
      </View>
      
      {/* ===== MENSAJE DE ERROR ===== */}
      {error ? <Text style={styles.error}>{error}</Text> : null}
      
      {/* ===== LISTA DE GRUPOS ===== */}
      <Text style={styles.subtitle}>Mis Grupos ({grupos.length})</Text>
      
      {grupos.length === 0 ? (
        <Text style={styles.emptyText}>
          No tienes grupos aún. ¡Crea tu primer grupo!
        </Text>
      ) : (
        <FlatList
          data={grupos}
          // keyExtractor: devuelve un ID único para cada elemento
          keyExtractor={(item) => item.id.toString()}
          // renderItem: cómo se muestra cada elemento
          renderItem={({ item }) => (
            <TouchableOpacity 
              style={styles.grupoItem}
              onPress={() => verGastos(item.id, item.name)}
            >
              <View>
                <Text style={styles.grupoNombre}>{item.name}</Text>
                <Text style={styles.grupoId}>ID: {item.id}</Text>
              </View>
              <Text style={styles.arrow}>→</Text>
            </TouchableOpacity>
          )}
          style={styles.lista}
        />
      )}
    </View>
  );
}

// ===== ESTILOS =====
const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: "#f5f5f5",
  },
  header: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 20,
  },
  title: {
    fontSize: 28,
    fontWeight: "bold",
  },
  subtitle: {
    fontSize: 20,
    fontWeight: "bold",
    marginTop: 20,
    marginBottom: 10,
  },
  formContainer: {
    backgroundColor: "white",
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
  },
  input: {
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 8,
    padding: 12,
    marginBottom: 10,
    fontSize: 16,
  },
  error: {
    color: "red",
    marginBottom: 10,
    textAlign: "center",
    fontWeight: "bold",
  },
  emptyText: {
    textAlign: "center",
    color: "#666",
    marginTop: 20,
    fontSize: 16,
  },
  lista: {
    flex: 1,
  },
  grupoItem: {
    backgroundColor: "white",
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    // Sombra para iOS
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    // Elevación para Android
    elevation: 3,
  },
  grupoNombre: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 5,
  },
  grupoId: {
    fontSize: 14,
    color: "#666",
  },
  arrow: {
    fontSize: 24,
    color: "#007AFF",
  },
});
```

---

## 💰 PARTE 4: Gestión de Gastos

### Archivo: `front/app/expenses.tsx`

```typescript
/**
 * ============================================
 * PANTALLA DE GESTIÓN DE GASTOS
 * ============================================
 * Permite añadir, modificar y eliminar gastos de un grupo
 */

import React, { useContext, useEffect, useState } from "react";
import { 
  Button, 
  FlatList, 
  Text, 
  TextInput, 
  View, 
  StyleSheet,
  Alert,
  TouchableOpacity 
} from "react-native";
import { AuthContext } from "../context/AuthContext";
import { useLocalSearchParams, useRouter } from "expo-router";
import Constants from "expo-constants";

const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";

// ===== INTERFACES =====
interface Gasto {
  id: number;
  desc: string;      // Descripción del gasto
  amount: number;    // Cantidad en euros
  paid_by: number;   // ID del usuario que pagó
}

export default function Expenses() {
  // ===== HOOKS =====
  const { token } = useContext(AuthContext);
  const router = useRouter();
  
  // Obtener parámetros de la URL (ID y nombre del grupo)
  const { groupId, groupName } = useLocalSearchParams();
  
  // Estado para la lista de gastos
  const [gastos, setGastos] = useState<Gasto[]>([]);
  
  // Estado para el formulario
  const [descripcion, setDescripcion] = useState<string>("");
  const [cantidad, setCantidad] = useState<string>("");
  
  // Estado para edición
  const [editandoId, setEditandoId] = useState<number | null>(null);
  
  // Estado para errores y carga
  const [error, setError] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  /**
   * Cargar gastos al montar el componente
   */
  useEffect(() => {
    cargarGastos();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  /**
   * Función para cargar los gastos del grupo
   */
  const cargarGastos = async () => {
    try {
      // GET /groups/{groupId}/expenses
      const res = await fetch(
        `${API_URL}/groups/${groupId}/expenses`,
        {
          method: "GET",
          headers: {
            "Authorization": `Bearer ${token}`
          }
        }
      );
      
      if (res.ok) {
        const data = await res.json();
        setGastos(data);
        setError("");
      } else {
        setError("Error al cargar gastos");
      }
    } catch (err) {
      setError("Error de conexión");
      console.error("Error al cargar gastos:", err);
    }
  };

  /**
   * Función para añadir un nuevo gasto
   */
  const añadirGasto = async () => {
    // 1. Validar campos
    if (!descripcion.trim()) {
      setError("La descripción no puede estar vacía");
      return;
    }
    
    if (!cantidad || isNaN(parseFloat(cantidad))) {
      setError("Ingresa una cantidad válida");
      return;
    }
    
    const cantidadNum = parseFloat(cantidad);
    if (cantidadNum <= 0) {
      setError("La cantidad debe ser mayor a 0");
      return;
    }
    
    // 2. Iniciar carga
    setLoading(true);
    setError("");
    
    try {
      // 3. POST /groups/{groupId}/expenses
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
            amount: cantidadNum
          })
        }
      );
      
      // 4. Verificar respuesta
      if (res.ok) {
        // Éxito: limpiar formulario y recargar lista
        setDescripcion("");
        setCantidad("");
        cargarGastos();
        Alert.alert("Éxito", "Gasto añadido correctamente");
      } else {
        const data = await res.json();
        setError(data.msg || "Error al añadir gasto");
      }
    } catch (err) {
      setError("Error de conexión");
      console.error("Error al añadir gasto:", err);
    } finally {
      setLoading(false);
    }
  };

  /**
   * Función para modificar un gasto existente
   */
  const modificarGasto = async (id: number) => {
    // 1. Validar que al menos un campo tenga valor
    if (!descripcion.trim() && !cantidad) {
      setError("Debes modificar al menos un campo");
      return;
    }
    
    // 2. Preparar el body con solo los campos que se van a actualizar
    const body: any = {};
    
    if (descripcion.trim()) {
      body.description = descripcion;
    }
    
    if (cantidad) {
      const cantidadNum = parseFloat(cantidad);
      if (isNaN(cantidadNum) || cantidadNum <= 0) {
        setError("Ingresa una cantidad válida");
        return;
      }
      body.amount = cantidadNum;
    }
    
    // 3. Iniciar carga
    setLoading(true);
    setError("");
    
    try {
      // 4. PUT /groups/{groupId}/expenses/{id}
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
      
      // 5. Verificar respuesta
      if (res.ok) {
        // Éxito: limpiar formulario y recargar
        setDescripcion("");
        setCantidad("");
        setEditandoId(null);
        cargarGastos();
        Alert.alert("Éxito", "Gasto modificado correctamente");
      } else {
        const data = await res.json();
        setError(data.msg || "Error al modificar gasto");
      }
    } catch (err) {
      setError("Error de conexión");
      console.error("Error al modificar gasto:", err);
    } finally {
      setLoading(false);
    }
  };

  /**
   * Función para eliminar un gasto
   */
  const eliminarGasto = async (id: number) => {
    // Mostrar confirmación antes de eliminar
    Alert.alert(
      "Confirmar eliminación",
      "¿Estás seguro de que quieres eliminar este gasto?",
      [
        {
          text: "Cancelar",
          style: "cancel"
        },
        {
          text: "Eliminar",
          style: "destructive",
          onPress: async () => {
            try {
              // DELETE /groups/{groupId}/expenses/{id}
              const res = await fetch(
                `${API_URL}/groups/${groupId}/expenses/${id}`,
                {
                  method: "DELETE",
                  headers: {
                    "Authorization": `Bearer ${token}`
                  }
                }
              );
              
              if (res.ok) {
                cargarGastos();
                Alert.alert("Éxito", "Gasto eliminado correctamente");
              } else {
                Alert.alert("Error", "No se pudo eliminar el gasto");
              }
            } catch (err) {
              Alert.alert("Error", "Error de conexión");
              console.error("Error al eliminar gasto:", err);
            }
          }
        }
      ]
    );
  };

  /**
   * Función para iniciar la edición de un gasto
   */
  const iniciarEdicion = (gasto: Gasto) => {
    setEditandoId(gasto.id);
    setDescripcion(gasto.desc);
    setCantidad(gasto.amount.toString());
    setError("");
  };

  /**
   * Función para cancelar la edición
   */
  const cancelarEdicion = () => {
    setEditandoId(null);
    setDescripcion("");
    setCantidad("");
    setError("");
  };

  /**
   * Calcular el total de gastos
   */
  const calcularTotal = (): number => {
    return gastos.reduce((total, gasto) => total + gasto.amount, 0);
  };

  return (
    <View style={styles.container}>
      {/* ===== ENCABEZADO ===== */}
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()}>
          <Text style={styles.backButton}>← Volver</Text>
        </TouchableOpacity>
        <Text style={styles.title}>{groupName}</Text>
      </View>
      
      {/* ===== FORMULARIO ===== */}
      <View style={styles.formContainer}>
        <Text style={styles.subtitle}>
          {editandoId ? "Modificar Gasto" : "Añadir Gasto"}
        </Text>
        
        <TextInput
          placeholder="Descripción (ej: Cena en restaurante)"
          value={descripcion}
          onChangeText={setDescripcion}
          style={styles.input}
        />
        
        <TextInput
          placeholder="Cantidad en € (ej: 45.50)"
          value={cantidad}
          onChangeText={setCantidad}
          keyboardType="decimal-pad" // Teclado numérico con decimales
          style={styles.input}
        />
        
        {/* ===== BOTONES DEL FORMULARIO ===== */}
        {editandoId ? (
          <View style={styles.buttonRow}>
            <View style={styles.buttonHalf}>
              <Button
                title={loading ? "Guardando..." : "Guardar Cambios"}
                onPress={() => modificarGasto(editandoId)}
                disabled={loading}
              />
            </View>
            <View style={styles.buttonHalf}>
              <Button
                title="Cancelar"
                onPress={cancelarEdicion}
                color="#666"
              />
            </View>
          </View>
        ) : (
          <Button
            title={loading ? "Añadiendo..." : "Añadir Gasto"}
            onPress={añadirGasto}
            disabled={loading}
          />
        )}
      </View>
      
      {/* ===== MENSAJE DE ERROR ===== */}
      {error ? <Text style={styles.error}>{error}</Text> : null}
      
      {/* ===== TOTAL DE GASTOS ===== */}
      <View style={styles.totalContainer}>
        <Text style={styles.totalLabel}>Total de gastos:</Text>
        <Text style={styles.totalAmount}>{calcularTotal().toFixed(2)} €</Text>
      </View>
      
      {/* ===== LISTA DE GASTOS ===== */}
      <Text style={styles.subtitle}>Gastos ({gastos.length})</Text>
      
      {gastos.length === 0 ? (
        <Text style={styles.emptyText}>
          No hay gastos aún. ¡Añade el primero!
        </Text>
      ) : (
        <FlatList
          data={gastos}
          keyExtractor={(item) => item.id.toString()}
          renderItem={({ item }) => (
            <View style={styles.gastoItem}>
              {/* Información del gasto */}
              <View style={styles.gastoInfo}>
                <Text style={styles.gastoDescripcion}>{item.desc}</Text>
                <Text style={styles.gastoCantidad}>{item.amount.toFixed(2)} €</Text>
                <Text style={styles.gastoPagadoPor}>Pagado por usuario #{item.paid_by}</Text>
              </View>
              
              {/* Botones de acción */}
              <View style={styles.gastoAcciones}>
                <TouchableOpacity
                  style={styles.botonEditar}
                  onPress={() => iniciarEdicion(item)}
                >
                  <Text style={styles.botonEditarTexto}>✏️ Editar</Text>
                </TouchableOpacity>
                
                <TouchableOpacity
                  style={styles.botonEliminar}
                  onPress={() => eliminarGasto(item.id)}
                >
                  <Text style={styles.botonEliminarTexto}>🗑️ Eliminar</Text>
                </TouchableOpacity>
              </View>
            </View>
          )}
          style={styles.lista}
        />
      )}
    </View>
  );
}

// ===== ESTILOS =====
const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: "#f5f5f5",
  },
  header: {
    marginBottom: 20,
  },
  backButton: {
    fontSize: 16,
    color: "#007AFF",
    marginBottom: 10,
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
  },
  subtitle: {
    fontSize: 18,
    fontWeight: "bold",
    marginTop: 15,
    marginBottom: 10,
  },
  formContainer: {
    backgroundColor: "white",
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
  },
  input: {
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 8,
    padding: 12,
    marginBottom: 10,
    fontSize: 16,
  },
  buttonRow: {
    flexDirection: "row",
    gap: 10,
  },
  buttonHalf: {
    flex: 1,
  },
  error: {
    color: "red",
    marginBottom: 10,
    textAlign: "center",
    fontWeight: "bold",
  },
  totalContainer: {
    backgroundColor: "#4CAF50",
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  totalLabel: {
    fontSize: 18,
    fontWeight: "bold",
    color: "white",
  },
  totalAmount: {
    fontSize: 24,
    fontWeight: "bold",
    color: "white",
  },
  emptyText: {
    textAlign: "center",
    color: "#666",
    marginTop: 20,
    fontSize: 16,
  },
  lista: {
    flex: 1,
  },
  gastoItem: {
    backgroundColor: "white",
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    elevation: 3,
  },
  gastoInfo: {
    marginBottom: 10,
  },
  gastoDescripcion: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 5,
  },
  gastoCantidad: {
    fontSize: 20,
    color: "#4CAF50",
    fontWeight: "bold",
    marginBottom: 5,
  },
  gastoPagadoPor: {
    fontSize: 14,
    color: "#666",
  },
  gastoAcciones: {
    flexDirection: "row",
    gap: 10,
  },
  botonEditar: {
    flex: 1,
    backgroundColor: "#007AFF",
    padding: 10,
    borderRadius: 8,
    alignItems: "center",
  },
  botonEditarTexto: {
    color: "white",
    fontWeight: "bold",
  },
  botonEliminar: {
    flex: 1,
    backgroundColor: "#d9534f",
    padding: 10,
    borderRadius: 8,
    alignItems: "center",
  },
  botonEliminarTexto: {
    color: "white",
    fontWeight: "bold",
  },
});
```

---

## 📁 CÓDIGO COMPLETO DE TODOS LOS ARCHIVOS

### Archivo: `front/tsconfig.json`

**⚠️ IMPORTANTE:** Este archivo debe estar configurado correctamente para que TypeScript funcione con JSX.

```json
{
  "extends": "expo/tsconfig.base",
  "compilerOptions": {
    "strict": true,
    "jsx": "react-native",  // ← NECESARIO para usar JSX
    "paths": {
      "@/*": [
        "./*"
      ]
    }
  },
  "include": [
    "**/*.ts",
    "**/*.tsx"
  ]
}
```

**Nota:** Si ves el error `Cannot use JSX unless the '--jsx' flag is provided`, asegúrate de que la línea `"jsx": "react-native"` esté presente y reinicia el servidor de TypeScript en VS Code (`Ctrl+Shift+P` → `TypeScript: Restart TS Server`).

---

### Archivo: `front/app/index.tsx`

```typescript
/**
 * ============================================
 * PÁGINA PRINCIPAL (HOME)
 * ============================================
 * Redirige a login si no hay sesión, o muestra el menú principal
 */

import { useRouter } from "expo-router";
import React, { useContext, useEffect } from "react";
import { Button, Text, View, StyleSheet } from "react-native";
import { AuthContext } from "../context/AuthContext";

export default function Home() {
  const { token, logout } = useContext(AuthContext);
  const router = useRouter();

  // Si no hay token, redirigir a login
  useEffect(() => {
    if (!token) {
      setTimeout(() => router.replace("/login"), 0);
    }
  }, [token]);

  // No mostrar nada mientras redirige
  if (!token) return null;

  return (
    <View style={styles.container}>
      <Text style={styles.title}>¡Bienvenido a SplitExpenser!</Text>
      <Text style={styles.subtitle}>Gestiona tus gastos compartidos</Text>
      
      <View style={styles.buttonContainer}>
        <Button 
          title="Ver Mis Grupos" 
          onPress={() => router.push("/groups")}
        />
      </View>
      
      <View style={styles.buttonContainer}>
        <Button 
          title="Cerrar Sesión" 
          onPress={() => logout()}
          color="#d9534f"
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 20,
    backgroundColor: "#f5f5f5",
  },
  title: {
    fontSize: 28,
    fontWeight: "bold",
    marginBottom: 10,
    textAlign: "center",
  },
  subtitle: {
    fontSize: 18,
    color: "#666",
    marginBottom: 40,
    textAlign: "center",
  },
  buttonContainer: {
    width: "100%",
    marginBottom: 15,
  },
});
```

### Archivo: `front/app/_layout.tsx`

```typescript
/**
 * ============================================
 * LAYOUT PRINCIPAL
 * ============================================
 * Envuelve toda la aplicación con el AuthProvider
 */

import { Stack } from "expo-router";
import { AuthProvider } from "../context/AuthContext";

export default function RootLayout() {
  return (
    <AuthProvider>
      <Stack>
        <Stack.Screen name="index" options={{ title: "Inicio" }} />
        <Stack.Screen name="login" options={{ title: "Iniciar Sesión" }} />
        <Stack.Screen name="register" options={{ title: "Registro" }} />
        <Stack.Screen name="groups" options={{ title: "Grupos" }} />
        <Stack.Screen name="expenses" options={{ title: "Gastos" }} />
      </Stack>
    </AuthProvider>
  );
}
```

### Archivo: `front/context/AuthContext.tsx`

```typescript
/**
 * ============================================
 * CONTEXTO DE AUTENTICACIÓN
 * ============================================
 * Maneja el estado de autenticación global de la app
 */

import * as SecureStore from "expo-secure-store";
import Constants from "expo-constants";
import React, { createContext, useEffect, useMemo, useState } from "react";

// Obtener configuración
const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";
const TOKEN_KEY = Constants.expoConfig?.extra?.tokenKey ?? "";

// ===== TIPOS =====
type AuthContextType = {
  token: string | null;
  loading: boolean;
  login: (username: string, password: string) => Promise<any>;
  register: (username: string, password: string) => Promise<any>;
  logout: () => Promise<void>;
};

// ===== CREAR CONTEXTO =====
export const AuthContext = createContext<AuthContextType>({
  token: null,
  loading: true,
  login: async () => ({}),
  register: async () => ({}),
  logout: async () => {},
});

// ===== PROVIDER =====
export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [token, setToken] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);

  /**
   * Al iniciar la app, intentar cargar el token guardado
   */
  useEffect(() => {
    const loadToken = async () => {
      try {
        const saved = await SecureStore.getItemAsync(TOKEN_KEY);
        if (saved) {
          setToken(saved);
        }
      } catch (err) {
        console.error("Error al cargar token:", err);
      } finally {
        setLoading(false);
      }
    };
    loadToken();
  }, []);

  /**
   * Función para registrar un nuevo usuario
   */
  const register = async (username: string, password: string) => {
    try {
      const res = await fetch(`${API_URL}/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });
      
      const data = await res.json();
      
      // Añadir flag de éxito
      return { ...data, ok: res.ok };
    } catch (err) {
      return { ok: false, msg: "Error de red" };
    }
  };

  /**
   * Función para iniciar sesión
   */
  const login = async (username: string, password: string) => {
    try {
      const res = await fetch(`${API_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });
      
      const data = await res.json();
      
      // Si el login fue exitoso, guardar el token
      if (res.ok && data.access_token) {
        await SecureStore.setItemAsync(TOKEN_KEY, data.access_token);
        setToken(data.access_token);
      }
      
      return data;
    } catch (err) {
      return { ok: false, msg: "Error de red" };
    }
  };

  /**
   * Función para cerrar sesión
   */
  const logout = async () => {
    try {
      await SecureStore.deleteItemAsync(TOKEN_KEY);
      setToken(null);
    } catch (err) {
      console.error("Error al cerrar sesión:", err);
    }
  };

  // Memorizar el valor del contexto para optimizar rendimiento
  const value = useMemo(
    () => ({ token, loading, login, register, logout }),
    [token, loading]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
```

### Archivo: `front/app.config.js`

```javascript
/**
 * ============================================
 * CONFIGURACIÓN DE LA APP
 * ============================================
 */

export default {
  expo: {
    name: "SplitExpenser",
    slug: "splitexpenser-front",
    version: "1.0.0",
    orientation: "portrait",
    icon: "./assets/icon.png",
    userInterfaceStyle: "light",
    splash: {
      image: "./assets/splash.png",
      resizeMode: "contain",
      backgroundColor: "#ffffff"
    },
    extra: {
      // ⚠️ IMPORTANTE: Cambiar esta URL según tu configuración
      // Si usas el emulador de Android: "http://10.0.2.2:8000"
      // Si usas tu móvil en la misma red: "http://TU_IP_LOCAL:8000"
      // Si usas el navegador web: "http://localhost:8000"
      apiUrl: "http://localhost:8000",
      tokenKey: "auth_token"
    }
  }
};
```

---

## 🚀 INSTRUCCIONES DE INSTALACIÓN Y EJECUCIÓN

### 1. Preparar el Backend

```bash
# Navegar a la carpeta del backend
cd back

# Ejecutar el servidor (se creará la base de datos automáticamente)
uv run python main.py

# El servidor estará disponible en: http://localhost:8000
```

### 2. Preparar el Frontend

```bash
# Navegar a la carpeta del frontend
cd front

# Crear la carpeta utils si no existe
mkdir utils

# Instalar dependencias (si aún no lo has hecho)
npm install

# Iniciar la aplicación
npx expo start
```

### 3. Crear los Archivos Nuevos

Debes crear estos archivos que no existen en el proyecto original:

1. **`front/utils/passwordValidator.ts`** - Validador de contraseñas
2. **`front/app/groups.tsx`** - Pantalla de grupos
3. **`front/app/expenses.tsx`** - Pantalla de gastos

### 4. Modificar los Archivos Existentes

Reemplaza el contenido de estos archivos:

1. **`front/app/register.tsx`** - Con la versión mejorada
2. **`front/app/login.tsx`** - Con la versión mejorada
3. **`front/app/index.tsx`** - Con la versión mejorada
4. **`front/app/_layout.tsx`** - Con la versión mejorada
5. **`front/context/AuthContext.tsx`** - Con la versión mejorada
6. **`front/app.config.js`** - Con la configuración correcta

---

## ✅ CHECKLIST PARA EL EXAMEN

### Antes de Empezar
- [ ] Backend corriendo en `http://localhost:8000`
- [ ] Frontend corriendo con `npx expo start`
- [ ] Todos los archivos creados y modificados

### Parte 1: Validación de Contraseña ✓
- [ ] Archivo `passwordValidator.ts` creado
- [ ] Función `validarPassword` implementada
- [ ] Función `obtenerErrorPassword` implementada
- [ ] Validación integrada en `register.tsx`
- [ ] Indicadores visuales de requisitos funcionando

### Parte 2: Registro e Inicio de Sesión ✓
- [ ] Registro funciona correctamente
- [ ] Login funciona correctamente
- [ ] Token se guarda en SecureStore
- [ ] Token se incluye en peticiones futuras
- [ ] Errores se muestran correctamente
- [ ] Navegación funciona (registro → login → home)

### Parte 3: Gestión de Grupos ✓
- [ ] Pantalla de grupos creada
- [ ] Listar grupos funciona
- [ ] Crear grupo funciona
- [ ] Token se envía en el header
- [ ] Navegación a gastos funciona

### Parte 4: Gestión de Gastos ✓
- [ ] Pantalla de gastos creada
- [ ] Listar gastos funciona
- [ ] Añadir gasto funciona
- [ ] Modificar gasto funciona (descripción y/o cantidad)
- [ ] Eliminar gasto funciona
- [ ] Confirmación antes de eliminar
- [ ] Total de gastos se calcula correctamente

---

## 🎯 RESPUESTAS RÁPIDAS PARA EL EXAMEN

### ¿Cómo validar una contraseña?
```typescript
import { validarPassword, obtenerErrorPassword } from "../utils/passwordValidator";

if (!validarPassword(password)) {
  const error = obtenerErrorPassword(password);
  setError(error);
  return;
}
```

### ¿Cómo hacer una petición GET con autenticación?
```typescript
const res = await fetch(`${API_URL}/groups`, {
  method: "GET",
  headers: {
    "Authorization": `Bearer ${token}`
  }
});
const data = await res.json();
```

### ¿Cómo hacer una petición POST con autenticación?
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

### ¿Cómo hacer una petición PUT?
```typescript
const res = await fetch(`${API_URL}/groups/${groupId}/expenses/${expenseId}`, {
  method: "PUT",
  headers: {
    "Content-Type": "application/json",
    "Authorization": `Bearer ${token}`
  },
  body: JSON.stringify({ description: nuevaDesc, amount: nuevaCantidad })
});
```

### ¿Cómo hacer una petición DELETE?
```typescript
const res = await fetch(`${API_URL}/groups/${groupId}/expenses/${expenseId}`, {
  method: "DELETE",
  headers: {
    "Authorization": `Bearer ${token}`
  }
});
```

---

## 📝 NOTAS IMPORTANTES

1. **URL de la API**: Asegúrate de configurar correctamente `apiUrl` en `app.config.js` según dónde ejecutes la app:
   - Navegador web: `http://localhost:8000`
   - Emulador Android: `http://10.0.2.2:8000`
   - Dispositivo físico: `http://TU_IP_LOCAL:8000`

2. **Token**: Siempre incluir `Authorization: Bearer ${token}` en peticiones autenticadas.

3. **JSON**: Usar `JSON.stringify()` para enviar datos y `await res.json()` para recibirlos.

4. **Errores**: Siempre usar try/catch y mostrar mensajes al usuario.

5. **Validación**: Validar datos antes de enviarlos al servidor.

---

¡Buena suerte en el examen! 🍀
