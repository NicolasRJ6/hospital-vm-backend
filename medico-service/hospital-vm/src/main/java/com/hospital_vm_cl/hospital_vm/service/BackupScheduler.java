package com.hospital_vm_cl.hospital_vm.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class BackupScheduler {

    @Autowired
    private BackupService backupService;

    @Scheduled(cron = "0 */2 * * * *")
    public void ejecutarRespaldo() {
        backupService.createBackup();
    }
}