package com.engine.fakau.stock.services.mappers;

import com.engine.fakau.stock.entities.CategorieEntity;
import com.engine.fakau.stock.services.dto.CategorieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieDTO toDto(CategorieEntity categorie);

    CategorieEntity toEntity(CategorieDTO categorieDTO);
}
