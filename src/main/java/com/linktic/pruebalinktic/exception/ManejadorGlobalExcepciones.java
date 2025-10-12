package com.linktic.pruebalinktic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarErrorGeneral(Exception ex) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("resultado", "ERROR");
        cuerpo.put("mensaje", "Ha ocurrido un error inesperado");
        cuerpo.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(cuerpo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorGeneralException.class)
    public ResponseEntity<Object> manejarErrorGeneral(ErrorGeneralException ex) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("resultado", "ERROR");
        cuerpo.put("mensaje", ex.getMessage());
        cuerpo.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(cuerpo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ElementoNoEncontradoException.class)
    public ResponseEntity<Object> manejarElementoNoEncontrado(ElementoNoEncontradoException ex) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("resultado", "ERROR");
        cuerpo.put("mensaje", ex.getMessage());
        cuerpo.put("codigo", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(cuerpo, HttpStatus.NOT_FOUND);
    }

}

