package com.linktic.pruebalinktic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linktic.pruebalinktic.dto.ProductoDtoIn;
import com.linktic.pruebalinktic.dto.ProductoDtoOut;
import com.linktic.pruebalinktic.service.implementation.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // cargar contexto de app
@AutoConfigureMockMvc
@TestPropertySource(properties = "apikey.valor=test-key")
class ProductoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ProductoService productoService;

  @Value("${apikey.valor}")
  private String apiKey;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void crearProductoExitoso() throws Exception {
    ProductoDtoOut productoDtoOut = new ProductoDtoOut();
    productoDtoOut.setId(1);
    productoDtoOut.setNombre("Cuaderno 100 hojas");

    ProductoDtoIn productoDtoIn = new ProductoDtoIn();
    productoDtoIn.setPrecio(5000L);
    productoDtoIn.setNombre("Cuaderno 100 hojas");
    String json = objectMapper.writeValueAsString(productoDtoIn);

    when(productoService.crearProducto(any(ProductoDtoIn.class))).thenReturn(productoDtoOut);

    mockMvc.perform(post("/producto/crear")
            .header("x-api-key", apiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1));
  }

  @Test
  void consultarProductoExitoso() throws Exception {
    ProductoDtoOut productoDtoOut = new ProductoDtoOut();
    productoDtoOut.setId(1);
    productoDtoOut.setNombre("Cuaderno 100 hojas");

    when(productoService.consultarProducto(anyInt())).thenReturn(productoDtoOut);

    mockMvc.perform(get("/producto/consultar/1")
            .header("x-api-key", apiKey)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1));
  }

  @Test
  void obtenerListaProductosExitoso() throws Exception {
    ProductoDtoOut productoDtoOut = new ProductoDtoOut();
    productoDtoOut.setId(1);
    productoDtoOut.setNombre("Cuaderno 100 hojas");

    List<ProductoDtoOut> productoDtoOutList = new ArrayList<>();
    productoDtoOutList.add(productoDtoOut);

    when(productoService.consultarListaProductos(anyInt(), anyInt())).thenReturn(productoDtoOutList);

    mockMvc.perform(get("/producto/consultar/0/10")
            .header("x-api-key", apiKey)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1));
  }
}
