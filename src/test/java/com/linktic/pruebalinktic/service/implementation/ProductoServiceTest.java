package com.linktic.pruebalinktic.service.implementation;

import com.linktic.pruebalinktic.dto.ProductoDtoIn;
import com.linktic.pruebalinktic.dto.ProductoDtoOut;
import com.linktic.pruebalinktic.exception.ElementoNoEncontradoException;
import com.linktic.pruebalinktic.exception.ErrorGeneralException;
import com.linktic.pruebalinktic.jpa.entity.ProductoEntity;
import com.linktic.pruebalinktic.jpa.repository.InventarioRepository;
import com.linktic.pruebalinktic.jpa.repository.ProductoRepository;
import com.linktic.pruebalinktic.mapper.ProductoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {
  @InjectMocks private ProductoService productoService;
  @Mock private ProductoRepository productoRepository;
  @Mock private InventarioRepository inventarioRepository;
  @Mock private ProductoMapper productoMapper;

  @Test
  void crearProductoExitoso() throws ErrorGeneralException {
    ProductoDtoIn productoDtoIn = new ProductoDtoIn();
    productoDtoIn.setNombre("Cuaderno 100 hojas");
    productoDtoIn.setPrecio(5000L);

    ProductoEntity productoEntity = new ProductoEntity();
    productoEntity.setId(1);
    productoEntity.setNombre(productoDtoIn.getNombre());
    productoEntity.setPrecio(productoDtoIn.getPrecio());

    ProductoDtoOut productoDtoOut = new ProductoDtoOut();
    productoDtoOut.setId(productoEntity.getId());

    when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntity);
    when(productoMapper.toDto(any(ProductoEntity.class))).thenReturn(productoDtoOut);

    ProductoDtoOut resultado = productoService.crearProducto(productoDtoIn);
    assert resultado != null;
    assertNotNull(resultado.getId());
  }

  @Test
  void consultarProductoExitoso() throws ElementoNoEncontradoException {
    ProductoEntity productoEntity = new ProductoEntity();
    ProductoDtoOut productoDtoOut = new ProductoDtoOut();

    when(productoRepository.findById(anyInt())).thenReturn(Optional.of(productoEntity));
    when(productoMapper.toDto(any(ProductoEntity.class))).thenReturn(productoDtoOut);

    ProductoDtoOut resultado = productoService.consultarProducto(1);
    assertNotNull(resultado);
  }

  @Test
  void consultarListaProductosExitoso() {

    List<ProductoEntity> productos = List.of(new ProductoEntity());
    Page<ProductoEntity> pagina = new PageImpl<>(productos);

    List<ProductoDtoOut> productoDtoOutList = new ArrayList<>();
    productoDtoOutList.add(new ProductoDtoOut());

    when(productoRepository.findAll(any(Pageable.class))).thenReturn(pagina);
    when(productoMapper.toDtoList(anyList())).thenReturn(productoDtoOutList);

    List<ProductoDtoOut> resultado = productoService.consultarListaProductos(0, 10);
    assertNotNull(resultado);
  }
}
