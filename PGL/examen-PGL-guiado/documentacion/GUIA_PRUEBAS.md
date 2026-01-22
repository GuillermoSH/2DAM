# 🧪 GUÍA DE PRUEBAS - EXAMEN SPLITEXPENSER

## 📋 CASOS DE PRUEBA PARA EL EXAMEN

Esta guía te ayudará a probar sistemáticamente cada parte del examen para asegurarte de que todo funciona correctamente.

---

## 🔐 PARTE 1: VALIDACIÓN DE CONTRASEÑA

### Test 1: Contraseña muy corta
```
Input: "Abc1!"
Resultado esperado: ❌ "La contraseña debe tener al menos 8 caracteres"
```

### Test 2: Sin mayúscula
```
Input: "abc12345!"
Resultado esperado: ❌ "La contraseña debe tener al menos una mayúscula (A-Z)"
```

### Test 3: Sin minúscula
```
Input: "ABC12345!"
Resultado esperado: ❌ "La contraseña debe tener al menos una minúscula (a-z)"
```

### Test 4: Sin número
```
Input: "Abcdefgh!"
Resultado esperado: ❌ "La contraseña debe tener al menos un número (0-9)"
```

### Test 5: Sin símbolo
```
Input: "Abc12345"
Resultado esperado: ❌ "La contraseña debe tener al menos un símbolo (!#$%&?)"
```

### Test 6: Contraseña válida
```
Input: "MiPass123!"
Resultado esperado: ✅ Registro exitoso, redirige a login
```

### Test 7: Indicadores visuales
```
Mientras escribes "Abc1" deberías ver:
✗ Mínimo 8 caracteres
✓ Al menos una mayúscula (A-Z)
✓ Al menos una minúscula (a-z)
✓ Al menos un número (0-9)
✗ Al menos un símbolo (!#$%&?)
```

---

## 🔑 PARTE 2: REGISTRO E INICIO DE SESIÓN

### Test 8: Registro con usuario nuevo
```
Pasos:
1. Ir a pantalla de registro
2. Ingresar username: "testuser1"
3. Ingresar password: "Test123!"
4. Presionar "Registrar"

Resultado esperado:
✅ Mensaje de éxito
✅ Redirige a pantalla de login
✅ Usuario guardado en base de datos
```

### Test 9: Registro con usuario existente
```
Pasos:
1. Intentar registrar "testuser1" nuevamente
2. Presionar "Registrar"

Resultado esperado:
❌ Error: "User already exists"
```

### Test 10: Login con credenciales correctas
```
Pasos:
1. Ir a pantalla de login
2. Ingresar username: "testuser1"
3. Ingresar password: "Test123!"
4. Presionar "Iniciar Sesión"

Resultado esperado:
✅ Recibe token
✅ Token se guarda en SecureStore
✅ Redirige a pantalla principal
```

### Test 11: Login con credenciales incorrectas
```
Pasos:
1. Ingresar username: "testuser1"
2. Ingresar password: "WrongPass123!"
3. Presionar "Iniciar Sesión"

Resultado esperado:
❌ Error: "Bad credentials"
```

### Test 12: Login con usuario inexistente
```
Pasos:
1. Ingresar username: "noexiste"
2. Ingresar password: "Test123!"
3. Presionar "Iniciar Sesión"

Resultado esperado:
❌ Error: "Bad credentials"
```

### Test 13: Persistencia de sesión
```
Pasos:
1. Hacer login exitoso
2. Cerrar la aplicación
3. Abrir la aplicación nuevamente

Resultado esperado:
✅ Sigue logueado (no pide login)
✅ Va directamente a pantalla principal
```

---

## 👥 PARTE 3: GESTIÓN DE GRUPOS

### Test 14: Listar grupos vacío
```
Pasos:
1. Hacer login con usuario nuevo
2. Ir a pantalla de grupos

Resultado esperado:
✅ Muestra mensaje "No tienes grupos aún"
✅ Contador muestra "Mis Grupos (0)"
```

