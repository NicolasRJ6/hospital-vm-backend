package com.hospital_vm_cl.hospital_vm.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.hospital_vm_cl.hospital_vm.model.Paciente;
import com.hospital_vm_cl.hospital_vm.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class PacienteServiceTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteService service;

    @Test
    public void testListarPacientes() {
        Paciente p = new Paciente();
        p.setNombre("Nicolas");
        p.setApellido("Delgado");
        
        when(repository.findAll()).thenReturn(List.of(p));

        List<Paciente> resultado = service.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Nicolas", resultado.get(0).getNombre());
        
        System.out.println("TEST EXITOSO: El servicio devolvió al paciente correctamente.");
    }
}