import { Task } from "../models";

/**
 * E03 – Arrays: filter/map/reduce/sort sin mutar original
 */

export function pendingTasks(tasks: Task[]): Task[] {
  return tasks.filter(task => !task.completed);
}

export function titlesSorted(tasks: Task[]): string[] {
  return tasks
    .map(task => task.title)
    .sort((a, b) => a.localeCompare(b));
}

export function completionPercent(tasks: Task[]): number {
  if (tasks.length === 0) return 0;

  const completedCount = tasks.reduce(
    (acc, task) => (task.completed ? acc + 1 : acc),
    0
  );

  return Math.round((completedCount / tasks.length) * 100);
}
