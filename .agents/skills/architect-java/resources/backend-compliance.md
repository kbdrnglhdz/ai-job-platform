# Compliance, Governança e ADRs

Garantizar que el desarrollo cumpla con las normativas vigentes y que las decisiones técnicas sean auditables.

## 1. Definition of Done (DoD) Enterprise

Una tarea solo se considera finalizada si cumple:
1. **Código**: Compila sin warnings y sigue SOLID.
2. **Pruebas**: Cobertura > 85%, pruebas unitarias y de integración (Testcontainers).
3. **Seguridad**: Escaneo de dependencias limpio y sin secretos hardcodeados.
4. **Auditoría**: Entidades con campos de auditoría y lógica de logs correcta.
5. **Documentación**: OpenAPI actualizada y ADR creada si hubo cambios de diseño.
6. **Revisión**: Peer review completado (en flujo real).

## 2. Manejo de PII (Personally Identifiable Information)

En entornos regulados, el manejo de datos personales está sujeto a leyes como GDPR o locales.
- **Enmascaramiento**: Los datos sensibles deben enmascararse en logs y pantallas de administración.
- **Cifrado en Reposo**: Datos críticos (ej. números de cuenta) deben almacenarse cifrados en la DB.
- **Principio de Mínimo Privilegio**: Solo el servicio que necesita el dato debe tener acceso a él.

## 3. Architecture Decision Records (ADR)

Toda decisión técnica significativa (ej. cambiar una base de datos, elegir una librería de seguridad, decidir un patrón de integración) debe documentarse.
- **Ubicación**: `docs/adr/NNNN-titulo-breve.md`.
- **Formato**:
  - **Contexto**: ¿Cuál es el problema?
  - **Decisión**: ¿Qué elegimos y por qué?
  - **Consecuencias**: Pros y contras de la decisión.

## 4. Auditoría de IA

Como agente, debo mantener una trazabilidad de mis acciones.
- Los planes de implementación en `ai-specs/changes/` sirven como evidencia del proceso de diseño previo a la ejecución.

---
[Volver al índice](./backend.md)