### Test 15: Crear primer grupo
```
Pasos:
1. Ingresar nombre: "Viaje a Madrid"
2. Presionar "Crear Grupo"

Resultado esperado:
✅ Mensaje de éxito
✅ Campo se limpia
✅ Grupo aparece en la lista
✅ Contador muestra "Mis Grupos (1)"
```

### Test 16: Crear múltiples grupos
```
Pasos:
1. Crear grupo "Piso compartido"
2. Crear grupo "Cena de amigos"
3. Crear grupo "Gimnasio"

Resultado esperado:
✅ Todos los grupos aparecen en la lista
✅ Contador muestra "Mis Grupos (4)"
```

### Test 17: Crear grupo sin nombre
```
Pasos:
1. Dejar campo vacío
2. Presionar "Crear Grupo"

Resultado esperado:
❌ Error: "El nombre del grupo no puede estar vacío"
```

### Test 18: Navegar a gastos de un grupo
```
Pasos:
1. Presionar sobre "Viaje a Madrid"

Resultado esperado:
✅ Navega a pantalla de gastos
✅ Muestra título "Viaje a Madrid"
```

### Test 19: Verificar token en peticiones
```
Pasos:
1. Abrir DevTools del navegador (F12)
2. Ir a pestaña "Network"
3. Crear un grupo
4. Ver la petición POST /groups

Resultado esperado:
✅ Header "Authorization: Bearer {token}" presente
✅ Header "Content-Type: application/json" presente
✅ Body contiene {"name": "Nombre del grupo"}
```

---

## 💰 PARTE 4: GESTIÓN DE GASTOS

### Test 20: Listar gastos vacío
```
Pasos:
1. Entrar a un grupo sin gastos

Resultado esperado:
✅ Muestra mensaje "No hay gastos aún"
✅ Total muestra "0.00 €"
✅ Contador muestra "Gastos (0)"
```

### Test 21: Añadir primer gasto
```
Pasos:
1. Ingresar descripción: "Cena en restaurante"
2. Ingresar cantidad: "45.50"
3. Presionar "Añadir Gasto"

Resultado esperado:
✅ Mensaje de éxito
✅ Campos se limpian
✅ Gasto aparece en la lista
✅ Total muestra "45.50 €"
✅ Contador muestra "Gastos (1)"
```

### Test 22: Añadir múltiples gastos
```
Pasos:
1. Añadir "Hotel" - 100.00
2. Añadir "Transporte" - 25.50
3. Añadir "Entradas museo" - 15.00

Resultado esperado:
✅ Todos los gastos aparecen
✅ Total muestra "186.00 €" (45.50 + 100 + 25.50 + 15)
✅ Contador muestra "Gastos (4)"
```

### Test 23: Añadir gasto sin descripción
```
Pasos:
1. Dejar descripción vacía
2. Ingresar cantidad: "10"
3. Presionar "Añadir Gasto"

Resultado esperado:
❌ Error: "La descripción no puede estar vacía"
```

### Test 24: Añadir gasto sin cantidad
```
Pasos:
1. Ingresar descripción: "Test"
2. Dejar cantidad vacía
3. Presionar "Añadir Gasto"

Resultado esperado:
❌ Error: "Ingresa una cantidad válida"
```

### Test 25: Añadir gasto con cantidad inválida
```
Pasos:
1. Ingresar descripción: "Test"
2. Ingresar cantidad: "abc"
3. Presionar "Añadir Gasto"

Resultado esperado:
❌ Error: "Ingresa una cantidad válida"
```

### Test 26: Añadir gasto con cantidad negativa
```
Pasos:
1. Ingresar descripción: "Test"
2. Ingresar cantidad: "-10"
3. Presionar "Añadir Gasto"

Resultado esperado:
❌ Error: "La cantidad debe ser mayor a 0"
```

### Test 27: Modificar descripción de un gasto
```
Pasos:
1. Presionar "Editar" en "Cena en restaurante"
2. Cambiar descripción a "Cena en italiano"
3. Dejar cantidad vacía
4. Presionar "Guardar Cambios"

Resultado esperado:
✅ Descripción se actualiza a "Cena en italiano"
✅ Cantidad se mantiene en 45.50 €
✅ Formulario se limpia
✅ Modo edición se desactiva
```

