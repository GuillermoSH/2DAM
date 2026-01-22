<<<<<<< HEAD
import { Injectable } from '@angular/core';
import { NewTask, Task } from '../models/task.model';

@Injectable({ providedIn: 'root' })
export class TasksService {
  private tasks: Task[] = [
    { id: 1, title: 'Instalar Angular', description: 'CLI + Node', completed: false },
    { id: 2, title: 'Crear primera página', completed: true },
  ];
  private nextId = 3;

  list(): Task[] {
    return this.tasks;
  }

  add(data: NewTask): Task {
    const created: Task = { id: this.nextId++, ...data };
    this.tasks = [created, ...this.tasks];
    return created;
  }

  remove(id: number): void {
    this.tasks = this.tasks.filter(t => t.id !== id);
  }
=======
import { Injectable } from '@angular/core';
import { NewTask, Task } from '../models/task.model';

@Injectable({ providedIn: 'root' })
export class TasksService {
  private tasks: Task[] = [
    { id: 1, titulo: 'Instalar Angular', descripcion: 'CLI + Node', completada: false },
    { id: 2, titulo: 'Crear primera página', completada: true },
  ];
  private nextId = 3;

  list(): Task[] {
    return this.tasks;
  }

  add(data: NewTask): Task {
    const created: Task = { id: this.nextId++, ...data };
    this.tasks = [created, ...this.tasks];
    return created;
  }

  remove(id: number): void {
    this.tasks = this.tasks.filter(t => t.id !== id);
  }
>>>>>>> b5aaef97f12fe63a31c3cc348b549c9f1a9bff98
}