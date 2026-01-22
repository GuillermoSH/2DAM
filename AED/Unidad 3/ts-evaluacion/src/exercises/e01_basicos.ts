/**
 * E01 – Tipos básicos: string/number/boolean/null/undefined
 */

export function normalizeBearer(authHeader: string): string {
  // trim + "Bearer <token>" (case-insensitive), colapsa espacios a 1, Error si inválido
  let normalizedWhitespaces = authHeader.replace(/\s\s+/g, ' ').trim();
  let normalized = (normalizedWhitespaces[0].toUpperCase() + normalizedWhitespaces.substring(1));
  if (!normalized.match(/^Bearer [\w.]*$/gm)) throw new Error("Invalid Bearer");
  return normalized;
}

export function clamp01(value: number): number {
  // Devuelve value limitado a [0,1]. Error si NaN o no finito.
  if (Number.isNaN(value) || !Number.isFinite(value)) throw new Error("Invalid number");
  if (value < 0) return 0;
  if (value > 1) return 1;
  
  return value;
}

export function safeBool(value: boolean | null | undefined): boolean {
  // null/undefined => false; boolean => mismo valor
  return value ? true : false;
}
