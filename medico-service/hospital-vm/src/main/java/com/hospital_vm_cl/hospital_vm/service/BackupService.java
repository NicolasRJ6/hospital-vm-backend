package com.hospital_vm_cl.hospital_vm.service;

import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class BackupService {

    public void createBackup() {
        
        String dumpPath = "C:/laragon/bin/mysql/mysql-8.4.3-winx64/bin/mysqldump.exe";
        String dbName = "db_hospital_vm";
        String dbUser = "root";
        String dbPass = ""; 
        String savePath = "C:/temp/backup_hospital.sql";

        new File("C:/temp").mkdirs();

        String command = String.format("%s -u %s %s --databases %s -r %s", 
                         dumpPath, dbUser, dbPass.isEmpty() ? "" : "-p" + dbPass, dbName, savePath);

        try {
            Process process = Runtime.getRuntime().exec(command);
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("EXITO: Backup creado en C:/temp");
            } else {
                System.err.println("ERROR: No se pudo crear el backup");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}