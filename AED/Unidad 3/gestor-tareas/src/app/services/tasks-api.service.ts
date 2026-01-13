import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import type { Observable } from 'rxjs';
import type { Task, NewTask } from '../models/task';

@Injectable({ providedIn: 'root' })
export class TasksApiService {
  private readonly baseUrl = 'http://localhost:8080/api/tasks';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.baseUrl);
  }

  create(t: NewTask): Observable<Task> {
    return this.http.post<Task>(this.baseUrl, t);
  }

  update(t: Task): Observable<Task> {
    return this.http.put<Task>(`${this.baseUrl}/${t.id}`, t);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}