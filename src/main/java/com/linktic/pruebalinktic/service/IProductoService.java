package com.linktic.pruebalinktic.service;

import com.linktic.pruebalinktic.dto.ProductoDtoIn;
import com.linktic.pruebalinktic.dto.ProductoDtoOut;
import com.linktic.pruebalinktic.exception.ElementoNoEncontradoException;
import com.linktic.pruebalinktic.exception.ErrorGeneralException;

import java.util.List;

/** Interfaz con métodos para gestionar productos */
public interface IProductoService {

    /**
     * Crea producto nuevo
     * @param productoDtoIn Información del nuevo producto
     * @return resultado de la creación del producto
     * @throws ErrorGeneralException excepción si ocurre un error
     */
    ProductoDtoOut crearProducto(ProductoDtoIn productoDtoIn) throws ErrorGeneralException;

    /**
     * Consulta producto filtrando por su identificador
     *
     * @param id identificador del produto
     * @return Devuelve objeto del objeto consultado
     * @throws ElementoNoEncontradoException excepción si no encuentra información
     */
    ProductoDtoOut consultarProducto(Integer id) throws ElementoNoEncontradoException;

  /**
   * Consulta lista de productos con paginación
   * @return lista de productos
   */
  List<ProductoDtoOut> consultarListaProductos(int pagina, int tamanioPagina);
}
