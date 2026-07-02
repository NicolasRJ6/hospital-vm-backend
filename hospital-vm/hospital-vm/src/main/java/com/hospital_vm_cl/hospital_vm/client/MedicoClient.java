package com.hospital_vm_cl.hospital_vm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "medico-service", url = "http://localhost:8082")
public interface MedicoClient {

    @GetMapping("/api/medicos/{id}")
    Object obtenerMedicoPorId(@PathVariable("id") Long id);
}