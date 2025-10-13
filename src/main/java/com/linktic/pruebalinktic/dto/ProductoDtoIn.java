package com.linktic.pruebalinktic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO con información de entrada de producto para crearlo */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDtoIn {
    private String nombre;
    private Long precio;
    private String descripcion;
}
