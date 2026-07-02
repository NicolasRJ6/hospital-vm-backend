CREATE TABLE turnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    paciente VARCHAR(100) NOT NULL,
    medico_id BIGINT,
    CONSTRAINT fk_medico FOREIGN KEY (medico_id) REFERENCES medicos(id)
);