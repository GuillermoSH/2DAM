import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import type { NewTask } from '../../models/task';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './task-form.component.html',
})
export class TaskFormComponent {
  @Output() crear = new EventEmitter<NewTask>();

  titulo: string = '';
  descripcion: string = '';
  error: string | null = null;

  enviar(): void {
    const t = this.titulo.trim();
    if (!t) {
      this.error = 'El título es obligatorio';
      return;
    }
    this.error = null;

    this.crear.emit({
      titulo: t,
      descripcion: this.descripcion.trim() || undefined,
      completada: false,
    });

    this.titulo = '';
    this.descripcion = '';
  }
}