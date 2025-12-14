# 📋 Gestión de Tareas Local y Remoto

## 🚀 Descripción

Esta aplicación es un **mini gestor de tareas** que funciona con **dos fuentes de datos**:

1. 💾 **Local**: SQLite3
2. 🌐 **Remoto**: API REST (Spring Boot + H2)

Permite **crear, listar, actualizar, borrar** tareas y **sincronizar** entre local y remoto.

---

## 🛠 Tecnologías utilizadas

* **Node.js + TypeScript**
* **SQLite3** para almacenamiento local
* **API REST remota** mediante `fetch`
* Arquitectura **por capas**: modelos, repositorios, servicios, API

---

## 📁 Estructura del proyecto

```
gestion-tareas-local_remoto/
├─ dist/                       🗂 Archivos compilados TypeScript
├─ node_modules/               📦 Dependencias
├─ src/
│  ├─ api/                     🌐 Cliente API
│  │  └─ tareasApiClient.ts
│  ├─ db/                      💾 Inicialización SQLite
│  │  └─ sqlite.ts
│  ├─ models/                  📝 Modelos
│  │  └─ tarea.ts
│  ├─ repositories/            🔄 Repositorios
│  │  ├─ interfaces/
│  │  │  └─ tareasRepository.ts
│  │  ├─ tareasApiRepository.ts
│  │  └─ tareasSqliteRepository.ts
│  ├─ services/                ⚙️ Lógica de negocio
│  │  └─ tareasService.ts
│  └─ index.ts                 🔑 Punto de entrada
├─ package.json
├─ tsconfig.json
├─ tareas.db                    🗃 Base de datos SQLite
└─ README.md
```

---

## ⚡ Instalación

1. Clonar el repositorio:

```bash
git clone <URL_DEL_REPOSITORIO>
cd gestion-tareas-local_remoto
```

2. Instalar dependencias:

```bash
npm install
```

3. Inicializar la base de datos (opcional, se crea automáticamente al ejecutar):

```ts
import { initDb } from './db/sqlite.js'
await initDb()
```

---

## 🏃 Scripts disponibles

| Script          | Descripción                                     |
| --------------- | ----------------------------------------------- |
| `npm run build` | Compila TypeScript a JS en `dist/`              |
| `npm start`     | Ejecuta el proyecto compilado (`dist/index.js`) |
| `npm run dev`   | Ejecuta directamente con `ts-node`              |

---

## ✨ Uso de la aplicación

```ts
import { TareasSqliteRepository } from './repositories/tareasSqliteRepository.js'
import { TareasApiClient } from './api/tareasApiClient.js'
import { TareasApiRepository } from './repositories/tareasApiRepository.js'
import { TareasService } from './services/tareasService.js'

// Repositorios
const localRepo = new TareasSqliteRepository()
const apiClient = new TareasApiClient()
const remoteRepo = new TareasApiRepository(apiClient)

// Servicios
const serviceLocal = new TareasService(localRepo)
const serviceRemote = new TareasService(remoteRepo)

// Crear tareas
await serviceLocal.crear({ id: 1, titulo: 'Tarea local', completada: false })
await serviceRemote.crear({ id: 2, titulo: 'Tarea remota', completada: false })

// Listar tareas
console.log('🌱 Local:', await serviceLocal.listar())
console.log('🌍 Remoto:', await serviceRemote.listar())

// Sincronizar remoto → local
await serviceLocal.sincronizarDesde(remoteRepo)
console.log('🔄 Local tras sincronizar:', await serviceLocal.listar())

// Sincronizar local → remoto
await serviceRemote.sincronizarDesde(localRepo)
console.log('🔄 Remoto tras sincronizar:', await serviceRemote.listar())

// Borrar todas las tareas locales
await serviceLocal.borrarTodas()
console.log('🗑 Local tras borrar todas:', await serviceLocal.listar())
```

---

## 🌐 API REST remota

* URL base: `http://localhost:8080/api/tareas`
* Endpoints:

| Método | Endpoint          | Descripción             |
| ------ | ----------------- | ----------------------- |
| GET    | `/api/tareas`     | Listar todas las tareas |
| GET    | `/api/tareas/:id` | Obtener tarea por id    |
| POST   | `/api/tareas`     | Crear nueva tarea       |
| PUT    | `/api/tareas/:id` | Actualizar tarea        |
| DELETE | `/api/tareas/:id` | Borrar tarea            |

> ⚠ Nota: Para usar `deleteAll()` en remoto, puede requerirse un endpoint extra.

---

## 🔄 Sincronización

* Método usado:

```ts
await serviceLocal.sincronizarDesde(remoteRepo)
```

* Estrategia: **se borran todas las tareas del repositorio destino y se reemplazan por las del repositorio origen**.
* Se puede sincronizar **remoto → local** o **local → remoto** según el repositorio pasado.

---

## 📝 Consideraciones

* Los IDs en SQLite son `PRIMARY KEY`. Para evitar errores de conflicto, se puede usar `INSERT OR REPLACE`.
* La interfaz `TareasRepository` permite intercambiar repositorios sin cambiar la lógica del servicio.
* Todos los métodos CRUD están implementados:

  * Crear ✔️
  * Listar ✔️
  * Actualizar ✔️
  * Borrar ✔️
  * Borrar todas ✔️
