package com.hospital_vm_cl.hospital_vm.assembler;

import com.hospital_vm_cl.hospital_vm.controller.PacienteControllerV2;
import com.hospital_vm_cl.hospital_vm.model.Paciente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PacienteModelAssembler implements RepresentationModelAssembler<Paciente, EntityModel<Paciente>> {

    @Override
    @NonNull
    public EntityModel<Paciente> toModel(@NonNull Paciente paciente) {
        return EntityModel.of(paciente,
                linkTo(methodOn(PacienteControllerV2.class).buscarPorId(paciente.getId())).withSelfRel(),
                linkTo(methodOn(PacienteControllerV2.class).listarConHateoas()).withRel("pacientes"));
    }
}