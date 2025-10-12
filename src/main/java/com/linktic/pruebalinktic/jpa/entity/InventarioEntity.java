package com.linktic.pruebalinktic.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/** Entidad que representa a la tabla de Inventario */
@Data
@Entity
@Table(name = "inventario")
public class InventarioEntity {

    @Id
    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "cantidad")
    private Integer cantidad;
}
