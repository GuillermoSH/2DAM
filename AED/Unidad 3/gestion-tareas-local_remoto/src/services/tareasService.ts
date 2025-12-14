import { Tarea } from '../models/tarea.js'
import { TareasRepository } from '../repositories/interfaces/tareasRepository.js'

export class TareasService {
  constructor(private repo: TareasRepository) {}

  listar(): Promise<Tarea[]> {
    return this.repo.findAll()
  }

  crear(tarea: Tarea): Promise<Tarea> {
    return this.repo.save(tarea)
  }

  actualizar(tarea: Tarea): Promise<Tarea> {
    return this.repo.update(tarea)
  }

  borrar(id: number): Promise<void> {
    return this.repo.delete(id)
  }

  borrarTodas(): Promise<void> {
    return this.repo.deleteAll()
  }

  async sincronizarDesde(origen: TareasRepository) {
    const tareas = await origen.findAll()
    await this.repo.deleteAll()
    for (const tarea of tareas) {
      await this.repo.save(tarea)
    }
  }
}
