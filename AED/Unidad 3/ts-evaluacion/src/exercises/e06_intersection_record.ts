import { Task } from "../models";

/**
 * E06 – Intersection + Record
 */

export type AdminTask = Task & { adminOnly: true };

export function makeAdminTask(task: Task): AdminTask {
  return { ...task, adminOnly: true };
}

export function buildAuthHeaders(token: string): Record<string, string> {
  const trimmedToken = token.trim();
  if (!trimmedToken) {
    throw new Error("Token cannot be empty");
  }

  return {
    Authorization: `Bearer ${trimmedToken}`,
    "Content-Type": "application/json",
  };
}

export function groupByCompleted(tasks: Task[]): Record<"done" | "pending", Task[]> {
  return {
    done: tasks.filter((t) => t.completed),
    pending: tasks.filter((t) => !t.completed),
  };
}
