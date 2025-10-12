package com.linktic.pruebalinktic.service.implementation;

import com.linktic.pruebalinktic.dto.ProductoDtoIn;
import com.linktic.pruebalinktic.dto.ProductoDtoOut;
import com.linktic.pruebalinktic.exception.ElementoNoEncontradoException;
import com.linktic.pruebalinktic.exception.ErrorGeneralException;
import com.linktic.pruebalinktic.jpa.entity.ProductoEntity;
import com.linktic.pruebalinktic.jpa.repository.ProductoRepository;
import com.linktic.pruebalinktic.mapper.ProductoMapper;
import com.linktic.pruebalinktic.service.IProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/** clase que implementa funcionalidades para gestionar productos */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

  private final ProductoRepository productoRepository;
  private final ProductoMapper productoMapper;


  /**
   * Crea producto nuevo
   *
   * @param productoDtoIn Información del nuevo producto
   * @return resultado de la creación del producto
   * @throws ErrorGeneralException excepción si ocurre un error
   */
  @Override
  public ProductoDtoOut crearProducto(ProductoDtoIn productoDtoIn) throws ErrorGeneralException {
    log.info("Creación de producto");
    validarDatosEntrada(productoDtoIn);
    ProductoEntity productoEntity = new ProductoEntity();
    productoEntity.setNombre(productoDtoIn.getNombre());
    productoEntity.setPrecio(productoDtoIn.getPrecio());
    productoEntity.setDescripcion(productoDtoIn.getDescripcion());

    return productoMapper.toDto(productoRepository.save(productoEntity));
  }

  /**
   * Consulta producto filtrando por su identificador
   *
   * @param id identificador del produto
   * @return Devuelve objeto del objeto consultado
   * @throws ElementoNoEncontradoException excepción si no encuentra información
   */
  @Override
  public ProductoDtoOut consultarProducto(Integer id) throws ElementoNoEncontradoException {
    Optional<ProductoEntity> productoEntityOptional = productoRepository.findById(id);
    if(productoEntityOptional.isEmpty()) {
      throw new ElementoNoEncontradoException(MessageFormat.format("No se encontró un producto con ID: {0}",id));
    }
    return productoMapper.toDto(productoEntityOptional.get());
  }

  /**
   * Consulta lista de productos con paginación
   * @param pagina número de pagina a consulta
   * @param tamanioPagina cantidad de registros a mostrar por pagina
   *
   * @return lista de productos
   */
  @Override
  public List<ProductoDtoOut> consultarListaProductos(int pagina, int tamanioPagina) {
    Pageable pageable = PageRequest.of(pagina, tamanioPagina, Sort.by("id").descending());
    Page<ProductoEntity> productos = productoRepository.findAll(pageable);

    return productoMapper.toDtoList(productos.stream().toList());
  }


  /**
   * Valida campos de entrada
   * @param productoDtoIn objeto con datos de entrada
   * @throws ErrorGeneralException excepción si ocurre un error
   */
  private void validarDatosEntrada(ProductoDtoIn productoDtoIn) throws ErrorGeneralException {
    log.info("Inicio de validaciones de producto");
    if(productoDtoIn.getPrecio() == null || productoDtoIn.getPrecio() <= 0) {
      throw new ErrorGeneralException("El precio debe ser mayor a 0");
    }
    if(productoDtoIn.getNombre() == null || productoDtoIn.getNombre().isBlank()) {
      throw new ErrorGeneralException("El producto debe tener un nombre");
    }
    log.info("Fin de validaciones de producto");
  }


}
