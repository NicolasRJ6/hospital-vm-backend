package com.hospital_vm_cl.hospital_vm;

import com.hospital_vm_cl.hospital_vm.model.Medico;
import com.hospital_vm_cl.hospital_vm.repository.MedicoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MedicoRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es", "CL"));
        Random random = new Random();
        
        String[] especialidades = {"Cardiología", "Pediatría", "Neurología", "Traumatología", "Dermatología"};

        if (repository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                Medico m = new Medico();
                m.setNombre("Dr. " + faker.name().fullName());
                
                m.setEspecialidad(especialidades[random.nextInt(especialidades.length)]);
                
                m.setTelefono(faker.phoneNumber().cellPhone());
                repository.save(m);
            }
            System.out.println("DATAFAKER: 20 médicos creados en el puerto 8082.");
        }
    }
}