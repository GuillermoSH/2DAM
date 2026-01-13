import { Tarea, IdTarea, FiltroTarea } from "../modelos/interface/models";
import { RepositorioTareas } from "../repository/interface/repositorioTareas";
import { IServicioTareas } from "./interface/IServicioTarea";

export class ServicioTareas implements IServicioTareas {
    constructor(private repo: RepositorioTareas) { }

    async listar(filtro: FiltroTarea): Promise<Tarea[]> {
        const todas = await this.repo.obtenerTodas();
        switch (filtro) {
            case "pendientes":
                return todas.filter((t) => !t.completada);
            case "completadas":
                return todas.filter((t) => t.completada);
            case "todas":
            default:
                return todas;
        }
    }

    async crear(titulo: string, descripcion?: string): Promise<Tarea> {
        if (!titulo || titulo.trim().length === 0) {
            throw new Error("El título no puede estar vacío");
        }
        return await this.repo.crear(titulo, descripcion);
    }

    async actualizar(tarea: Tarea): Promise<Tarea> {
        if (tarea == null || tarea.id == null ||
            !tarea.titulo || tarea.titulo.trim().length === 0) {
            throw new Error("la tarea no puede estar  nula");
        }
        const actualizada = await this.repo.actualizar(tarea);
        if (!actualizada) throw new Error("No se pudo actualizar");
        return actualizada;
    }

    async borrarPorId(IdTarea: IdTarea): Promise<boolean> {
        if (IdTarea == null) {
            return false;
        }
        return await this.repo.borrar(IdTarea);
    }

    async borrar(tarea: Tarea): Promise<boolean> {
        if (tarea == null) {
            return false;
        }
        return await this.borrarPorId(tarea.id)
    }

}
