import { Role, Task } from "../models";

/**
 * E07 – Map / Set
 */

export function indexTasksById(tasks: Task[]): Map<string, Task> {
  const taskMap = new Map<string, Task>();
  tasks.forEach((task) => taskMap.set(task.id, task));
  return taskMap;
}

export function uniqueRoles(roles: Role[]): Set<Role> {
  return new Set(roles);
}

export function touchSession(sessions: Map<string, Date>, token: string, now: Date): Date | undefined {
  const previousDate = sessions.get(token);
  sessions.set(token, now);
  return previousDate;
}
