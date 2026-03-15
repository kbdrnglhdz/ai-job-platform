# Principios SOLID y DRY

## Single Responsibility Principle

Cada clase debe tener una única razón para cambiar. Por ejemplo, separar la lógica de negocio de la persistencia.

**Bien**: La entidad `Candidato` solo maneja reglas de negocio; el repositorio maneja persistencia.

## Open/Closed Principle

Las clases deben estar abiertas a extensión, cerradas a modificación. Se logra mediante interfaces y herencia.

**Ejemplo**: Definir una interfaz `ValidadorDocumento` y varias implementaciones según el tipo de documento.

## Liskov Substitution Principle

Las clases derivadas deben poder sustituir a sus bases sin alterar el comportamiento. Evitar herencia inapropiada; preferir composición.

## Interface Segregation Principle

Interfaces específicas son mejores que una general. Por ejemplo, separar `CandidatoRepository` en `CandidatoQueryRepository` y `CandidatoCommandRepository` si los usos son distintos.

## Dependency Inversion Principle

Depender de abstracciones, no de concreciones. En el ejemplo anterior, `CandidatoRepository` es una interfaz, y la implementación concreta se inyecta.

## DRY (Don't Repeat Yourself)

Evitar duplicación de código. Extraer lógica común a métodos privados o clases utilitarias. Por ejemplo, validación de email en un solo lugar.

[Volver al índice](./backend.md)