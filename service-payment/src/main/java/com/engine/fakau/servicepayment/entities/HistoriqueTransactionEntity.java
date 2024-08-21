package com.engine.fakau.servicepayment.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class HistoriqueTransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroTransaction;
    private LocalDateTime dateTransaction;
    private BigDecimal montant;
    @ManyToOne
    private CarteCreditEntity carteCredit;
    @ManyToOne
    private CarteCreditEntity compteMarchand;

}
