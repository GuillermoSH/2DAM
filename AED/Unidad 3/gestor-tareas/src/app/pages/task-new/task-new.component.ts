import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TasksService } from '../../services/tasks.service';
import { TasksApiService } from '../../services/tasks-api.service';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-new',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './task-new.component.html',
  styleUrl: './task-new.component.css',
})
export class TaskNewComponent {
  constructor(public tasksService: TasksApiService) { }
  
  private fb = inject(FormBuilder);
  private tasks: Task[] = [];
  private router = inject(Router);

  ngOnInit() {
    this.tasksService.list().subscribe((tasks) => this.tasks = tasks);
  }

  form = this.fb.nonNullable.group({
    title: this.fb.nonNullable.control('', [Validators.required, Validators.minLength(3)]),
    description: this.fb.nonNullable.control(''),
    completed: this.fb.nonNullable.control(false),
  });

  save() {
    if (this.form.invalid) {
      console.warn('form inválido', this.form.errors, this.form.value);
      this.form.markAllAsTouched();
      return;
    }
    this.tasksService.create(this.form.getRawValue()).subscribe((newTask) => this.tasks.push(newTask));
    console.log(this.tasks)
    //this.router.navigateByUrl('/tareas');
  }

  cancel() {
    this.router.navigateByUrl('/tareas');
  }
}