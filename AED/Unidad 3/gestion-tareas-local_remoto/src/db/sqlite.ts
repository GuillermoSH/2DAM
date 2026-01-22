import sqlite3 from 'sqlite3'
import { open } from 'sqlite'

export const dbPromise = open({
  filename: 'tareas.db',
  driver: sqlite3.Database
})

export async function initDb() {
  const db = await dbPromise
  await db.exec(`
    CREATE TABLE IF NOT EXISTS tareas (
      id INTEGER PRIMARY KEY,
      titulo TEXT NOT NULL,
      descripcion TEXT,
      completada INTEGER NOT NULL
    )
  `)
}
