package com.hospital_vm_cl.hospital_vm.controller;

import com.hospital_vm_cl.hospital_vm.model.Paciente;
import com.hospital_vm_cl.hospital_vm.service.PacienteService;
import com.hospital_vm_cl.hospital_vm.service.CargaMasivaService;
import com.hospital_vm_cl.hospital_vm.client.MedicoClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(name = "Pacientes", description = "Operaciones relacionadas con la gestión de fichas clínicas del Hospital V&M")
public class PacienteController {

    @Autowired private PacienteService service;
    @Autowired private CargaMasivaService cargaMasivaService;
    @Autowired private MedicoClient medicoClient;

    @Operation(summary = "Obtener todos los pacientes", description = "Retorna una lista completa de pacientes desde MariaDB")
    @GetMapping
    public List<Paciente> listar() {
        return service.findAll();
    }

    @Operation(summary = "Obtener un paciente por ID", description = "Busca los datos de un paciente específico en la base de datos")
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return service.findById(id.intValue()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo paciente", description = "Registra un paciente en el sistema. El RUN debe ser único.")
    @PostMapping
    public Paciente guardar(@RequestBody Paciente p) {
        return service.save(p);
    }

    @Operation(summary = "Actualizar datos de paciente", description = "Modifica la información de un paciente existente según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente p) {
        return service.findById(id.intValue()).map(existente -> {
            p.setId(id);
            return ResponseEntity.ok(service.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un paciente", description = "Borra permanentemente la ficha de un paciente del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id.intValue());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Carga Masiva de registros", description = "Endpoint optimizado para subir múltiples pacientes usando Flush y Clear de Hibernate")
    @PostMapping("/masivo")
    public ResponseEntity<String> carga(@RequestBody List<Paciente> lista) {
        cargaMasivaService.procesarCarga(lista);
        return ResponseEntity.ok("Proceso de carga masiva finalizado");
    }

    @Operation(summary = "Consultar Médico (Inter-microservicio)", description = "Llama al puerto 8082 usando Feign Client para traer datos del médico asignado")
    @GetMapping("/comunicacion-medico/{id}")
    public ResponseEntity<?> probarComunicacion(@PathVariable Long id) {
        return ResponseEntity.ok(medicoClient.obtenerMedicoPorId(id));
    }
}