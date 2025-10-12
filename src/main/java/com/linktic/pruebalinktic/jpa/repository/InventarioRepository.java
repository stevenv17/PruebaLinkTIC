package com.linktic.pruebalinktic.jpa.repository;

import com.linktic.pruebalinktic.jpa.entity.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repositorio para gestionar las operaciones en base de datos de la entidad {@link InventarioEntity} */
@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer> {
}
