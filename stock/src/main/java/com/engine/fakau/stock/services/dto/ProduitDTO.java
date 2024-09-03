package com.engine.fakau.stock.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitDTO {
    private Long id;
    private String libelle;
    private String description;
    private Double prix;
    private Integer quantite;
    private CategorieDTO categorie;
}
