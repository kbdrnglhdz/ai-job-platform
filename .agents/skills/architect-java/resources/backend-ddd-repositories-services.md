# DDD: Repositorios, Servicios de Dominio, Eventos y Fábricas

## Repositorios

Los repositorios proveen una interfaz para acceder a los agregados desde la capa de aplicación. En el dominio se define la interfaz; en infraestructura se implementa.

**Interfaz en dominio**:

```java
package com.ejemplo.proyecto.domain.repository;

import com.ejemplo.proyecto.domain.model.Candidato;
import com.ejemplo.proyecto.domain.model.CandidatoId;
import java.util.Optional;

public interface CandidatoRepository {
    Optional<Candidato> findById(CandidatoId id);
    Candidato save(Candidato candidato);
    // otros métodos
}
```

**Implementación en infraestructura** (usando Spring Data JPA y mapper):

```java
package com.ejemplo.proyecto.infrastructure.persistence.repository;

import com.ejemplo.proyecto.domain.model.Candidato;
import com.ejemplo.proyecto.domain.model.CandidatoId;
import com.ejemplo.proyecto.domain.repository.CandidatoRepository;
import com.ejemplo.proyecto.infrastructure.persistence.entity.CandidatoJpa;
import com.ejemplo.proyecto.infrastructure.persistence.mapper.CandidatoMapper;
import com.ejemplo.proyecto.infrastructure.persistence.jpa.CandidatoJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CandidatoRepositoryImpl implements CandidatoRepository {

    private final CandidatoJpaRepository jpaRepository;
    private final CandidatoMapper mapper;

    public CandidatoRepositoryImpl(CandidatoJpaRepository jpaRepository, CandidatoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Candidato> findById(CandidatoId id) {
        return jpaRepository.findById(id.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public Candidato save(Candidato candidato) {
        CandidatoJpa jpa = mapper.toJpa(candidato);
        CandidatoJpa saved = jpaRepository.save(jpa);
        return mapper.toDomain(saved);
    }
}
```

## Servicios de Dominio

Contienen lógica de negocio que no pertenece naturalmente a una entidad u objeto valor, a menudo involucrando múltiples agregados.

**Ejemplo**:

```java
package com.ejemplo.proyecto.domain.service;

import com.ejemplo.proyecto.domain.model.Candidato;
import com.ejemplo.proyecto.domain.model.Posicion;
import com.ejemplo.proyecto.domain.model.Aplicacion;
import com.ejemplo.proyecto.domain.repository.CandidatoRepository;
import com.ejemplo.proyecto.domain.repository.PosicionRepository;

public class ProcesoSeleccionService {
    private final CandidatoRepository candidatoRepository;
    private final PosicionRepository posicionRepository;

    public ProcesoSeleccionService(CandidatoRepository candidatoRepository, PosicionRepository posicionRepository) {
        this.candidatoRepository = candidatoRepository;
        this.posicionRepository = posicionRepository;
    }

    public Aplicacion aplicarCandidatoAPosicion(CandidatoId candidatoId, PosicionId posicionId) {
        Candidato candidato = candidatoRepository.findById(candidatoId)
                .orElseThrow(() -> new IllegalArgumentException("Candidato no encontrado"));
        Posicion posicion = posicionRepository.findById(posicionId)
                .orElseThrow(() -> new IllegalArgumentException("Posición no encontrada"));
        // Lógica de negocio, por ejemplo verificar que el candidato cumple requisitos
        return candidato.aplicarA(posicion);  // método en la entidad
    }
}
```

## Eventos de Dominio

Los eventos de dominio notifican que algo relevante ha ocurrido en el agregado, permitiendo reacciones desacopladas.

**Definición de un evento**:

```java
package com.ejemplo.proyecto.domain.event;

import java.time.Instant;

public class CandidatoCreado {
    private final CandidatoId candidatoId;
    private final Instant ocurridoEn;

    public CandidatoCreado(CandidatoId candidatoId) {
        this.candidatoId = candidatoId;
        this.ocurridoEn = Instant.now();
    }

    // getters...
}
```

**Publicación en servicio de aplicación**:

```java
@Service
@Transactional
public class CandidatoApplicationService {
    private final CandidatoRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public CandidatoApplicationService(CandidatoRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Candidato crearCandidato(CrearCandidatoCommand command) {
        Candidato candidato = new Candidato(new CandidatoId(UUID.randomUUID()), command.getNombre(), command.getEmail());
        Candidato saved = repository.save(candidato);
        eventPublisher.publishEvent(new CandidatoCreado(saved.getId()));
        return saved;
    }
}
```

## Fábricas

Para la creación de objetos complejos del dominio, se pueden usar fábricas (métodos estáticos o clases separadas). Esto centraliza la lógica de creación.

**Ejemplo**:

```java
public class CandidatoFactory {
    public static Candidato crearConEducacion(String nombre, String email, List<Educacion> educaciones) {
        Candidato candidato = new Candidato(new CandidatoId(UUID.randomUUID()), nombre, email);
        educaciones.forEach(candidato::agregarEducacion);
        return candidato;
    }
}
```

[Volver al índice](./backend.md)