# Arquitectura y Diseño Backend

## Introducción

Este documento define los estándares, buenas prácticas y convenciones para el desarrollo backend en Java con Spring Boot, siguiendo los principios de Domain-Driven Design (DDD), SOLID y una arquitectura limpia. Está orientado a entornos de desarrollo local, sin incluir consideraciones de infraestructura en la nube o despliegues serverless. El objetivo es garantizar consistencia, mantenibilidad y escalabilidad del código.

## Stack Tecnológico

- **Java 17+** (LTS)
- **Spring Boot 3.x** (con Spring MVC, Spring Data JPA, Spring Cloud para ciertos patrones si es necesario)
- **Maven** (gestión de dependencias y construcción)
- **MySQL 8** (base de datos relacional, ejecutada localmente vía Docker o instalación nativa)
- **Flyway** (migraciones de base de datos)
- **JUnit 5**, **Mockito**, **Testcontainers** (pruebas)
- **Lombok** (opcional, para reducir boilerplate, pero con criterio)
- **MapStruct** (para mapeo entre entidades y DTOs, recomendado)

## Domain-Driven Design (DDD)

DDD es una metodología que pone el foco en el dominio del negocio. El modelo de dominio debe reflejar el lenguaje ubicuo y las reglas de negocio. Se organiza en torno a **agregados**, **entidades**, **objetos valor**, **repositorios** y **servicios de dominio**.

## Arquitectura Hexagonal / Por Capas

Se adopta una arquitectura hexagonal (puertos y adaptadores) o por capas con separación clara:

- **Capa de Aplicación**: Casos de uso, orquestación, DTOs, validación de entrada.
- **Capa de Dominio**: Modelo de negocio, entidades, objetos valor, interfaces de repositorios, eventos.
- **Capa de Infraestructura**: Implementaciones técnicas (repositorios JPA, clientes HTTP, configuraciones).
- **Capa de Presentación (API REST)**: Controladores, manejo de peticiones HTTP.

## Estructura de Paquetes

```
com.ejemplo.proyecto/
├── application/
│   ├── dto/               # Objetos de transferencia (request/response)
│   ├── service/            # Servicios de aplicación (casos de uso)
│   └── validator/          # Validadores específicos
├── domain/
│   ├── model/              # Entidades y objetos valor del dominio
│   ├── repository/         # Interfaces de repositorios (puertos)
│   ├── service/            # Servicios de dominio (lógica pura)
│   └── event/              # Eventos de dominio
├── infrastructure/
│   ├── persistence/        # Implementaciones JPA de repositorios
│   │   ├── entity/         # Entidades JPA (pueden ser distintas a las de dominio)
│   │   ├── mapper/         # Mappers entre entidades JPA y dominio
│   │   └── repository/     # Implementaciones concretas (Spring Data)
│   ├── config/             # Configuraciones de Spring (beans, seguridad, etc.)
│   └── client/             # Clientes HTTP, servicios externos
└── presentation/
    ├── controller/         # Controladores REST
    ├── advice/             # Manejadores globales de excepciones
    └── interceptor/        # Interceptores (si aplica)
```

[Volver al índice](./backend.md)