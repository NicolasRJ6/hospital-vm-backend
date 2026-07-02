package com.hospital_vm_cl.hospital_vm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.hospital_vm_cl.hospital_vm.repository.UsuarioRepository;
import com.hospital_vm_cl.hospital_vm.model.Usuario;
import com.hospital_vm_cl.hospital_vm.model.Role;
@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class HospitalVmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalVmApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.deleteAll(); 
            
            Usuario user = new Usuario();
            user.setUsername("admin");
            user.setPassword(encoder.encode("1234"));
            user.setRole(Role.ROLE_ADMIN);
            repo.save(user);
            
            System.out.println("USUARIO ADMIN CREADO CON ÉXITO: admin / 1234");
        };
    }
}