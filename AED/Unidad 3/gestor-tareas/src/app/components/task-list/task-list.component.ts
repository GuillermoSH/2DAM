import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import type { Task, NewTask } from '../../models/task';
import { TasksApiService } from '../../services/tasks-api.service';
import { TaskFormComponent } from '../task-form/task-form.component';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, TaskFormComponent],
  templateUrl: './task-list.component.html',
})
export class TaskListComponent implements OnInit {
  tareas: Task[] = [];
  cargando = false;
  error: string | null = null;

  constructor(private api: TasksApiService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.cargando = true;
    this.error = null;

    this.api.getAll().subscribe({
      next: (t) => {
        this.tareas = t;
        this.cargando = false;
      },
      error: () => {
        this.error = 'No se pudo cargar la lista de tareas';
        this.cargando = false;
      },
    });
  }

  crear(nueva: NewTask): void {
    this.api.create(nueva).subscribe({
      next: () => this.cargar(),
      error: () => (this.error = 'No se pudo crear la tarea'),
    });
  }

  toggle(t: Task): void {
    const actualizado: Task = { ...t, completada: !t.completada };
    this.api.update(actualizado).subscribe({
      next: () => this.cargar(),
      error: () => (this.error = 'No se pudo actualizar la tarea'),
    });
  }

  borrar(id: number): void {
    this.api.delete(id).subscribe({
      next: () => this.cargar(),
      error: () => (this.error = 'No se pudo borrar la tarea'),
    });
  }

  trackById(index: number, t: Task): number {
    return t.id;
  }
}