# 📋 Gestor de Tareas - Task Manager

Sistema de gestión de tareas con TypeScript que soporta almacenamiento local (SQLite) y remoto (API REST).

## 🚀 Cómo ejecutar el proyecto

### Prerrequisitos

- Node.js (v18 o superior)
- npm o yarn
- TypeScript instalado globalmente (opcional)

### Instalación

```bash
# Instalar dependencias
npm install

# Instalar dependencias específicas del proyecto
npm install better-sqlite3 node-fetch@2
npm install -D @types/better-sqlite3 @types/node
```

### Ejecución

El proyecto tiene tres modos de ejecución principales:

#### 1. CLI Interactiva (Modo Local o Remoto)

```bash
# Compilar TypeScript
npx tsc

# Ejecutar CLI interactiva
node dist/cli/index.js
```

La aplicación te preguntará si deseas trabajar en modo Local (SQLite) o Remoto (API).

#### 2. Modo Local Únicamente

```bash
# Ejecutar con ts-node (desarrollo)
npx ts-node src/cli/index-local.ts

# O compilar y ejecutar
npx tsc
node dist/cli/index-local.js
```

#### 3. Modo Remoto Únicamente

**Importante**: Primero debes iniciar el servidor API:

```bash
# En una terminal separada, iniciar json-server (o tu API)
npx json-server --watch db.json --port 8080 --routes routes.json
```

Luego ejecutar el cliente:

```bash
npx ts-node src/cli/index-remoto.ts
# o
node dist/cli/index-remoto.js
```

#### 4. CLI Simple (sin interacción)

```bash
# Filtrar tareas desde línea de comandos
node dist/cli/Cli.js todas
node dist/cli/Cli.js pendientes
node dist/cli/Cli.js completadas
```

## 📁 Estructura de carpetas

```
proyecto/
│
├── src/
│   ├── cli/                          # Punto de entrada de la aplicación
│   │   ├── index.ts                  # CLI interactiva (local + remoto)
│   │   ├── index-local.ts            # Ejecución solo modo local
│   │   ├── index-remoto.ts           # Ejecución solo modo remoto
│   │   └── Cli.ts                    # CLI simple sin interacción
│   │
│   ├── modelos/                      # Definiciones de datos
│   │   ├── interface/
│   │   │   └── models.ts             # Interfaces Tarea, IdTarea, FiltroTarea
│   │   └── tareas.ts                 # Funciones auxiliares de tareas
│   │
│   ├── repository/                   # Capa de acceso a datos
│   │   ├── interface/
│   │   │   └── repositorioTareas.ts  # Interfaz RepositorioTareas
│   │   ├── repositorioTareasSqlite.ts  # Implementación SQLite
│   │   └── repositorioApiTareas.ts     # Implementación API REST
│   │
│   ├── servicios/                    # Lógica de negocio
│   │   ├── interface/
│   │   │   └── IServicioTarea.ts     # Interfaz del servicio
│   │   └── ServicioTareas.ts         # Implementación del servicio
│   │
│   └── resources/                    # Recursos y configuración
│       └── db.ts                     # Configuración SQLite
│
├── dist/                             # Código compilado (generado)
├── tareas.db                         # Base de datos SQLite (generada)
├── db.json                           # Datos para json-server (opcional)
├── tsconfig.json                     # Configuración TypeScript
├── package.json                      # Dependencias del proyecto
└── README.md                         # Este archivo
```

### Descripción de componentes clave

- **`cli/`**: Contiene los puntos de entrada de la aplicación con diferentes interfaces de usuario
- **`modelos/`**: Define las estructuras de datos y tipos TypeScript
- **`repository/`**: Implementa el patrón Repository para abstraer el acceso a datos
- **`servicios/`**: Contiene la lógica de negocio y validaciones
- **`resources/`**: Configuración de recursos como la base de datos

## 🌐 Endpoints remotos y configuración

### URL Base

La configuración de la API se encuentra en `src/repository/repositorioApiTareas.ts`:

```typescript
const API_URL = "http://localhost:8080/api/tareas";
```

### Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/tareas` | Obtener todas las tareas |
| `GET` | `/api/tareas/:id` | Obtener una tarea por ID |
| `POST` | `/api/tareas` | Crear una nueva tarea |
| `PUT` | `/api/tareas/:id` | Actualizar una tarea existente |
| `DELETE` | `/api/tareas/:id` | Eliminar una tarea |

### Configuración del servidor

Para cambiar la URL base, modifica la constante en `repositorioApiTareas.ts`:

