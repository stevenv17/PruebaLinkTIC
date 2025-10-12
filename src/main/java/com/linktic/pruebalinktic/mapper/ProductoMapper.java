package com.linktic.pruebalinktic.mapper;

import com.linktic.pruebalinktic.dto.ProductoDtoOut;
import com.linktic.pruebalinktic.jpa.entity.ProductoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
  ProductoDtoOut toDto(ProductoEntity productoEntity);
  List<ProductoDtoOut> toDtoList(List<ProductoEntity> productoEntityList);
}

