import { JwtPayload, Role } from "../models";

/**
 * E05 – Union + type guards (unknown) + JWT
 */

export function normalizeId(id: string | number): string {
  const normalized = typeof id === "number" ? id.toString() : id.trim();
  if (normalized.length === 0) {
    throw new Error("ID cannot be empty");
  }
  return normalized;
}

export function isJwtPayload(value: unknown): value is JwtPayload {
  if (typeof value !== "object" || value === null) return false;

  const p = value as JwtPayload;

  const hasValidSub = typeof p.sub === "string" && p.sub.trim().length > 0;
  const hasValidRole = p.role === Role.USER || p.role === Role.ADMIN;
  const hasValidExp = typeof p.exp === "number" && Number.isFinite(p.exp) && p.exp >= 0;

  return hasValidSub && hasValidRole && hasValidExp;
}

export function requireRole(payload: JwtPayload, role: Role): void {
  if (payload.role !== role) {
    throw new Error(`Unauthorized: missing role ${role}`);
  }
}
