# Seguridad Enterprise y Gestión de Identidad

Estándares avanzados de seguridad para aplicaciones robustas y reguladas.

## 1. Autenticación y Autorización (IAM)

- **OAuth2 / OpenID Connect (OIDC)**: Usar estándares de la industria. No implementar autenticación propia.
- **JWT (JSON Web Tokens)**: Usar tokens firmados y de corta duración.
- **RBAC (Role-Based Access Control)**: Definir roles claros. Usar `@PreAuthorize` en la capa de Aplicación o Presentación.
- **Desarrollo Local**: Usar **Keycloak** vía Docker para simular el entorno de identidad real.

## 2. Protección contra Vulnerabilidades

- **OWASP Top 10**: Mitigar activamente inyección SQL (vía JPA), XSS (vía encriptación de salida), y CSRF (si hay sesiones).
- **Dependency Scanning**: Integrar herramientas para detectar librerías vulnerables (ej. `mvn ossindex:audit`).
- **Secret Management**:
  - NUNCA subir secretos al repositorio.
  - Usar variables de entorno en local.
  - En producción, usar Vault o Secret Managers de la nube.

## 3. Seguridad de Datos

- **Mascara de Datos**: Implementar serializadores personalizados para DTOs que oculten datos sensibles (ej. `****-****-1234`).
- **Validación Robusta**: Validación en la entrada (DTO) y validación de invariantes en el Dominio.

## 4. Pruebas de Seguridad

- Escribir pruebas de seguridad que verifiquen que un usuario sin privilegios recibe un `403 Forbidden`.
- Usar `SecurityMockMvcConfigurers` para mockear usuarios autenticados en pruebas de integración.

---
[Volver al índice](./backend.md)