### Test 28: Modificar cantidad de un gasto
```
Pasos:
1. Presionar "Editar" en "Hotel"
2. Dejar descripción vacía
3. Cambiar cantidad a "120.00"
4. Presionar "Guardar Cambios"

Resultado esperado:
✅ Cantidad se actualiza a 120.00 €
✅ Descripción se mantiene como "Hotel"
✅ Total se recalcula correctamente
```

### Test 29: Modificar descripción y cantidad
```
Pasos:
1. Presionar "Editar" en "Transporte"
2. Cambiar descripción a "Taxi al aeropuerto"
3. Cambiar cantidad a "30.00"
4. Presionar "Guardar Cambios"

Resultado esperado:
✅ Ambos campos se actualizan
✅ Total se recalcula correctamente
```

### Test 30: Cancelar edición
```
Pasos:
1. Presionar "Editar" en cualquier gasto
2. Cambiar algunos valores
3. Presionar "Cancelar"

Resultado esperado:
✅ Formulario se limpia
✅ Modo edición se desactiva
✅ Gasto no se modifica
```

### Test 31: Eliminar gasto con confirmación
```
Pasos:
1. Presionar "Eliminar" en "Entradas museo"
2. En el diálogo, presionar "Eliminar"

Resultado esperado:
✅ Muestra diálogo de confirmación
✅ Gasto se elimina
✅ Total se recalcula
✅ Contador se actualiza
```

### Test 32: Cancelar eliminación
```
Pasos:
1. Presionar "Eliminar" en cualquier gasto
2. En el diálogo, presionar "Cancelar"

Resultado esperado:
✅ Diálogo se cierra
✅ Gasto NO se elimina
```

### Test 33: Verificar cálculo de total
```
Pasos:
1. Tener gastos: 45.50, 120.00, 30.00
2. Ver el total

Resultado esperado:
✅ Total muestra "195.50 €"
✅ Total se actualiza al añadir gasto
✅ Total se actualiza al modificar gasto
✅ Total se actualiza al eliminar gasto
```

---

## 🔄 PRUEBAS DE INTEGRACIÓN

### Test 34: Flujo completo de usuario nuevo
```
Pasos:
1. Registrarse con "usuario_test" / "Test123!"
2. Hacer login
3. Crear grupo "Test Grupo"
4. Entrar al grupo
5. Añadir gasto "Test" - 10.00
6. Modificar gasto a "Test Modificado" - 15.00
7. Eliminar gasto
8. Cerrar sesión

Resultado esperado:
✅ Todo funciona sin errores
✅ Navegación fluida entre pantallas
```

### Test 35: Múltiples grupos y gastos
```
Pasos:
1. Crear 3 grupos diferentes
2. En cada grupo, añadir 3 gastos
3. Modificar 1 gasto en cada grupo
4. Eliminar 1 gasto en cada grupo
5. Volver a lista de grupos
6. Verificar que todos los grupos siguen ahí

Resultado esperado:
✅ Cada grupo mantiene sus gastos independientes
✅ Totales calculados correctamente en cada grupo
```

### Test 36: Persistencia de datos
```
Pasos:
1. Crear grupos y gastos
2. Cerrar la aplicación
3. Detener el backend
4. Reiniciar el backend
5. Abrir la aplicación

Resultado esperado:
✅ Todos los datos siguen ahí
✅ Grupos y gastos se cargan correctamente
```

---

## 🚨 PRUEBAS DE ERRORES

### Test 37: Backend no disponible
```
Pasos:
1. Detener el backend
2. Intentar crear un grupo

Resultado esperado:
❌ Error: "Error de conexión"
```

### Test 38: Token expirado/inválido
```
Pasos:
1. Modificar manualmente el token en SecureStore
2. Intentar cargar grupos

Resultado esperado:
❌ Error 401
✅ Redirige a login
```

### Test 39: Campos con espacios en blanco
```
Pasos:
1. Ingresar "   " (solo espacios) en nombre de grupo
2. Presionar "Crear Grupo"

Resultado esperado:
❌ Error: "El nombre del grupo no puede estar vacío"
```

