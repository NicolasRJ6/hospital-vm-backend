package com.hospital_vm_cl.hospital_vm.service;

import com.hospital_vm_cl.hospital_vm.model.Paciente;
import com.hospital_vm_cl.hospital_vm.repository.PacienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<Paciente> findAll() {
        log.info("SISTEMA: Consultando lista completa de pacientes");
        return repository.findAll();
    }

    public Optional<Paciente> findById(Integer id) {
        log.info("SISTEMA: Buscando paciente con ID: {}", id);
        return repository.findById(id);
    }

    public Paciente save(Paciente paciente) {
        log.info("SISTEMA: Registrando/Actualizando paciente con RUN: {}", paciente.getRun());
        return repository.save(paciente);
    }

    public void delete(Integer id) {
        log.warn("SISTEMA: Eliminando paciente con ID: {}", id);
        repository.deleteById(id);
    }
}