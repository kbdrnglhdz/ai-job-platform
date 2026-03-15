# Persistencia con JPA y Flyway

## Modelo de Datos y Entidades JPA

- Las entidades JPA pueden diferir del modelo de dominio (por ejemplo, usando `@Entity` en clases separadas). Preferir esta separación para no acoplar el dominio a JPA.
- Mapear mediante MapStruct o manualmente en los repositorios.

**Ejemplo de entidad JPA**:

```java
@Entity
@Table(name = "candidatos")
public class CandidatoJpa {
    @Id
    private UUID id;
    private String nombre;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidato_id")
    private List<EducacionJpa> educaciones = new ArrayList<>();

    // getters, setters, constructores...
}
```

## Migraciones con Flyway

- Los scripts SQL de migración van en `src/main/resources/db/migration`.
- Nomenclatura: `V<numero>__<descripcion>.sql` (ej. `V1__crear_tabla_candidatos.sql`).
- Nunca modificar scripts ya aplicados; crear nuevos para cambios.

## Repositorios Spring Data

- Usar `JpaRepository` para operaciones CRUD básicas, pero encapsuladas detrás de la interfaz de dominio.
- Para consultas complejas, usar `@Query` o especificaciones.

## Transacciones

- Definir transacciones en la capa de servicio (aplicación) con `@Transactional`.
- Ajustar propagación y aislamiento según necesidad.
- Tener en cuenta que las operaciones que modifican múltiples agregados deben ser transaccionales; diseñar agregados para mantener consistencia dentro de una transacción.

[Volver al índice](./backend.md)