### Test 40: Números decimales en gastos
```
Pasos:
1. Ingresar cantidad: "45.99"
2. Añadir gasto

Resultado esperado:
✅ Acepta decimales
✅ Muestra "45.99 €"
```

---

## 📊 CHECKLIST DE PRUEBAS

### Parte 1: Validación de Contraseña
- [ ] Test 1: Contraseña muy corta
- [ ] Test 2: Sin mayúscula
- [ ] Test 3: Sin minúscula
- [ ] Test 4: Sin número
- [ ] Test 5: Sin símbolo
- [ ] Test 6: Contraseña válida
- [ ] Test 7: Indicadores visuales

### Parte 2: Autenticación
- [ ] Test 8: Registro exitoso
- [ ] Test 9: Usuario duplicado
- [ ] Test 10: Login correcto
- [ ] Test 11: Login incorrecto
- [ ] Test 12: Usuario inexistente
- [ ] Test 13: Persistencia de sesión

### Parte 3: Grupos
- [ ] Test 14: Lista vacía
- [ ] Test 15: Crear primer grupo
- [ ] Test 16: Crear múltiples grupos
- [ ] Test 17: Grupo sin nombre
- [ ] Test 18: Navegación a gastos
- [ ] Test 19: Token en peticiones

### Parte 4: Gastos
- [ ] Test 20: Lista vacía
- [ ] Test 21: Añadir primer gasto
- [ ] Test 22: Añadir múltiples gastos
- [ ] Test 23: Sin descripción
- [ ] Test 24: Sin cantidad
- [ ] Test 25: Cantidad inválida
- [ ] Test 26: Cantidad negativa
- [ ] Test 27: Modificar descripción
- [ ] Test 28: Modificar cantidad
- [ ] Test 29: Modificar ambos
- [ ] Test 30: Cancelar edición
- [ ] Test 31: Eliminar con confirmación
- [ ] Test 32: Cancelar eliminación
- [ ] Test 33: Cálculo de total

### Integración
- [ ] Test 34: Flujo completo
- [ ] Test 35: Múltiples grupos
- [ ] Test 36: Persistencia

### Errores
- [ ] Test 37: Backend no disponible
- [ ] Test 38: Token inválido
- [ ] Test 39: Espacios en blanco
- [ ] Test 40: Decimales

---

## 🎯 DATOS DE PRUEBA RECOMENDADOS

### Usuarios
```
Usuario 1:
- Username: testuser1
- Password: Test123!

Usuario 2:
- Username: alumno
- Password: Examen2024!

Usuario 3:
- Username: demo
- Password: Demo123#
```

### Grupos
```
- Viaje a Madrid
- Piso compartido
- Cena de amigos
- Gimnasio
- Proyecto universidad
```

### Gastos
```
Viaje a Madrid:
- Vuelos: 150.00
- Hotel: 200.00
- Comidas: 85.50
- Transporte: 45.00
- Entradas: 30.00

Piso compartido:
- Alquiler: 600.00
- Luz: 45.50
- Agua: 25.00
- Internet: 35.00
- Limpieza: 20.00
```

---

## 🔍 CÓMO VERIFICAR QUE TODO FUNCIONA

### 1. Verificar Base de Datos
```bash
# En la carpeta back/
sqlite3 splitexpenser.db

# Ver usuarios
SELECT * FROM user;

# Ver grupos
SELECT * FROM "group";

# Ver gastos
SELECT * FROM expense;

# Salir
.quit
```

### 2. Verificar Token en DevTools
```
1. Abrir DevTools (F12)
2. Ir a Application > Storage > Local Storage
3. Buscar "auth_token"
4. Verificar que existe y tiene un valor largo
```

### 3. Verificar Peticiones HTTP
```
1. Abrir DevTools (F12)
2. Ir a Network
3. Hacer una acción (crear grupo, etc.)
4. Ver la petición
5. Verificar:
   - URL correcta
   - Método correcto (GET, POST, PUT, DELETE)
   - Headers correctos
   - Body correcto (si aplica)
   - Respuesta correcta
```

---

¡Usa esta guía para probar sistemáticamente tu solución! ✅
