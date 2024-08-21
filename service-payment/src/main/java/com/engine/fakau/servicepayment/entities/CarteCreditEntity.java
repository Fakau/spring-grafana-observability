package com.engine.fakau.servicepayment.entities;

import com.engine.fakau.servicepayment.entities.enumeration.Devise;
import com.engine.fakau.servicepayment.entities.enumeration.TypeCompte;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class CarteCreditEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCarte;
    private LocalDate dateExpiration;
    private String codeSecurite;
    private String nomProprietaire;
    private String prenomProprietaire;
    @Enumerated(EnumType.STRING)
    private Devise devise;
    private BigDecimal limitCredit;
    private BigDecimal creditDisponible;
    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;
    @OneToMany(mappedBy = "carteCredit", fetch = FetchType.LAZY)
    private List<HistoriqueTransactionEntity> historiqueTransactions;
}
