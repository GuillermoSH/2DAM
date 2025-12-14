import { TareasRepository } from './interfaces/tareasRepository.js';
import { Tarea } from '../models/tarea.js';
import { dbPromise } from '../db/sqlite.js';

export class TareasSqliteRepository implements TareasRepository {
  async findAll(): Promise<Tarea[]> {
    const db = await dbPromise;
    return db.all('SELECT * FROM tareas');
  }

  async findById(id: number): Promise<Tarea | undefined> {
    const db = await dbPromise;
    return db.get('SELECT * FROM tareas WHERE id = ?', id);
  }

  async save(tarea: Tarea): Promise<Tarea> {
    const db = await dbPromise;
    await db.run(
      'INSERT INTO tareas(id, titulo, descripcion, completada) VALUES (?, ?, ?, ?)',
      tarea.id, tarea.titulo, tarea.descripcion ?? null, tarea.completada ? 1 : 0
    );
    return tarea;
  }

  async update(tarea: Tarea): Promise<Tarea> {
    const db = await dbPromise;
    await db.run(
      'UPDATE tareas SET titulo = ?, descripcion = ?, completada = ? WHERE id = ?',
      tarea.titulo, tarea.descripcion ?? null, tarea.completada ? 1 : 0, tarea.id
    );
    return tarea;
  }

  async delete(id: number): Promise<void> {
    const db = await dbPromise;
    await db.run('DELETE FROM tareas WHERE id = ?', id);
  }

  async deleteAll(): Promise<void> {
    const db = await dbPromise;
    await db.run('DELETE FROM tareas');
  }
}
