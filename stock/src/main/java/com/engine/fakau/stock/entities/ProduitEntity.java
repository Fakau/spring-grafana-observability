package com.engine.fakau.stock.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class ProduitEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String description;
    private Double prix;
    private Integer quantite;
    @ManyToOne
    private CategorieEntity categorie;
}
