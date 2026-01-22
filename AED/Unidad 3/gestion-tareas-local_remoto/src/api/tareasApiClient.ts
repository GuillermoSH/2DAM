import { Tarea } from '../models/tarea.js'

const BASE_URL = 'http://localhost:8080/api/tareas'

export class TareasApiClient {

  async findAll(): Promise<Tarea[]> {
    const res = await fetch(BASE_URL)
    return res.json()
  }

  async findById(id: number): Promise<Tarea> {
    const res = await fetch(`${BASE_URL}/${id}`)
    return res.json()
  }

  async create(tarea: Tarea): Promise<Tarea> {
    const res = await fetch(BASE_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(tarea)
    })
    return res.json()
  }

  async update(id: number, tarea: Tarea): Promise<Tarea> {
    const res = await fetch(`${BASE_URL}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(tarea)
    })
    return res.json()
  }

  async delete(id: number): Promise<void> {
    await fetch(`${BASE_URL}/${id}`, { method: 'DELETE' })
  }
}
