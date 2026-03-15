# Estándares de Codificación

## Idioma y Nombres

- Todo el código (clases, métodos, variables, comentarios) debe estar en **inglés**.
- Usar **camelCase** para métodos y variables, **PascalCase** para clases e interfaces, **UPPER_SNAKE_CASE** para constantes.
- Nombres descriptivos: `calcularEdad` en lugar de `calc`.

## Uso de Java y Tipado

- Usar `final` siempre que sea posible (variables locales, parámetros, campos inmutables).
- Preferir `List`, `Set`, `Map` con tipos genéricos.
- Evitar `var` si el tipo no es obvio; usarlo con moderación.
- Anotar `@Override` consistentemente.
- Usar `Optional` para valores que pueden ser nulos, pero no en campos de entidades (preferir objetos valor con presencia/ausencia).

## Manejo de Excepciones

- Definir excepciones de dominio (checked o unchecked) que reflejen problemas de negocio.
- Usar `@ControllerAdvice` para manejar excepciones y devolver respuestas HTTP consistentes.
- No tragar excepciones; registrar el error con el logger.

## Validaciones

- Validar entrada en la capa de aplicación (DTOs) usando Bean Validation (`@NotNull`, `@Size`, etc.) y/o validadores personalizados.
- Las reglas de negocio se validan en el dominio (lanzando excepciones).

## Logging

- Usar una interfaz de logging (SLF4J con Logback).
- Niveles: `ERROR` para fallos graves, `WARN` para situaciones anómalas, `INFO` para eventos importantes (inicio, creación de recursos), `DEBUG` para detalles de desarrollo.
- Incluir contexto relevante (IDs, etc.).

## Configuración y Propiedades

- Usar archivos `application.yml` o `application.properties`.
- Externalizar configuración por entorno: `application-dev.yml`, `application-test.yml`, etc.
- No hardcodear valores sensibles; usar variables de entorno o perfiles.

[Volver al índice](./backend.md)