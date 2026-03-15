# DDD: Entidades, Objetos Valor y Agregados

## Entidades

Una entidad tiene una identidad única que la distingue de otras, incluso si sus atributos son iguales. Debe encapsular comportamientos relacionados con su ciclo de vida.

**Ejemplo**:

```java
package com.ejemplo.proyecto.domain.model;

import java.util.Objects;

public class Candidato {
    private final CandidatoId id;          // Objeto valor para ID
    private String nombre;
    private String email;
    private List<Educacion> educaciones;   // Lista de objetos valor o entidades hijas

    public Candidato(CandidatoId id, String nombre, String email) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        setNombre(nombre);
        setEmail(email);
        this.educaciones = new ArrayList<>();
    }

    // Getters y setters con lógica de negocio
    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public void agregarEducacion(Educacion educacion) {
        // Validaciones de negocio
        this.educaciones.add(educacion);
    }

    // equals y hashCode basados en id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidato candidato = (Candidato) o;
        return id.equals(candidato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

## Objetos Valor

Son inmutables y se definen por sus atributos. No tienen identidad propia.

**Ejemplo**:

```java
package com.ejemplo.proyecto.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Educacion {
    private final String institucion;
    private final String titulo;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;  // puede ser null

    public Educacion(String institucion, String titulo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.institucion = Objects.requireNonNull(institucion);
        this.titulo = Objects.requireNonNull(titulo);
        this.fechaInicio = Objects.requireNonNull(fechaInicio);
        this.fechaFin = fechaFin; // puede ser null
        // Validaciones: fechaFin no puede ser anterior a fechaInicio
        if (fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio");
        }
    }

    // Getters (sin setters)
    // equals y hashCode basados en todos los atributos
}
```

## Agregados

Un agregado es un clúster de entidades y objetos valor que se tratan como una unidad. La raíz del agregado es la única puerta de entrada para modificaciones, garantizando la consistencia.

**Ejemplo**: `Candidato` es la raíz del agregado que contiene `Educacion` (objeto valor). Cualquier operación sobre `Educacion` debe realizarse a través de métodos de `Candidato`.

[Volver al índice](./backend.md)