```typescript
/** Desarrollo local
 * Utiliza la api creada en clase spring-tareas-rest-soap
 */
const API_URL = "http://localhost:8080/api/tareas";

// Producción
const API_URL = "https://mi-api.com/api/tareas";
```

### Configurar json-server (para desarrollo)

1. Crear `db.json` en la raíz del proyecto:

```json
{
  "tareas": [
    {
      "id": 1,
      "titulo": "Tarea de ejemplo",
      "descripcion": "Esta es una tarea de prueba",
      "completada": false
    }
  ]
}
```

2. Crear `routes.json` (opcional):

```json
{
  "/api/*": "/$1"
}
```

3. Iniciar el servidor:

```bash
npx json-server --watch db.json --port 8080 --routes routes.json
```

## 🔄 Sincronización Remoto ↔ Local

### Arquitectura de sincronización

El proyecto utiliza el **patrón Repository** para abstraer el almacenamiento de datos, permitiendo cambiar entre implementaciones sin modificar la lógica de negocio.

```
ServicioTareas
      ↓
RepositorioTareas (Interfaz)
      ↓
      ├── RepositorioTareasSqlite (Local)
      └── RepositorioTareasApi (Remoto)
```

### Flujo de datos: Remoto → Local

#### 1. Lectura desde API

```typescript
// En repositorioApiTareas.ts
async obtenerTodas(): Promise<Tarea[]> {
  const respuesta = await fetch(API_URL);
  if (!respuesta.ok) throw new Error(`Error: ${respuesta.status}`);
  const datos = await respuesta.json();
  return datos as Tarea[];
}
```

#### 2. Procesamiento en el servicio

```typescript
// En ServicioTareas.ts
async listar(filtro: FiltroTarea): Promise<Tarea[]> {
  const todas = await this.repo.obtenerTodas(); // Llama al repo (local o remoto)
  switch (filtro) {
    case "pendientes":
      return todas.filter(t => !t.completada);
    case "completadas":
      return todas.filter(t => t.completada);
    default:
      return todas;
  }
}
```

#### 3. Presentación en CLI

```typescript
// En index.ts
const servicio = new ServicioTareas(remoto); // o local
const tareas = await servicio.listar("todas");
// Muestra las tareas al usuario
```

### Estrategias de sincronización

#### Opción 1: Selección manual (implementada actualmente)

El usuario elige al inicio si trabaja en modo local o remoto. No hay sincronización automática entre ambos.

```typescript
// El usuario selecciona el repositorio
const servicio = modoLocal 
  ? new ServicioTareas(new RepositorioTareasSqlite())
  : new ServicioTareas(new RepositorioTareasApi());
```

#### Opción 2: Sincronización automática (posible extensión)

Para implementar sincronización bidireccional, se podría:

1. **Al iniciar la aplicación**: Descargar tareas remotas → guardar en SQLite

```typescript
async sincronizarRemotoALocal() {
  const tareasRemotas = await repoRemoto.obtenerTodas();
  for (const tarea of tareasRemotas) {
    await repoLocal.crear(tarea.titulo, tarea.descripcion);
  }
}
```

2. **Al crear/modificar**: Guardar localmente primero → enviar a servidor

```typescript
async crearConSincronizacion(titulo: string, desc?: string) {
  // Guardar local (siempre funciona)
  const tareaLocal = await repoLocal.crear(titulo, desc);
  
  try {
    // Intentar sincronizar con remoto
    await repoRemoto.crear(titulo, desc);
  } catch (error) {
    console.log("Sin conexión, se sincronizará después");
  }
  
  return tareaLocal;
}
```

3. **Periódicamente**: Verificar cambios y sincronizar diferencias

### Ventajas del diseño actual

- ✅ **Flexibilidad**: Cambia entre local/remoto sin modificar código de negocio
- ✅ **Offline-first**: SQLite funciona sin conexión
- ✅ **Testeable**: Fácil crear mocks de repositorios
- ✅ **Escalable**: Agregar nuevos repositorios (MongoDB, Firebase) es trivial

### Limitaciones actuales

- ⚠️ No hay sincronización automática entre local y remoto
- ⚠️ Los datos locales y remotos son independientes
- ⚠️ No hay detección de conflictos

## Testing

```bash
# Prueba el modo local
npm run test:local

# Prueba el modo remoto (asegúrate de tener json-server corriendo)
npm run test:remoto
```

## 📝 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

**Nota**: Para un uso en producción, considera implementar:
- Manejo robusto de errores de red
- Sistema de retry para peticiones fallidas
- Cache de datos para mejorar rendimiento
- Sincronización bidireccional con resolución de conflictos