import { getDb } from "../resources/db";
import { Tarea, IdTarea } from "../modelos/interface/models";
import { RepositorioTareas } from "./interface/repositorioTareas";

export class RepositorioTareasSqlite implements RepositorioTareas {

    private db = getDb();

    async obtenerTodas(): Promise<Tarea[]> {
        const data: any[] = this.db.prepare("SELECT id, titulo, descripcion, completada FROM tareas")
            .all();
        return data.map(tarea => ({
            id: tarea.id,
            titulo: tarea.titulo,
            descripcion: tarea.descripcion === null ? undefined : tarea.descripcion,
            completada: tarea.completada === 1
        }));
    }

    async obtenerPorId(id: IdTarea): Promise<Tarea | undefined> {

        const data = this.db.prepare("SELECT id, titulo, descripcion, completada FROM tareas WHERE id = ?")
            .get(id) as { id: number; titulo: string; descripcion: string | null; completada: number } | undefined;
        if (!data) {
            return undefined;
        }
        return {
            id: data.id,
            titulo: data.titulo,
            descripcion: data.descripcion === null ? undefined : data.descripcion,
            completada: data.completada === 1
        };
    }

    async crear(titulo: string, descripcion?: string): Promise<Tarea> {

        const resultado = this.db.prepare(
            "INSERT INTO tareas (titulo, descripcion, completada) VALUES (?, ?, 0)"
        ).run(titulo, descripcion ?? null);

        const id = Number(resultado.lastInsertRowid)

        const tarea = await this.obtenerPorId(id);
        if (!tarea) throw new Error("No se pudo recuperar la tarea creada");
        return tarea;
    }

    async actualizar(tarea: Tarea): Promise<Tarea | undefined> {
        const respuesta = this.db.prepare(
            "UPDATE tareas SET titulo = ?, descripcion = ?, completada = ? WHERE id = ?"
        ).run(tarea.titulo,tarea.descripcion ?? null,tarea.completada ? 1:0,tarea.id) 

        if (respuesta.changes === 0) {
            return undefined;
        }

        return tarea;
    }

    async borrar(id: IdTarea): Promise<boolean> {

        const resultado = this.db.prepare(
            "DELETE FROM tareas WHERE id = ?"
        ).run(id);

        return resultado.changes === 1;
    }
}