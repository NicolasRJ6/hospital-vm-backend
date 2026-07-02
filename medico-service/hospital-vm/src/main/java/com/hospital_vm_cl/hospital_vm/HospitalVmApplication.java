package com.hospital_vm_cl.hospital_vm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class HospitalVmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalVmApplication.class, args);
    }

}