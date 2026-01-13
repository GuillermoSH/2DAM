import { Tarea, IdTarea, FiltroTarea } from "../../modelos/interface/models";

export interface IServicioTareas {
    listar(filtro: FiltroTarea): Promise<Tarea[]>;
    crear(titulo: string, descripcion?: string): Promise<Tarea>;
    actualizar(tarea: Tarea): Promise<Tarea>;
    borrarPorId(id: IdTarea): Promise<boolean>;
    borrar(tarea: Tarea): Promise<boolean>;
}