import { RepositorioTareasApi } from "../repository/repositorioApiTareas";
import { ServicioTareas } from "../servicios/ServicioTareas"; 
import { Tarea } from "../modelos/interface/models";

async function main() {
  const repo = new RepositorioTareasApi();
  const servicio = new ServicioTareas(repo);

  console.log("Cargando tareas desde la API...");
  const tareas: Tarea[] = await servicio.listar("todas");
  console.log("Tareas iniciales:", tareas);

  console.log("Creando una nueva tarea remota...");
  const nueva = await servicio.crear(
    "Tarea creada desde index-rest.ts",
    "Probando POST contra json-server");

  console.log("Tarea creada:", nueva);

  const tareasActualizadas = await servicio.listar("todas");
  console.log("Tareas tras la creación:", tareasActualizadas);
}

main().catch((error) => {
  console.error("Error en main:", error);
});