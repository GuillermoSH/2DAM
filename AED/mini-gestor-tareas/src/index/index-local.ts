import { RepositorioTareasSqlite } from "../repository/repositorioTareasSqlite";
import { ServicioTareas } from "../servicios/ServicioTareas";

async function main() {
  const repo = new RepositorioTareasSqlite();
  const servicio = new ServicioTareas(repo);

  console.log("Tareas actuales:");
  console.log(await servicio.listar("todas"));

  console.log("Creando una nueva tarea...");
  const nueva = await servicio.crear("Aprender SQLite3 con TypeScript", "Práctica 3");
  console.log("Tarea creada:", nueva);

  console.log("Tareas tras la creación:");
  console.log(await servicio.listar("todas"));
}

main().catch((error) => {
  console.error("Error en main:", error);
});