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

        ProcessBuilder pb = new ProcessBuilder(
            dumpPath, 
            "-u" + dbUser, 
            dbName, 
            "-r", savePath
        );
        
        pb.environment().put("MYSQL_PWD", dbPass);

        try {
            Process process = pb.start();
            int processComplete = process.waitFor();
            
            if (processComplete == 0) {
                System.out.println("EXITO: Backup creado en C:/temp");
            } else {
                System.err.println("ERROR: Código de salida " + processComplete);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}