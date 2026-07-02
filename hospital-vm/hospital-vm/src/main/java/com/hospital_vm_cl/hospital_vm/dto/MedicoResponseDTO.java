package com.hospital_vm_cl.hospital_vm.dto;
import lombok.Data;

@Data
public class MedicoResponseDTO {
    private Long id;
    private String nombre;
    private String especialidad;
    private String telefono;
}