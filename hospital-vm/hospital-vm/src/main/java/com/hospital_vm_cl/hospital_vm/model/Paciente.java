package com.hospital_vm_cl.hospital_vm.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "pacientes")
@Data
@Schema(description = "Información detallada del paciente para la ficha clínica")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único autoincremental", example = "1")
    private Long id;

    @Schema(description = "RUN del paciente (con guion y dígito verificador)", example = "12345678-9")
    @Column(unique = true, nullable = false)
    private String run;

    @Schema(description = "Nombres del paciente", example = "Juan Alberto")
    private String nombre;

    @Schema(description = "Apellidos del paciente", example = "Pérez González")
    private String apellido;

    @Schema(description = "Fecha de nacimiento del paciente", example = "1995-03-25")
    private Date fechaNacimiento;

    @Schema(description = "Correo electrónico institucional o personal", example = "juan.perez@hospital.cl")
    private String correo;
}