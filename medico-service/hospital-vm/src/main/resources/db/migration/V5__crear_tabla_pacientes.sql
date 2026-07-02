CREATE TABLE pacientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    run VARCHAR(13) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    correo VARCHAR(100) NOT NULL
);