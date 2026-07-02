package com.hospital_vm_cl.hospital_vm.controller;

import com.hospital_vm_cl.hospital_vm.model.Medico;
import com.hospital_vm_cl.hospital_vm.repository.MedicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
@Tag(name = "Médicos", description = "Microservicio independiente de personal médico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Operation(summary = "Obtener médico por ID", description = "Consulta la base de datos db_medicos")
    @GetMapping("/{id}")
    public Medico obtenerMedico(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado en db_medicos"));
    }
}