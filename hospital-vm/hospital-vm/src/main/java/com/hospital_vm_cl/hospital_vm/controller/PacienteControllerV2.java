package com.hospital_vm_cl.hospital_vm.controller;

import com.hospital_vm_cl.hospital_vm.model.Paciente;
import com.hospital_vm_cl.hospital_vm.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;

import com.hospital_vm_cl.hospital_vm.assembler.PacienteModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/pacientes")
public class PacienteControllerV2 {

    @Autowired private PacienteService service;
    @Autowired private PacienteModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Paciente>> listarConHateoas() {
        List<EntityModel<Paciente>> pacientes = service.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pacientes,
                linkTo(methodOn(PacienteControllerV2.class).listarConHateoas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Paciente> buscarPorId(@PathVariable Long id) {
        Paciente paciente = service.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        
        return assembler.toModel(paciente);
    }

    @Operation(summary = "Reporte: Buscar por Apellido", description = "Lista pacientes que coincidan con el apellido y entrega links HATEOAS")
    @GetMapping("/reporte/apellido/{apellido}")
    public CollectionModel<EntityModel<Paciente>> buscarPorApellido(@PathVariable String apellido) {
        List<EntityModel<Paciente>> pacientes = service.findAll().stream()
                .filter(p -> p.getApellido() != null && p.getApellido().equalsIgnoreCase(apellido))
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pacientes,
                linkTo(methodOn(PacienteControllerV2.class).buscarPorApellido(apellido)).withSelfRel());
    }

    @Operation(summary = "Reporte: Total de registros", description = "Muestra el conteo total de pacientes en el sistema")
    @GetMapping("/reporte/total")
    public ResponseEntity<String> obtenerTotal() {
        long total = service.findAll().size();
        return ResponseEntity.ok("El total de pacientes en el Hospital V&M es: " + total);
    }
}