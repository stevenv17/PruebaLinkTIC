package com.linktic.pruebalinktic.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

/** Entidad que representa a la tabla de Producto */
@Data
@Entity
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Long precio;

    @Column(name = "descripcion")
    private String descripcion;

}
