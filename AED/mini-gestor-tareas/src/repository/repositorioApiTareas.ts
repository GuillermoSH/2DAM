import { RepositorioTareas } from "./interface/repositorioTareas";
import { Tarea, IdTarea } from "../modelos/interface/models";

const API_URL = "http://localhost:8080/api/tareas";

export class RepositorioTareasApi implements RepositorioTareas {
  async obtenerTodas(): Promise<Tarea[]> {
    const respuesta = await fetch(API_URL);
    if (!respuesta.ok) throw new Error(`Error al cargar tareas: ${respuesta.status} ${respuesta.statusText}`);
    const datos: unknown = await respuesta.json();
    return datos as Tarea[];
  }

  async obtenerPorId(id: IdTarea): Promise<Tarea | undefined> {
    const respuesta = await fetch(`${API_URL}/${id}`);
    if (!respuesta.ok) return undefined;
    const datos: unknown = await respuesta.json();
    return datos as Tarea;
  }

  async crear(titulo: string, descripcion?: string): Promise<Tarea> {
    const nueva = { titulo, descripcion, completada: false } as Partial<Tarea>;
    const respuesta = await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nueva),
    });
    if (!respuesta.ok) throw new Error(`Error al crear tarea: ${respuesta.status} ${respuesta.statusText}`);
    const datos: unknown = await respuesta.json();
    return datos as Tarea;
  }

  async actualizar(tarea: Tarea): Promise<Tarea | undefined> {
    const respuesta = await fetch(`${API_URL}/${tarea.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(tarea),
    });
    if (!respuesta.ok) return undefined;
    const datos: unknown = await respuesta.json();
    return datos as Tarea;
  }

  async borrar(id: IdTarea): Promise<boolean> {
    const respuesta = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    return respuesta.ok;
  }
}
const repo = new RepositorioTareasApi();

export const obtenerTodas = () => repo.obtenerTodas();
export const obtenerPorId = (id: number) => repo.obtenerPorId(id);
export const crear = (tarea: Partial<Tarea>) => {
  if (!tarea.titulo) throw new Error("titulo requerido");
  return repo.crear(tarea.titulo, tarea.descripcion);
};export const actualizar = (tarea: Tarea) => repo.actualizar(tarea);
export const borrar = (id: number) => repo.borrar(id);


