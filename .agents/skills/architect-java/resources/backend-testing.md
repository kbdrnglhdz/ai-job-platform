# Pruebas

## Pruebas Unitarias

- Probar la lógica de dominio y servicios de aplicación aislados (con mocks).
- Usar JUnit 5 y Mockito.
- Nombrar los métodos con `should_[expectedBehavior]_when_[condition]`.

**Ejemplo**:

```java
@Test
void shouldThrowExceptionWhenEmailIsInvalid() {
    // given
    CandidatoId id = new CandidatoId(UUID.randomUUID());
    // when / then
    assertThrows(IllegalArgumentException.class, () -> new Candidato(id, "Juan", "correo-invalido"));
}
```

## Pruebas de Integración

- Probar la integración con la base de datos usando Testcontainers o una base de datos H2 (con precaución).
- Levantar el contexto de Spring con `@SpringBootTest` y slices (`@DataJpaTest`, `@WebMvcTest`).
- Asegurar que las migraciones de Flyway se ejecuten en el entorno de prueba.

## Cobertura y Calidad

- Mantener cobertura mínima del **85%** en líneas y ramas.
- Generar reportes con JaCoCo.
- Ejecutar análisis estático con SonarQube (opcional en local).

## Mocks y Testcontainers

- Usar Mockito para mockear dependencias externas (repositorios, servicios).
- Para pruebas de integración con base de datos real, usar Testcontainers con MySQL.

**Ejemplo**:

```java
@Testcontainers
@SpringBootTest
class CandidatoRepositoryIT {
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }
    // pruebas...
}
```

[Volver al índice](./backend.md)