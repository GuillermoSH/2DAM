import { TareasSqliteRepository } from './repositories/tareasSqliteRepository.js'
import { TareasApiClient } from './api/tareasApiClient.js'
import { TareasApiRepository } from './repositories/tareasApiRepository.js'
import { TareasService } from './services/tareasService.js'

const localRepo = new TareasSqliteRepository()
const apiClient = new TareasApiClient()
const remoteRepo = new TareasApiRepository(apiClient)

const serviceLocal = new TareasService(localRepo)
const serviceRemote = new TareasService(remoteRepo)

// Crear tareas
await serviceLocal.crear({ id: 100, titulo: 'Tarea local', completada: false })
await serviceRemote.crear({ id: 0,  titulo: 'Tarea remota', completada: false })
console.log('Tareas locales', await serviceLocal.listar())
console.log('Tareas en remoto', await serviceRemote.listar())

// Sincronizar remoto -> local
await serviceLocal.sincronizarDesde(remoteRepo)

// Borrar todas las tareas locales
await serviceLocal.borrarTodas()
console.log('Tareas locales tras sincronizar desde remoto y borrar todas:', await serviceLocal.listar())