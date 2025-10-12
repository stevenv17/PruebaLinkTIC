package com.linktic.pruebalinktic.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador para gestionar inventario
 */
@Slf4j
@RestController
@RequestMapping("/inventario")
public class InventarioController {


    @GetMapping("/inicio")
    @Operation(summary = "Prueba inicio", description = "Retorna mensaje de prueba")
    public String inicio() {
        log.info("Inicio...");
        return "Prueba de funcionamiento";
    }

}
