<<<<<<< HEAD
export interface Task {
  id: number;
  title: string;
  description?: string;
  completed: boolean;
}

=======
export interface Task {
  id: number;
  titulo: string;
  descripcion?: string;
  completada: boolean;
}

>>>>>>> b5aaef97f12fe63a31c3cc348b549c9f1a9bff98
export type NewTask = Omit<Task, 'id'>;