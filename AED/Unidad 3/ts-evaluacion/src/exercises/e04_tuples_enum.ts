import { Role } from "../models";

/**
 * E04 – Tuples y enum
 */

export type JwtParts = [header: string, payload: string, signature: string];

export function splitJwt(token: string): JwtParts {
  const parts = token.split(".");
  if (parts.length !== 3) {
    throw new Error("Invalid JWT format");
  }
  return [parts[0], parts[1], parts[2]];
}

export function roleFromString(value: string): Role {
  const upperValue = value.toUpperCase();
  if (upperValue === "ADMIN") return Role.ADMIN;
  if (upperValue === "USER") return Role.USER;
  throw new Error("Invalid role");
}

export function formatUserTag(username: string, role: Role): string {
  const trimmedUsername = username.trim();
  if (!trimmedUsername) {
    throw new Error("Username cannot be empty");
  }
  return `${trimmedUsername}#${role}`;
}
