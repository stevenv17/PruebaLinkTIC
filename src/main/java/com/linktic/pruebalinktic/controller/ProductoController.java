package com.linktic.pruebalinktic.controller;

import com.linktic.pruebalinktic.dto.ProductoDtoIn;
import com.linktic.pruebalinktic.dto.ProductoDtoOut;
import com.linktic.pruebalinktic.exception.ElementoNoEncontradoException;
import com.linktic.pruebalinktic.exception.ErrorGeneralException;
import com.linktic.pruebalinktic.service.IProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador para gestionar producto
 */
@Slf4j
@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService iProductoService;

    /**
     * Crear un producto
     *
     * @param producto datos de entrada para crear el producto
     * @return Objeto que representa el producto creado
     * @throws ErrorGeneralException excepción si ocurre un error
     */
    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoDtoOut> crearProducto(@RequestBody ProductoDtoIn producto) throws ErrorGeneralException {
        return ResponseEntity.ok(iProductoService.crearProducto(producto));
    }

    /**
     * Consultar producto
     * @param id identificador del producto
     * @return Objeto que representa al producto
     * @throws ElementoNoEncontradoException excepción si no encuentra información
     */
    @GetMapping(value = "consultar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoDtoOut> obtenerProductoPorId(@PathVariable Integer id) throws ElementoNoEncontradoException {
        return ResponseEntity.ok(iProductoService.consultarProducto(id));
    }

  /**
   * Consultar listado de productos con paginación
   *
   * @param pagina pagina
   * @param tamanio cantidad de registros por pagina
   * @return Listado de productos
   */
  @GetMapping(value = "consultar/{pagina}/{tamanio}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ProductoDtoOut>> obtenerListaProductos(@PathVariable Integer pagina, @PathVariable Integer tamanio) {
    return ResponseEntity.ok(iProductoService.consultarListaProductos(pagina, tamanio));
  }



}
