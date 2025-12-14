import { TareasRepository } from './interfaces/tareasRepository.js';
import { Tarea } from '../models/tarea.js';
import { TareasApiClient } from '../api/tareasApiClient.js';

export class TareasApiRepository implements TareasRepository {
  constructor(private api: TareasApiClient) {}

  findAll(): Promise<Tarea[]> {
    return this.api.findAll();
  }

  findById(id: number): Promise<Tarea | undefined> {
    return this.api.findById(id);
  }

  save(tarea: Tarea): Promise<Tarea> {
    return this.api.create(tarea);
  }

  update(tarea: Tarea): Promise<Tarea> {
    return this.api.update(tarea.id, tarea);
  }

  delete(id: number): Promise<void> {
    return this.api.delete(id);
  }

  deleteAll(): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
