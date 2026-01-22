import { Tarea } from '../../models/tarea.js'

export interface TareasRepository {
  findAll(): Promise<Tarea[]>
  findById(id: number): Promise<Tarea | undefined>
  save(tarea: Tarea): Promise<Tarea>
  update(tarea: Tarea): Promise<Tarea>
  delete(id: number): Promise<void>
  deleteAll(): Promise<void>
}
