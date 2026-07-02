Sistema de Gestión Clínica: Hospital Vida y Meditación (V&M)

![alt text](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)


![alt text](https://img.shields.io/badge/Spring_Boot-3.2.5-brightgreen?style=for-the-badge&logo=spring-boot)


![alt text](https://img.shields.io/badge/MariaDB-8.4-blue?style=for-the-badge&logo=mariadb)


![alt text](https://img.shields.io/badge/JWT-JSON_Web_Token-black?style=for-the-badge&logo=json-web-tokens)

Descripción del Proyecto

El proyecto Hospital Vida y Meditación es una solución backend robusta diseñada bajo una arquitectura de microservicios. El sistema permite la gestión integral de fichas clínicas de pacientes, integrando prácticas de medicina tradicional con beneficios de terapias naturales.

Este software fue desarrollado por la consultora tecnológica Nuvox Technologi como parte del proceso de migración de una infraestructura monolítica hacia un ecosistema distribuido, escalable y de alta disponibilidad.
 Arquitectura del Sistema

El sistema se basa en el desacoplamiento de responsabilidades, dividiéndose en servicios autónomos que gestionan su propia persistencia (Database per Service):

    Microservicio de Pacientes (hospital-vm): Puerta de entrada principal (Puerto 8080). Gestiona la seguridad, autenticación de usuarios y la data maestra de los pacientes.

    Microservicio de Médicos (medico-service): Servicio de apoyo (Puerto 8082). Provee información detallada sobre el personal clínico y sus especialidades.

Tecnologías e Implementaciones Técnicas
1. Persistencia y Migración (Flyway & Hibernate)

Se implementó Flyway para el control de versiones de la base de datos (Database as Code). El historial de migración abarca desde la creación de esquemas base hasta la limpieza de tablas obsoletas (V1 a V7), garantizando que el entorno de desarrollo sea idéntico al de producción. Hibernate actúa como ORM para el mapeo de entidades.
2. Seguridad Avanzada (Spring Security & JWT)

Implementación de un modelo de seguridad Stateless:

    JWT (JSON Web Token): Generación y validación de tokens Bearer para proteger los endpoints.

    BCrypt: Encriptación de contraseñas de nivel bancario para el resguardo de credenciales.

    Filtros Personalizados: Uso de JwtFilter para interceptar y validar peticiones en tiempo real.

3. Optimización: Carga Masiva de Datos

Desarrollo de un servicio de carga masiva capaz de procesar grandes volúmenes de información sin saturar la memoria RAM. Se utilizan los métodos flush() y clear() de la interfaz EntityManager para vaciar el contexto de persistencia de Hibernate tras cada lote (batch) de 50 registros.
4. Comunicación Inter-Microservicio (OpenFeign)

El sistema utiliza OpenFeign para la comunicación síncrona. El microservicio de pacientes consume datos del microservicio de médicos de forma declarativa, permitiendo un flujo de información coherente entre los componentes distribuidos.
5. Documentación Profesional (Swagger & HATEOAS)

    Swagger/OpenAPI 3: Interfaz web interactiva con documentación semántica de todos los controladores.

    HATEOAS: Implementación del Nivel 3 de Madurez de Richardson en la API de pacientes (V2), proporcionando enlaces dinámicos (_links) para la navegabilidad del cliente.

6. Resiliencia: Backup Automatizado

Configuración de un Trigger de Backup mediante @Scheduled. El sistema ejecuta automáticamente un proceso de mysqldump cada periodo programado, almacenando un respaldo físico .sql en una ruta segura (C:/temp).
Estructura del Proyecto
code Text

Hospital-VM-Final/
├── hospital-vm/               # Microservicio Principal (8080)
│   ├── src/main/java/...      # Controller, Service, Repository, Security, DTO, Assembler
│   ├── src/main/resources/    # application.properties (Perfiles: dev/test), db/migration (V1-V7)
│   └── pom.xml                # Dependencias core
└── medico-service/            # Microservicio de Apoyo (8082)
    ├── src/main/java/...      # Controller, Service, Repository
    └── pom.xml                # Dependencias específicas
 
 Configuración e Instalación

    Base de Datos: Crear los esquemas db_hospital_vm y db_medicos en MariaDB (Laragon).

    Perfiles: Asegurar que spring.profiles.active=dev esté configurado en los archivos de propiedades.

    Ejecución: Ejecutar ambos servicios simultáneamente mediante el comando:
    code Bash

    mvn spring-boot:run

    Acceso:

        Swagger UI: http://localhost:8080/swagger-ui.html

        Base de datos: Verificable en HeidiSQL.

Testing

Se incluye una suite de pruebas unitarias desarrolladas con JUnit 5 y Mockito, logrando una cobertura completa en la capa de servicios y validando el contexto de seguridad mediante @WithMockUser.
Desarrolladores

    Nicola Delgado - Ingeniero de Software / Backend Developer

    Pedro Rivas - Ingeniero de Software / Arquitecto de Datos

Este proyecto es parte de la formación académica de Duoc UC - Desarrollo Fullstack I.
