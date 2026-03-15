# PRD: Plataforma de Empleos con Inteligencia Artificial

## 1. Introducción / Resumen
Esta funcionalidad consiste en un ecosistema de empleo bidireccional que utiliza IA para resolver la fricción entre la búsqueda de talento y la búsqueda de oportunidades. La plataforma actúa como un asistente técnico que extrae datos de CVs, puntúa la compatibilidad con vacantes y ayuda a los candidatos a optimizar su perfil, manteniendo siempre al humano como el único tomador de decisiones final.

## 2. Objetivos
* **Automatizar la extracción de datos:** Convertir CVs (PDF/Word) y perfiles sociales en datos estructurados.
* **Facilitar el filtrado:** Proporcionar a los reclutadores un ranking de compatibilidad basado en habilidades.
* **Empoderar al candidato:** Ofrecer sugerencias de optimización de perfil basadas en vacantes específicas.
* **Garantizar la transparencia:** Asegurar que la IA sea una herramienta de apoyo y no un filtro eliminatorio autónomo.

## 3. Historias de Usuario
* **Como reclutador**, quiero ver un ranking de candidatos con un porcentaje de compatibilidad y un resumen de sus fortalezas/debilidades para priorizar a quién contactar primero.
* **Como candidato**, quiero recibir sugerencias de redacción y palabras clave en mi perfil para que mi experiencia se alinee mejor con los requisitos de una vacante de mi interés.
* **Como administrador del sistema**, quiero que los datos de los candidatos se exporten en formato JSON para poder integrarlos con otros sistemas de gestión de talento (ATS).

## 4. Requerimientos Funcionales
1.  **Módulo de Ingesta de Datos:** El sistema debe permitir la carga de archivos PDF y Word, así como la vinculación de perfiles de LinkedIn/GitHub.
2.  **Extracción mediante IA:** El sistema debe identificar y extraer: nombre, contacto, habilidades técnicas, años de experiencia y educación.
3.  **Algoritmo de Matching:** La IA debe comparar el perfil del candidato contra la descripción de la vacante y generar un puntaje de compatibilidad (0-100%).
4.  **Generador de Resúmenes:** Para cada candidato, la IA debe redactar un párrafo breve con "Pros" y "Contras" técnicos relativos a la posición.
5.  **Asistente de Optimización:** Interfaz para el candidato donde, al seleccionar una vacante, la IA resalte brechas en su perfil y sugiera mejoras de redacción.
6.  **Exportación de Datos:** Botón para descargar la ficha del candidato en formato JSON estructurado.

## 5. No objetivos (Fuera de alcance)
* La IA **no** tiene permitido rechazar automáticamente a un candidato del proceso.
* La IA **no** enviará correos electrónicos automáticos de descarte.
* No se incluye, en esta fase, la realización de video-entrevistas automáticas.

## 6. Consideraciones de Diseño
* **Panel del Reclutador:** Debe incluir una tabla comparativa con los puntajes de IA claramente visibles.
* **Disclaimers:** Cada puntaje generado por la IA debe ir acompañado de un icono de información que indique: "Este puntaje es una sugerencia generada por IA para asistencia; la decisión final depende del reclutador".

## 7. Consideraciones Técnicas
* **Procesamiento de Lenguaje Natural (NLP):** Uso de modelos de lenguaje para el análisis semántico de habilidades (evitar solo búsqueda de palabras clave).
* **Seguridad:** Los datos extraídos deben anonimizarse si se utilizan para entrenamiento o pruebas externas de modelos.
* **Integración:** El backend debe exponer un endpoint para la exportación JSON de los perfiles procesados.

## 8. Métricas de Éxito
* Reducción del 40% en el tiempo que el reclutador dedica a la revisión inicial de CVs.
* Aumento del 20% en la tasa de coincidencia entre candidatos pre-seleccionados y candidatos que pasan a entrevista técnica.
* Nivel de satisfacción del candidato > 4/5 respecto a las sugerencias de optimización.