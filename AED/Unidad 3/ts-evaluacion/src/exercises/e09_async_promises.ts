/**
 * E09 – Async/Promises
 */

export async function delay(ms: number): Promise<void> {
  if (ms < 0 || !Number.isFinite(ms)) {
    throw new Error("Invalid delay amount");
  }
  return new Promise((resolve) => setTimeout(resolve, ms));
}

export async function retry<T>(fn: () => Promise<T>, attempts: number): Promise<T> {
  let lastError: unknown;

  for (let i = 0; i < attempts; i++) {
    try {
      return await fn();
    } catch (error) {
      lastError = error;
    }
  }

  throw lastError;
}

export async function parallelSum(values: Array<Promise<number>>): Promise<number> {
  const results = await Promise.all(values);
  
  return results.reduce((acc, val) => {
    if (!Number.isFinite(val)) {
      throw new Error("Value is not finite");
    }
    return acc + val;
  }, 0);
}