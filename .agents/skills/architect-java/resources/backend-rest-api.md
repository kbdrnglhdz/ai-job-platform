# Diseño de APIs REST

## Endpoints y Métodos HTTP

- Usar nombres de recursos en plural: `/candidatos`, `/posiciones`.
- Métodos:
  - `GET /candidatos` → listar
  - `GET /candidatos/{id}` → obtener uno
  - `POST /candidatos` → crear
  - `PUT /candidatos/{id}` → reemplazar
  - `PATCH /candidatos/{id}` → actualización parcial
  - `DELETE /candidatos/{id}` → eliminar

## Formato de Petición/Respuesta

- Usar JSON.
- Estructura consistente:

```json
{
  "success": true,
  "data": { ... },
  "message": "Operación exitosa"
}
```

Para listas, `data` puede ser un array. Para errores:

```json
{
  "success": false,
  "error": {
    "code": "CANDIDATO_NO_ENCONTRADO",
    "message": "No existe candidato con id 123",
    "details": [ ... ]
  }
}
```

## Manejo de Errores

- Usar `@ControllerAdvice` para capturar excepciones y traducirlas a códigos HTTP adecuados.
- Códigos comunes: 400 (validación), 404 (no encontrado), 409 (conflicto), 500 (error interno).

## Versionado de API

- Incluir versión en la URL: `/api/v1/candidatos`.
- Mantener versiones anteriores mientras sea necesario.

[Volver al índice](./backend.md)