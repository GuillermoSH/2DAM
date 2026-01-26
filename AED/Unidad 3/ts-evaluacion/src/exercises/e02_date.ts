/**
 * E02 – Date: parseo YYYY-MM-DD, validación y diferencias en días.
 */

export function isValidISODate(iso: string): boolean {
  const regex = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;
  if (!regex.test(iso)) return false;

  const [year, month, day] = iso.split("-").map(Number);
  const date = new Date(Date.UTC(year, month - 1, day));

  return (
    date.getUTCFullYear() === year &&
    date.getUTCMonth() === month - 1 &&
    date.getUTCDate() === day
  );
}

export function nightsBetween(entrada: string, salida: string): number {
  if (!isValidISODate(entrada) || !isValidISODate(salida)) {
    throw new Error("Invalid date format");
  }

  const d1 = new Date(entrada);
  const d2 = new Date(salida);

  if (d2 <= d1) {
    throw new Error("Departure must be after arrival");
  }

  const diffInMs = d2.getTime() - d1.getTime();
  return Math.floor(diffInMs / (1000 * 60 * 60 * 24));
}

export function toIsoDateOnly(date: Date): string {
  if (isNaN(date.getTime())) {
    throw new Error("Invalid date");
  }

  return date.toISOString().split("T")[0];
}