<<<<<<< HEAD
import { Component } from '@angular/core';
import { TasksApiService } from '../../services/tasks-api.service';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-tasks',
  standalone: true,
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.css',
})
export class TasksComponent {
  constructor(public tasksService: TasksApiService) { }

  ngOnInit() {
    this.list();
  }

  taskList: Task[] = [];

  remove(id: number) {
    this.tasksService.remove(id).subscribe();
  }

  list(): void {
    this.tasksService.list().subscribe((taskList) => this.taskList = taskList);
  }
}
=======
import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/task.model';
import { TasksApiService } from '../../services/tasks-api.service';

@Component({
  selector: 'app-tasks',
  standalone: true,
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.css',
})
export class TasksComponent implements OnInit {
  tasks: Task[] = [];
  errorMsg: string | null = null;
  loading = false;

  constructor(public api: TasksApiService) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.loading = true;
    this.errorMsg = null;

    this.api.list().subscribe({
      next: data => {
        this.tasks = data;
        this.loading = false;
      },
      error: (e: Error) => {
        this.tasks = [];
        this.errorMsg = e.message;
        this.loading = false;
      }
    });
  }

  remove(id: number) {
    this.errorMsg = null;

    this.api.remove(id).subscribe({
      next: () => this.load(),
      error: (e: Error) => this.errorMsg = e.message
    });
  }
}
>>>>>>> b5aaef97f12fe63a31c3cc348b549c9f1a9bff98
