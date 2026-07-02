package com.hospital_vm_cl.hospital_vm;

import com.hospital_vm_cl.hospital_vm.model.Paciente;
import com.hospital_vm_cl.hospital_vm.repository.PacienteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(java.util.Locale.forLanguageTag("es-CL"));

        if (repository.count() == 0) {
            for (int i = 0; i < 50; i++) {
                Paciente p = new Paciente();
                p.setRun(faker.idNumber().valid());
                p.setNombre(faker.name().firstName());
                p.setApellido(faker.name().lastName());
                p.setCorreo(faker.internet().emailAddress());
                p.setFechaNacimiento(faker.date().birthday(1, 90));
                repository.save(p);
            }
            System.out.println("DATAFAKER: 50 pacientes creados exitosamente.");
        }
    }
}