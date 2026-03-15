# Seguridad Básica (Desarrollo Local)

## Validación de Entradas

- Usar Bean Validation en los DTOs de entrada.
- Validar también en el dominio para reglas de negocio.

## Configuración de CORS

- En desarrollo, permitir origen del frontend (ej. `http://localhost:3000`) mediante configuración.

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowCredentials(true);
    }
}
```

## Manejo de Secretos en Desarrollo

- Usar archivos `.env` o `application-dev.yml` con valores por defecto, pero no versionar secretos reales.
- Spring Boot permite usar `${VARIABLE_ENTORNO}` para inyectar desde el sistema.

[Volver al índice](./backend.md)