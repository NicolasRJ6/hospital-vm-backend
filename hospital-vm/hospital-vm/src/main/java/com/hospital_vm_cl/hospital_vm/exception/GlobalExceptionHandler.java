package com.hospital_vm_cl.hospital_vm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException; // IMPORTANTE
import java.util.Map;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public void handleNoResourceFoundException(NoResourceFoundException ex) throws NoResourceFoundException {
        throw ex; 
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(Map.of(
            "timestamp", LocalDateTime.now(),
            "mensaje", "Error interno en el Hospital V&M",
            "detalle", ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}