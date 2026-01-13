import * as readline from 'node:readline';
import { stdin as input, stdout as output } from 'node:process';
import { RepositorioTareasSqlite } from "../repository/repositorioTareasSqlite";
import { ServicioTareas } from "../servicios/ServicioTareas";
import { RepositorioTareasApi } from "../repository/repositorioApiTareas";
import { Tarea, FiltroTarea, IdTarea } from "../modelos/interface/models"; 


const leerLinea = readline.createInterface({ input, output, terminal: false });

/**
 * Muestra una pregunta y espera la respuesta del usuario.
 * @param query La pregunta a mostrar.
 * @returns La respuesta del usuario.
 */
function preguntar(query: string): Promise<string> {
    return new Promise((resolve) => {
        leerLinea.question(query, (answer) => {
            resolve(answer.trim());
        });
    });
}


/**
 * Muestra las tareas según un filtro.
 * @param servicio Instancia de ServicioTareas.
 */
async function mostrarTareas(servicio: ServicioTareas): Promise<void> {
    console.log("\n--- Mostrar Tareas ---");
    let filtro: FiltroTarea;
    
    // Bucle de validación para el filtro
    let filtroValido = false;
    while (!filtroValido) {
        const respuesta = await preguntar("Filtrar por (1:Todas, 2:Pendientes, 3:Completadas): ");
        switch (respuesta) {
            case "1":
                filtro = "todas";
                filtroValido = true;
                break;
            case "2":
                filtro = "pendientes";
                filtroValido = true;
                break;
            case "3":
                filtro = "completadas";
                filtroValido = true;
                break;
            default:
                console.log("Opción no válida. Inténtalo de nuevo.");
        }
    }

    try {
        const tareas = await servicio.listar(filtro!);
        console.log(`\nTareas Encontradas (${filtro!.toUpperCase()}):`);
        if (tareas.length === 0) {
            console.log("-> No hay tareas en esta lista.");
        }
        tareas.forEach(t => {
            const estado = t.completada ? "Completada" : "Esperando";
            console.log(`ID: ${t.id} ${estado} | Título: ${t.titulo} | Desc: ${t.descripcion || 'N/A'}`);
        });
    } catch (error) {
        console.error("Error al listar tareas:", (error as Error).message);
    }
}

/**
 * Crea una nueva tarea.
 * @param servicio Instancia de ServicioTareas.
 */
async function crearNuevaTarea(servicio: ServicioTareas): Promise<void> {
    console.log("\n--- Crear Nueva Tarea ---");
    try {
        const titulo = await preguntar("Título de la tarea: ");
        const descripcion = await preguntar("Descripción (opcional): ");

        const nuevaTarea = await servicio.crear(titulo, descripcion);
        console.log("Tarea creada con éxito:");
        console.log(nuevaTarea);
    } catch (error) {
        console.error("Error al crear tarea:", (error as Error).message);
    }
}

/**
 * Borra una tarea por su ID.
 * @param servicio Instancia de ServicioTareas.
 */
async function borrarTarea(servicio: ServicioTareas): Promise<void> {
    console.log("\n--- Borrar Tarea ---");
    const idStr = await preguntar("ID de la tarea a borrar: ");
    const id: IdTarea = parseInt(idStr);

    if (isNaN(id)) {
        console.log("El ID introducido no es un número válido.");
        return;
    }

    try {
        const borradoExitoso = await servicio.borrarPorId(id);
        if (borradoExitoso) {
            console.log(`Tarea con ID ${id} borrada con éxito.`);
        } else {
            console.log(`No se pudo borrar la tarea ID ${id}. Quizás no existe.`);
        }
    } catch (error) {
        console.error("Error al borrar tarea:", (error as Error).message);
    }
}

/**
 * Actualiza una tarea existente (simplemente la marcamos como completada).
 * @param servicio Instancia de ServicioTareas.
 */
async function completarTarea(servicio: ServicioTareas): Promise<void> {
    console.log("\n--- Marcar Tarea como Completada ---");
    const idStr = await preguntar("ID de la tarea a completar: ");
    const id: IdTarea = parseInt(idStr);

    if (isNaN(id)) {
        console.log("El ID introducido no es un número válido.");
        return;
    }

    try {
        const tareaParaActualizar: Tarea = {
            id: id,
            titulo: "Título de la tarea",
            completada: true,
        } as Tarea;

        const tareaActualizada = await servicio.actualizar(tareaParaActualizar);
        console.log("Tarea actualizada con éxito:");
        console.log(tareaActualizada);
    } catch (error) {
        console.error("❌ Error al actualizar tarea:", (error as Error).message);
    }
}

async function main() {
    console.log("\n==========================================");
    console.log("          GESTOR DE TAREAS CLI");
    console.log("==========================================");

    const local = new RepositorioTareasSqlite();
    const remoto = new RepositorioTareasApi();
    let servicio: ServicioTareas;

    let entradaValida = false;
    while (!entradaValida) {
        const respuesta = await preguntar(
            "¿Como quieres trabajar? (1:Local, 2:Remoto): "
        );

        if (respuesta === "1") {
            servicio = new ServicioTareas(local);
            console.log("\n✅ MODO: LOCAL (SQLite) seleccionado.");
            entradaValida = true;
        } else if (respuesta === "2") {
            servicio = new ServicioTareas(remoto);
            console.log("\n✅ MODO: REMOTO (API) seleccionado.");
            entradaValida = true;
        } else {
            console.log("❌ Respuesta no válida. Por favor, escribe '1' o '2'.");
        }
    }
    
    let salir = false;
    while (!salir) {
        console.log("\n--- MENÚ PRINCIPAL ---");
        console.log("1. Listar tareas");
        console.log("2. Crear nueva tarea");
        console.log("3. Completar tarea (Actualizar)");
        console.log("4. Borrar tarea por ID");
        console.log("0. Salir");
        console.log("----------------------");
        
        const opcion = await preguntar("Selecciona una opción: ");

        switch (opcion) {
            case "1":
                await mostrarTareas(servicio!);
                break;
            case "2":
                await crearNuevaTarea(servicio!);
                break;
            case "3":
                await completarTarea(servicio!);
                break;
            case "4":
                await borrarTarea(servicio!);
                break;
            case "0":
                salir = true;
                break;
            default:
                console.log("Opción no reconocida. Por favor, intenta de nuevo.");
        }
    }

    leerLinea.close();
    console.log("\n ¡Gracias por usar el Gestor de Tareas! Programa finalizado.");
}

main().catch(error => {
    leerLinea.close();
    console.error("\n*** Error fatal en la aplicación ***", error);
    process.exit(1);
});