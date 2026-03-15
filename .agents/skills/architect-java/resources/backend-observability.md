# Observabilidad y Trazabilidad Enterprise

En entornos regulados, la capacidad de observar el comportamiento del sistema y rastrear transacciones es obligatoria para auditorías y diagnóstico de fallos.

## 1. Trazabilidad (Distributed Tracing)

- **Correlation ID**: Cada petición debe tener un ID único que se propague por todos los logs y llamadas a servicios externos.
- **Implementación**: Usar **Micrometer Tracing** (sucesor de Sleuth en Spring Boot 3).
- **Log Pattern**: Configurar el log para incluir `traceId` y `spanId`.
  ```properties
  logging.pattern.level=%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]
  ```

## 2. Logs Estructurados

- **Formato JSON**: En producción, los logs deben estar en formato JSON para facilitar su ingesta en herramientas como ELK o Splunk.
- **Log Levels**:
  - `ERROR`: Fallos que requieren intervención.
  - `WARN`: Comportamientos inesperados pero recuperables.
  - `INFO`: Mensajes de negocio (ej. "Loan approved for ID: XXX").
  - `DEBUG`: Información técnica detallada (desactivado en Prod).
- **No Loguear Datos Sensibles**: Está estrictamente prohibido loguear PII (nombres, tarjetas, contraseñas) o secretos.

## 3. Auditoría de Dominio (Audit Trail)

- **Auditoría Técnica**: Usar **Spring Data Envers** para mantener un historial de versiones de las entidades.
- **Campos de Auditoría**: Toda tabla de negocio debe tener:
  - `created_at`: Timestamp de creación.
  - `created_by`: Usuario que creó el registro.
  - `updated_at`: Timestamp de última modificación.
  - `updated_by`: Usuario que modificó el registro.
- **Implementación**: Usar `@EntityListeners(AuditingEntityListener.class)`.

## 4. Métricas y Salud (Health Checks)

- **Actuator**: Habilitar `/actuator/health` y `/actuator/metrics`.
- **Prometheus**: Exponer métricas en formato Prometheus para Grafana.
- **Custom Metrics**: Usar `MeterRegistry` para registrar eventos de negocio importantes (ej. contador de transacciones fallidas).

---
[Volver al índice](./backend.md)
