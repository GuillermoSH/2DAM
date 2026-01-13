import { Tarea, IdTarea } from "../../modelos/interface/models";

export interface RepositorioTareas {
  obtenerTodas(): Promise<Tarea[]>;
  obtenerPorId(id: IdTarea): Promise<Tarea | undefined>;
  crear(titulo: string, descripcion?: string): Promise<Tarea>;
  actualizar(tarea: Tarea): Promise<Tarea | undefined>;
  borrar(id: IdTarea): Promise<boolean>;
}
