package com.engine.fakau.stock.services.mappers;

import com.engine.fakau.stock.entities.ProduitEntity;
import com.engine.fakau.stock.services.dto.ProduitDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategorieMapper.class})
public interface ProduitMapper {
    ProduitDTO toDto(ProduitEntity produit);

    ProduitEntity toEntity(ProduitDTO produitDTO);
}
