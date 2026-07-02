Hospital Vida y Meditación (V&M)

    Solución Backend de Microservicios para Gestión Clínica Profesional

![alt text](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)


![alt text](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)


![alt text](https://img.shields.io/badge/MariaDB-8.4-003545?style=for-the-badge&logo=mariadb&logoColor=white)


![alt text](https://img.shields.io/badge/JWT-Stateless-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)

📌 Contexto del Proyecto

Este proyecto representa la modernización digital del Hospital Vida y Meditación (V&M) en Puerto Montt. Implementamos una arquitectura distribuida para gestionar pacientes y personal médico, asegurando la privacidad de los datos y la alta disponibilidad de los servicios mediante el uso de microservicios independientes.
🏗️ Arquitectura y Microservicios

El sistema se divide en dos componentes desacoplados que se comunican mediante OpenFeign:

    hospital-vm (Puerto 8080): Servicio central. Gestiona el registro de pacientes, la seguridad JWT y la orquestación de datos.

    medico-service (Puerto 8082): Microservicio de apoyo. Mantiene el catálogo de médicos y especialidades clínicas.

🛠️ Stack Tecnológico e Implementaciones
🔐 1. Seguridad y Autenticación

    JWT (JSON Web Token): Autenticación basada en tokens para una arquitectura Stateless.

    BCrypt: Enmascaramiento de contraseñas de administrativos y personal técnico.

    Filtros de Seguridad: Implementación de JwtFilter para validación de cabeceras en cada petición.

🔄 2. Persistencia y Migraciones (Flyway)

    Database as Code: Uso de Flyway para la evolución controlada del esquema SQL.

    Versiones: Historial documentado desde V1 hasta V7, incluyendo creación de tablas y limpieza de datos obsoletos.

    Hibernate: Mapeo de entidades bajo el patrón JPA.

⚡ 3. Optimización de Memoria (Carga Masiva)

    Uso de EntityManager con métodos flush() y clear().

    Procesamiento por lotes (Batch size: 50) para evitar el desbordamiento de la memoria RAM al cargar miles de fichas clínicas.

📡 4. Comunicación Inter-servicio

    OpenFeign: Cliente HTTP declarativo que permite al servicio de pacientes consultar al servicio de médicos en tiempo real de forma transparente.

📖 5. Documentación y Navegabilidad

    Swagger / OpenAPI 3: Interfaz interactiva para pruebas de API con soporte para inyección de Token JWT.

    HATEOAS: Implementación del Nivel 3 de Madurez de Richardson, añadiendo enlaces dinámicos (_links) para mejorar la navegabilidad del cliente.

💾 6. Resiliencia y Backup

    Trigger de Respaldo: Tarea programada vía @Scheduled que ejecuta un mysqldump automático cada 2 minutos hacia una ruta segura en C:/temp.

🚀 Instalación y Uso

    Requisitos: Tener instalado Laragon (MariaDB 8.4) y Java 21.

    Base de Datos: Crear los esquemas vacíos db_hospital_vm y db_medicos.

    Configuración: Verificar la ruta de mysqldump en BackupService.java.

    Lanzamiento:
    code Bash

    # En la carpeta hospital-vm
    mvn spring-boot:run

    # En la carpeta medico-service
    mvn spring-boot:run

    Acceso Web: http://localhost:8080/swagger-ui.html

🧪 Pruebas Unitarias

El proyecto cuenta con una robusta suite de pruebas unitarias usando JUnit 5 y Mockito, garantizando que la lógica de negocio esté validada al 100% de forma aislada.
👥 Integrantes

    Nicola Delgado - Líder de Desarrollo Backend

    Pedro Rivas - Arquitecto de Sistemas Distribuidos

Desarrollado para la asignatura de Desarrollo Fullstack I - Duoc UC
