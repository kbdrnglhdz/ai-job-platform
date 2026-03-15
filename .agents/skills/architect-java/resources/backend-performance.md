# Rendimiento y Buenas Prácticas

## Consultas a Base de Datos

- Evitar N+1: usar `JOIN FETCH` o `@EntityGraph` en consultas.
- Seleccionar solo los campos necesarios mediante proyecciones (interfaces o DTOs).
- Indexar columnas usadas en búsquedas frecuentes.

## Manejo de Transacciones

- Mantener las transacciones lo más cortas posible.
- No realizar operaciones costosas (llamadas HTTP) dentro de una transacción de base de datos.

## Concurrencia y Async

- Para tareas asíncronas, usar `@Async` con un `Executor` configurado.
- Considerar bloqueos optimistas (`@Version`) para evitar conflictos de concurrencia.

[Volver al índice](./backend.md)