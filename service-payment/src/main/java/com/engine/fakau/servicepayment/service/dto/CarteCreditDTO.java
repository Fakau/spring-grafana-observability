package com.engine.fakau.servicepayment.service.dto;

import com.engine.fakau.servicepayment.entities.enumeration.Devise;
import com.engine.fakau.servicepayment.entities.enumeration.TypeCompte;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CarteCreditDTO implements Serializable {
    private Long id;
    private String numeroCarte;
    private LocalDate dateExpiration;
    private String codeSecurite;
    private String nomProprietaire;
    private String prenomProprietaire;
    private Devise devise;
    private BigDecimal limitCredit;
    private BigDecimal creditDisponible;
    private TypeCompte typeCompte;

}
