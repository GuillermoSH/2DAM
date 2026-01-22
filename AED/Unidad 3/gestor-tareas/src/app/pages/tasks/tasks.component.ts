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